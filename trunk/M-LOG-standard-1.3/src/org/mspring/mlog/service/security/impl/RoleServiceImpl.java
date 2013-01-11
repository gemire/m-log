/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.platform.core.AbstractServiceSupport;
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

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.security.RoleService#getRolesByUser(java.lang.Long)
     */
    @Override
    public List<Role> getRolesByUser(Long userId) {
        // TODO Auto-generated method stub
        return find("select userRole.PK.role from UserRole userRole where userRole.PK.user.id = ?", userId);
    }

}
