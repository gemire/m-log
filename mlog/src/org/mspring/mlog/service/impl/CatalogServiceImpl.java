/**
 * 
 */
package org.mspring.mlog.service.impl;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.service.CatalogService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description
 * @TODO
 */
@Service
@Transactional
public class CatalogServiceImpl extends AbstractServiceSupport implements CatalogService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#createCatalog(org.mspring.mlog
     * .entity.Catalog)
     */
    @Override
    public Catalog createCatalog(Catalog catalog) {
        // TODO Auto-generated method stub
        Long id = (Long) save(catalog);
        return getCatalogById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#deleteCatalog(java.lang.Long[])
     */
    @Override
    public void deleteCatalog(Long... idArray) {
        // TODO Auto-generated method stub
        //delete(Catalog.class, idArray);
        if (idArray != null && idArray.length > 0) {
            String hql = "delete from Catalog c where c.id = ?";
            for (int i = 0; i < idArray.length; i++) {
                executeUpdate(hql, idArray[i]);
            }
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#findCatalog(org.mspring.platform
     * .persistence.support.Page, java.lang.String, java.lang.Object[])
     */
    @Override
    public Page<Catalog> findCatalog(Page<Catalog> page, String queryString, Object... params) {
        // TODO Auto-generated method stub
        return findPage(page, queryString, params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#findCatalog(org.mspring.platform
     * .persistence.support.Page, java.lang.String)
     */
    @Override
    public Page<Catalog> findCatalog(Page<Catalog> page, String queryString) {
        // TODO Auto-generated method stub
        return findPage(page, queryString);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#findCatalog(org.mspring.platform
     * .persistence.support.Page,
     * org.mspring.platform.persistence.query.QueryCriterion)
     */
    @Override
    public Page<Catalog> findCatalog(Page<Catalog> page, QueryCriterion criterion) {
        // TODO Auto-generated method stub
        return findPage(page, criterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#getCatalogById(java.lang.Long)
     */
    @Override
    public Catalog getCatalogById(Long catalogId) {
        // TODO Auto-generated method stub
        return (Catalog) get(Catalog.class, catalogId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#updateCatalog(org.mspring.mlog
     * .entity.Catalog)
     */
    @Override
    public void updateCatalog(Catalog catalog) {
        // TODO Auto-generated method stub
        update(catalog);
    }

}
