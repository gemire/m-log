/**
 * 
 */
package org.mspring.platform.persistence.query.converter;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class ConverterManager {
    private static final String DEFAULT_CONVERTER = "default_converter";
    private static final Map converters = new HashMap();

    static {
        converters.put(DEFAULT_CONVERTER, new DefaultConverter());
        converters.put(BigInteger.class, new BigIntegerConverter());
        converters.put(Date.class, new DateConverter());
        converters.put(Integer.class, new IntegerConverter());
        converters.put(Long.class, new LongConverter());
        converters.put(Boolean.class, new BooleanConverter());
    }

    public static Converter lookup(Class clazz) {
        Converter converter = (Converter) converters.get(clazz);
        if (converter == null) {
            return (Converter) converters.get(DEFAULT_CONVERTER);
        }
        return converter;
    }
}
