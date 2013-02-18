/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.InstallService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.security.UserService;

/**
 * @author Gao Youbo
 * @since 2012-12-24
 * @Description
 * @TODO
 */
public abstract class AbstractInstallService implements InstallService {

    private static final Logger log = Logger.getLogger(AbstractInstallService.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.InstallService#hasInstall()
     */
    @Override
    public boolean hasInstall() {
        // TODO Auto-generated method stub
        OptionService optionService = ServiceFactory.getOptionService();
        String hasInstalled = optionService.getOption("hasInstalled");
        if ("true".equals(hasInstalled)) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.InstallService#setHasInstalled()
     */
    @Override
    public void setHasInstalled() {
        // TODO Auto-generated method stub
        ServiceFactory.getOptionService().setOption("hasInstalled", "true");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.InstallService#hasUser()
     */
    @Override
    public boolean hasUser() {
        // TODO Auto-generated method stub
        return ServiceFactory.getUserService().hasUser();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.InstallService#initBlogInfo(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public void initBlogInfo(String blogname, String blogurl, String username, String alias, String password, String email) {
        // TODO Auto-generated method stub
        log.debug("init bloginfo...");
        OptionService optionService = ServiceFactory.getOptionService();
        optionService.setOption("blogname", blogname);
        optionService.setOption("blogurl", blogurl);
        optionService.setOption("blogurl", blogurl);

        User user = new User();
        user.setName(username);
        user.setAlias(alias);
        user.setEmail(email);
        user.setPassword(password);
        ServiceFactory.getUserService().createUser(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.InstallService#initPosts()
     */
    @Override
    public void initPosts() {
        // TODO Auto-generated method stub
        log.debug("init posts...");
        CatalogService catalogService = ServiceFactory.getCatalogService();
        PostService postService = ServiceFactory.getPostService();
        UserService userService = ServiceFactory.getUserService();

        Catalog catalog = new Catalog();
        catalog.setName("默认分类");
        catalog.setDescription("M-LOG默认分类");
        catalog = catalogService.createCatalog(catalog);

        User author = userService.getFirstUser();

        Post post = new Post();
        Set<Catalog> catalogSet = new HashSet<Catalog>();
        catalogSet.add(catalog);
        post.setCatalogs(catalogSet);
        post.setAuthor(author);
        post.setTitle("欢迎使用M-LOG");
        post.setSummary("<p>欢迎使用M-LOG，M-LOG是一个以Java语言搭建的高性能博客程序。</p><p>M-LOG功能</p><p><br /></p><p>1、支持mateweblog离线写作协议</p><p>2、支持RSS2.0、Atom订阅功能</p><p>3、相册功能</p><p>4、支持动态换肤</p><p>5、基于分类和Tag的文章分类功能</p><p>6、SEO优化功能</p><p>7、评论回复及邮件提醒</p><p>8、可配置文章固定永久链接</p><p>9、可配置易扩展</p><p>10、高效严禁的缓存功能</p><p><br /></p>");
        post.setContent("<p>欢迎使用M-LOG，M-LOG是一个以Java语言搭建的高性能博客程序。</p><p>M-LOG功能</p><p><br /></p><p>1、支持mateweblog离线写作协议</p><p>2、支持RSS2.0、Atom订阅功能</p><p>3、相册功能</p><p>4、支持动态换肤</p><p>5、基于分类和Tag的文章分类功能</p><p>6、SEO优化功能</p><p>7、评论回复及邮件提醒</p><p>8、可配置文章固定永久链接</p><p>9、可配置易扩展</p><p>10、高效严禁的缓存功能</p><p><br /></p>");
        postService.createPost(post);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.InstallService#initLinks()
     */
    @Override
    public void initLinks() {
        // TODO Auto-generated method stub
        LinkType linkType = new LinkType();
        linkType.setName("M-Loger");
        linkType.setDescription("M-Loger");
        linkType.setOrder(new Long(1));
        linkType.setVisable(true);
        linkType = ServiceFactory.getLinkTypeService().createLinkType(linkType);

        Link mspring = new Link();
        mspring.setName("M-Spring");
        mspring.setUrl("http://www.mspring.org");
        mspring.setTarget(Link.Target._BLANK);
        mspring.setOrder(new Long(1));
        mspring.setType(linkType);
        mspring.setVisable(true);

        Link wiki = new Link();
        wiki.setName("M-LOG WIKI");
        wiki.setUrl("http://wiki.mspring.org");
        wiki.setTarget(Link.Target._BLANK);
        wiki.setOrder(new Long(2));
        wiki.setType(linkType);
        wiki.setVisable(true);

        ServiceFactory.getLinkService().createLink(mspring);
        ServiceFactory.getLinkService().createLink(wiki);
    }

}
