/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.RoleResource;
import org.mspring.mlog.entity.security.RoleResourcePK;
import org.mspring.mlog.service.security.ResourceSecurityService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-2-17
 * @description
 * @TODO
 */
@Service
@Transactional
public class ResourceSecurityServiceImpl extends AbstractServiceSupport implements ResourceSecurityService {

    @Override
    public List<Resource> findResourceByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select roleResource.PK.resource from RoleResource roleResource where roleResource.PK.role.id = ?", roleId);
    }

    @Override
    public List<Resource> findResourceByUser(Long userId) {
        // TODO Auto-generated method stub
        return find("select roleResource.PK.resource from RoleResource roleResource, UserRole userRole where userRole.PK.role.id = roleResource.PK.role.id and userRole.PK.user.id = ?", userId);
    }

    @Override
    public boolean hasUserResourcePermission(Long userId, Long resourceId) {
        // TODO Auto-generated method stub
        int count = count("select count(*) from RoleResource roleResource, UserRole userRole where userRole.PK.role.id = roleResource.PK.role.id and userRole.PK.user.id = ? and roleResource.PK.resource.id = ?", new Long[] { userId, resourceId });
        return count > 0;
    }

    @Override
    public List<RoleResource> findRoleResourceByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select roleResource from RoleResource roleResource where roleResource.PK.role.id = ?", roleId);
    }

    @Override
    public void createRoleResource(Long roleId, Long resourceId) {
        // TODO Auto-generated method stub
        RoleResourcePK pK = new RoleResourcePK();
        pK.setRole(new Role(roleId));
        pK.setResource(new Resource(resourceId));
        RoleResource roleResource = new RoleResource(pK);
        create(roleResource);
    }

}
