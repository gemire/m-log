/**
 * 
 */
package org.mspring.mlog.service.security.impl;

import java.util.List;

import org.mspring.mlog.dao.security.TreeDao;
import org.mspring.mlog.entity.security.Tree;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Jan 14, 2012
 */
@Service
@Transactional
public class TreeServiceImpl implements TreeService {

    private TreeDao treeDao;

    @Autowired
    public void setTreeDao(TreeDao treeDao) {
        this.treeDao = treeDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TreeService#findRootTreeItem()
     */
    @Override
    public List<Tree> findRootTreeItem(String panelId, User user) {
        // TODO Auto-generated method stub
        // String queryString = "select t from Tree t where t.deleted = 0 and
        // t.parent = ? and t.type = ? order by id";
        StringBuffer queryString = new StringBuffer();
        queryString.append("select tree from Tree tree, RoleAuthority roleAuth, AuthorityTree authTree, UserRole userRole ");
        queryString.append("where tree.deleted = 0 ");
        queryString.append("and tree.id = authTree.PK.tree.id ");
        queryString.append("and authTree.PK.authority.authorityId = roleAuth.PK.authority.authorityId ");
        queryString.append("and userRole.PK.role.roleId = roleAuth.PK.role.roleId ");
        queryString.append("and tree.parent = ? ");
        queryString.append("and tree.type = ? ");
        queryString.append("and userRole.PK.user.id = ? ");
        queryString.append("order by tree.id ");
        return treeDao.find(queryString.toString(), new Object[] { panelId, TYPE_TREEITEM, user.getId() });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TreeService#findTreeItemByParent(int)
     */
    @Override
    public List<Tree> findTreeItemByParent(String parent, User user) {
        // TODO Auto-generated method stub
        // String queryString = "select t from Tree t where t.deleted = 0 and
        // t.parent = ? and t.type = ? order by id";
        StringBuffer queryString = new StringBuffer();
        queryString.append("select tree from Tree tree, RoleAuthority roleAuth, AuthorityTree authTree, UserRole userRole ");
        queryString.append("where tree.deleted = 0 ");
        queryString.append("and tree.id = authTree.PK.tree.id ");
        queryString.append("and authTree.PK.authority.authorityId = roleAuth.PK.authority.authorityId ");
        queryString.append("and userRole.PK.role.roleId = roleAuth.PK.role.roleId ");
        queryString.append("and tree.parent = ? ");
        queryString.append("and tree.type = ? ");
        queryString.append("and userRole.PK.user.id = ? ");
        queryString.append("order by tree.id ");
        return treeDao.find(queryString.toString(), new Object[] { parent, TYPE_TREEITEM, user.getId() });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TreeService#findTreeItemById(java.lang.Integer)
     */
    @Override
    public Tree findTreeItemById(String id, User user) {
        // TODO Auto-generated method stub
        StringBuffer queryString = new StringBuffer();
        queryString.append("select tree from Tree tree, RoleAuthority roleAuth, AuthorityTree authTree, UserRole userRole ");
        queryString.append("where tree.deleted = 0 ");
        queryString.append("and authTree.PK.authority.authorityId = roleAuth.PK.authority.authorityId ");
        queryString.append("and userRole.PK.role.roleId = roleAuth.PK.role.roleId ");
        queryString.append("and tree.id = ? ");
        queryString.append("and userRole.PK.user.id = ? ");
        List<Tree> list = treeDao.find(queryString.toString(), new Object[] { id, user.getId() });
        if (list == null || list.size() == 0)
            return null;
        else
            return (Tree) list.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.TreeService#findAllTreePanel()
     */
    @Override
    public List<Tree> findAllTreePanel(User user) {
        // TODO Auto-generated method stub
        // String queryString = "select t from Tree t where t.deleted = 0 and
        // t.type = ? order by id";
        StringBuffer queryString = new StringBuffer();
        queryString.append("select tree from Tree tree, RoleAuthority roleAuth, AuthorityTree authTree, UserRole userRole ");
        queryString.append("where tree.deleted = 0 ");
        queryString.append("and tree.id = authTree.PK.tree.id ");
        queryString.append("and authTree.PK.authority.authorityId = roleAuth.PK.authority.authorityId ");
        queryString.append("and userRole.PK.role.roleId = roleAuth.PK.role.roleId ");
        queryString.append("and tree.type = ? ");
        queryString.append("and userRole.PK.user.id = ? ");
        queryString.append("order by tree.id ");
        return treeDao.find(queryString.toString(), new Object[] { TYPE_PANEL, user.getId() });
    }

}
