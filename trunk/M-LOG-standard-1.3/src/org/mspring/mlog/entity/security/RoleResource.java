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
 * @since 2013-1-11
 * @Description
 * @TODO
 */
@Entity
@Table(name = "role_resource")
public class RoleResource implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5572160892229574949L;

    private RoleResourcePK PK;

    /**
     * 
     */
    public RoleResource() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pK
     */
    public RoleResource(RoleResourcePK pK) {
        super();
        PK = pK;
    }
    
    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)), @AttributeOverride(name = "resourceId", column = @Column(name = "resource_id", nullable = false)) })
    public RoleResourcePK getPK() {
        return PK;
    }

    public void setPK(RoleResourcePK pK) {
        PK = pK;
    }

}
