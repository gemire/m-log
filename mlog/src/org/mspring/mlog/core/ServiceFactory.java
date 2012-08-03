/**
 * 
 */
package org.mspring.mlog.core;

import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.web.freemarker.ExtendsFreeMarkerConfigurer;
import org.mspring.platform.core.ContextManager;

import freemarker.template.Configuration;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO 该类用于获取bean对象
 */
public class ServiceFactory {
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

    public static ExtendsFreeMarkerConfigurer getExtendsFreeMarkerConfigurer() {
        return ContextManager.getApplicationContext().getBean(ExtendsFreeMarkerConfigurer.class);
    }

    public static Configuration getFreemarkerConfiguration() {
        return getExtendsFreeMarkerConfigurer().getConfiguration();
    }
}
