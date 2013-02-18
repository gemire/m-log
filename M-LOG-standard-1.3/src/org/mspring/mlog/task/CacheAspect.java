///**
// * 
// */
//package org.mspring.mlog.task;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.mspring.mlog.service.cache.CacheService;
//import org.mspring.mlog.service.cache.DefaultCacheService;
//import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author Gao Youbo
// * @since 2013-2-18
// * @description
// * @TODO
// */
//@Aspect
//@Component
//public class CacheAspect {
//    private static final Logger logger = Logger.getLogger(CacheAspect.class);
//
//    @Autowired
//    private DefaultCacheService defaultCacheService;
//
//    @Before("execution(* org.mspring.mlog.service.security.*.find*(..)) || execution(* org.mspring.mlog.service.security.*.get*(..)) || execution(* org.mspring.mlog.service.security.*.has*(..)) || execution(* org.mspring.mlog.service.security.*.load*(..))")
//    public Object findAndCreateCache(JoinPoint jp) throws Throwable {
//        String cacheKey = getCacheKey(jp.getSignature().toLongString(), jp.getArgs());
//        Object cacheValue = defaultCacheService.getDefaultCacheValue(cacheKey);
//        if (cacheValue == null) {
//            System.out.println("++++++++++++++++++++++++++++++++++" + cacheKey);
//            logger.debug("Hold up method , Get method result and create cache........!");
//            try {
//                if (jp.getClass().equals(MethodInvocationProceedingJoinPoint.class)) {
//                    cacheValue = ((MethodInvocationProceedingJoinPoint) jp).proceed();
//                }
//                defaultCacheService.updateDefaultCacheValue(cacheKey, cacheValue, CacheService.ONE_DAY * 365);
//            } catch (Exception e) {
//                // TODO: handle exception
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("----------------------------------" + cacheKey);
//        }
//        return cacheValue;
//    }
//
//    @AfterReturning(("execution(* org.mspring.mlog.service.security.*.create*(..)) || execution(* org.mspring.mlog.service.security.*.update*(..)) || execution(* org.mspring.mlog.service.security.*.delete*(..)) || execution(* org.mspring.mlog.service.security.*.remove*(..)) || execution(* org.mspring.mlog.service.security.*.set*(..))"))
//    public void flushCache(JoinPoint jp) {
//        String className = jp.getSignature().getClass().getName();
//        List list = defaultCacheService.getCacheKeys();
//        for (int i = 0; i < list.size(); i++) {
//            String cacheKey = String.valueOf(list.get(i));
//            if (cacheKey.startsWith(className)) {
//                defaultCacheService.deleteDefaultCacheValue(cacheKey);
//                logger.debug("remove cache " + cacheKey);
//            }
//        }
//    }
//
//    /**
//     * 获得 cache key 的方法,cache key 是 Cache 中一个 Element 的唯一标识 cache key 包括
//     * 包名+类名+方法名,如 com.co.cache.service.UserServiceImpl.getAllUser
//     * 
//     * @param targetName
//     * @param methodName
//     * @param arguments
//     * @return
//     */
//    private String getCacheKey(String targetName, Object[] arguments) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(targetName);
//        if ((arguments != null) && (arguments.length != 0)) {
//            for (int i = 0; i < arguments.length; i++) {
//                sb.append(".").append(arguments[i]);
//            }
//        }
//        return sb.toString();
//    }
//}
