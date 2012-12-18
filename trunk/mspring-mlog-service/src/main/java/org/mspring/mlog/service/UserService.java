/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.User;

/**
 * @author Gao Youbo
 * @since 2012-7-12
 * @Description
 * @TODO
 */
public interface UserService {
    /**
     * 是否登录
     * 
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    /**
     * 根据ID获取用户
     * 
     * @param id
     * @return
     */
    public User getUserById(Long id);

    /**
     * 根据名字获取用户
     * 
     * @param username
     * @return
     */
    public User getUserByName(String username);

    /**
     * 更新用户信息
     * 
     * @param user
     */
    public void updateUserInfo(User user);

    /**
     * 是否存在用户
     * 
     * @return
     */
    public boolean hasUser();

    /**
     * 添加用户
     * 
     * @param user
     * @return
     */
    public User addUser(User user);
    
    /**
     * 获取数据库中的第一个用户
     * @return
     */
    public User getFirstUser();
    
    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Long id);
    
    /**
     * 清空所有用户
     */
    public void clearUser();
}
