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
     * @see org.mspring.mlog.service.TreeItemService#findTreeItemByUser()
     */
    @Override
    public List<TreeItem> findTreeItemByUser(Long userId) {
        // TODO Auto-generated method stub
        return find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and (roleTreeItem.PK.treeItem.type = ? or roleTreeItem.PK.treeItem.type = ?) and userRole.PK.user.id = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TREE_FOLDER, TreeItem.Type.TREE_ITEM, userId });
    }

    @Override
    public List<TreeItem> findTreeItemByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem where roleTreeItem.PK.treeItem.deleted = false and (roleTreeItem.PK.treeItem.type = ? or roleTreeItem.PK.treeItem.type = ?) and roleTreeItem.PK.role.id = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TREE_FOLDER, TreeItem.Type.TREE_ITEM, roleId });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TreeItemService#findTabItems(java.lang.String)
     */
    @Override
    public List<TreeItem> findTabItems(String parent, Long userId) {
        // TODO Auto-generated method stub
        //String queryString = "select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? order by t.id";
        //return find(queryString, new Object[] { TreeItem.Type.TAB, parent });
        return find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and roleTreeItem.PK.treeItem.type = ? and userRole.PK.user.id = ? and roleTreeItem.PK.treeItem.parent = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TAB, userId, parent });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.TreeItemService#getOpenTab(java.lang.String)
     */
    @Override
    public TreeItem getOpenTab(String parent, Long userId) {
        // TODO Auto-generated method stub
        //List<TreeItem> list = find("select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? and t.open = true order by t.id", new Object[] { TreeItem.Type.TAB, parent });
        List<TreeItem> list = find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and roleTreeItem.PK.treeItem.open = true and roleTreeItem.PK.treeItem.type = ? and userRole.PK.user.id = ? and roleTreeItem.PK.treeItem.parent = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TAB, userId, parent });
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        //list = find("select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? order by t.id", new Object[] { TreeItem.Type.TAB, parent });
        list = find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and roleTreeItem.PK.treeItem.type = ? and userRole.PK.user.id = ? and roleTreeItem.PK.treeItem.parent = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TAB, userId, parent });
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

    @Override
    public TreeItem findTreeItemByUserAndUrl(String url, Long userId) {
        // TODO Auto-generated method stub
        Object item = findUnique("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where userRole.PK.role.id = roleTreeItem.PK.role.id and roleTreeItem.PK.treeItem.call = ? and userRole.PK.user.id = ?", new Object[] { url, userId });
        return item == null ? null : (TreeItem) item;
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.security.TreeItemService#findTreeItemByUrl(java.lang.String)
     */
    @Override
    public TreeItem findTreeItemByUrl(String url) {
        // TODO Auto-generated method stub
        Object item = findUnique("select treeItem from TreeItem treeItem where treeItem.call = ?", url);
        return item == null ? null : (TreeItem) item; 
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.security.TreeItemService#hasUserTreeItem(java.lang.String, java.lang.Long)
     */
    @Override
    public boolean hasUserTreeItem(String treeItemId, Long userId) {
        // TODO Auto-generated method stub
        int count = count("select count(*) from RoleTreeItem roleTreeItem, UserRole userRole where userRole.PK.role.id = roleTreeItem.PK.role.id and roleTreeItem.PK.treeItem.id = ? and userRole.PK.user.id = ?", new Object[] { treeItemId, userId });
        return count > 0;
    }

}
