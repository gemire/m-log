/**
 * 
 */
package org.mspring.mlog.core;

import org.hibernate.SessionFactory;
import org.mspring.mlog.service.AdService;
import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.AttachmentService;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.FileService;
import org.mspring.mlog.service.HQLExecuteService;
import org.mspring.mlog.service.InstallService;
import org.mspring.mlog.service.JobLogService;
import org.mspring.mlog.service.JobService;
import org.mspring.mlog.service.LinkService;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.StatService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.mlog.service.cache.DefaultCacheService;
import org.mspring.mlog.service.cache.OptionCacheService;
import org.mspring.mlog.service.cache.WidgetCacheService;
import org.mspring.mlog.service.search.HibernateSearchService;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.core.ContextManager;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO 该类用于获取bean对象
 */
public class ServiceFactory {
    public static SessionFactory getSessionFactory() {
        return (SessionFactory) ContextManager.getApplicationContext().getBean("sessionFactory");
    }

    public static UserService getUserService() {
        return ContextManager.getApplicationContext().getBean(UserService.class);
    }

    public static OptionService getOptionService() {
        return ContextManager.getApplicationContext().getBean(OptionService.class);
    }

    public static TagService getTagService() {
        return ContextManager.getApplicationContext().getBean(TagService.class);
    }

    public static CatalogService getCatalogService() {
        return ContextManager.getApplicationContext().getBean(CatalogService.class);
    }

    public static PostService getPostService() {
        return ContextManager.getApplicationContext().getBean(PostService.class);
    }

    public static CommentService getCommentService() {
        return ContextManager.getApplicationContext().getBean(CommentService.class);
    }

    public static StatService getStatService() {
        return ContextManager.getApplicationContext().getBean(StatService.class);
    }

    public static FileService getFileService() {
        return ContextManager.getApplicationContext().getBean(FileService.class);
    }

    public static AlbumService getAlbumService() {
        return ContextManager.getApplicationContext().getBean(AlbumService.class);
    }

    public static PhotoService getPhotoService() {
        return ContextManager.getApplicationContext().getBean(PhotoService.class);
    }

    public static TreeItemService getTreeItemService() {
        return ContextManager.getApplicationContext().getBean(TreeItemService.class);
    }

    public static LinkTypeService getLinkTypeService() {
        return ContextManager.getApplicationContext().getBean(LinkTypeService.class);
    }

    public static LinkService getLinkService() {
        return ContextManager.getApplicationContext().getBean(LinkService.class);
    }

    public static CacheService getCacheService() {
        return ContextManager.getApplicationContext().getBean(CacheService.class);
    }

    public static DefaultCacheService getDefaultCacheService() {
        return ContextManager.getApplicationContext().getBean(DefaultCacheService.class);
    }

    public static OptionCacheService getOptionCacheService() {
        return ContextManager.getApplicationContext().getBean(OptionCacheService.class);
    }

    public static WidgetCacheService getWidgetCacheService() {
        return ContextManager.getApplicationContext().getBean(WidgetCacheService.class);
    }

    public static InstallService getInstallService() {
        return ContextManager.getApplicationContext().getBean(InstallService.class);
    }

    public static HQLExecuteService getHQLExecuteService() {
        return ContextManager.getApplicationContext().getBean(HQLExecuteService.class);
    }

    public static JobService getJobService() {
        return ContextManager.getApplicationContext().getBean(JobService.class);
    }

    public static JobLogService getJobLogService() {
        return ContextManager.getApplicationContext().getBean(JobLogService.class);
    }

    public static AttachmentService getAttachmentService() {
        return ContextManager.getApplicationContext().getBean(AttachmentService.class);
    }

    public static HibernateSearchService getHibernateSearchService() {
        return ContextManager.getApplicationContext().getBean(HibernateSearchService.class);
    }

    public static AdService getAdService() {
        return ContextManager.getApplicationContext().getBean(AdService.class);
    }

}
