/**
 * 
 */
package org.mspring.mlog.api.t.service;

import org.mspring.mlog.api.t.common.TConfigTokens;
import org.mspring.mlog.api.t.service.impl.TencentService;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public class TServiceFactory {
    public TService createService(String app) {
        if (TConfigTokens.APP_TENCENT.equals(app)) {
            return new TencentService();
        }
        return null;
    }
}
