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
@Table(name = "user_role")
public class UserRole implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4000402743154058605L;

    private UserRolePK PK;

    /**
     * 
     */
    public UserRole() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pK
     */
    public UserRole(UserRolePK pK) {
        super();
        PK = pK;
    }

    
    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)), @AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)) })
    public UserRolePK getPK() {
        return PK;
    }

    public void setPK(UserRolePK pK) {
        PK = pK;
    }

}
