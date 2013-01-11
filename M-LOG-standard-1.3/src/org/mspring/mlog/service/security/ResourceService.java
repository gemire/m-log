/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.Resource;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description 
 * @TODO 
 */
public interface ResourceService {
    List<Resource> findAllResources();
    
    List<Resource> findResourceByRole(Long roleId);
}
