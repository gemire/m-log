/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;
import java.util.Map;

import org.mspring.mlog.entity.Option;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
public interface OptionService {
    // 保存所有Option缓存的key
    public static final String OPTION_CACHE_MAP_KEY = "OPTION_CACHE_MAP_KEY";

    // 保存默认配置的缓存KEY
    public static final String DEFAULT_OPTIOINS_KEY = "DEFAULT_OPTIONS_KEY";

    public String getOption(String key);

    public Map<String, String> getOptions();

    public void setOption(String key, String value);

    public void setOption(Option option);

    public void setOptions(List<Option> options);

    public void setOptions(Map<String, String> options);

    /**
     * 从default_options.properties中获取值，这些值都不加入缓存
     * 
     * @param key
     */
    public String getPropertiesOption(String key);

    /**
     * 设置default_options.properties中的值
     * 
     * @param key
     * @param value
     */
    public void setPropertiesOption(String key, String value);
}
