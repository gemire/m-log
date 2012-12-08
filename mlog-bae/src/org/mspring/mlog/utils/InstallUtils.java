/**
 * 
 */
package org.mspring.mlog.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.entity.TreeItem;
import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.TreeItemService;
import org.mspring.mlog.service.UserService;

/**
 * @author Gao Youbo
 * @since 2012-11-29
 * @Description
 * @TODO 用于完成安装的通用类
 */
public class InstallUtils {
    private static final Logger log = Logger.getLogger(InstallUtils.class);

    /**
     * 判断系统是否安装
     * 
     * @return
     */
    public static boolean hasInstall() {
        OptionService optionService = ServiceFactory.getOptionService();
        String hasInstalled = optionService.getOption("hasInstalled");
        if ("true".equals(hasInstalled)) {
            return true;
        }
        return false;
    }

    /**
     * 设置为已经安装
     * 
     * @return
     */
    public static void setHasInstalled() {
        ServiceFactory.getOptionService().setOption("hasInstalled", "true");
    }

    /**
     * 判断是否存在用户
     * 
     * @return
     */
    public static boolean hasUser() {
        return ServiceFactory.getUserService().hasUser();
    }

    /**
     * 初始化博客信息
     * 
     * @param blogname
     * @param blogurl
     * @param username
     * @param alias
     * @param password
     * @param email
     */
    public static void initBlogInfo(String blogname, String blogurl, String username, String alias, String password, String email) {
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
        ServiceFactory.getUserService().addUser(user);
    }

    /**
     * 初始化添加一篇文章
     */
    public static void initPosts() {
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

    public static void initLinks() {
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

    /**
     * 初始化TreeItem
     */
    public static void initTreeItems() {
        log.debug("init tree items...");
        TreeItemService treeItemService = ServiceFactory.getTreeItemService();

        treeItemService.clearItems();

        List<TreeItem> items = new ArrayList<TreeItem>();
        items.add(new TreeItem("1", "功能菜单", "0", "", false, true, "main"));
        items.add(new TreeItem("105", "首页", "1", "admin/about", false, true, "main"));
        items.add(new TreeItem("115", "文章", "1", "", false, true, "main"));
        items.add(new TreeItem("11505", "文章管理", "115", "admin/post/list", false, true, "main"));
        items.add(new TreeItem("11515", "分类管理", "115", "admin/catalog/list", false, true, "main"));
        items.add(new TreeItem("11520", "评论管理", "115", "admin/comment/list", false, true, "main"));
        items.add(new TreeItem("120", "链接管理", "1", "admin/link/list", false, true, "main"));
        items.add(new TreeItem("125", "相册管理", "1", "admin/album/list", false, true, "main"));

        items.add(new TreeItem("2", "配置", "0", "", false, true, "main"));
        items.add(new TreeItem("205", "用户信息", "2", "admin/user/userinfo", false, true, "main"));
        items.add(new TreeItem("220", "博客信息", "2", "admin/setting/bloginfo", false, true, "main"));
        items.add(new TreeItem("225", "邮件设置", "2", "admin/setting/mail", false, true, "main"));
        items.add(new TreeItem("230", "皮肤设置", "2", "admin/setting/skin", false, true, "main"));
        items.add(new TreeItem("235", "BAE设置", "2", "admin/setting/bae", false, true, "main"));
        items.add(new TreeItem("240", "SEO设置", "2", "admin/setting/seo", false, true, "main"));
        items.add(new TreeItem("245", "缓存管理", "2", "admin/cache/setting", false, true, "main"));

        items.add(new TreeItem("3", "关于", "0", "", false, true, "main"));
        items.add(new TreeItem("305", "关于", "3", "admin/about", false, true, "main"));
        items.add(new TreeItem("310", "联系我们", "3", "admin/contact", false, true, "main"));

        for (TreeItem item : items) {
            treeItemService.createItem(item);
        }
    }
}
