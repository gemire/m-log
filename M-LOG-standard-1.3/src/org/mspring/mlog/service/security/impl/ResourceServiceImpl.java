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

    @Override
    public Resource createResource(Resource resource) {
        // TODO Auto-generated method stub
        Long id = (Long) create(resource);
        return getResourceById(id);
    }

    @Override
    public Resource getResourceById(Long id) {
        // TODO Auto-generated method stub
        return (Resource) getById(Resource.class, id);
    }

}
