package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.entity.security.RoleResource;

public interface RoleResourceService {
    /**
     * 根据角色查找该角色拥有的Resource
     * 
     * @param roleId
     * @return
     */
    List<Resource> findResourceByRole(Long roleId);

    /**
     * 根据User编号查找Resource
     * 
     * @param userId
     * @return
     */
    List<Resource> findResourceByUser(Long userId);

    /**
     * 判断用户是否有资源权限
     * 
     * @param userId
     *            用户ID
     * @param resourceId
     *            资源ID
     * @return
     */
    boolean hasUserResourcePermission(Long userId, Long resourceId);

    /**
     * 查找RoleResource
     * 
     * @param roleId
     * @return
     */
    List<RoleResource> findRoleResourceByRole(Long roleId);
}
