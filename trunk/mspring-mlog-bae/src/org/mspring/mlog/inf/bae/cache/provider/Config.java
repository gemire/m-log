/**
 * 
 */
package org.mspring.mlog.inf.bae.cache.provider;

import java.util.Properties;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description
 * @TODO
 */
public class Config {
    /**
     * 配置文件的前缀
     */
    public static final String PROP_PREFIX = "hibernate.baecache.";

    /**
     * cache配置文件
     */
    private static final String CACHE_CONFIGURATION_LOCATION = "configLocation";
    public static final String PROP_CACHE_CONFIGURATION_LOCATION = PROP_PREFIX + CACHE_CONFIGURATION_LOCATION;

    private Properties props;

    /**
     * 
     */
    public Config(Properties props) {
        // TODO Auto-generated constructor stub
        this.props = props;
    }

    /**
     * 获取配置文件路径
     * 
     * @return
     */
    public String getConfigLocation() {
        return props.getProperty(PROP_CACHE_CONFIGURATION_LOCATION);
    }
}
