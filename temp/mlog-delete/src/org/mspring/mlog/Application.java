/**
 * 
 */
package org.mspring.mlog;

import org.apache.commons.lang.SystemUtils;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
public class Application {
    public static final String PRODUCT_NAME = "MLOG";
    public static final String ALIAS_NAME = "mlog";
    public static final String VERSION = "1.0";
    public static final String LICENSE = "GNU Affero Genral Public License (AGPL3)";
    public static final String HOME_PAGE = "http://www.mspring.org";
    public static final String HOME_PAGE_NAME = "慕春博客";

    // operating system properties
    public static final String OS_NAME = SystemUtils.OS_NAME;
    public static final String OS_ARCH = SystemUtils.OS_ARCH;
    public static final String OS_VERVION = SystemUtils.OS_VERSION;
    //
    public static final String JAVA_HOME = SystemUtils.JAVA_HOME;
    public static final String JAVA_TMPDIR = SystemUtils.JAVA_IO_TMPDIR;
    public static final String JAVA_VENDOR = SystemUtils.JAVA_VENDOR;
    public static final String JAVA_VERSION = SystemUtils.JAVA_VERSION;

    private static Application instance = new Application();

    private Application() {
    }

    public static Application getInstance() {
        return instance;
    }

    public String getProductName() {
        return PRODUCT_NAME;
    }

    public String getAliasName() {
        return ALIAS_NAME;
    }

    public String getLicense() {
        return LICENSE;
    }

    public String getVersion() {
        return VERSION;
    }
    
    public String getHomePage(){
        return HOME_PAGE;
    }
    
    public String getHomePageName(){
        return HOME_PAGE_NAME;
    }

    public String getOsArch() {
        return OS_ARCH;
    }

    public String getOsName() {
        return OS_NAME;
    }

    public String getOsVersion() {
        return OS_VERVION;
    }

    public String getJavaHome() {
        return JAVA_HOME;
    }

    public String getJavaTmpdir() {
        return JAVA_TMPDIR;
    }

    public String getJavaVendor() {
        return JAVA_VENDOR;
    }

    public String getJavaVersion() {
        return JAVA_VERSION;
    }
}
