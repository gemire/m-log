/**
 * 
 */
package org.mspring.platform.spring.web.handler;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.bind.annotation.support.HandlerMethodInvoker;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Gao Youbo
 * @since 2013-3-11
 * @description
 * @TODO
 */
public class MethodInvokerIntercepterManager {
    IMethodIntercepterHolder root;
    private ThreadLocal<MethodInvokerContext> context = new ThreadLocal<MethodInvokerContext>();

    public ThreadLocal<MethodInvokerContext> getContext() {
        return context;
    }

    Object doChain(HandlerMethodInvoker invoker, Object handler, Method handlerMethod, ServletWebRequest webRequest, ExtendedModelMap model) throws Exception {
        if (root != null) {
            context.set(new MethodInvokerContext(invoker, webRequest, model));
            return root.doChain(handlerMethod, handler, webRequest.getRequest(), webRequest.getResponse(), model);
        }
        return invoker.invokeHandlerMethod(handlerMethod, handler, webRequest, model);
    }

    public void setIntercepters(List<IMethodInvokerIntercepter> intercepters) {
        if (intercepters != null && intercepters.size() > 0) {
            int size = intercepters.size();
            MethodIntercepterHolder holder = null;
            for (int i = 0; i < size; i++) {
                MethodIntercepterHolder previous = holder;
                IMethodInvokerIntercepter intercepter = intercepters.get(i);
                holder = new MethodIntercepterHolder(intercepter, this);
                if (previous != null) {
                    previous.setNext(holder);
                } else {
                    root = holder;
                }
            }
            holder.setNext(new MethodIntercepterHolder(holder, this));
        }
    }

    class MethodInvokerContext {
        HandlerMethodInvoker invoker;
        ServletWebRequest webRequest;
        ExtendedModelMap model;

        MethodInvokerContext(HandlerMethodInvoker invoker, ServletWebRequest webRequest, ExtendedModelMap model) {
            this.invoker = invoker;
            this.webRequest = webRequest;
            this.model = model;
        }
    }

}