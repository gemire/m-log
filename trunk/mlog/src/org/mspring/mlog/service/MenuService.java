/**
 * @since Jul 10, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.Menu;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;

/**
 * 
 * @author Gao Youbo
 */
public interface MenuService {
    List<Menu> findAll();

    List<Menu> findWithoutSystem();

    Menu findMenuById(Long id);

    Menu createMenu(Menu menu);

    void deleteMenu(Long... ids);

    void updateMenu(Menu menu);
    
    void mergeMenu(Menu menu);

    Page<Menu> queryMenu(Page<Menu> page, QueryCriterion queryCriterion);
}
