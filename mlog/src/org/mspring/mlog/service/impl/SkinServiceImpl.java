/**
 * 
 */
package org.mspring.mlog.service.impl;

import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.SkinService;
import org.mspring.mlog.web.OptionKeys;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
@Service
@Transactional
public class SkinServiceImpl extends AbstractServiceSupport implements SkinService {
    private OptionService optionService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.SkinService#getCurrentSkinFolder()
     */
    @Override
    public String getCurrentSkinFolder() {
        // TODO Auto-generated method stub
        String skin = optionService.getOption(OptionKeys.CURRENT_SKIN);
        if (StringUtils.isBlank(skin)) {
            skin = "default";
        }
        return skin;
    }

}
