package org.mspring.mlog.api.dbank.nsp;

import org.mspring.mlog.api.dbank.nsp.support.common.AssocArrayUtil;
import org.mspring.mlog.api.dbank.nsp.support.common.NSPException;

/**
 * dbank-sdk nsp.sms<br/>
 * Desc:针对nsp.sms.*服务的封装<br/>
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a>
 * 2011 下午5:02:00
 */
public class sms {
    private NSPClient client;

    public sms(NSPClient client) {
        this.client = client;
    }

    /**
     * 发送短信（需要权限）
     * @param <b>mobile</b> 接收手机号
     * @param <b>message</b> 短信内容
     * @return Boolean
     * @throws NSPException
     */
    public Boolean send(String mobile, String message) throws NSPException {
        Object obj = client.callService("nsp.sms.send", new Object[] { mobile, message });
        return (Boolean) AssocArrayUtil.toObject(obj, false);
    }
}
