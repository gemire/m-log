/**
 * 
 */
package org.mspring.mlog.common;

import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.CacheService;
import org.mspring.mlog.service.CategoryService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.CustomService;
import org.mspring.mlog.service.LinkService;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.mlog.service.MenuService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.service.PluginService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.service.ThemeService;
import org.mspring.mlog.service.security.TreeService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.core.ContextManager;
import org.mspring.platform.event.EventManager;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since Mar 17, 2012
 */
public class ServiceFactory {
    
    public static UserService getUserService(){
        return ContextManager.getApplicationContext().getBean(UserService.class);
    }
    
    public static AlbumService getAlbumService() {
        return ContextManager.getApplicationContext().getBean(AlbumService.class);
    }

    public static ArticleService getArticleService() {
        return ContextManager.getApplicationContext().getBean(ArticleService.class);
    }

    public static CacheService getCacheService() {
        return ContextManager.getApplicationContext().getBean(CacheService.class);
    }

    public static CategoryService getCategoryService() {
        return ContextManager.getApplicationContext().getBean(CategoryService.class);
    }

    public static CommentService getCommentService() {
        return ContextManager.getApplicationContext().getBean(CommentService.class);
    }

    public static CustomService getCustomService() {
        return ContextManager.getApplicationContext().getBean(CustomService.class);
    }

    public static LinkService getLinkService() {
        return ContextManager.getApplicationContext().getBean(LinkService.class);
    }

    public static LinkTypeService getLinkTypeService() {
        return ContextManager.getApplicationContext().getBean(LinkTypeService.class);
    }

    public static MenuService getMenuService() {
        return ContextManager.getApplicationContext().getBean(MenuService.class);
    }

    public static OptionService getOptionService() {
        return ContextManager.getApplicationContext().getBean(OptionService.class);
    }

    public static PhotoService getPhotoService() {
        return ContextManager.getApplicationContext().getBean(PhotoService.class);
    }

    public static PluginService getPluginService() {
        return ContextManager.getApplicationContext().getBean(PluginService.class);
    }

    public static TagService getTagService() {
        return ContextManager.getApplicationContext().getBean(TagService.class);
    }

    public static ThemeService getThemeService() {
        return ContextManager.getApplicationContext().getBean(ThemeService.class);
    }

    public static TreeService getTreeService() {
        return ContextManager.getApplicationContext().getBean(TreeService.class);
    }
    
    public static Configuration getFreemarkerConfiguration(){
        return (Configuration) ContextManager.getApplicationContext().getBean("freemarkerConfiguration");
    }
    
    public static EventManager getDefaultEventManager(){
        return (EventManager) ContextManager.getApplicationContext().getBean("eventManager");
    }
}
