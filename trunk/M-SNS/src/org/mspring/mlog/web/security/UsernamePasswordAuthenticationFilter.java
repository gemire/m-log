/**
 * 
 */
package org.mspring.mlog.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mspring.mlog.common.Keys;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
public class UsernamePasswordAuthenticationFilter extends org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter {
    public static final String VALIDATE_CODE_PARAM = "validateCode";
    public static final String USERNAME_PARAM = "name";
    public static final String PASSWORD_PARAM = "password";

    @Autowired
    private UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // 验证码
        checkValidateCode(request);

        // 用户验证
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        User loginUser = userService.login(username, password);
        if (loginUser == null) {
            throw new AuthenticationServiceException("登录失败，用户名或密码错误");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // 允许子类设置详细属性
        setDetails(request, authRequest);

        Authentication auth = null;
        try {
            auth = this.getAuthenticationManager().authenticate(authRequest);
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        // 保存登录相关信息
        request.getSession().setAttribute(Keys.CURRENT_USER, loginUser);
        return auth;
    }

    /**
     * 验证码验证
     * 
     * @param request
     */
    protected void checkValidateCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionValidateCode = obtainSessionValidateCode(session);
        session.setAttribute(Keys.SESSION_VALIDATE_CODE, null); // 让上一次的验证码失效
        String validateCodeParameter = obtainValidateCodeParameter(request);
        if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            throw new AuthenticationServiceException("登录失败，验证码输入错误");
        }
    }

    /**
     * 获取表单提交的验证码
     * 
     * @param request
     * @return
     */
    private String obtainValidateCodeParameter(HttpServletRequest request) {
        Object obj = request.getParameter(VALIDATE_CODE_PARAM);
        return null == obj ? "" : obj.toString();
    }

    /**
     * 获取Session中保存的验证码
     * 
     * @param session
     * @return
     */
    protected String obtainSessionValidateCode(HttpSession session) {
        Object obj = session.getAttribute(Keys.SESSION_VALIDATE_CODE);
        return null == obj ? "" : obj.toString();
    }

    /**
     * 获取表单提交的用户名
     * 
     * @param request
     * @return
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(USERNAME_PARAM);
        return null == obj ? "" : obj.toString();
    }

    /**
     * 获取表单提交的密码
     * 
     * @param request
     * @return
     */
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(PASSWORD_PARAM);
        return null == obj ? "" : obj.toString();
    }
}
