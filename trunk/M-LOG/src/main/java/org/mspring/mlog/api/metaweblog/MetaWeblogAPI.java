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
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Attachment;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.exception.BusinessException;
import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.MimeTypeUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.mspring.platform.utils.XMLUtils;
import org.mspring.platform.web.render.XMLRender;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author Gao Youbo
 * @since 2012-8-6
 * @Description
 * @TODO MetaWeblog离线写作API
 */
public class MetaWeblogAPI {
    /**
     * Logger.
     */
    private static final Logger log = Logger.getLogger(MetaWeblogAPI.class);

    private UserService userService = ServiceFactory.getUserService();
    private CatalogService catalogService = ServiceFactory.getCatalogService();
    private PostService postService = ServiceFactory.getPostService();
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
     * Method name: "metaWeblog.newMediaObject"
     */
    private static final String METHOD_NEW_MEDIA_OBJECT = "metaWeblog.newMediaObject";

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
        XMLRender renderer = new XMLRender();

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

            User user = null;
            // 这里处理删除时的参数列表,删除时第一个参数"appkey"无用
            if (METHOD_DELETE_POST.equals(methodName)) {
                final String username = XMLUtils.parseForNode(doc, "/methodCall/params/param[" + (INDEX_USER_NAME + 1) + "]/value/string").getTextContent();
                user = userService.getUserByName(username);
                if (null == user) {
                    throw new BusinessException("No user[username=" + username + "]");
                }

                final String password = XMLUtils.parseForNode(doc, "/methodCall/params/param[" + (INDEX_USER_PWD + 1) + "]/value/string").getTextContent();
                if (!user.getPassword().equals(StringUtils.getMD5(password))) {
                    throw new BusinessException("Wrong password");
                }
            } else {
                final String username = XMLUtils.parseForNode(doc, "/methodCall/params/param[" + INDEX_USER_NAME + "]/value/string").getTextContent();
                user = userService.getUserByName(username);
                if (null == user) {
                    throw new BusinessException("No user[username=" + username + "]");
                }

                final String password = XMLUtils.parseForNode(doc, "/methodCall/params/param[" + INDEX_USER_PWD + "]/value/string").getTextContent();
                if (!user.getPassword().equals(StringUtils.getMD5(password))) {
                    throw new BusinessException("Wrong password");
                }
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
                responseContent = newPost(doc, user);
            }
            // 获取文章
            else if (METHOD_GET_POST.equals(methodName)) {
                final String postId = XMLUtils.parseForString(doc, "/methodCall/params/param[" + INDEX_POST_ID + "]/value/string");
                responseContent = getPost(postId);
            }
            // 编辑文章
            else if (METHOD_EDIT_POST.equals(methodName)) {
                responseContent = editPost(doc, user);
            }
            // 删除文章
            else if (METHOD_DELETE_POST.equals(methodName)) {
                final String postId = XMLUtils.parseForString(doc, "/methodCall/params/param[" + (INDEX_POST_ID + 1) + "]/value/string");
                if (StringUtils.isNotBlank(postId) && ValidatorUtils.isNumber(postId)) {
                    postService.deletePost(new Long(postId));
                }
                final StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><boolean>").append(true).append("</boolean></value></param></params></methodResponse>");
                responseContent = stringBuilder.toString();
            }
            // 插入媒体文件
            else if (METHOD_NEW_MEDIA_OBJECT.equals(methodName)) {
                final String type = XMLUtils.parseForString(doc, "/methodCall/params/param[4]/value/struct/member[2]/value/string");
                final String base64Data = XMLUtils.parseForString(doc, "/methodCall/params/param[4]/value/struct/member[3]/value/base64");
                responseContent = newMediaObject(request, type, base64Data, user);
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
            log.error(e.getMessage(), e);
            responseContent = "";
            final StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><fault><value><struct>").append("<member><name>faultCode</name><value><int>500</int></value></member>").append("<member><name>faultString</name><value><string>").append(e.getMessage()).append("</string></value></member></struct></value></fault></methodResponse>");
            responseContent = stringBuilder.toString();
            renderer.setContent(responseContent);
        }
    }

    private String getUsersBlogs() {
        final String blogId = "";
        final String blogName = optionService.getOption("blogname");
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

        final List<Catalog> catalogs = catalogService.findAllCatalog();
        for (Catalog catalog : catalogs) {
            final String categoryid = catalog.getId().toString();
            final String categoryname = catalog.getName();
            final String description = catalog.getDescription();

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

        List<Post> recentPosts = postService.getRecentPost(numOfPosts);
        for (Post post : recentPosts) {
            StringBuilder element = new StringBuilder();
            try {
                Long id = post.getId();
                String createTime = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(post.getCreateTime());
                String title = StringEscapeUtils.escapeXml(post.getTitle());
                String content = StringEscapeUtils.escapeXml(post.getContent());
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

                Set<Catalog> catalogs = post.getCatalogs();
                for (Catalog catalog : catalogs) {
                    element.append("                                                   <value>").append(catalog.getName()).append("</value>\n");
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
    private String newPost(Document doc, User user) throws NullPointerException, XPathExpressionException, RuntimeException {
        Post post = parsePost(doc);
        if (post.getAuthor() == null) {
            post.setAuthor(user);
        }
        if (StringUtils.isBlank(post.getPostIp())) {
            post.setPostIp("from.xmlrpc");
        }
        post = postService.createPost(post);
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        result.append("<methodResponse>\n");
        result.append("     <params>\n");
        result.append("         <param>\n");
        result.append("             <value>\n");
        result.append("                 <string>").append(post.getId()).append("</string>\n");
        result.append("             </value>\n");
        result.append("         </param>\n");
        result.append("     </params>\n");
        result.append("</methodResponse>\n");
        return result.toString();
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
            throw new Exception("Not found post [postId = " + postId + "]");
        }

        Post post = postService.getPostById(new Long(postId));
        if (post == null) {
            throw new Exception(new StringBuilder().append("Not found article[id=").append(postId).append("]").toString());
        }

        final String title = StringEscapeUtils.escapeXml(post.getTitle());
        final String content = StringEscapeUtils.escapeXml(post.getContent());
        final String createTime = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(post.getCreateTime());
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

        Set<Catalog> catalogs = post.getCatalogs();
        for (Catalog catalog : catalogs) {
            result.append("                                     <value>").append(catalog.getName()).append("</value>");
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
    private String editPost(Document doc, User user) throws NullPointerException, XPathExpressionException, RuntimeException {
        final Post post = parsePost(doc);
        if (post.getId() == null) {
            log.warn("execute metaweblog api [metaWeblog.editPost] error, postId can't be null.");
            throw new BusinessException("execute metaweblog api [metaWeblog.editPost] error, postId can't be null.");
        }
        if (post.getAuthor() == null) {
            post.setAuthor(user);
        }
        Post oldPost = postService.getPostById(post.getId());
        oldPost.setCatalogs(post.getCatalogs());
        oldPost.setTitle(post.getTitle());
        oldPost.setSummary(post.getSummary());
        oldPost.setContent(post.getContent());
        oldPost.setStatus(post.getStatus());
        postService.updatePost(oldPost);

        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<methodResponse>");
        result.append("     <params>");
        result.append("         <param>");
        result.append("             <value>");
        result.append("                 <string>").append(post.getId()).append("</string>");
        result.append("             </value>");
        result.append("         </param>");
        result.append("     </params>");
        result.append("</methodResponse>");
        return result.toString();
    }

    /**
     * 发表媒体文件
     * 
     * @param request
     * @param type
     * @param base64Data
     * @return
     */
    private String newMediaObject(HttpServletRequest request, String type, String base64Data, User user) {
        // 根据mimetype获取文件后缀名
        String ext = MimeTypeUtils.getExtension(type);
        Attachment attachment = ServiceFactory.getAttachmentService().createAttachment(base64Data, ext, user.getId());
        String url = request.getContextPath() + attachment.getPath();

        StringBuffer result = new StringBuffer();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><struct><member><name>url</name><value><string>");
        result.append(StringEscapeUtils.escapeXml(url));
        result.append("</string></value></member></struct></value></param></params></methodResponse>");
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
    private Post parsePost(Document doc) throws NullPointerException, XPathExpressionException, RuntimeException {
        final List<Node> members = XMLUtils.parseForNodeList(doc, "/methodCall/params/param/value/struct/member");
        Post post = new Post();
        // 处理postId(更新post时会用到postId)
        final String postId = XMLUtils.parseForString(doc, "/methodCall/params/param[" + INDEX_POST_ID + "]/value/string");
        if (StringUtils.isNotBlank(postId) && ValidatorUtils.isNumber(postId.trim())) {
            post.setId(new Long(postId.trim()));
        }
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
                post.setCreateTime(date);
            } else if ("title".equals(name)) {
                String value = XMLUtils.parseForString(member, "value/string");
                post.setTitle(value);
            } else if ("description".equals(name)) {
                String value = XMLUtils.parseForString(member, "value/string");
                post.setContent(value);
                if (value.length() > ARTICLE_ABSTRACT_LENGTH) {
                    post.setSummary(value.substring(0, ARTICLE_ABSTRACT_LENGTH));
                } else {
                    post.setSummary(value);
                }
            } else if ("categories".equals(name)) {
                List<Node> categories = XMLUtils.parseForNodeList(member, "value/array/data/value");
                Set<Catalog> catalogSet = new HashSet<Catalog>();
                for (Node catalogNode : categories) {
                    String categoryName = XMLUtils.parseForString(catalogNode, "string");
                    if (!StringUtils.isBlank(categoryName)) {
                        Catalog catalog = catalogService.getCatalogByName(categoryName);
                        if (catalog != null) {
                            catalogSet.add(catalog);
                        }
                    }
                }
                post.setCatalogs(catalogSet);
            }
        }
        String publish_str = XMLUtils.parseForString(doc, "/methodCall/params/param[" + INDEX_PUBLISH + "]/value/boolean");
        if ("1".equals(publish_str)) {
            post.setStatus(Post.Status.PUBLISH);
        } else {
            post.setStatus(Post.Status.DRAFT);
        }
        if (post.getCreateTime() == null) {
            post.setCreateTime(new Date());
        }
        return post;
    }
}
