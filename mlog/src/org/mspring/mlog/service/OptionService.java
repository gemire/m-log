/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;
import java.util.Map;

import org.mspring.mlog.entity.Option;
import org.mspring.platform.common.KeyValue;

/**
 * @author Gao Youbo
 * @since Jan 12, 2012
 */
public interface OptionService {
    public static final String OPTIONS_CACHE_KEY = "mspring_options_cache_key";

    public String getOption(String key);
    
    /**
     * 该方法将不进入数据库查询缓存
     * @param key
     */
    public String _getOption(String key);

    public Map<String, String> getOptionsByPrefix(String prefix);

    public void setOption(String key, String value);

    public Map<String, String> getOptions();

    public void setOption(Option option);

    public void setOptions(List<Option> options);

    public void setOptions(Map<String, String> options);

    public void setJSONOption(String key, List<KeyValue> keyValues);

    public void setJSONOptions(Map<String, List<KeyValue>> options);

    public List<KeyValue> getJSONOption(String key);

    public Map<String, String> findAlbumConfig();

    public Map<String, String> findWebConfig();

    public Map<String, String> findBaseWebConfig();

    public Map<String, String> findGlobalWebConfig();

    public Map<String, String> findSeoWebConfig();

    public Map<String, String> findSystemWebConfig();
}
