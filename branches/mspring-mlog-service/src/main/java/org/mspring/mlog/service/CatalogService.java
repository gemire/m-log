/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Catalog;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;

/**
 * @author Gao Youbo
 * @since 2012-7-18
 * @Description
 * @TODO
 */
public interface CatalogService {
    public Catalog createCatalog(Catalog catalog);

    public void deleteCatalog(Long... idArray);

    public List<Catalog> findAllCatalog();

    public Page<Catalog> findCatalog(Page<Catalog> page, String queryString, Object... params);

    public Page<Catalog> findCatalog(Page<Catalog> page, String queryString);

    public Page<Catalog> findCatalog(Page<Catalog> page, QueryCriterion criterion);

    public Catalog getCatalogById(Long catalogId);

    public Catalog getCatalogByName(String name);

    public void updateCatalog(Catalog catalog);

    public boolean catalogExists(String name, Long id);
}
