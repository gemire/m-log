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
import org.mspring.platform.core.AbstractServiceSupport;
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
    public String getOption(String key) {
        // TODO Auto-generated method stub
        Object option = findUnique("select option from Option option where option.name = ?", key);
        if (option != null) {
            return ((Option) option).getValue();
        }
        return "";
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

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.OptionService#getOptions()
     */
    @Override
    public Map<String, String> getOptions() {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<String, String>();
        List<Option> options = find("select option from Option option");
        if (options != null && options.size() > 0) {
            for (Option option : options) {
                map.put(option.getName(), option.getValue());
            }
        }
        return map;
    }

}
