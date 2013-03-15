/**
 * 
 */
package org.mspring.platform.spring.web.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.spring.web.handler.MethodInvokerIntercepterManager.MethodInvokerContext;
import org.springframework.ui.Model;

/**
 * @author Gao Youbo
 * @since 2013-3-11
 * @description
 * @TODO
 */
public class MethodIntercepterHolder implements IMethodIntercepterHolder, IMethodInvokerIntercepter {
    MethodInvokerIntercepterManager manager;
    IMethodInvokerIntercepter self;
    IMethodIntercepterHolder next;

    MethodIntercepterHolder(IMethodInvokerIntercepter self, MethodInvokerIntercepterManager manager) {
        this.self = self;
        this.manager = manager;
    }

    void setNext(IMethodIntercepterHolder next) {
        this.next = next;
    }

    @Override
    public Object doChain(Method handlerMethod, Object handler, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        return self.invokeHandlerMethod(handlerMethod, handler, request, response, model, next);
    }

    @Override
    public Object invokeHandlerMethod(Method handlerMethod, Object handler, HttpServletRequest request, HttpServletResponse response, Model model, IMethodIntercepterHolder chain) throws Exception {
        MethodInvokerContext ctx = manager.getContext().get();
        manager.getContext().remove();
        return ctx.invoker.invokeHandlerMethod(handlerMethod, handler, ctx.webRequest, ctx.model);
    }

}
