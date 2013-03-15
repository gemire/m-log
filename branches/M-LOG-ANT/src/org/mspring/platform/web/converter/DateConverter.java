/**
 * 
 */
package org.mspring.platform.web.converter;

import java.util.Date;

import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
public class DateConverter implements Converter<String, Date> {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private String pattern = DEFAULT_DATE_PATTERN;

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang
     * .Object)
     */
    public Date convert(String dateString) {
        // TODO Auto-generated method stub
        if (StringUtils.isNotBlank(dateString)) {
            return DateUtils.parse(dateString, pattern);
        }
        return new Date();
    }

}
