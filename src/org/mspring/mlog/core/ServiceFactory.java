/**
 * 
 */
package org.mspring.mlog.core;

import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.FileService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostMetaService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.StatService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.service.UserService;
import org.mspring.mlog.service.search.PostSearchService;
import org.mspring.mlog.web.freemarker.ExtendsFreeMarkerConfigurer;
import org.mspring.platform.core.ContextManager;
import org.springframework.jdbc.core.JdbcTemplate;

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

    public static ExtendsFreeMarkerConfigurer getExtendsFreeMarkerConfigurer() {
        return ContextManager.getApplicationContext().getBean(ExtendsFreeMarkerConfigurer.class);
    }

    public static Configuration getFreemarkerConfiguration() {
        return getExtendsFreeMarkerConfigurer().getConfiguration();
    }

    public static FileService getFileService() {
        return ContextManager.getApplicationContext().getBean(FileService.class);
    }

    public static PostMetaService getPostMetaService() {
        return ContextManager.getApplicationContext().getBean(PostMetaService.class);
    }

    public static PostSearchService getPostSearchService() {
        return ContextManager.getApplicationContext().getBean(PostSearchService.class);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return ContextManager.getApplicationContext().getBean(JdbcTemplate.class);
    }
}
