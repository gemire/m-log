package org.mspring.platform.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 5, 2011
 */
public class PropertiesUtils {
    private static final Logger logger = Logger.getLogger(PropertiesUtils.class);

    public static Properties loadProperty(String classPathFileName) {
        ClassPathResource cpr = new ClassPathResource(classPathFileName);
        File file = null;
        try {
            file = cpr.getFile();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            logger.warn("can not find file [" + classPathFileName + "] in classpath");
            e.printStackTrace();
        }
        return loadProperty(file);
    }

    public static Properties loadProperty(File file) {
        Properties props = null;
        InputStream inStream;
        try {
            inStream = new FileInputStream(file);
            props = loadProperty(inStream);
            inStream.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            logger.warn("can not find file [" + file.getAbsolutePath() + "]", e);
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            logger.warn("read file [" + file.getAbsolutePath() + "] in classpath", e);
            e.printStackTrace();
        }
        return props;
    }

    public static Properties loadProperty(InputStream inStream) {
        Properties props = new Properties();
        try {
            props.load(inStream);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return props;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getPropertyMap(String classPathFileName) {
        Properties props = loadProperty(classPathFileName);

        Assert.notNull(props);

        Map<String, String> retMap = new HashMap<String, String>();
        for (Map.Entry entry : props.entrySet()) {
            retMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return retMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getPropertyMap(File file) {
        Properties props = loadProperty(file);

        Assert.notNull(props);

        Map<String, String> retMap = new HashMap<String, String>();
        for (Map.Entry entry : props.entrySet()) {
            retMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return retMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getPropertyMap(InputStream inStream) {
        Properties props = loadProperty(inStream);

        Assert.notNull(props);

        Map<String, String> retMap = new HashMap<String, String>();
        for (Map.Entry entry : props.entrySet()) {
            retMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return retMap;
    }

    public static void setPropertyMap(String classPathFileName, Map<String, String> map) {
        Properties props = loadProperty(classPathFileName);

        Assert.notNull(props);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            props.setProperty(entry.getKey(), entry.getValue());
        }

        ClassPathResource cpr = new ClassPathResource(classPathFileName);
        File file = null;
        try {
            file = cpr.getFile();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            logger.warn("read file [" + classPathFileName + "] in classpath", e);
            e.printStackTrace();
        }
        saveProperties(props, file);
    }

    public static void saveProperties(Properties props, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            props.store(out, "");
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            logger.warn("can not find file [" + file.getAbsolutePath() + "]", e);
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            logger.warn("read file [" + file.getAbsolutePath() + "]", e);
            e.printStackTrace();
        }
    }

    public static void saveProperties(Properties props, String classPathFileName) {
        ClassPathResource cpr = new ClassPathResource(classPathFileName);
        try {
            File file = cpr.getFile();
            saveProperties(props, file);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取properties
     * 
     * @param key
     * @param propertiesInClassPath
     */
    public static String getProperty(String key, String propertiesInClassPath) {
        Properties p = loadProperty(propertiesInClassPath);
        String value = p.getProperty(key);
        return value == null ? "" : value;
    }

    /**
     * 设置
     * @param key
     * @param value
     * @param propertiesInClassPath
     */
    public static void setProperties(String key, String value, String propertiesInClassPath) {
        Properties p = loadProperty(propertiesInClassPath);
        p.setProperty(key, value);
        saveProperties(p, propertiesInClassPath);
    }
}
