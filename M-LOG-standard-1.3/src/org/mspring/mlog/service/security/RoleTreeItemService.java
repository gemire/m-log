/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.TreeItem;

/**
 * @author Gao Youbo
 * @since 2013-1-14
 * @Description
 * @TODO
 */
public interface RoleTreeItemService {
    /**
     * 授权
     * 
     * @param roleId
     * @param treeItems
     */
    void setPremission(Long roleId, String[] treeItems);
    
    /**
     * 取消授权
     * @param roleId
     * @param treeItems
     */
    void removePremission(Long roleId, String[] treeItems);
    
    /**
     * 根据Role获取已经被授权的treeItem
     * @param roleId
     * @return
     */
    List<TreeItem> getPremissions(Long roleId);
}
