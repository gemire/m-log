package org.mspring.mlog.entity.security;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AuthoritiesResources entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "authorities_resources")
public class AuthorityResource implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -3049112351908218486L;

    private AuthorityResourcePK PK;

    /**
     * 
     */
    public AuthorityResource() {
        super();
    }

    /**
     * @param pk
     */
    public AuthorityResource(AuthorityResourcePK pk) {
        super();
        PK = pk;
    }

    @EmbeddedId
    @AttributeOverrides( { @AttributeOverride(name = "authorityId", column = @Column(name = "authority_id", nullable = false)), @AttributeOverride(name = "resourceId", column = @Column(name = "resource_id", nullable = false)) })
    public AuthorityResourcePK getPK() {
        return PK;
    }

    public void setPK(AuthorityResourcePK pk) {
        PK = pk;
    }

}