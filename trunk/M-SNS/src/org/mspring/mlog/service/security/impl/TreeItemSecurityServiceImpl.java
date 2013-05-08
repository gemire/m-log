/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.RoleTreeItem;
import org.mspring.mlog.entity.security.RoleTreeItemPK;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.security.TreeItemSecurityService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-2-17
 * @description
 * @TODO
 */
@Service
@Transactional
public class TreeItemSecurityServiceImpl extends AbstractServiceSupport implements TreeItemSecurityService {

    private static final Logger log = Logger.getLogger(TreeItemSecurityServiceImpl.class);

    @Override
    public List<TreeItem> loadTreeByUser(Long userId) {
        // TODO Auto-generated method stub
        return find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and (roleTreeItem.PK.treeItem.type = ? or roleTreeItem.PK.treeItem.type = ?) and userRole.PK.user.id = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TREE_FOLDER, TreeItem.Type.TREE_ITEM, userId });
    }

    @Override
    public List<TreeItem> loadTreeByRole(Long roleId) {
        // TODO Auto-generated method stub
        return find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem where roleTreeItem.PK.treeItem.deleted = false and (roleTreeItem.PK.treeItem.type = ? or roleTreeItem.PK.treeItem.type = ?) and roleTreeItem.PK.role.id = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TREE_FOLDER, TreeItem.Type.TREE_ITEM, roleId });
    }

    @Override
    public List<TreeItem> loadTabByUser(String parent, Long userId) {
        // TODO Auto-generated method stub
        // String queryString =
        // "select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? order by t.id";
        // return find(queryString, new Object[] { TreeItem.Type.TAB, parent });
        return find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and roleTreeItem.PK.treeItem.type = ? and userRole.PK.user.id = ? and roleTreeItem.PK.treeItem.parent = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TAB, userId, parent });
    }

    @Override
    public TreeItem getOpenTab(String parent, Long userId) {
        // TODO Auto-generated method stub
        // List<TreeItem> list =
        // find("select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? and t.open = true order by t.id",
        // new Object[] { TreeItem.Type.TAB, parent });
        List<TreeItem> list = find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and roleTreeItem.PK.treeItem.open = true and roleTreeItem.PK.treeItem.type = ? and userRole.PK.user.id = ? and roleTreeItem.PK.treeItem.parent = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TAB, userId, parent });
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        // list =
        // find("select t from TreeItem t where t.deleted = false and t.type = ? and t.parent = ? order by t.id",
        // new Object[] { TreeItem.Type.TAB, parent });
        list = find("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where roleTreeItem.PK.role.id = userRole.PK.role.id and roleTreeItem.PK.treeItem.deleted = false and roleTreeItem.PK.treeItem.type = ? and userRole.PK.user.id = ? and roleTreeItem.PK.treeItem.parent = ? order by roleTreeItem.PK.treeItem.id", new Object[] { TreeItem.Type.TAB, userId, parent });
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public TreeItem findTreeItemByUserAndUrl(String url, Long userId) {
        // TODO Auto-generated method stub
        Object item = findUnique("select roleTreeItem.PK.treeItem from RoleTreeItem roleTreeItem, UserRole userRole where userRole.PK.role.id = roleTreeItem.PK.role.id and roleTreeItem.PK.treeItem.call = ? and userRole.PK.user.id = ?", new Object[] { url, userId });
        return item == null ? null : (TreeItem) item;
    }

    @Override
    public boolean hasUserTreeItem(String treeItemId, Long userId) {
        // TODO Auto-generated method stub
        int count = count("select count(*) from RoleTreeItem roleTreeItem, UserRole userRole where userRole.PK.role.id = roleTreeItem.PK.role.id and roleTreeItem.PK.treeItem.id = ? and userRole.PK.user.id = ?", new Object[] { treeItemId, userId });
        return count > 0;
    }

    @Override
    public void setPremission(Long roleId, String[] treeItems) {
        // TODO Auto-generated method stub
        Role role = new Role(roleId);
        TreeItem treeItem = null;
        for (String id : treeItems) {
            try {
                treeItem = ServiceFactory.getTreeItemService().getItemById(id);
                RoleTreeItemPK PK = new RoleTreeItemPK(role, treeItem);
                RoleTreeItem roleTreeItem = new RoleTreeItem(PK);
                merge(roleTreeItem);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.error("setPremission treeItem failure! roleId = " + roleId + ", treeItemId = " + id + ", Message:" + e.getMessage());
                continue;
            }
        }
    }

    @Override
    public void removePremission(Long roleId, String[] treeItems) {
        // TODO Auto-generated method stub
        for (String id : treeItems) {
            try {
                executeUpdate("delete RoleTreeItem rt where rt.PK.role.id = ? and rt.PK.treeItem.id = ?", new Object[] { roleId, id });
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                log.error("unsetPremission treeItem failure! roleId = " + roleId + ", treeItemId = " + id + ", Message:" + e.getMessage());
                continue;
            }
        }
    }

    @Override
    public List<TreeItem> getPremissions(Long roleId) {
        // TODO Auto-generated method stub
        return find("select rt.PK.treeItem from RoleTreeItem rt where rt.PK.role.id = ?", roleId);
    }

}
