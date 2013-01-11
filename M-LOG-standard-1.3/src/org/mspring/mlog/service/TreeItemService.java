/**
 * 
 */
package org.mspring.mlog.service;

import java.util.List;

import org.mspring.mlog.entity.TreeItem;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
public interface TreeItemService {
    public TreeItem getItemById(String id);
    
    public List<TreeItem> findTreeItems();
    
    public List<TreeItem> findTabItems(String parent);
    
    public TreeItem getOpenTab(String parent);

    public void createItem(TreeItem item);
    
    public void clearItems();
}
