/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.TreeItem;

/**
 * @author Gao Youbo
 * @since 2013-2-17
 * @description
 * @TODO
 */
public interface TreeItemSecurityService {
    /**
     * 根据用户加载树形菜单
     * 
     * @param userId
     * @return
     */
    public List<TreeItem> loadTreeByUser(Long userId);

    /**
     * 根据角色加载树形菜单
     * 
     * @param roleId
     * @return
     */
    public List<TreeItem> loadTreeByRole(Long roleId);

    /**
     * 根据用户加载Tab
     * 
     * @param parent
     * @param userId
     * @return
     */
    public List<TreeItem> loadTabByUser(String parent, Long userId);

    /**
     * 获取自动打开的Tab
     * 
     * @param parent
     * @param userId
     * @return
     */
    public TreeItem getOpenTab(String parent, Long userId);

    /**
     * 根据URL查找制定用户的TreeItem
     * 
     * @param url
     * @param userId
     * @return
     */
    public TreeItem findTreeItemByUserAndUrl(String url, Long userId);

    /**
     * 判断用户是否有指定的TreeItem的权限
     * 
     * @param itemId
     * @param userId
     * @return
     */
    public boolean hasUserTreeItem(String itemId, Long userId);

    /**
     * 授权
     * 
     * @param roleId
     * @param treeItems
     */
    void setPremission(Long roleId, String[] treeItems);

    /**
     * 取消授权
     * 
     * @param roleId
     * @param treeItems
     */
    void removePremission(Long roleId, String[] treeItems);

    /**
     * 根据Role获取已经被授权的treeItem
     * 
     * @param roleId
     * @return
     */
    List<TreeItem> getPremissions(Long roleId);
}
