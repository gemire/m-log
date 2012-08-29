/**
 * 
 */
package org.mspring.mlog.web.api.t.service;

import org.mspring.mlog.web.api.t.service.impl.TencentService;
import org.mspring.mlog.web.api.t.service.impl.UnrealizedService;
import org.mspring.mlog.web.api.t.utils.TConfigKeys;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public class TServiceFactory {
    public TService createService(String app) {
        if (TConfigKeys.APP_TENCENT.equals(app)) {
            return new TencentService();
        }
        return new UnrealizedService();
    }
}
