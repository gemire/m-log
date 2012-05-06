/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.Resource;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
public interface ResourceService {
    public List<Resource> findResourcesByAuthName(String authName);
}
