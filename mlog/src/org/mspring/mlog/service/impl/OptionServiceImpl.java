/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Option;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.web.Keys;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.CacheUtils;
import org.mspring.platform.utils.StringUtils;
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
        if (option != null) {
            value = ((Option) option).getValue();
            // 更新缓存
            setOptionCacheValue(key, value);
            return value;
        }
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#getOptions()
     */
    @Override
    public Map<String, String> getOptions() {
        // TODO Auto-generated method stub
        //先从缓存中查找
        Map<String, String> cachedMap = getOptionCacheMap();
        if (cachedMap != null) {
            return cachedMap;
        }
        
        //如果缓存中没有找到
        Map<String, String> map = new HashMap<String, String>();
        List<Option> options = find("select option from Option option");
        if (options != null && options.size() > 0) {
            for (Option option : options) {
                map.put(option.getName(), option.getValue());
            }
        }
        //更新缓存内容
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
        return CacheUtils.getStringValue(CacheManager.getInstance(), Keys.OPTION_CACHE_NAME, key);
    }

    private void setOptionCacheValue(String key, String value) {
        CacheUtils.updateValue(CacheManager.getInstance(), Keys.OPTION_CACHE_NAME, key, value);
        setOptionCacheMap(null);
    }

    private Map<String, String> getOptionCacheMap() {
        Object map = CacheUtils.getObjectValue(CacheManager.getInstance(), Keys.OPTION_CACHE_NAME, Keys.OPTION_CACHE_MAP_KEY);
        return map != null ? (Map<String, String>)map : null;
    }
    
    private void setOptionCacheMap(Map<String, String> map){
        CacheUtils.updateValue(CacheManager.getInstance(), Keys.OPTION_CACHE_NAME, Keys.OPTION_CACHE_MAP_KEY, map);
    }

}
