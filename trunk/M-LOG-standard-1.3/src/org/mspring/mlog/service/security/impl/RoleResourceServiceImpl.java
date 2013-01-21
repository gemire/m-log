package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.entity.security.RoleResource;
import org.mspring.mlog.service.security.RoleResourceService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleResourceServiceImpl extends AbstractServiceSupport implements RoleResourceService {

    @Override
    public List<Resource> findResourceByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select roleResource.PK.resource from RoleResource roleResource where roleResource.PK.role.id = ?", roleId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleResourceService#findResourceByUser
     * (java.lang.Long)
     */
    @Override
    public List<Resource> findResourceByUser(Long userId) {
        // TODO Auto-generated method stub
        return find("select roleResource.PK.resource from RoleResource roleResource, UserRole userRole where userRole.PK.role.id = roleResource.PK.role.id and userRole.PK.user.id = ?", userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.security.RoleResourceService#
     * hasUserResourcePermission(java.lang.Long, java.lang.Long)
     */
    @Override
    public boolean hasUserResourcePermission(Long userId, Long resourceId) {
        // TODO Auto-generated method stub
        int count = count("select count(*) from RoleResource roleResource, UserRole userRole where userRole.PK.role.id = roleResource.PK.role.id and userRole.PK.user.id = ? and roleResource.PK.resource.id = ?", new Long[]{userId, resourceId});
        return count > 0;
    }

    @Override
    public List<RoleResource> findRoleResourceByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select roleResource from RoleResource roleResource where roleResource.PK.role.id = ?", roleId);
    }

}
