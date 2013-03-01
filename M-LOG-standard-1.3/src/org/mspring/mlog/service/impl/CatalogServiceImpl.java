/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.utils.CatalogUtils;
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
        } else {
            count = count("select count(*) from Catalog catalog where catalog.name = ? and catalog.id <> ?", new Object[] { name, id });
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#setCatalogOrders(java.lang.Long
     * [], java.lang.Long[])
     */
    @Override
    public void setCatalogOrders(Long[] ids, Long[] orders) {
        // TODO Auto-generated method stub
        if (ids == null || orders == null || ids.length == 0 || orders.length == 0) {
            return;
        }
        if (ids.length != orders.length) {
            return;
        }
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            Long order = orders[i];
            if (id == null) {
                continue;
            }
            executeUpdate("update Catalog c set c.order = ? where c.id = ?", order, id);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.CatalogService#setCatalogParent(java.lang.Long,
     * java.lang.Long)
     */
    @Override
    public void setCatalogParent(Long catalogId, Long parentId) {
        // TODO Auto-generated method stub
        if (catalogId == null) {
            return;
        }
        executeUpdate("update Catalog catalog set catalog.parent.id = ? where catalog.id = ?", parentId, catalogId);
    }

    @Override
    public List<Catalog> findChildCatalogs(Long parent) {
        // TODO Auto-generated method stub
        if (parent == null || parent.equals(new Long(0))) {
            return find("select catalog from Catalog catalog where catalog.parent.id is null");
        }
        return find("select catalog from Catalog catalog where catalog.parent.id = ?", parent);
    }

    @Override
    public List<Catalog> findAllChildCatalogs(Long parent) {
        // TODO Auto-generated method stub
        List<Catalog> catalogs = findAllCatalog();
        Catalog parentCatalog = getCatalogById(parent);
        List<Catalog> result = new ArrayList<Catalog>();
        CatalogUtils.getTreeByParent(parentCatalog, catalogs, result);
        return result;
    }
}
