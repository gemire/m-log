/**
 * 
 */
package org.mspring.mlog.dao;

import java.util.List;

import org.mspring.mlog.entity.Menu;
import org.mspring.platform.dao.BaseDao;

/**
 * @author Gao Youbo
 * @since 2012-3-16
 * 
 */
public abstract interface MenuDao extends BaseDao<Menu> {
    List<Menu> findWithoutSystem();
}
