/**
 * 
 */
package org.mspring.mlog.support.resolver;

import org.mspring.platform.utils.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Gao Youbo
 * @since 2013-3-7
 * @description
 * @TODO
 */
public class QueryParamHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private ConversionService conversionService;

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // TODO Auto-generated method stub
        PathParam pathParam = parameter.getParameterAnnotation(PathParam.class);
        String value = webRequest.getParameter(parameter.getParameterName());
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
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // TODO Auto-generated method stub
        return parameter.hasParameterAnnotation(PathParam.class);
    }

}
