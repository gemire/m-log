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
     * @see org.mspring.mlog.service.TreeItemService#findAllItems()
     */
    @Override
    public List<TreeItem> findTreeItems(Long userId) {
        // TODO Auto-generated method stub
//        String queryString = "select t from TreeItem t where t.deleted = false and (t.type = ? or t.type = ?) order by t.id";
//        return find(queryString, new String[] { TreeItem.Type.TREE_FOLDER, TreeItem.Type.TREE_ITEM });
        return find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and (roleTreeItem.PK.treeItem.type = ? or roleTreeItem.PK.treeItem.type = ?) and userRole.PK.user.id = ? order by roleTreeItem.PK.treeItem.id", new Object[]{TreeItem.Type.TREE_FOLDER, TreeItem.Type.TREE_ITEM, userId});
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TreeItemService#getOpenTab(java.lang.String)
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
