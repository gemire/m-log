/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.Role;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
public interface RoleService {

    /**
     * 根据用户编号获取用户组
     * 
     * @param userId
     * @return
     */
    List<Role> getRolesByUser(Long userId);

    /**
     * 分页查找Role对象
     * 
     * @param queryString
     * @param page
     * @return
     */
    Page<Role> findRole(String queryString, Page<Role> page);

    /**
     * 分页查找Role对象
     * 
     * @param queryString
     * @param page
     * @param params
     * @return
     */
    Page<Role> findRole(String queryString, Page<Role> page, Object... params);

    /**
     * 创建Role
     * 
     * @param role
     * @return
     */
    Role createRole(Role role);

    /**
     * 更新Role
     * 
     * @param role
     */
    void updateRole(Role role);

    /**
     * 根据ID获取Role
     * 
     * @param id
     * @return
     */
    Role getRoleById(Long id);

    /**
     * 根据Name获取Role
     * 
     * @param name
     * @return
     */
    Role getRoleByName(String name);

    /**
     * 检测RoleName是否存在
     * 
     * @param name
     * @param id
     * @return
     */
    boolean checkRoleNameExists(String name, Long id);

    /**
     * 查找可用的用户组
     * 
     * @return
     */
    List<Role> findEnabledRole();

    /**
     * 删除角色
     * 
     * @param roleId
     */
    void deleteRole(Long roleId);

    /**
     * 判断该角色下是否存在用户
     * 
     * @param roleId
     * @return
     */
    boolean hasUserInRole(Long roleId);

}
