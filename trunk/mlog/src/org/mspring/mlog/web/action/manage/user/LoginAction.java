/**
 * 
 */
package org.mspring.mlog.web.action.manage.user;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.platform.utils.CookieUtils;

/**
 * @author Gao Youbo
 * @since Apr 28, 2012
 */
public class LoginAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = -384765007397194847L;

    private String username;
    private String password;
    private String validateCode;
    private Boolean remember = false;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Boolean isRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String execute() {
        Object objectSessionValidateCode = getSessionAttribute(Const.SESSION_VALIDATE_CODE);
        if (objectSessionValidateCode == null) {
            message = "验证码输入错误";
            return INPUT;
        }
        String sessionValidateCode = objectSessionValidateCode.toString();
        if (!sessionValidateCode.equalsIgnoreCase(validateCode)) {
            message = "验证码输入错误";
            return INPUT;
        }
        User user = userService.validateUser(username, password);
        if (user == null) {
            message = "用户名或密码错误";
            return INPUT;
        }
        setSessionAttribute(Const.SESSION_LOGINUSER, user);
        CookieUtils.setCookie(ServletActionContext.getResponse(), Const.MSPRING_COOKIE_USERNAME, username, 365);
        CookieUtils.setCookie(ServletActionContext.getResponse(), Const.MSPRING_COOKIE_PASSWORD, password, 365);
        CookieUtils.setCookie(ServletActionContext.getResponse(), Const.MSPRING_COOKIE_REMEMBER, remember.toString(), 365);
        return SUCCESS;
    }
}
