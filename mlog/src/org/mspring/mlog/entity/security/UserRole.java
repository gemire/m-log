package org.mspring.mlog.entity.security;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users_roles")
public class UserRole implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3912625392862400393L;

    private UserRolePK PK;

    /**
     * 
     */
    public UserRole() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pk
     */
    public UserRole(UserRolePK pk) {
        super();
        PK = pk;
    }

    @EmbeddedId
    @AttributeOverrides( { @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)), @AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)) })
    public UserRolePK getPK() {
        return PK;
    }

    public void setPK(UserRolePK pk) {
        PK = pk;
    }

}