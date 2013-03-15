/**
 * 
 */
package org.mspring.platform.persistence.query.converter;

import java.math.BigInteger;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class BigIntegerConverter implements Converter {

    /* (non-Javadoc)
     * @see org.mspring.platform.dao.query.converter.Converter#convert(java.lang.Class, java.lang.String)
     */
    public Object convert(Class type, String value) throws ConversionException {
        // TODO Auto-generated method stub
        return new BigInteger(value);
    }

}
