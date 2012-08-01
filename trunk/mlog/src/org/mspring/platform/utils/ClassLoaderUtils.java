/**
 * Feb 20, 201111:23:08 AM
 * gblog-core
 * org.gaoyoubo.core.util
 * ClassLoaderUtils.java
 * @author gaoyb
 */
package org.mspring.platform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author gaoyb TODO
 * 
 */
public class ClassLoaderUtils {
    public static URL getResource(String resourceName, Class callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = ClassLoaderUtils.class.getClassLoader().getResource(resourceName);
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();
            if (cl != null)
                url = cl.getResource(resourceName);
        }

        if ((url == null) && (resourceName != null) && (resourceName.charAt(0) != '/')) {
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

    public static InputStream getResourceAsStream(String resourceName, Class callingClass) {
        URL url = getResource(resourceName, callingClass);
        try {
            return ((url != null) ? url.openStream() : null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class loadClass(String className, Class callingClass) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                try {
                    return ClassLoaderUtils.class.getClassLoader().loadClass(className);
                } catch (ClassNotFoundException exc) {
                    try {
                        return callingClass.getClassLoader().loadClass(className);
                    } catch (ClassNotFoundException e1) {
                        throw new RuntimeException(e1);
                    }
                }
            }
        }
    }

    public static Object instantiate(String className, Class callingClass) {
        return instantiate(loadClass(className, callingClass));
    }

    public static Object instantiate(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printClassLoader() {
        System.out.println("ClassLoaderUtil.printClassLoader");
        printClassLoader(Thread.currentThread().getContextClassLoader());
    }

    public static void printClassLoader(ClassLoader cl) {
        System.out.println("ClassLoaderUtil.printClassLoader(cl = " + cl + ")");
        if (cl != null)
            printClassLoader(cl.getParent());
    }
}
