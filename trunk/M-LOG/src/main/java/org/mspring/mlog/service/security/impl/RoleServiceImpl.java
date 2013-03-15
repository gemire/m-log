/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractServiceSupport implements RoleService {

    private static final Logger log = Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#getRolesByUser(java.lang
     * .Long)
     */
    @Override
    public List<Role> getRolesByUser(Long userId) {
        // TODO Auto-generated method stub
        return find("select userRole.PK.role from UserRole userRole where userRole.PK.user.id = ?", userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#findRole(java.lang.String,
     * org.mspring.platform.persistence.support.Page)
     */
    @Override
    public Page<Role> findRole(String queryString, Page<Role> page) {
        // TODO Auto-generated method stub
        return findPage(queryString, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#findRole(java.lang.String,
     * org.mspring.platform.persistence.support.Page, java.lang.Object[])
     */
    @Override
    public Page<Role> findRole(String queryString, Page<Role> page, Object... params) {
        // TODO Auto-generated method stub
        return findPage(queryString, page, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#createRole(org.mspring.
     * mlog.entity.security.Role)
     */
    @Override
    public Role createRole(Role role) {
        // TODO Auto-generated method stub
        Long id = (Long) create(role);
        return getRoleById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#updateRole(org.mspring.
     * mlog.entity.security.Role)
     */
    @Override
    public void updateRole(Role role) {
        // TODO Auto-generated method stub
        update(role);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#getRoleById(java.lang.Long)
     */
    @Override
    public Role getRoleById(Long id) {
        // TODO Auto-generated method stub
        return (Role) getById(Role.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#getRoleByName(java.lang
     * .String)
     */
    @Override
    public Role getRoleByName(String name) {
        // TODO Auto-generated method stub
        Object obj = findUnique("select role from Role role where role.name = ?", name);
        return obj == null ? null : (Role) obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#checkRoleNameExists(java
     * .lang.String, java.lang.Long)
     */
    @Override
    public boolean checkRoleNameExists(String name, Long id) {
        // TODO Auto-generated method stub
        int count = 0;
        if (id == null) {
            count = count("select count(*) from Role role where role.name = ?", name);
        } else {
            count = count("select count(*) from Role role where role.name = ? and role.id <> ?", new Object[] { name, id });
        }
        return count > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.security.RoleService#findEnabledRole()
     */
    @Override
    public List<Role> findEnabledRole() {
        // TODO Auto-generated method stub
        return find("select role from Role role where role.enabled = ?", true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#deleteRole(java.lang.Long)
     */
    @Override
    public void deleteRole(Long roleId) {
        // TODO Auto-generated method stub
        if (hasUserInRole(roleId)) {
            log.warn("can't delete role, RoleID = " + roleId + ", has some users in this role.");
            return;
        }
        remove(Role.class, roleId);
        // 删除跟该角色相关联的数据
        executeUpdate("delete RoleResource roleResource where roleResource.PK.role.id = ?", roleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleService#hasUserInRole(java.lang
     * .Long)
     */
    @Override
    public boolean hasUserInRole(Long roleId) {
        // TODO Auto-generated method stub
        int count = count("select count(*) from UserRole userRole where userRole.PK.role.id = ?", roleId);
        return count > 0;
    }

}
