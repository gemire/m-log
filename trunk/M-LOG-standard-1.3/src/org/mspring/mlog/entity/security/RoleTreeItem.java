/**
 * 
 */
package org.mspring.mlog.entity.security;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gao Youbo
 * @since 2013-1-14
 * @Description 
 * @TODO 
 */
@Entity
@Table(name = "role_tree_item")
public class RoleTreeItem implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6698300900707180899L;
    
    private RoleTreeItemPK PK;
    
    /**
     * 
     */
    public RoleTreeItem() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pK
     */
    public RoleTreeItem(RoleTreeItemPK pK) {
        super();
        PK = pK;
    }

    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)), @AttributeOverride(name = "treeItemId", column = @Column(name = "tree_item_id", nullable = false)) })
    public RoleTreeItemPK getPK() {
        return PK;
    }

    public void setPK(RoleTreeItemPK pK) {
        PK = pK;
    }
    
    

}
