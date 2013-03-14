package org.mspring.mlog.api.dbank.nsp.support.common;

/**
 * dbank-sdk
 * nsp.NSPException<br/>
 * Desc:NSP异常信息<br/>
 * @author <a href="mailto: 61606619@qq.com">Bruce Shen</a>
 * 2011 下午1:44:48
 */
@SuppressWarnings("serial")
public class NSPException extends Exception {

    private int code;

    public NSPException(int code, String message) {
        super(code + " " + message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
