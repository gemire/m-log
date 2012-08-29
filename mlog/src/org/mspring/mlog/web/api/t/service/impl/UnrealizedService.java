/**
 * 
 */
package org.mspring.mlog.web.api.t.service.impl;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.api.t.service.TService;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO 未实现的微博接口
 */
public class UnrealizedService implements TService {
    private static final Logger log = Logger.getLogger(UnrealizedService.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.api.t.service.TService#add()
     */
    @Override
    public void add() {
        // TODO Auto-generated method stub
        log.warn("unrealized...");
    }

}
