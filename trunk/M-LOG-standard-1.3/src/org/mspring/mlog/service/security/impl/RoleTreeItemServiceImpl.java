/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.RoleTreeItem;
import org.mspring.mlog.entity.security.RoleTreeItemPK;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.security.RoleTreeItemService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2013-1-14
 * @Description
 * @TODO
 */
@Service
@Transactional
public class RoleTreeItemServiceImpl extends AbstractServiceSupport implements RoleTreeItemService {

    private static final Logger log = Logger.getLogger(RoleTreeItemServiceImpl.class);
    

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleTreeItemService#authorize(java.
     * lang.Long, java.lang.String[])
     */
    @Override
    public void authorize(Long roleId, String[] treeItems) {
        // TODO Auto-generated method stub
        Role role = new Role(roleId);
        TreeItem treeItem = null;
        for (String id : treeItems) {
            try {
                treeItem = new TreeItem(id);
                RoleTreeItemPK PK = new RoleTreeItemPK(role, treeItem);
                RoleTreeItem roleTreeItem = new RoleTreeItem(PK);
                merge(roleTreeItem);
            }
            catch (Exception e) {
                // TODO: handle exception
                log.error("authorize treeItem failure! roleId = " + roleId + ", treeItemId = " + id + ", Message:" + e.getMessage());
                continue;
            }
        }
    }
    

    /* (non-Javadoc)
     * @see org.mspring.mlog.service.security.RoleTreeItemService#unAuthorize(java.lang.Long, java.lang.String[])
     */
    @Override
    public void unAuthorize(Long roleId, String[] treeItems) {
        // TODO Auto-generated method stub
        for (String id : treeItems) {
            try {
                executeUpdate("delete RoleTreeItem rt where rt.PK.role.id = ? and rt.PK.treeItem.id = ?", new Object[]{roleId, id});
            }
            catch (Exception e) {
                // TODO: handle exception
                log.error("unAuthorize treeItem failure! roleId = " + roleId + ", treeItemId = " + id + ", Message:" + e.getMessage());
                continue;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.security.RoleTreeItemService#getAuthorizedList
     * (java.lang.Long)
     */
    @Override
    public List<TreeItem> getAuthorizedList(Long roleId) {
        // TODO Auto-generated method stub
        return find("select rt.PK.treeItem from RoleTreeItem rt where rt.PK.role.id = ?", roleId);
    }


}
