/**
 * 
 */
package org.mspring.mlog.api.bae.cache.support;

import java.io.Serializable;

import org.mspring.mlog.api.bae.cache.CacheServiceImpl;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description xml配置文件对应的javabean
 * @TODO
 */
public class CacheBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4119039518552645483L;
    
    public static final long DEFAULT_CACHE_TIME_SECONDS = 1 * CacheServiceImpl.ONE_MINUTE;

    private String name;
    private long timeToIdleSeconds;
    
    /**
     * 
     */
    public CacheBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     * @param timeToIdleSeconds
     */
    public CacheBean(String name, long timeToIdleSeconds) {
        super();
        this.name = name;
        this.timeToIdleSeconds = timeToIdleSeconds;
    }

    /**
     * @param name
     * @param timeToIdleSeconds
     */
    public CacheBean(String name, String timeToIdleSeconds) {
        super();
        this.name = name;
        if (StringUtils.isBlank(timeToIdleSeconds) || !ValidatorUtils.isNumber(timeToIdleSeconds.trim())) {
            this.timeToIdleSeconds = DEFAULT_CACHE_TIME_SECONDS;
        }
        else {
            this.timeToIdleSeconds = new Long(timeToIdleSeconds.trim());
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the timeToIdleSeconds
     */
    public long getTimeToIdleSeconds() {
        return timeToIdleSeconds;
    }

    /**
     * @param timeToIdleSeconds
     *            the timeToIdleSeconds to set
     */
    public void setTimeToIdleSeconds(long timeToIdleSeconds) {
        this.timeToIdleSeconds = timeToIdleSeconds;
    }

}
