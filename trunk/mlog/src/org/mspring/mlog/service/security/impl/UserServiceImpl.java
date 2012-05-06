package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.dao.security.UserDao;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.common.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 5, 2011 org.mspring.mlog.service.impl
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#validateUser(java.lang.String,
     * java.lang.String)
     */
    public User validateUser(String userName, String password) {
        // TODO Auto-generated method stub
        return validateUser(userName, password, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.UserService#validateUser(java.lang.String,
     * java.lang.String, boolean)
     */
    public User validateUser(String userName, String password, boolean isMD5) {
        // TODO Auto-generated method stub
        List list = userDao.find("select m from User m where m.name = ? and m.password = ?", new String[] { userName, isMD5 ? password : MD5.getMD5(password) });
        if (list != null && list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.UserService#findUserByName(java.lang.String)
     */
    public User findUserByName(String userName) {
        // TODO Auto-generated method stub
        String queryString = "from User m where m.name = ?";
        List list = userDao.find(queryString, userName);
        if (list != null && list.size() > 0)
            return (User) list.get(0);
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.security.service.UserService#getPasswordByName(java.
     * lang.String)
     */
    @Override
    public String getPasswordByName(String userName) {
        // TODO Auto-generated method stub
        String queryString = "select u from User u where u.name = ?";
        List<User> users = userDao.find(queryString, userName);
        if (users == null || users.size() == 0) {
            return null;
        }
        return users.get(0).getPassword();
    }
}
