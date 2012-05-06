/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.dao.security.AuthorityResourceDao;
import org.mspring.mlog.entity.security.AuthorityResource;
import org.mspring.mlog.service.security.AuthorityResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
@Service
@Transactional
public class AuthorityResourceServiceImpl implements AuthorityResourceService {

    private AuthorityResourceDao authorityResourceDao;

    @Autowired
    public void setAuthorityResourceDao(AuthorityResourceDao authorityResourceDao) {
        this.authorityResourceDao = authorityResourceDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.security.service.AuthoritiesResourceService#
     * findAllAuthoritiesResources()
     */
    @Override
    public List<AuthorityResource> findAllAuthoritiesResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
