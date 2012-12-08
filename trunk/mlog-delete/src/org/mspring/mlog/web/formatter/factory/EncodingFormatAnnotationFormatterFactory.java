/**
 * 
 */
package org.mspring.mlog.web.formatter.factory;

import java.util.HashSet;
import java.util.Set;

import org.mspring.mlog.web.formatter.EncodingFotmatter;
import org.mspring.mlog.web.formatter.stereotype.EncodingFormat;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 * @author Gao Youbo
 * @since 2012-8-1
 * @Description
 * @TODO
 */
public class EncodingFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<EncodingFormat> {

    private final Set<Class<?>> fieldTypes;
    private final EncodingFotmatter formatter;

    /**
     * 
     */
    public EncodingFormatAnnotationFormatterFactory() {
        // TODO Auto-generated constructor stub
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(String.class);
        this.fieldTypes = set;
        this.formatter = new EncodingFotmatter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.format.AnnotationFormatterFactory#getFieldTypes()
     */
    @Override
    public Set<Class<?>> getFieldTypes() {
        // TODO Auto-generated method stub
        return fieldTypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.format.AnnotationFormatterFactory#getParser(java.
     * lang.annotation.Annotation, java.lang.Class)
     */
    @Override
    public Parser<?> getParser(EncodingFormat format, Class<?> arg1) {
        // TODO Auto-generated method stub
        String encoding = format.encoding();
        formatter.setEncoding(encoding);
        return formatter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.format.AnnotationFormatterFactory#getPrinter(java
     * .lang.annotation.Annotation, java.lang.Class)
     */
    @Override
    public Printer<?> getPrinter(EncodingFormat format, Class<?> clazz) {
        // TODO Auto-generated method stub
        String encoding = format.encoding();
        formatter.setEncoding(encoding);
        return formatter;
    }

}
