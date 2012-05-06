package org.mspring.mlog.service.security;

import org.mspring.mlog.entity.security.User;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 5, 2011 org.mspring.mlog.service
 */
public interface UserService {
    /**
     * 验证用户名密码
     * 
     * @param userName
     * @param password
     * @return
     */
    public User validateUser(String userName, String password);

    /**
     * 验证用户名密码
     * 
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @param isMD5
     *            参数password是否已经经过MD5加密，默认为false
     * @return
     */
    public User validateUser(String userName, String password, boolean isMD5);

    /**
     * 根据用户名查找用户
     * 
     * @param userName
     * @return
     */
    public User findUserByName(String userName);

    /**
     * 根据用户名获取密码
     * 
     * @param userName
     * @return
     */
    public String getPasswordByName(String userName);
}
