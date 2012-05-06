/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.dao.security.ResourceDao;
import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.service.security.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    private ResourceDao resourceDao;

    @Autowired
    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.web.security.service.ResourceService#findResourcesByAuthName
     *      (java.lang.String)
     */
    @Override
    public List<Resource> findResourcesByAuthName(String authName) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select res from Resource res, AuthorityResource authRes ");
        queryString.append("where res.resourceId = authRes.PK.resource.resourceId ");
        queryString.append("and res.enabled = true ");
        queryString.append("and authRes.PK.authority.enabled = true ");
        queryString.append("and authRes.PK.authority.authorityName = ? ");
        return resourceDao.find(queryString.toString(), authName);
    }

}
