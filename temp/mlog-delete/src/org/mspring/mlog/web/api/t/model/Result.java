/**
 * 
 */
package org.mspring.mlog.web.api.t.model;

/**
 * @author Gao Youbo
 * @since 2012-8-31
 * @Description
 * @TODO
 */
public class Result {
    private String errcode;
    private String msg;
    private String ret;

    /**
     * @return the errcode
     */
    public String getErrcode() {
        return errcode;
    }

    /**
     * @param errcode
     *            the errcode to set
     */
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the ret
     */
    public String getRet() {
        return ret;
    }

    /**
     * @param ret
     *            the ret to set
     */
    public void setRet(String ret) {
        this.ret = ret;
    }

}
