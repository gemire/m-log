/**
 * 
 */
package org.mspring.platform.persistence.query.converter;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
@SuppressWarnings({"rawtypes"})
public abstract interface Converter {
    public Object convert(Class type, String value) throws ConversionException;
}
