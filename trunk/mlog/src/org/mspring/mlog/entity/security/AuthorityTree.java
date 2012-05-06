package org.mspring.mlog.entity.security;

// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Article entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "authorities_tree")
public class AuthorityTree implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8299312348750290923L;

    private AuthorityTreePK PK;

    /**
     * 
     */
    public AuthorityTree() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pk
     */
    public AuthorityTree(AuthorityTreePK pk) {
        super();
        PK = pk;
    }

    @EmbeddedId
    @AttributeOverrides( { @AttributeOverride(name = "authorityId", column = @Column(name = "authority_id", nullable = false)), @AttributeOverride(name = "treeId", column = @Column(name = "tree_id", nullable = false)) })
    public AuthorityTreePK getPK() {
        return PK;
    }

    public void setPK(AuthorityTreePK pk) {
        PK = pk;
    }

}