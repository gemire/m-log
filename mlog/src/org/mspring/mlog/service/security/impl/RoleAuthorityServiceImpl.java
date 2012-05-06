/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import org.mspring.mlog.dao.security.RoleAuthorityDao;
import org.mspring.mlog.service.security.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
@Service
@Transactional
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
    private RoleAuthorityDao roleAuthorityDao;

    @Autowired
    public void setRoleAuthorityDao(RoleAuthorityDao roleAuthorityDao) {
        this.roleAuthorityDao = roleAuthorityDao;
    }

}
