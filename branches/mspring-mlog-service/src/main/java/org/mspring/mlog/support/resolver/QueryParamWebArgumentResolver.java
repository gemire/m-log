/**
 * 
 */
package org.mspring.mlog.support.resolver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.platform.utils.RequestUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Gao Youbo
 * @since 2012-8-5
 * @Description
 * @TODO
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class QueryParamWebArgumentResolver implements WebArgumentResolver {
    private static final Logger log = Logger.getLogger(QueryParamWebArgumentResolver.class);
    private ConversionService conversionService;

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.bind.support.WebArgumentResolver#resolveArgument
     * (org.springframework.core.MethodParameter,
     * org.springframework.web.context.request.NativeWebRequest)
     */

    @Override
    public Object resolveArgument(MethodParameter parameter, NativeWebRequest nativeWebRequest) throws Exception {
        // TODO Auto-generated method stub
        QueryParam queryParam = parameter.getParameterAnnotation(QueryParam.class);
        if (queryParam == null) {
            return WebArgumentResolver.UNRESOLVED;
        }
        Map requestParams = nativeWebRequest.getParameterMap();
        Map queryParams = new HashMap();

        Iterator it;
        for (it = requestParams.keySet().iterator(); it.hasNext();) {
            String key = (String) it.next();
            String value = RequestUtils.getRequestParameter(requestParams, key);
            queryParams.put(key, value);
            log.debug("set queryParameter : " + key + " = " + value);
        }
        return queryParams;
    }

}
