/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.entity.security.UserRole;
import org.mspring.mlog.entity.security.UserRolePK;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
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

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

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
     * org.mspring.mlog.service.UserService#createUser(org.mspring.mlog.entity.
     * User)
     */
    @Override
    public User createUser(User user) {
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

        // 删除用户对应的信息
        executeUpdate("delete UserRole userRole where userRole.PK.user.id = ?", id);
        executeUpdate("delete Post post where post.author.id = ?", id);
        executeUpdate("delete PostCatalog postCatalog where postCatalog.PK.post.id = ?", id);
        executeUpdate("delete PostTag postTag where postTag.PK.post.id = ?", id);
        executeUpdate("delete Comment comment where comment.post.id = ?", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#findUser(org.mspring.platform
     * .persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<User> findUser(Page<User> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return findPage(queryCriterion, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#getUserRoles(java.lang.
     * Long)
     */
    @Override
    public List<Role> getUserRoles(Long userId) {
        // TODO Auto-generated method stub
        return find("select userRole.PK.role from UserRole userRole where userRole.PK.user.id = ?", userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#setUserRoles(java.lang.
     * Long, java.lang.Long[])
     */
    @Override
    public void setUserRoles(Long user, Serializable... roles) {
        // TODO Auto-generated method stub
        executeUpdate("delete from UserRole userRole where userRole.PK.user.id = ?", user);
        User userEntity = new User(user);
        Role roleEntity = null;
        for (Serializable role : roles) {
            try {
                roleEntity = new Role(new Long(role.toString()));
                UserRolePK PK = new UserRolePK(userEntity, roleEntity);
                UserRole userRole = new UserRole(PK);
                merge(userRole);
            } catch (Exception e) {
                // TODO: handle exception
                log.error("setUserRoles failure! user = " + user + ", role = " + role + ", Message:" + e.getMessage());
                continue;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#removeUserRoles(java.lang
     * .Long, java.lang.Long[])
     */
    @Override
    public void removeUserRoles(Long user, Serializable... roles) {
        // TODO Auto-generated method stub
        for (Serializable role : roles) {
            try {
                executeUpdate("delete UserRole userRole where userRole.PK.user.id = ? and userRole.PK.role.id = ?", new Object[] { user, role });
            } catch (Exception e) {
                // TODO: handle exception
                log.error("removeUserRoles failure! user = " + user + ", role = " + role + ", Message:" + e.getMessage());
                continue;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#userNameExists(java.lang
     * .String, java.lang.Long)
     */
    @Override
    public boolean userNameExists(String name, Long id) {
        // TODO Auto-generated method stub
        int count = 0;
        if (id == null) {
            count = count("select count(*) from User user where user.name = ?", name);
        } else {
            count = count("select count(*) from User user where user.name = ? and user.id <> ?", new Object[] { name, id });
        }
        return count > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#userEmailExists(java.lang
     * .String, java.lang.Long)
     */
    @Override
    public boolean userEmailExists(String email, Long id) {
        // TODO Auto-generated method stub
        int count = 0;
        if (id == null) {
            count = count("select count(*) from User user where user.email = ?", email);
        } else {
            count = count("select count(*) from User user where user.email = ? and user.id <> ?", new Object[] { email, id });
        }
        return count > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#userAliasExists(java.lang
     * .String, java.lang.Long)
     */
    @Override
    public boolean userAliasExists(String alias, Long id) {
        // TODO Auto-generated method stub
        int count = 0;
        if (id == null) {
            count = count("select count(*) from User user where user.alias = ?", alias);
        } else {
            count = count("select count(*) from User user where user.alias = ? and user.id <> ?", new Object[] { alias, id });
        }
        return count > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.UserService#findUsersByRole(java.lang
     * .Long)
     */
    @Override
    public List<User> findUsersByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select userRole.PK.user from UserRole userRole where userRole.PK.role.id = ?", roleId);
    }

}
