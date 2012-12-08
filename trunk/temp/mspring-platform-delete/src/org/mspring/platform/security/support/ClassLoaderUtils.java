package org.mspring.platform.security.support;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ClassLoaderUtils {
    public static URL getResource(String resourceName, Class callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = ClassLoaderUtils.class.getClassLoader().getResource(resourceName);
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();
            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if ((url == null) && (resourceName != null) && (resourceName.charAt(0) != '/')) {
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }

    public static InputStream getResourceAsStream(String resourceName, Class callingClass) {
        URL url = getResource(resourceName, callingClass);
        try {
            return url != null ? url.openStream() : null;
        } catch (IOException e) {
        }
        return null;
    }

    public static Class loadClass(String className, Class callingClass) throws ClassNotFoundException {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException ex) {
                try {
                    return ClassLoaderUtils.class.getClassLoader().loadClass(className);
                } catch (ClassNotFoundException exc) {
                }
            }
        }
        return callingClass.getClassLoader().loadClass(className);
    }

    public static Object instantiate(String className, Class callingClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return instantiate(loadClass(className, callingClass));
    }

    public static Object instantiate(Class clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}