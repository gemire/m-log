/**
 * 
 */
package org.mspring.mlog.support.log;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.LogService;
import org.mspring.platform.core.ContextManager;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.task.AbstractTask;
import org.mspring.platform.utils.RequestUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

/**
 * @author Gao Youbo
 * @since 2013-3-12
 * @description
 * @TODO
 */
@Component
public class LogTask extends AbstractTask {

    @Override
    protected void doTask(Map<Object, Object> paramMap) throws Exception {
        // TODO Auto-generated method stub
        if (paramMap == null) {
            return;
        }
        JoinPoint jp = null;
        Log logAnno = null;
        User user = null;

        HttpServletRequest request = paramMap.get("request") == null ? null : (HttpServletRequest) paramMap.get("request");
        Object jpObj = paramMap.get("joinPoint");
        if (jpObj != null) {
            jp = (JoinPoint) jpObj;
        }
        Object logAnnoObj = paramMap.get("logAnno");
        if (logAnnoObj != null) {
            logAnno = (Log) logAnnoObj;
        }
        Object userObj = paramMap.get("user");
        if (userObj != null) {
            user = (User) userObj;
        }

        if (jp == null || logAnno == null) {
            return;
        }

        Object[] params = jp.getArgs();// 获取目标方法体参数
        String arguments = parseArguments(params); // 解析目标方法体的参数
        String className = jp.getTarget().getClass().toString(); // 获取目标类名
        String signature = jp.getSignature().toString();// 获取目标方法签名
        String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));
        String ip = RequestUtils.getRemoteIP(request);
        String agent = RequestUtils.getUserAgent(request);

        org.mspring.mlog.entity.Log log = new org.mspring.mlog.entity.Log();
        log.setActionTime(new Date());
        log.setUser(user);
        log.setAction(logAnno.action());
        log.setDescription(logAnno.description());
        log.setIp(ip);
        log.setAgent(agent);
        log.setClassName(className);
        log.setMethodName(methodName);
        log.setArguments(arguments);

        ContextManager.getApplicationContext().getBean(LogService.class).createLog(log);
    }

    /**
     * 解析方法参数
     * 
     * @param params
     *            方法参数
     * @return 解析后的方法参数
     */
    private String parseArguments(Object[] params) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof ServletRequest || params[i] instanceof ServletResponse || params[i] instanceof Model) {
                continue;
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setExclusionStrategies(new ExclusionStrategyEntity());
            String json = gsonBuilder.create().toJson(params[i]);
            sb.append(json + ", ");
        }
        String arguments = sb.toString();
        return arguments;
    }
}

class ExclusionStrategyEntity implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // TODO Auto-generated method stub
        if (f.getDeclaredClass().getName().indexOf("org.mspring.mlog") != -1 || f.getDeclaredClass().isAssignableFrom(Collection.class) || f.getDeclaredClass().isAssignableFrom(Set.class)) {
            return true;
        }
        if (f.getDeclaringClass().isAssignableFrom(Page.class) && "result".equals(f.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }
}