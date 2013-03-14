/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.Date;
import java.util.List;

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
        catalog.setCreateTime(new Date());
        Long id = (Long) create(catalog);
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
        // delete(Catalog.class, idArray);
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
        return findPage(queryString, page, params);
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
        return findPage(queryString, page);
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
        return findPage(criterion, page);
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
        return (Catalog) getById(Catalog.class, catalogId);
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

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.CatalogService#findAllCatalog()
     */
    @Override
    public List<Catalog> findAllCatalog() {
        // TODO Auto-generated method stub
        return findAll(Catalog.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#getCatalogByName(java.lang.String
     * )
     */
    @Override
    public Catalog getCatalogByName(String name) {
        // TODO Auto-generated method stub
        Object catalog = findUnique("select catalog from Catalog catalog where catalog.name = ?", name);
        return catalog == null ? null : (Catalog) catalog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#catalogExists(java.lang.String,
     * java.lang.String)
     */
    @Override
    public boolean catalogExists(String name, Long id) {
        // TODO Auto-generated method stub
        int count = 0;
        if (id == null) {
            count = count("select count(*) from Catalog catalog where catalog.name = ?", name);
        }
        else {
            count = count("select count(*) from Catalog catalog where catalog.name = ? and catalog.id <> ?", new Object[] { name, id });
        }
        if (count > 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
