/**
 * 
 */
package org.mspring.platform.dao.query.converter;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public abstract interface Converter {
    public Object convert(Class type, String value) throws ConversionException;
}
