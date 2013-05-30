/**
 * 
 */
package org.mspring.platform.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2013-5-24
 * @description
 * @TODO 用于Ajax返回页面JSON信息
 */
public class ResponseEntity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7967635578102238230L;

    private Boolean success = true;
    private String message = "";
    private Map<String, Object> data = new HashMap<String, Object>();
    
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void put(String key, Object value) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put(key, value);
    }

    public Object get(String key) {
        if (data == null) {
            return null;
        }
        return data.get(key);
    }
}
