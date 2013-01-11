/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.util.List;

import org.mspring.mlog.entity.TreeItem;
import org.mspring.mlog.service.TreeItemService;
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
     * @see org.mspring.mlog.service.TreeItemService#findAllItems()
     */
    @Override
    public List<TreeItem> findTreeItems() {
        // TODO Auto-generated method stub
        String queryString = "select t from TreeItem t where t.deleted = false and (t.type = ? or t.type = ?) order by t.id";
        return find(queryString, new String[]{TreeItem.Type.TREE_FOLDER, TreeItem.Type.TREE_ITEM});
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TreeItemService#findTabItems(java.lang.String)
     */
    @Override
    public List<TreeItem> findTabItems(String parent) {
        // TODO Auto-generated method stub
        String queryString = "select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? order by t.id";
        return find(queryString, new Object[] { TreeItem.Type.TAB, parent });
    }
    

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.TreeItemService#getOpenTab(java.lang.String)
     */
    @Override
    public TreeItem getOpenTab(String parent) {
        // TODO Auto-generated method stub
        List<TreeItem> list = find("select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? and t.open = true order by t.id", new Object[] { TreeItem.Type.TAB, parent });
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        list = find("select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? order by t.id", new Object[] { TreeItem.Type.TAB, parent });
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
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


}
