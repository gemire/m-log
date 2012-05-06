/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.Tree;
import org.mspring.mlog.entity.security.User;

/**
 * @author Gao Youbo
 * @since Jan 14, 2012
 */
public interface TreeService {
    public static final Integer TYPE_PANEL = new Integer(1);
    public static final Integer TYPE_TREEITEM = new Integer(2);

    public List<Tree> findRootTreeItem(String panelId, User user);

    public List<Tree> findTreeItemByParent(String parent, User user);

    public Tree findTreeItemById(String id, User user);

    public List<Tree> findAllTreePanel(User user);
}
