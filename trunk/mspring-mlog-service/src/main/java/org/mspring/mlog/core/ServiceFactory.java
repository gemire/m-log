/**
 * 
 */
package org.mspring.mlog.core;

import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.CacheService;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.FileService;
import org.mspring.mlog.service.LinkService;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.service.PostMetaService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.StatService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.service.TreeItemService;
import org.mspring.mlog.service.UserService;
import org.mspring.platform.core.ContextManager;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO 该类用于获取bean对象
 */
public class ServiceFactory {
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

    public static PostMetaService getPostMetaService() {
        return ContextManager.getApplicationContext().getBean(PostMetaService.class);
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
}
