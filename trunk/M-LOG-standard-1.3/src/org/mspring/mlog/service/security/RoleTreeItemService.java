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
    void authorize(Long roleId, String[] treeItems);
    
    /**
     * 取消授权
     * @param roleId
     * @param treeItems
     */
    void unAuthorize(Long roleId, String[] treeItems);
    
    /**
     * 根据Role获取已经被授权的treeItem
     * @param roleId
     * @return
     */
    List<TreeItem> getAuthorizedList(Long roleId);
}
