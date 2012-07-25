/**
 * 
 */
package org.mspring.mlog.core;

import org.mspring.mlog.service.TagService;
import org.mspring.platform.core.ContextManager;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description 
 * @TODO 该类用于获取bean对象
 */
public class ServiceFactory {
    public static TagService getTagService(){
        return ContextManager.getApplicationContext().getBean(TagService.class);
    }
}
