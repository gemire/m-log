package org.mspring.mlog.api.dbank.nsp.support.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.phprpc.util.AssocArray;

/**
 * 将PHP反序列化得到的对象，转化为指定的Class或者Bean
 * 
 * @author root
 * 
 */
public class NSPWrapper {

    public static final Map<Class, Map<String, PropertyDescriptor>> CACHE = new HashMap<Class, Map<String, PropertyDescriptor>>(
            2);

    public static Object toBean(Object obj, Type type) {
        return convert(obj, type);
    }

    public static <T> T toBean(Object obj, Class<T> clazz) {
        return (T) convert(obj, clazz);
    }

    public static Type getReturnType() {
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();
        StackTraceElement e = stackElements[1];

        String cn = e.getClassName();
        String mn = e.getMethodName();
        try {
            Class clz = Class.forName(cn);
            Method method = null;
            Method[] methods = clz.getMethods();
            for (Method m : methods) {
                if (m.getName().equals(mn)) {
                    method = m;
                    break;
                }
            }
            return method.getGenericReturnType();
        } catch (Exception e1) {
        }
        return null;
    }

    private static Object convert(Object obj, Type type) {

        if (obj == null)
            return null;

        Class clazz = null;
        Type[] types = null;
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            if (ptype.getRawType() instanceof Class)
                clazz = (Class) ptype.getRawType();
            types = ptype.getActualTypeArguments();
        } else if (type instanceof GenericArrayType) {
            GenericArrayType ptype = (GenericArrayType) type;
            clazz = Object[].class;
            types = new Type[] { ptype.getGenericComponentType() };
        }

        if (clazz == null && type instanceof Class) {
            clazz = (Class) type;
        }

        if (clazz != null && clazz == String.class) {
            // byte[] 转 string
            if (obj instanceof byte[])
                return new String((byte[]) obj);
            // number 转 string
            if (obj instanceof Number)
                return String.valueOf((Number) obj);
        }

        // byte[], string 转 Long
        if (clazz != null && clazz == Long.class) {
            if (obj instanceof byte[])
                return Long.valueOf(new String((byte[]) obj));
            if (obj instanceof String)
                return Long.valueOf((String) obj);
        }

        // byte[], string 转 Integer
        if (clazz != null && clazz == Integer.class) {
            if (obj instanceof byte[])
                return Integer.valueOf(new String((byte[]) obj));
            if (obj instanceof String)
                return Integer.valueOf((String) obj);
        }

        // obj是简单数据类型（这里包含string）及其数组，并且与指定clazz吻合
        // 就可以直接转换
        Class objClz = obj.getClass();
        if (clazz == null || clazz.isInstance(obj) || obj instanceof AssocArray) {
            if (isSimple(objClz))
                return obj;
            if (objClz.isArray() && (objClz.getComponentType().isPrimitive() || isSimple(objClz.getComponentType())))
                return obj;

            if (clazz == null && obj instanceof AssocArray) {
                clazz = HashMap.class;
            }

            if (clazz != null) {
                // 如果clazz是一个集合，且obj与之相同或者是AssocArray
                if (Collection.class.isAssignableFrom(clazz) && (clazz.isInstance(obj) || obj instanceof AssocArray)) {

                    if (obj instanceof AssocArray)
                        obj = ((AssocArray) obj).toArrayList();

                    Collection d = null;
                    if (clazz.isInterface()) {
                        if (clazz == Set.class)
                            d = new HashSet();
                        else if (clazz == Queue.class)
                            d = new LinkedList();
                        else
                            d = new ArrayList();
                    } else {
                        d = (Collection) getInstance(clazz);
                    }
                    for (Object o : (Collection) obj) {
                        if (types != null && types.length == 1)
                            d.add(convert(o, types[0]));
                        else
                            d.add(convert(o, null));
                    }
                    return d;
                }

                // 如果clazz是一个Map，且obj与之相同或者是AssocArray
                if (Map.class.isAssignableFrom(clazz) && (clazz.isInstance(obj) || obj instanceof AssocArray)) {

                    if (obj instanceof AssocArray)
                        obj = ((AssocArray) obj).toLinkedHashMap();

                    Map d = null;
                    if (clazz.isInterface()) {
                        d = new LinkedHashMap();
                    } else {
                        d = (Map) getInstance(clazz);
                    }
                    for (Object o : ((Map) obj).entrySet()) {
                        Map.Entry e = (Map.Entry) o;
                        if (types != null && types.length == 2) {
                            // 如果是指定了泛型Map<T,S> ，则直接转换
                            d.put(convert(e.getKey(), types[0]), convert(e.getValue(), types[1]));
                            continue;
                        }

                        if (!clazz.isInterface()) {
                            String key = toBean(e.getKey(), String.class);
                            if (key != null) {
                                Type valType = getType(clazz, key);
                                if (valType != null) {
                                    try {
                                        Object val = convert(e.getValue(), valType);

                                        PropertyUtils.setSimpleProperty(d, key, val);
                                        continue;
                                    } catch (Exception e1) {
                                    }
                                }
                            }
                        }

                        d.put(convert(e.getKey(), null), convert(e.getValue(), null));

                    }
                    return d;
                }

                // 如果clazz是一个数组，且obj与之相同或者是AssocArray
                if (clazz.isArray() && (clazz.isInstance(obj) || obj instanceof AssocArray)) {
                    int length = 0;
                    if (obj instanceof AssocArray) {
                        length = ((AssocArray) obj).size();
                        obj = ((AssocArray) obj).toArrayList().toArray();
                    } else {
                        length = ((Object[]) obj).length;
                    }

                    if (length == 0)
                        return null;

                    Class ctype = clazz.getComponentType();
                    if (types != null && types.length == 1) {
                        if (types[0] instanceof ParameterizedType) {
                            ParameterizedType ptype = (ParameterizedType) types[0];
                            if (ptype.getRawType() instanceof Class)
                                ctype = (Class) ptype.getRawType();
                        } else if (types[0] instanceof GenericArrayType) {
                            GenericArrayType ptype = (GenericArrayType) types[0];
                            ctype = Object[].class;
                        } else {
                            ctype = (Class) types[0];
                        }
                    }

                    Object[] d = (Object[]) Array.newInstance(ctype, length);

                    Object[] s = (Object[]) obj;
                    for (int i = 0, j = s.length; i < j; i++) {
                        d[i] = convert(s[i], types != null && types.length == 1 ? types[0] : ctype);
                    }
                    return d;
                }

                // 如果clazz是一个Class，且obj是AssocArray
                if (!clazz.isInterface() && obj instanceof AssocArray) {

                    Object d = getInstance(clazz);
                    obj = ((AssocArray) obj).toLinkedHashMap();

                    for (Object o : ((Map) obj).entrySet()) {
                        Map.Entry e = (Map.Entry) o;
                        if (e == null || e.getKey() == null)
                            continue;

                        String name = null;
                        if (e.getKey() instanceof String)
                            name = (String) e.getKey();
                        else if (e.getKey() instanceof byte[])
                            name = new String((byte[]) e.getKey());

                        Type valType = getType(clazz, name);
                        if (valType != null) {
                            try {
                                Object val = convert(e.getValue(), valType);
                                PropertyUtils.setSimpleProperty(d, name, val);
                            } catch (Exception e1) {
                            }
                        }
                    }
                    return d;
                }
            }
        }
        // 默认直接返回
        return obj;
    }

    private static Type getType(Class clazz, String name) {
        Map<String, PropertyDescriptor> props = CACHE.get(clazz);
        if (props == null) {
            PropertyDescriptor[] descs = PropertyUtils.getPropertyDescriptors(clazz);
            if (descs == null)
                return null;

            props = new HashMap<String, PropertyDescriptor>();
            for (PropertyDescriptor d : descs)
                props.put(d.getName(), d);

            CACHE.put(clazz, props);
        }

        PropertyDescriptor pd = props.get(name);
        if (pd == null)
            return null;

        Method writeMethod = PropertyUtils.getWriteMethod(pd);
        if (writeMethod == null)
            return null;

        Type[] ptypes = writeMethod.getGenericParameterTypes();
        if (ptypes != null && ptypes.length > 0)
            return ptypes[0];

        return null;
    }

    private static Object getInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
        }
        return null;
    }

    private static boolean isSimple(Class c) {
        return c == Boolean.class || c == Character.class || c == Byte.class || c == Short.class || c == Integer.class
                || c == Long.class || c == Float.class || c == Double.class || c == String.class;
    }

}
