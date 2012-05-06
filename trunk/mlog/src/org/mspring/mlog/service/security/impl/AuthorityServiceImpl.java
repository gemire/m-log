/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.dao.security.AuthorityDao;
import org.mspring.mlog.entity.security.Authority;
import org.mspring.mlog.service.security.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    private static final Logger log = Logger.getLogger(AuthorityServiceImpl.class);

    private AuthorityDao authorityDao;

    @Autowired
    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.security.service.AuthoritiesService#findAllAuthorities()
     */
    @Override
    public List<Authority> findAllAuthorities() {
        // TODO Auto-generated method stub
        String queryString = "select auth from Authority auth where auth.enabled = ?";
        return authorityDao.find(queryString, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.security.service.AuthorityService#findUserAuthorities
     *      (java.lang.String)
     */
    @Override
    public List<Authority> findUserAuthorities(String userName) {
        // TODO Auto-generated method stub
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append("select auth from Authority auth, RoleAuthority roleAuth, UserRole userRole ");
            queryString.append("where userRole.PK.role.roleId = roleAuth.PK.role.roleId ");
            queryString.append("and roleAuth.PK.authority.authorityId = auth.authorityId ");
            queryString.append("and userRole.PK.role.enabled = true ");
            queryString.append("and auth.enabled = true ");
            queryString.append("and userRole.PK.user.name = ? ");
            return authorityDao.find(queryString.toString(), userName);
        } catch (RuntimeException re) {
            log.error("find by authorities by username failed." + re.getMessage());
            throw re;
        }
    }

}
