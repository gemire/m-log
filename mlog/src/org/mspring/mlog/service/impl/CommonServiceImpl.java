/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.service.CommonService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Jun 19, 2012
 */
@Service
@Transactional
public class CommonServiceImpl extends AbstractServiceSupport implements CommonService {

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.CommonService#getOverviewInfo()
     */
    @Override
    public Map getOverviewInfo() {
        // TODO Auto-generated method stub
        Map info = new HashMap();
        Object articleCount = findUnique("select count(*) from Article");
        Object commentCount = findUnique("select count(*) from Comment");
        Object tagCount = findUnique("select count(*) from Tag");
        Object categoryCount = findUnique("select count(*) from Category");
        Object currentTheme = findUnique("select option.value from Option option where option.key = ?", ConfigTokens.mspring_config_base_theme);
        
        info.put("articleCount", articleCount);
        info.put("commentCount", commentCount);
        info.put("tagCount", tagCount);
        info.put("categoryCount", categoryCount);
        info.put("currentTheme", currentTheme);
        return info;
    }

}
