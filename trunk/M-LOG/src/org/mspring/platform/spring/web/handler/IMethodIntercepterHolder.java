/**
 * 
 */
package org.mspring.platform.spring.web.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

/**
 * @author Gao Youbo
 * @since 2013-3-11
 * @description
 * @TODO
 */
public interface IMethodIntercepterHolder {
    Object doChain(Method handlerMethod, Object handler, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception;
}
