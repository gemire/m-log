/**
 * 
 */
package org.mspring.mlog.support.resolver;

import org.mspring.platform.utils.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Gao Youbo
 * @since 2013-3-7
 * @description
 * @TODO
 */
public class PathParamWebArgumentRequestResolver implements WebArgumentResolver {

    private ConversionService conversionService;

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, NativeWebRequest nativeWebRequest) throws Exception {
        // TODO Auto-generated method stub
        // PathParam pathParam =
        // parameter.getParameterAnnotation(PathParam.class);
        // if (pathParam == null) {
        // return WebArgumentResolver.UNRESOLVED;
        // }
        // if (StringUtils.isNotBlank(pathParam.defaultValue())) {
        // return pathParam.defaultValue();
        // }
        //
        // String paramName = parameter.getParameterName();
        // String value = nativeWebRequest.getParameter(paramName);
        // if (value == null) {
        // if (pathParam.required()) {
        // throw new Exception("parameter [" + paramName + "] is required.");
        // }
        // return value;
        // }
        // return StringUtils.encoding(value, "ISO-8859-1", "UTF-8");

        PathParam pathParam = parameter.getParameterAnnotation(PathParam.class);
        if (pathParam == null) {
            return WebArgumentResolver.UNRESOLVED;
        }
        if (StringUtils.isNotBlank(pathParam.defaultValue())) {
            return pathParam.defaultValue();
        }

        String value = nativeWebRequest.getParameter(parameter.getParameterName());
        if (value == null) {
            if (pathParam.required()) {
                throw new Exception("parameter [" + parameter.getParameterName() + "] is required.");
            }
            return value;
        }

        value = StringUtils.encoding(value, "ISO-8859-1", "UTF-8");

        Class<?> requiredType = parameter.getParameterType();
        if (conversionService.canConvert(String.class, requiredType)) {
            return conversionService.convert(value, TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(requiredType));
        }
        return WebArgumentResolver.UNRESOLVED;
    }

}
