/**
 * 
 */
package org.mspring.mlog.entity.security;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Gao Youbo
 * @since 2013-1-14
 * @Description
 * @TODO
 */
@Embeddable
public class RoleTreeItemPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8080918969572750029L;

    private Role role;
    private TreeItem treeItem;

    /**
     * 
     */
    public RoleTreeItemPK() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param role
     * @param treeItem
     */
    public RoleTreeItemPK(Role role, TreeItem treeItem) {
        super();
        this.role = role;
        this.treeItem = treeItem;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = TreeItem.class)
    @JoinColumn(name = "tree_item_id")
    public TreeItem getTreeItem() {
        return treeItem;
    }

    public void setTreeItem(TreeItem treeItem) {
        this.treeItem = treeItem;
    }

}
