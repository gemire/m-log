/**
 * 
 */
package org.mspring.mlog;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
public class Application {
    public static final String PRODUCT_NAME = "M-LOG";
    public static final String ALIAS_NAME = "mlog";
    public static final String VERSION = "1.2";
    public static final String LICENSE = "Apache License Version 2.0";
    public static final String HOME_PAGE = "http://www.mspring.org";
    public static final String HOME_PAGE_NAME = "M-Spring";

    public static final String DEFAULT_ENCODING = "UTF-8";

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

    public String getHomePage() {
        return HOME_PAGE;
    }

    public String getHomePageName() {
        return HOME_PAGE_NAME;
    }

    /**
     * 获取默认编码
     * 
     * @return
     */
    public static String getDefaultEncoding() {
        return DEFAULT_ENCODING;
    }

}
