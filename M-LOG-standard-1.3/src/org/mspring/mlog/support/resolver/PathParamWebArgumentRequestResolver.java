/**
 * 
 */
package org.mspring.mlog.support.resolver;

import org.mspring.platform.utils.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Gao Youbo
 * @since 2013-3-7
 * @description
 * @TODO
 */
public class PathParamWebArgumentRequestResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter parameter, NativeWebRequest nativeWebRequest) throws Exception {
        // TODO Auto-generated method stub
        PathParam pathParam = parameter.getParameterAnnotation(PathParam.class);
        if (pathParam == null) {
            return WebArgumentResolver.UNRESOLVED;
        }
        if (StringUtils.isNotBlank(pathParam.defaultValue())) {
            return pathParam.defaultValue();
        }

        String paramName = parameter.getParameterName();
        String value = nativeWebRequest.getParameter(paramName);
        if (value == null) {
            if (pathParam.required()) {
                throw new Exception("parameter [" + paramName + "] is required.");
            }
            return value;
        }
        return StringUtils.encoding(value, "ISO-8859-1", "UTF-8");
    }

}
