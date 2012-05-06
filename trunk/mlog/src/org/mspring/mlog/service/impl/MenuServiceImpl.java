/**
 * @since Jul 10, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.service.impl;

import java.io.Serializable;
import java.util.List;

import org.mspring.mlog.common.MenuType;
import org.mspring.mlog.dao.MenuDao;
import org.mspring.mlog.entity.Menu;
import org.mspring.mlog.service.MenuService;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Gao Youbo
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao;

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#findAll()
     */
    @Override
    public List<Menu> findAll() {
        // TODO Auto-generated method stub
        return menuDao.findAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#createMenu(org.mspring.mlog.entity.Menu)
     */
    @Override
    public Menu createMenu(Menu menu) {
        // TODO Auto-generated method stub
        Serializable s = menuDao.save(menu);
        return menuDao.get(s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#deleteMenu(java.lang.Long[])
     */
    @Override
    public void deleteMenu(Long... ids) {
        // TODO Auto-generated method stub
        if (ids != null) {
            for (Long id : ids) {
                Menu menu = menuDao.get(id);
                // 禁止删除系统菜单
                if (menu.getType() != MenuType.SYSTEM) {
                    menuDao.delete(id);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#findMenuById(java.lang.Long)
     */
    @Override
    public Menu findMenuById(Long id) {
        // TODO Auto-generated method stub
        return menuDao.get(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#findWithoutSystem()
     */
    @Override
    public List<Menu> findWithoutSystem() {
        // TODO Auto-generated method stub
        return menuDao.findWithoutSystem();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#queryMenu(org.mspring.platform.dao.support.Page,
     *      org.mspring.platform.dao.query.QueryCriterion)
     */
    @Override
    public Page<Menu> queryMenu(Page<Menu> page, QueryCriterion queryCriterion) {
        // TODO Auto-generated method stub
        return menuDao.findPage(page, queryCriterion);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#mergeMenu(org.mspring.mlog.entity.Menu)
     */
    @Override
    public void mergeMenu(Menu menu) {
        // TODO Auto-generated method stub
        menuDao.merge(menu);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.MenuService#updateMenu(org.mspring.mlog.entity.Menu)
     */
    @Override
    public void updateMenu(Menu menu) {
        // TODO Auto-generated method stub
        menuDao.update(menu);
    }

}
