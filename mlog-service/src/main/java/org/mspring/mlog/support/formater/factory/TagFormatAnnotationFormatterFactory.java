/**
 * 
 */
package org.mspring.mlog.support.formater.factory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.support.formater.TagFormatter;
import org.mspring.mlog.support.formater.stereotype.TagFormat;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO
 */
public class TagFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<TagFormat> {// ①指定可以解析/格式化的字段注解类型

    private final Set<Class<?>> fieldTypes;
    private final TagFormatter formatter;

    /**
     * 
     */
    public TagFormatAnnotationFormatterFactory() {
        // TODO Auto-generated constructor stub
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(Tag.class);
        set.add(List.class);
        set.add(Set.class);
        this.fieldTypes = set;
        this.formatter = new TagFormatter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.format.AnnotationFormatterFactory#getFieldTypes()
     * 
     * @description ②指定可以被解析/格式化的字段类型集合
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
     * 
     * @description ③根据注解信息和字段类型获取解析器
     */
    @Override
    public Printer<?> getPrinter(TagFormat paramA, Class<?> paramClass) {
        // TODO Auto-generated method stub
        return formatter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.format.AnnotationFormatterFactory#getParser(java.
     * lang.annotation.Annotation, java.lang.Class)
     * 
     * @description ④根据注解信息和字段类型获取格式化器
     */
    @Override
    public Parser<?> getParser(TagFormat paramA, Class<?> paramClass) {
        // TODO Auto-generated method stub
        return formatter;
    }

}
