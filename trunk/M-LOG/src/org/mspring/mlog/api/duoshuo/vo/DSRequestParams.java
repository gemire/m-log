/**
 * 
 */
package org.mspring.mlog.api.duoshuo.vo;

import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since 2013-5-3
 * @description
 * @TODO 请求的基类
 */
public class DSRequestParams implements Serializable {

    protected String short_name; // 站点申请的多说二级域名。
    protected String secret; // 站点密钥。

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
