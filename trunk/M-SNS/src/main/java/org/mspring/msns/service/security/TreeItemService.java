/**
 * 
 */
package org.mspring.msns.service.security;

import java.util.List;

import org.mspring.msns.entity.security.TreeItem;

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

    
}
