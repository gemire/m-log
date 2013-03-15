/**
 * 
 */
package org.mspring.platform.utils;

import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.collections.FastHashMap;

/**
 * @author Gao Youbo
 * @since 2012-9-11
 * @Description
 * @TODO
 */
public class BeanUtils {
    private static final char INDEXED_DELIM = '[';
    private static final char MAPPED_DELIM = '(';
    private static final char NESTED_DELIM = '.';
    private static FastHashMap descriptorsCache = null;
    private static FastHashMap mappedDescriptorsCache = null;

    private static Set notWritableType = null;

    private static final void registerNotWritableType() {
        notWritableType = new HashSet();

        notWritableType.add(Collection.class);
        notWritableType.add(Map.class);
    }

    private static boolean canWrite(Class type) {
        if (type == null) {
            return false;
        }

        for (Iterator it = notWritableType.iterator(); it.hasNext();) {
            Class clazz = (Class) it.next();
            if ((clazz.isAssignableFrom(type)) || (type.isArray())) {
                return false;
            }
        }
        return true;
    }

    public static void copySingleProperties(Object target, Object source) throws IllegalArgumentException {
        copySingleProperties(target, source, null);
    }

    public static void copySingleProperties(Object target, Object source, String[] ignoreProperties) throws IllegalArgumentException {
        Assert.notNull(target, "No target bean specified");
        Assert.notNull(source, "No source bean specified");

        List ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        PropertyDescriptor[] origDescriptors = getPropertyDescriptors(source);
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            Class type = origDescriptors[i].getPropertyType();

            if (((ignoreProperties != null) && (ignoreList.contains(name))) || (!canWrite(type)) || (!isReadable(source, name)) || (!isWriteable(target, name))) continue;
            Object value = getSimpleProperty(source, name);
            setSimpleProperty(target, name, value);
        }
    }

    public static Object getSimpleProperty(Object bean, String name) {
        Assert.notNull(bean, "No bean specified");
        Assert.notNull(name, "No name specified");

        Assert.notContain(name, '.', "Nested property names are not allowed");
        Assert.notContain(name, '[', "Indexed property names are not allowed");
        Assert.notContain(name, '(', "Mapped property names are not allowed");

        PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
        if (descriptor == null) {
            throw new RuntimeException("NoSuchMethod property '" + name + "'");
        }
        Method readMethod = getReadMethod(descriptor);
        if (readMethod == null) {
            throw new RuntimeException("NoSuchMethod Property '" + name + "' has no getter method");
        }

        return invokeMethod(readMethod, bean, new Object[0]);
    }

    public static void setSimpleProperty(Object bean, String name, Object value) {
        Assert.notNull(bean, "No bean specified");
        Assert.notNull(name, "No name specified");

        Assert.notContain(name, '.', "Nested property names are not allowed");
        Assert.notContain(name, '[', "Indexed property names are not allowed");
        Assert.notContain(name, '(', "Mapped property names are not allowed");

        PropertyDescriptor descriptor = getPropertyDescriptor(bean, name);
        if (descriptor == null) {
            throw new RuntimeException("NoSuchMethod Unknown property '" + name + "'");
        }
        Method writeMethod = getWriteMethod(descriptor);
        if (writeMethod == null) {
            throw new RuntimeException("NoSuchMethod Property '" + name + "' has no setter method");
        }

        Object[] values = new Object[1];
        values[0] = value;
        invokeMethod(writeMethod, bean, values);
    }

    private static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
        Assert.notNull(bean, "No bean specified");
        return getPropertyDescriptors(bean.getClass());
    }

    private static PropertyDescriptor[] getPropertyDescriptors(Class beanClass) {
        Assert.notNull(beanClass, "No bean class specified");

        PropertyDescriptor[] descriptors = null;
        descriptors = (PropertyDescriptor[]) (PropertyDescriptor[]) descriptorsCache.get(beanClass);
        if (descriptors != null) {
            return descriptors;
        }

        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(beanClass);
        }
        catch (IntrospectionException e) {
            return new PropertyDescriptor[0];
        }
        descriptors = beanInfo.getPropertyDescriptors();
        if (descriptors == null) {
            descriptors = new PropertyDescriptor[0];
        }
        descriptorsCache.put(beanClass, descriptors);
        return descriptors;
    }

    private static PropertyDescriptor getPropertyDescriptor(Object bean, String name) {
        Assert.notNull(bean, "No bean specified");
        Assert.notNull(name, "No name specified");

        PropertyDescriptor[] descriptors = getPropertyDescriptors(bean);
        if (descriptors != null) {
            for (int i = 0; i < descriptors.length; i++) {
                if (name.equals(descriptors[i].getName())) {
                    return descriptors[i];
                }
            }
        }
        return null;
    }

    private static boolean isReadable(Object bean, String name) {
        Assert.notNull(bean, "No bean specified");
        Assert.notNull(name, "No name specified");

        PropertyDescriptor desc = getPropertyDescriptor(bean, name);
        if (desc != null) {
            Method readMethod = desc.getReadMethod();
            if ((readMethod == null) && ((desc instanceof IndexedPropertyDescriptor))) {
                readMethod = ((IndexedPropertyDescriptor) desc).getIndexedReadMethod();
            }
            return readMethod != null;
        }
        return false;
    }

    private static boolean isWriteable(Object bean, String name) {
        Assert.notNull(bean, "No bean specified");
        Assert.notNull(name, "No name specified");

        PropertyDescriptor desc = getPropertyDescriptor(bean, name);
        if (desc != null) {
            Method writeMethod = desc.getWriteMethod();
            if ((writeMethod == null) && ((desc instanceof IndexedPropertyDescriptor))) {
                writeMethod = ((IndexedPropertyDescriptor) desc).getIndexedWriteMethod();
            }
            return writeMethod != null;
        }
        return false;
    }

    private static Method getReadMethod(PropertyDescriptor descriptor) {
        return MethodUtils.getAccessibleMethod(descriptor.getReadMethod());
    }

    private static Method getWriteMethod(PropertyDescriptor descriptor) {
        return MethodUtils.getAccessibleMethod(descriptor.getWriteMethod());
    }

    private static Object invokeMethod(Method method, Object bean, Object[] values) {
        try {
            return method.invoke(bean, values);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cannot invoke " + method.getDeclaringClass().getName() + "." + method.getName() + " - " + e.getMessage());
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        descriptorsCache = new FastHashMap();
        descriptorsCache.setFast(true);
        mappedDescriptorsCache = new FastHashMap();
        mappedDescriptorsCache.setFast(true);

        registerNotWritableType();
    }
}