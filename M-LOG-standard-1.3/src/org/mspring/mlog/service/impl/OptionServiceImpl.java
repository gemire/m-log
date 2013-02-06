/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Option;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.cache.CacheService;
import org.mspring.mlog.service.cache.OptionCacheService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.PropertiesUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-23
 * @Description
 * @TODO
 */
@Service
@Transactional
public class OptionServiceImpl extends AbstractServiceSupport implements OptionService {

    private static final Logger log = Logger.getLogger(OptionServiceImpl.class);

    private static final String DEFAULT_OPTIONS_PROPERTIES = "default_options.properties";

    @Autowired
    private OptionCacheService optionCacheService;

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#getOption(java.lang.String)
     */
    @Override
    public String getOption(final String key) {
        // TODO Auto-generated method stub
        // 先从缓存中获取
        String value = getOptionCacheValue(key);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }

        // 如果缓存中没有找到，从数据库中查询
        Object option = findUnique("select option from Option option where option.name = ?", key);
        if (option == null) { // 如果数据库中没有找到，那么从default_options.properties中查找默认值
            value = getDefaultOptions().get(key);
        } else {
            value = ((Option) option).getValue();
        }
        // 更新缓存
        setOptionCacheValue(key, value);
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#getOptions()
     */
    @Override
    public Map<String, String> getOptions() {
        // TODO Auto-generated method stub
        // 先从缓存中查找
        Map<String, String> cachedMap = getOptionCacheMap();
        if (cachedMap != null) {
            return cachedMap;
        }

        // 如果缓存中没有找到
        Map<String, String> map = getDefaultOptions();// 初始化为默认配置
        List<Option> options = find("select option from Option option");
        if (options != null && options.size() > 0) {
            for (Option option : options) {
                // if (StringUtils.isNotBlank(option.getValue())) {
                map.put(option.getName(), option.getValue());
                // }
            }
        }
        // 更新缓存内容
        setOptionCacheMap(map);
        return map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.OptionService#setOption(org.mspring.mlog.entity
     * .Option)
     */
    @Override
    public void setOption(Option option) {
        // TODO Auto-generated method stub
        if (StringUtils.isBlank(option.getName())) {
            log.warn("can't update option, key can't be null");
            return;
        }
        merge(option);

        // 更新、新增Option缓存
        setOptionCacheValue(option.getName(), option.getValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#setOption(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void setOption(String key, String value) {
        // TODO Auto-generated method stub
        setOption(new Option(key, value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#setOptions(java.util.List)
     */
    @Override
    public void setOptions(List<Option> options) {
        // TODO Auto-generated method stub
        for (Option option : options) {
            setOption(option);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#setOptions(java.util.Map)
     */
    @Override
    public void setOptions(Map<String, String> options) {
        // TODO Auto-generated method stub
        for (Map.Entry<String, String> entry : options.entrySet()) {
            setOption(entry.getKey(), entry.getValue());
        }
    }

    private String getOptionCacheValue(String key) {
        Object value = optionCacheService.getOptionCacheValue(key);
        return value == null ? null : value.toString();
    }

    private void setOptionCacheValue(String key, String value) {
        optionCacheService.updateOptionCacheValue(key, value, CacheService.ONE_DAY);
        setOptionCacheMap(null);
    }

    private Map<String, String> getOptionCacheMap() {
        Object value = optionCacheService.getOptionCacheValue(OPTION_CACHE_MAP_KEY);
        return value != null ? (Map<String, String>) value : null;
    }

    private void setOptionCacheMap(Map<String, String> map) {
        optionCacheService.updateOptionCacheValue(OPTION_CACHE_MAP_KEY, map, CacheService.ONE_DAY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.OptionService#getPropertiesOption(java.lang.
     * String)
     */
    @Override
    public String getPropertiesOption(String key) {
        // TODO Auto-generated method stub
        Map<String, String> map = PropertiesUtils.getPropertyMap(DEFAULT_OPTIONS_PROPERTIES);
        String value = map.get(key);
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.OptionService#setPropertiesOption(java.lang.
     * String, java.lang.String)
     */
    @Override
    public void setPropertiesOption(String key, String value) {
        // TODO Auto-generated method stub
        PropertiesUtils.setProperties(key, value, DEFAULT_OPTIONS_PROPERTIES);
    }

    /**
     * 获取默认配置
     * 
     * @return
     */
    private Map<String, String> getDefaultOptions() {
        try {
            log.debug("get default options");
            Object cached = optionCacheService.getOptionCacheValue(DEFAULT_OPTIOINS_KEY);
            if (cached != null && cached instanceof Map) {
                return (Map<String, String>) cached;
            }
            Map<String, String> map = PropertiesUtils.getPropertyMap(DEFAULT_OPTIONS_PROPERTIES);
            optionCacheService.updateOptionCacheValue(DEFAULT_OPTIOINS_KEY, map, CacheService.ONE_DAY);
            return map;
        } catch (Exception e) {
            // TODO: handle exception
            log.error("get default options error", e);
            return new HashMap<String, String>();
        }
    }

}