/**
 * 
 */
package org.mspring.mlog.task.twitter;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2013-5-31
 * @description
 * @TODO
 */
@Aspect
@Component
public class TwitterAspect {

    @Autowired
    private SyncTencentWeiboTask syncTencentWeiboTask;

    @AfterReturning(value = "execution(* org.mspring.mlog.service.TwitterService.createTwitter(..))", returning = "twitterId")
    public void syncTencentWeibo(JoinPoint jp, Long twitterId) {
        Map<Object, Object> context = new HashMap<Object, Object>();
        context.put("twitterId", twitterId);
        syncTencentWeiboTask.doAsyncTask(context);
    }

}
