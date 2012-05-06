/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import org.mspring.mlog.dao.security.RoleDao;
import org.mspring.mlog.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
