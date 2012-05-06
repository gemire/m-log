package org.mspring.mlog.entity.security;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Authorities entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6170280789955076061L;
    // Fields
    private Integer authorityId;
    private String authorityName;
    private String authorityAlias;
    private String authorityDesc;
    private boolean enabled;

    // Constructors

    /** default constructor */
    public Authority() {
    }

    /** full constructor */
    public Authority(String authorityName, String authorityDesc, boolean enabled) {
        this.authorityName = authorityName;
        this.authorityDesc = authorityDesc;
        this.enabled = enabled;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "authority_id", unique = true, nullable = false)
    public Integer getAuthorityId() {
        return this.authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Column(name = "authority_name", length = 100)
    public String getAuthorityName() {
        return this.authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Column(name = "authority_alias", length = 100)
    public String getAuthorityAlias() {
        return authorityAlias;
    }

    public void setAuthorityAlias(String authorityAlias) {
        this.authorityAlias = authorityAlias;
    }

    @Column(name = "authority_desc", length = 100)
    public String getAuthorityDesc() {
        return this.authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    @Column(name = "enabled")
    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}