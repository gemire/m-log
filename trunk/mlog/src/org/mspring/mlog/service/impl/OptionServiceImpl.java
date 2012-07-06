/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.dao.OptionDao;
import org.mspring.mlog.entity.Option;
import org.mspring.mlog.service.OptionService;
import org.mspring.platform.common.KeyValue;
import org.mspring.platform.utils.JSONUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;

/**
 * @author Gao Youbo
 * @since Jan 12, 2012
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {
    private static final Logger log = Logger.getLogger(OptionServiceImpl.class);

    private OptionDao optionDao;

    @Autowired
    public void setOptionDao(OptionDao optionDao) {
        this.optionDao = optionDao;
    }

    private List<Option> Map2Options(Map<String, String> map) {
        List<Option> list = new ArrayList<Option>();
        Option option = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            option = new Option(entry.getKey(), entry.getValue());
            list.add(option);
        }
        return list;
    }

    private Map<String, String> options2Map(List<Option> options) {
        Map<String, String> map = new HashMap<String, String>();
        for (Option option : options) {
            map.put(option.getKey(), option.getValue());
        }
        return map;
    }

    public Map<String, String> getOptions() {
        // TODO Auto-generated method stub
        List<Option> options = optionDao.findAll();
        return options2Map(options);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#getOption()
     */
    @Override
    public String getOption(String key) {
        // TODO Auto-generated method stub
        Option option = optionDao.get(key);
        if (option == null) {
            return null;
        }
        return option.getValue();
    }
    
    /* (non-Javadoc)
     * @see org.mspring.mlog.service.OptionService#_getOption(java.lang.String)
     */
    @Override
    public String _getOption(String key) {
        // TODO Auto-generated method stub
        Option option = optionDao.get(key);
        if (option == null) {
            return null;
        }
        return option.getValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#setOption(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void setOption(String key, String value) {
        // TODO Auto-generated method stub
        if (StringUtils.isBlank(key)) {
            log.warn("can't update option, key can't be null");
            return;
        }

        // 更新数据库中缓存
        Option option = new Option(key, value);
        optionDao.merge(option);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#setOption(org.mspring.mlog.entity
     *      .Option)
     */
    @Override
    public void setOption(Option option) {
        // TODO Auto-generated method stub
        setOption(option.getKey(), option.getValue());
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
        for (Map.Entry<String, String> option : options.entrySet()) {
            setOption(option.getKey(), option.getValue());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#getJSONOption(java.lang.String)
     */
    @Override
    public List<KeyValue> getJSONOption(String key) {
        // TODO Auto-generated method stub
        String json = getOption(key);
        if (!StringUtils.isBlank(json)) {
            return (List<KeyValue>) JSONUtils.fromJson(json, new TypeToken(){});
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#setJSONOption(java.lang.String,
     *      java.util.List)
     */
    @Override
    public void setJSONOption(String key, List<KeyValue> keyValues) {
        // TODO Auto-generated method stub
        String value = JSONUtils.toJson(keyValues);
        setOption(key, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#setJSONOptions(java.util.Map)
     */
    @Override
    public void setJSONOptions(Map<String, List<KeyValue>> options) {
        // TODO Auto-generated method stub
        for (Map.Entry<String, List<KeyValue>> item : options.entrySet()) {
            setJSONOption(item.getKey(), item.getValue());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#getOptionsByPrefix(java.lang.String )
     */
    @Override
    public Map<String, String> getOptionsByPrefix(String prefix) {
        // TODO Auto-generated method stub
        if (StringUtils.isBlank(prefix)) {
            return null;
        }
        List<Option> list = optionDao.find("select o from Option o where o.key like '" + prefix + "%'");
        return options2Map(list);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#findAlbumConfig()
     */
    @Override
    public Map<String, String> findAlbumConfig() {
        // TODO Auto-generated method stub
        return getOptionsByPrefix(ConfigTokens.MSPRING_ALBUM_PREFIX);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#findBaseWebConfig()
     */
    @Override
    public Map<String, String> findBaseWebConfig() {
        // TODO Auto-generated method stub
        return getOptionsByPrefix(ConfigTokens.MSPRING_CONFIG_BASE_PREFIX);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#findGlobalWebConfig()
     */
    @Override
    public Map<String, String> findGlobalWebConfig() {
        // TODO Auto-generated method stub
        return getOptionsByPrefix(ConfigTokens.MSPRING_CONFIG_GLOBAL_PREFIX);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#findSeoWebConfig()
     */
    @Override
    public Map<String, String> findSeoWebConfig() {
        // TODO Auto-generated method stub
        return getOptionsByPrefix(ConfigTokens.MSPRING_CONFIG_SEO_PREFIX);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#findWebConfig()
     */
    @Override
    public Map<String, String> findWebConfig() {
        // TODO Auto-generated method stub
        return getOptionsByPrefix(ConfigTokens.MSPRING_CONFIG_PREFIX);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#findSystemWebConfig()
     */
    @Override
    public Map<String, String> findSystemWebConfig() {
        // TODO Auto-generated method stub
        return getOptionsByPrefix(ConfigTokens.MSPRING_CONFIG_SYSTEM_PREFIX);
    }

}
