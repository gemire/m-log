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
    /**
     * 查找所有resource
     * 
     * @return
     */
    List<Resource> findAllResources();

    /**
     * 创建Resource
     * 
     * @param resource
     * @return
     */
    Resource createResource(Resource resource);

    /**
     * 根据ResourceID获取resource
     * 
     * @param id
     * @return
     */
    Resource getResourceById(Long id);
}
