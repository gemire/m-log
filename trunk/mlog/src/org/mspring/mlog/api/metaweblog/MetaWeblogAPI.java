/**
 * 
 */
package org.mspring.mlog.api.metaweblog;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.entity.Article;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.CategoryService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.common.MD5;
import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.XMLUtils;
import org.mspring.platform.web.servlet.renderer.XMLRenderer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author Gao Youbo
 * @since Apr 16, 2012
 */
public final class MetaWeblogAPI {
    /**
     * Logger.
     */
    private static final Logger log = Logger.getLogger(MetaWeblogAPI.class);

    private UserService userService = ServiceFactory.getUserService();
    private CategoryService categoryService = ServiceFactory.getCategoryService();
    private ArticleService articleService = ServiceFactory.getArticleService();
    private OptionService optionService = ServiceFactory.getOptionService();

    /**
     * Method name: "blogger.getUsersBlogs".
     */
    private static final String METHOD_GET_USERS_BLOGS = "blogger.getUsersBlogs";
    /**
     * Method name: "metaWeblog.getCategories".
     */
    private static final String METHOD_GET_CATEGORIES = "metaWeblog.getCategories";
    /**
     * Method name: "metaWeblog.getRecentPosts".
     */
    private static final String METHOD_GET_RECENT_POSTS = "metaWeblog.getRecentPosts";
    /**
     * Method name: "metaWeblog.newPost".
     */
    private static final String METHOD_NEW_POST = "metaWeblog.newPost";
    /**
     * Method name: "metaWeblog.editPost".
     */
    private static final String METHOD_EDIT_POST = "metaWeblog.editPost";
    /**
     * Method name: "metaWeblog.getPost".
     */
    private static final String METHOD_GET_POST = "metaWeblog.getPost";
    /**
     * Method name: "blogger.deletePost".
     */
    private static final String METHOD_DELETE_POST = "blogger.deletePost";

    /**
     * Argument "username" index.
     */
    private static final int INDEX_USER_NAME = 2;
    /**
     * Argument "postid" index.
     */
    private static final int INDEX_POST_ID = 1;
    /**
     * Argument "password" index.
     */
    private static final int INDEX_USER_PWD = 3;
    /**
     * Argument "numberOfPosts" index.
     */
    private static final int INDEX_NUM_OF_POSTS = 4;
    /**
     * Argument "publish" index.
     */
    private static final int INDEX_PUBLISH = 5;
    /**
     * Article abstract length.
     */
    private static final int ARTICLE_ABSTRACT_LENGTH = 500;

    /**
     * MetaWeblog requests processing.
     * 
     * @param request
     *            the specified http servlet request
     * @param response
     *            the specified http servlet response
     * @param context
     *            the specified http request context
     */
    public void metaWeblog(final HttpServletRequest request, final HttpServletResponse response) {
        XMLRenderer renderer = new XMLRenderer();

        String responseContent = null;
        try {
            final ServletInputStream inputStream = request.getInputStream();
            final String xml = IOUtils.toString(inputStream, "UTF-8");

            if (StringUtils.isBlank(xml)) {
                log.warn("request xml is null");
                return;
            }

            Reader reader = new StringReader(xml);
            Document doc = XMLUtils.parse(reader);

            final String methodName = XMLUtils.parseForNode(doc, "/methodCall/methodName").getTextContent();
            log.debug("MetaWeblog [methodName = " + methodName + "]");

            if (METHOD_DELETE_POST.equals(methodName)) {
                // params.remove(0); // Removes the first argument "appkey"
            }

            final String username = XMLUtils.parseForNode(doc, "/methodCall/params/param[" + INDEX_USER_NAME + "]/value/string").getTextContent();
            final User user = userService.findUserByName(username);
            if (null == user) {
                throw new Exception("No user[username=" + username + "]");
            }

            final String password = XMLUtils.parseForNode(doc, "/methodCall/params/param[" + INDEX_USER_PWD + "]/value/string").getTextContent();
            if (!user.getPassword().equals(MD5.getMD5(password))) {
                throw new Exception("Wrong password");
            }

            // 获取博客
            if (METHOD_GET_USERS_BLOGS.equals(methodName)) {
                responseContent = getUsersBlogs();
            }
            // 获取分类
            else if (METHOD_GET_CATEGORIES.equals(methodName)) {
                responseContent = getCategories();
            }
            // 获取最近发布文章
            else if (METHOD_GET_RECENT_POSTS.equals(methodName)) {
                String str_numOfPosts = XMLUtils.parseForNode(doc, "/methodCall/params/param[" + INDEX_NUM_OF_POSTS + "]/value/int").getTextContent();
                final int numOfPosts = new Integer(str_numOfPosts);
                responseContent = getRecentPosts(numOfPosts);
            }
            // 新建文章
            else if (METHOD_NEW_POST.equals(methodName)) {
                responseContent = newPost(doc);
            }
            // 获取文章
            else if (METHOD_GET_POST.equals(methodName)) {
                final String postId = XMLUtils.parseForString(doc, "/methodCall/params/param[" + INDEX_POST_ID + "]/value/string");
                responseContent = getPost(postId);
            }
            // 编辑文章
            else if (METHOD_EDIT_POST.equals(methodName)) {
                responseContent = editPost(doc);
            }
            // 删除文章
            else if (METHOD_DELETE_POST.equals(methodName)) {
                final String postId = XMLUtils.parseForString(doc, "/methodCall/params/param[" + INDEX_POST_ID + "]/value/string");
                articleService.deleteArticle(new Long(postId));

                final StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><boolean>").append(true).append("</boolean></value></param></params></methodResponse>");
                responseContent = stringBuilder.toString();
            }
            // 方法未实现
            else {
                throw new UnsupportedOperationException("Unsupported method[name=" + methodName + "]");
            }
            if (!StringUtils.isBlank(responseContent)) {
                renderer.setContent(responseContent);
                renderer.render(response);
            }
        } catch (final Exception e) {
            log.debug(e.getMessage(), e);
            responseContent = "";
            final StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><fault><value><struct>").append("<member><name>faultCode</name><value><int>500</int></value></member>").append("<member><name>faultString</name><value><string>").append(e.getMessage()).append("</string></value></member></struct></value></fault></methodResponse>");
            responseContent = stringBuilder.toString();
            renderer.setContent(responseContent);
        }
    }

    private String getUsersBlogs() {
        final String blogId = "";
        final String blogName = optionService.getOption(ConfigTokens.mspring_config_base_blogintro);
        final String blogURL = "";

        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<methodResponse>\n");
        result.append("     <params>\n");
        result.append("         <param>\n");
        result.append("             <value>\n");
        result.append("                 <array>\n");
        result.append("                     <data>\n");
        result.append("                         <value>\n");
        result.append("                             <struct>\n");

        result.append("                                 <member>\n");
        result.append("                                     <name>blogid</name>\n");
        result.append("                                     <value>").append(blogId).append("</value>\n");
        result.append("                                 </member>\n");

        result.append("                                 <member>\n");
        result.append("                                     <name>url</name>\n");
        result.append("                                     <value>").append(blogURL).append("</value>\n");
        result.append("                                 </member>\n");

        result.append("                                 <member>\n");
        result.append("                                     <name>blogName</name>\n");
        result.append("                                     <value>").append(blogName).append("</value>\n");
        result.append("                                 </member>\n");

        result.append("                             </struct>\n");
        result.append("                         </value>\n");
        result.append("                     </data>\n");
        result.append("                 </array>\n");
        result.append("             </value>\n");
        result.append("         </param>\n");
        result.append("     </params>\n");
        result.append("</methodResponse>\n");
        return result.toString();
    }

    private String getCategories() {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        result.append("<methodResponse>\n");
        result.append("     <params>\n");
        result.append("         <param>\n");
        result.append("             <value>\n");
        result.append("                 <array>\n");
        result.append("                     <data>\n");

        final List<Category> categories = categoryService.findAll();
        for (Category category : categories) {
            final String categoryid = category.getId().toString();
            final String categoryname = category.getName();
            final String description = category.getIntro();

            result.append("                     <value>\n");
            result.append("                         <struct>\n");

            result.append("                             <member>\n");
            result.append("                                 <name>description</name>\n");
            result.append("                                 <value>").append(description).append("</value>\n");
            result.append("                             </member>\n");

            result.append("                             <member>\n");
            result.append("                                 <name>title</name>\n");
            result.append("                                 <value>").append(categoryname).append("</value>\n");
            result.append("                             </member>\n");

            result.append("                             <member>\n");
            result.append("                                 <name>categoryid</name>\n");
            result.append("                                 <value>").append(categoryid).append("</value>\n");
            result.append("                             </member>\n");

            result.append("                             <member>\n");
            result.append("                                 <name>htmlUrl</name>\n");
            result.append("                                 <value>").append("   ").append("</value>\n");
            result.append("                             </member>\n");

            result.append("                             <member>\n");
            result.append("                                 <name>rsslUrl</name>\n");
            result.append("                                 <value>").append("   ").append("</value>\n");
            result.append("                             </member>\n");

            result.append("                         </struct>\n");
            result.append("                     </value>\n");
        }

        result.append("                     </data>\n");
        result.append("                 </array>\n");
        result.append("             </value>\n");
        result.append("         </param>\n");
        result.append("     </params>\n");
        result.append("</methodResponse>\n");

        return result.toString();
    }

    private String getRecentPosts(int numOfPosts) {
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        result.append("<methodResponse>\n");
        result.append("     <params>\n");
        result.append("         <param>\n");
        result.append("             <value>\n");
        result.append("                 <array>\n");
        result.append("                     <data>\n");

        List<Article> recentPosts = articleService.getRecentArticles(numOfPosts);
        for (Article article : recentPosts) {
            StringBuilder element = new StringBuilder();
            try {
                Long id = article.getId();
                String createTime = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(article.getCreateTime());
                String title = StringEscapeUtils.escapeXml(article.getTitle());
                String content = StringEscapeUtils.escapeXml(article.getContent());
                element.append("                           <value>\n");
                element.append("                               <struct>\n");

                element.append("                                   <member>\n");
                element.append("                                       <name>dateCreated</name>\n");
                element.append("                                       <value><dateTime.iso8601>").append(createTime).append("</dateTime.iso8601></value>\n");
                element.append("                                   </member>\n");

                element.append("                                   <member>\n");
                element.append("                                       <name>description</name>\n");
                element.append("                                       <value>").append(content).append("</value>\n");
                element.append("                                   </member>\n");

                element.append("                                   <member>\n");
                element.append("                                       <name>title</name>\n");
                element.append("                                       <value>").append(title).append("</value>\n");
                element.append("                                   </member>\n");

                element.append("                                   <member>\n");
                element.append("                                       <name>postid</name>\n");
                element.append("                                       <value>").append(id).append("</value>\n");
                element.append("                                   </member>\n");

                element.append("                                   <member>\n");
                element.append("                                       <name>categories</name>\n");
                element.append("                                       <value>\n");
                element.append("                                           <array>\n");
                element.append("                                               <data>\n");

                Set<Category> categories = article.getCategories();
                for (Category category : categories) {
                    element.append("                                                   <value>").append(category.getName()).append("</value>\n");
                }

                element.append("                                               </data>\n");
                element.append("                                           </array>\n");
                element.append("                                       </value>\n");
                element.append("                                   </member>\n");

                element.append("                               </struct>\n");
                element.append("                           </value>\n");
            } catch (Exception e) {
                // TODO: handle exception
                continue;
            }
            result.append(element);
        }

        result.append("                     </data>\n");
        result.append("                 </array>\n");
        result.append("             </value>\n");
        result.append("         </param>\n");
        result.append("     </params>\n");
        result.append("</methodResponse>");

        return result.toString();
    }

    /**
     * 发表文章
     * 
     * @param doc
     * @return
     * @throws NullPointerException
     * @throws XPathExpressionException
     * @throws RuntimeException
     */
    private String newPost(Document doc) throws NullPointerException, XPathExpressionException, RuntimeException {
        Article article = parsePost(doc);
        article.setIp("from.xmlrpc");
        article = articleService.createArticle(article);
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        result.append("<methodResponse>\n");
        result.append("     <params>\n");
        result.append("         <param>\n");
        result.append("             <value>\n");
        result.append("                 <string>").append(article.getId()).append("</string>\n");
        result.append("             </value>\n");
        result.append("         </param>\n");
        result.append("     </params>\n");
        result.append("</methodResponse>\n");
        return result.toString();
    }

    /**
     * 将xml描述转换为文章
     * 
     * @param doc
     * @return
     * @throws NullPointerException
     * @throws XPathExpressionException
     * @throws RuntimeException
     */
    private Article parsePost(Document doc) throws NullPointerException, XPathExpressionException, RuntimeException {
        final List<Node> members = XMLUtils.parseForNodeList(doc, "/methodCall/params/param/value/struct/member");
        Article article = new Article();
        for (Node member : members) {
            String name = XMLUtils.parseForString(member, "name");
            if ("dateCreated".equals(name)) {
                String value = XMLUtils.parseForString(member, "value/dateTime.iso8601");
                Date date = null;
                try {
                    date = (Date) DateFormatUtils.ISO_DATETIME_FORMAT.parseObject(value);
                } catch (Exception e) {
                    // TODO: handle exception
                    log.warn("Parses article create date failed with ISO8601, retry to parse with pattern[yyyy-MM-dd'T'HH:mm:ss]");
                    date = DateUtils.parse(value, "yyyyMMdd'T'HH:mm:ss");
                }
                article.setCreateTime(date);
            } else if ("title".equals(name)) {
                String value = XMLUtils.parseForString(member, "value/string");
                article.setTitle(value);
            } else if ("description".equals(name)) {
                String value = XMLUtils.parseForString(member, "value/string");
                article.setContent(value);
                if (value.length() > ARTICLE_ABSTRACT_LENGTH) {
                    article.setIntro(value.substring(0, ARTICLE_ABSTRACT_LENGTH));
                } else {
                    article.setIntro(value);
                }
            } else if ("categories".equals(name)) {
                List<Node> categories = XMLUtils.parseForNodeList(member, "value/array/data/value");
                Set<Category> categorySet = new HashSet<Category>();
                for (Node categoryNode : categories) {
                    String categoryName = XMLUtils.parseForString(categoryNode, "string");
                    if (!StringUtils.isBlank(categoryName)) {
                        Category category = categoryService.findCategoryByName(categoryName);
                        if (category != null) {
                            categorySet.add(category);
                        }
                    }
                }
                article.setCategories(categorySet);
            }
        }
        String publish_str = XMLUtils.parseForString(doc, "/methodCall/params/param[" + INDEX_PUBLISH + "]/value/boolean");
        boolean publish = "1".equals(publish_str);
        article.setPublish(publish);
        if (article.getCreateTime() == null) {
            article.setCreateTime(new Date());
        }
        return article;
    }

    /**
     * 获取文章
     * 
     * @return
     * @throws Exception
     */
    private String getPost(String postId) throws Exception {
        if (StringUtils.isBlank(postId)) {
            log.warn("[MetaWeblogAPI] PostId is null");
            return "";
        }

        Article article = articleService.findArticleById(new Long(postId));
        if (article == null) {
            throw new Exception(new StringBuilder().append("Not found article[id=").append(postId).append("]").toString());
        }

        final String title = StringEscapeUtils.escapeXml(article.getTitle());
        final String content = StringEscapeUtils.escapeXml(article.getContent());
        final String createTime = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(article.getCreateTime());
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<methodResponse>");
        result.append("     <params>");
        result.append("         <param>");
        result.append("             <value>");
        result.append("                 <struct>");

        result.append("                     <member>");
        result.append("                         <name>dateCreated</name>");
        result.append("                         <value><dateTime.iso8601>").append(createTime).append("</dateTime.iso8601></value>");
        result.append("                     </member>");

        result.append("                     <member>");
        result.append("                         <name>description</name>");
        result.append("                         <value>").append(content).append("</value>");
        result.append("                     </member>");

        result.append("                     <member>");
        result.append("                         <name>title</name>");
        result.append("                         <value>").append(title).append("</value>");
        result.append("                     </member>");

        result.append("                     <member>");
        result.append("                         <name>categories</name>");
        result.append("                         <value>");
        result.append("                             <array>");
        result.append("                                 <data>");
        Set<Category> categorySet = article.getCategories();
        for (Category category : categorySet) {
            result.append("                                     <value>").append(category.getName()).append("</value>");
        }
        result.append("                                 </data>");
        result.append("                             </array>");
        result.append("                         </value>");
        result.append("                     </member>");
        result.append("                 </struct>");
        result.append("             </value>");
        result.append("         </param>");
        result.append("     </params>");
        result.append("</methodResponse>");
        return result.toString();
    }

    /**
     * 编辑文章
     * 
     * @param doc
     * @return
     * @throws NullPointerException
     * @throws XPathExpressionException
     * @throws RuntimeException
     */
    private String editPost(Document doc) throws NullPointerException, XPathExpressionException, RuntimeException {
        final Article article = parsePost(doc);
        final String postId = XMLUtils.parseForString(doc, "/methodCall/params/param[" + INDEX_POST_ID + "]/value/string");
        article.setId(new Long(postId));
        articleService.updateArticle(article);

        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<methodResponse>");
        result.append("     <params>");
        result.append("         <param>");
        result.append("             <value>");
        result.append("                 <string>").append(postId).append("</string>");
        result.append("             </value>");
        result.append("         </param>");
        result.append("     </params>");
        result.append("</methodResponse>");
        return result.toString();
    }
}
