/**
 * 
 */
package org.mspring.platform.persistence.query.converter;

import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-9-20
 * @Description 
 * @TODO 
 */
@SuppressWarnings({ "rawtypes" })
public class BooleanConverter implements Converter {

    /* (non-Javadoc)
     * @see org.mspring.platform.persistence.query.converter.Converter#convert(java.lang.Class, java.lang.String)
     */
    public Object convert(Class type, String value) throws ConversionException {
        // TODO Auto-generated method stub
        if (StringUtils.isNotBlank(value)) {
            if ("1".equals(value) || "true".equals(value.toLowerCase())) {
                return true;
            }
            else if ("0".equals(value) || "false".equals(value.toLowerCase())) {
                return false;
            }
        }
        return false;
    }

}
