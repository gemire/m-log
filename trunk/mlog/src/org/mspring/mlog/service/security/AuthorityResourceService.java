/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.AuthorityResource;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
public interface AuthorityResourceService {
    public List<AuthorityResource> findAllAuthoritiesResources();
}
