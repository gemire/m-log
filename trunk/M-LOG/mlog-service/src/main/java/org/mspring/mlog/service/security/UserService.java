/**
 * 
 */
package org.mspring.mlog.service.security;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.User;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

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
    public User createUser(User user);

    /**
     * 获取数据库中的第一个用户
     * 
     * @return
     */
    public User getFirstUser();

    /**
     * 删除用户
     * 
     * @param id
     */
    public void deleteUser(Long id);

    /**
     * 分页查找用户
     * 
     * @param page
     * @param queryCriterion
     */
    public Page<User> findUser(Page<User> page, QueryCriterion queryCriterion);

    /**
     * 获取用户所在的用户组
     * 
     * @param userId
     * @return
     */
    public List<Role> getUserRoles(Long userId);

    /**
     * 设置用户对应的角色
     * 
     * @param userId
     * @param roleId
     */
    public void setUserRoles(Long user, Serializable... roles);

    /**
     * 删除用户对应的角色
     * 
     * @param user
     * @param roles
     */
    public void removeUserRoles(Long user, Serializable... roles);

    /**
     * 用户名是否存在
     * 
     * @param name
     * @param id
     * @return
     */
    public boolean userNameExists(String name, Long id);

    /**
     * 用户email是否存在
     * 
     * @param email
     * @param id
     * @return
     */
    public boolean userEmailExists(String email, Long id);

    /**
     * 用户别名是否存在
     * 
     * @param alias
     * @param id
     * @return
     */
    public boolean userAliasExists(String alias, Long id);

    /**
     * 根据角色查找该角色下的用户
     * 
     * @param roleId
     * @return
     */
    public List<User> findUsersByRole(Long roleId);
}
