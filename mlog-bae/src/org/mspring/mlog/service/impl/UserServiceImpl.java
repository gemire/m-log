/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.UserService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-12
 * @Description
 * @TODO
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceSupport implements UserService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#login(java.lang.String,
     * java.lang.String)
     */
    @Override
    public User login(String username, String password) {
        // TODO Auto-generated method stub
        // 密码加密
        password = StringUtils.getMD5(password);
        String queryString = "select u from User u where u.name = ? and u.password = ?";
        List list = find(queryString, new String[] { username, password });
        if (list != null && list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#getUserByName(java.lang.String)
     */
    @Override
    public User getUserByName(String username) {
        // TODO Auto-generated method stub
        String queryString = "select user from User user where user.name = ?";
        return (User) findUnique(queryString, username);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.UserService#updateUserInfo(org.mspring.mlog.
     * entity.User)
     */
    @Override
    public void updateUserInfo(User user) {
        // TODO Auto-generated method stub
        update(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#hasUser()
     */
    @Override
    public boolean hasUser() {
        // TODO Auto-generated method stub
        return count("select count(*) from User user") > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.UserService#addUser(org.mspring.mlog.entity.
     * User)
     */
    @Override
    public User addUser(User user) {
        // TODO Auto-generated method stub
        user.setCreateTime(new Date());
        user.setPassword(StringUtils.getMD5(user.getPassword()));
        Serializable id = create(user);
        return getUserById((Long) id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#getUserById(java.lang.Long)
     */
    @Override
    public User getUserById(Long id) {
        // TODO Auto-generated method stub
        return (User) getById(User.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#getFirstUser()
     */
    @Override
    public User getFirstUser() {
        // TODO Auto-generated method stub
        List<User> users = find("select u from User u");
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#deleteUser(java.lang.Long)
     */
    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        remove(User.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#clearUser()
     */
    @Override
    public void clearUser() {
        // TODO Auto-generated method stub
        executeUpdate("delete from User");
    }

}
