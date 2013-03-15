/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
@Service
@Transactional
public class TreeItemServiceImpl extends AbstractServiceSupport implements TreeItemService {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TreeItemService#getItemById(java.lang.String)
     */
    @Override
    public TreeItem getItemById(String id) {
        // TODO Auto-generated method stub
        return (TreeItem) getById(TreeItem.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TreeItemService#createItem(org.mspring.mlog.
     * entity.TreeItem)
     */
    @Override
    public void createItem(TreeItem item) {
        // TODO Auto-generated method stub
        create(item);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TreeItemService#clearItems()
     */
    @Override
    public void clearItems() {
        // TODO Auto-generated method stub
        executeUpdate("delete from TreeItem");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TreeItemService#findAllTreeItems()
     */
    @Override
    public List<TreeItem> findAllTreeItems() {
        // TODO Auto-generated method stub
        String queryString = "select t from TreeItem t where t.deleted = false order by t.id";
        return find(queryString);
    }

}
