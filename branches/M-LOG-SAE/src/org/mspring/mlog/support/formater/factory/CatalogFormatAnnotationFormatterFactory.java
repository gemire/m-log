/**
 * 
 */
package org.mspring.mlog.support.formater.factory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.support.formater.CatalogFormatter;
import org.mspring.mlog.support.formater.stereotype.CatalogFormat;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 * @author Gao Youbo
 * @since 2012-8-6
 * @Description
 * @TODO
 */
public class CatalogFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<CatalogFormat> {

    private final Set<Class<?>> fieldTypes;
    private final CatalogFormatter formatter;

    /**
     * 
     */
    public CatalogFormatAnnotationFormatterFactory() {
        // TODO Auto-generated constructor stub
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(Catalog.class);
        set.add(List.class);
        set.add(Set.class);
        this.fieldTypes = set;
        this.formatter = new CatalogFormatter();
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
     * org.springframework.format.AnnotationFormatterFactory#getPrinter(java
     * .lang.annotation.Annotation, java.lang.Class)
     */
    @Override
    public Printer<?> getPrinter(CatalogFormat paramA, Class<?> paramClass) {
        // TODO Auto-generated method stub
        return formatter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.format.AnnotationFormatterFactory#getParser(java.
     * lang.annotation.Annotation, java.lang.Class)
     */
    @Override
    public Parser<?> getParser(CatalogFormat paramA, Class<?> paramClass) {
        // TODO Auto-generated method stub
        return formatter;
    }

}
