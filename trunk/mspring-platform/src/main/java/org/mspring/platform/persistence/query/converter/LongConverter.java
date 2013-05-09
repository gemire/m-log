/**
 * 
 */
package org.mspring.platform.persistence.query.converter;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class LongConverter implements Converter {

    /* (non-Javadoc)
     * @see org.mspring.platform.dao.query.converter.Converter#convert(java.lang.Class, java.lang.String)
     */
    public Object convert(Class type, String value) throws ConversionException {
        // TODO Auto-generated method stub
        return Long.valueOf(value);
    }

}
