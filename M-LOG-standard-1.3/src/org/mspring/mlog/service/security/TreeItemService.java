/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.TreeItem;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
public interface TreeItemService {
    /**
     * 根据编号获取TreeItem
     * 
     * @param id
     * @return
     */
    public TreeItem getItemById(String id);

    /**
     * 根据用户查找该用户的TreeItems
     * 
     * @param userId
     * @return
     */
    public List<TreeItem> findTreeItemByUser(Long userId);

    /**
     * 根据角色查找TreeItem
     * 
     * @param roleId
     * @return
     */
    public List<TreeItem> findTreeItemByRole(Long roleId);

    /**
     * 查找TabItems
     * 
     * @param parent
     * @param userId
     * @return
     */
    public List<TreeItem> findTabItems(String parent, Long userId);

    /**
     * 获取自动打开的Tab
     * 
     * @param parent
     * @param userId
     * @return
     */
    public TreeItem getOpenTab(String parent, Long userId);

    /**
     * 创建TreeItem
     * 
     * @param item
     */
    public void createItem(TreeItem item);

    /**
     * 清空TreeItem
     */
    public void clearItems();

    /**
     * 查找所有的TreeItem
     * 
     * @return
     */
    public List<TreeItem> findAllTreeItems();

    /**
     * 根据URL查找制定用户的TreeItem
     * 
     * @param url
     * @param userId
     * @return
     */
    public TreeItem findTreeItemByUserAndUrl(String url, Long userId);

    /**
     * 根据TreeeItem的Call查找TreeItem
     * 
     * @param url
     * @return
     */
    public TreeItem findTreeItemByUrl(String url);
    
    
    /**
     * 判断用户是否有指定的TreeItem的权限
     * @param itemId
     * @param userId
     * @return
     */
    public boolean hasUserTreeItem(String itemId, Long userId);
}
