/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.service.security.ResourceService;
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
public class ResourceServiceImpl extends AbstractServiceSupport implements ResourceService {

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.security.ResourceService#findAllResources()
     */
    @Override
    public List<Resource> findAllResources() {
        // TODO Auto-generated method stub
        return findAll(Resource.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.ResourceService#findResourceByRole(
     * java.lang.Long)
     */
    @Override
    public List<Resource> findResourceByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select roleResource.PK.resource from RoleResource roleResource where roleResource.PK.role.id = ?", roleId);
    }

}
