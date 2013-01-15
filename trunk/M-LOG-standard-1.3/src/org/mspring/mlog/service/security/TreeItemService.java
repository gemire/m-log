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
    public TreeItem getItemById(String id);
    
    public List<TreeItem> findTreeItems(Long userId);
    
    public List<TreeItem> findTabItems(String parent);
    
    public TreeItem getOpenTab(String parent);

    public void createItem(TreeItem item);
    
    public void clearItems();
    
    public List<TreeItem> findAllTreeItems();
}
