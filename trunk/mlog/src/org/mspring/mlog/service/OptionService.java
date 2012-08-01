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
    public String getOption(String key);

    public Map<String, String> getOptions();

    public void setOption(String key, String value);

    public void setOption(Option option);

    public void setOptions(List<Option> options);

    public void setOptions(Map<String, String> options);
}
