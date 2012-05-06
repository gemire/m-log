package org.mspring.mlog.entity.security;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RoleAuthorites entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roles_authorities")
public class RoleAuthority implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 781890645586515410L;

    private RoleAuthorityPK PK;

    /**
     * 
     */
    public RoleAuthority() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pk
     */
    public RoleAuthority(RoleAuthorityPK pk) {
        super();
        PK = pk;
    }

    @EmbeddedId
    @AttributeOverrides( { @AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)), @AttributeOverride(name = "authorityId", column = @Column(name = "authority_id", nullable = false)) })
    public RoleAuthorityPK getPK() {
        return PK;
    }

    public void setPK(RoleAuthorityPK pk) {
        PK = pk;
    }

}