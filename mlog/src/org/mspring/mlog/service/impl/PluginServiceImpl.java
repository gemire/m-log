/**
 * @since Jul 25, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.service.impl;

import org.mspring.mlog.dao.PluginBeanDao;
import org.mspring.mlog.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Gao Youbo
 */
@Service
@Transactional
public class PluginServiceImpl implements PluginService {

    private PluginBeanDao pluginBeanDao;

    @Autowired
    public void setPluginBeanDao(PluginBeanDao pluginBeanDao) {
        this.pluginBeanDao = pluginBeanDao;
    }
}
