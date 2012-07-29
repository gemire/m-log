/**
 * 
 */
package org.mspring.mlog.service.impl;

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

}
