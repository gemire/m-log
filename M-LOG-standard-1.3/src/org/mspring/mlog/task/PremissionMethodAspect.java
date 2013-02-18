///**
// * 
// */
//package org.mspring.mlog.task;
//
//import java.lang.reflect.Method;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.mspring.mlog.entity.security.User;
//import org.mspring.mlog.service.security.TreeItemSecurityService;
//import org.mspring.mlog.web.security.SecurityUtils;
//import org.mspring.mlog.web.security.annotation.Premission;
//import org.mspring.platform.core.ContextManager;
//import org.mspring.platform.utils.ArrayUtils;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.stereotype.Component;
//
///**
// * @author Administrator
// * @since Gao Youbo
// * @description
// * @TODO
// */
//@Aspect
//@Component
//public class PremissionMethodAspect {
//    
//    @Before(value = "@annotation(org.mspring.mlog.web.security.annotation.Premission)")
//    public void preHandle(JoinPoint jp) {
//        Signature signature = jp.getSignature();
//        if (signature instanceof MethodSignature) {
//            MethodSignature methodSignature = (MethodSignature) jp.getSignature();
//            Method method = methodSignature.getMethod();
//            Premission premission = method.getAnnotation(Premission.class);
//            if (premission != null) {
//                String[] items = premission.item();
//                if (items != null && items.length > 0) {
//                    User user = SecurityUtils.getCurrentUser();
//                    if (user == null) {
//                        throw new AccessDeniedException(" 没有权限访问！请先登录系统。 ");
//                    }
//                    for (String string : items) {
//                        boolean hasPremission = ContextManager.getApplicationContext().getBean(TreeItemSecurityService.class).hasUserTreeItem(string, user.getId());
//                        if (hasPremission) {
//                            return;
//                        }
//                    }
//                    throw new AccessDeniedException(" 没有权限访问！ User[id=" + user.getId() + ", name=" + user.getName() + ", alias=" + user.getAlias() + "], Items=" + ArrayUtils.toString(items));
//                }
//            }
//        }
//    }
//}
