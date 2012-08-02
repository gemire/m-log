/**
 * 
 */
package org.mspring.platform.web.resolver;

import org.mspring.platform.web.resolver.stereotype.UrlVariable;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Gao Youbo
 * @since 2012-8-2
 * @Description
 * @TODO
 */
public class UrlVariableMethodArgumentResolver implements WebArgumentResolver {

    /* (non-Javadoc)
     * @see org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.context.request.NativeWebRequest)
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, NativeWebRequest nativeWebRequest) throws Exception {
        // TODO Auto-generated method stub
        UrlVariable urlVariable = parameter.getParameterAnnotation(UrlVariable.class);
        if (urlVariable == null) {
            return WebArgumentResolver.UNRESOLVED;
        }
        
        return null;
    }

}
