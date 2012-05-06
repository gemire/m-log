package org.mspring.mlog.entity.security;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Resource entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "resources")
public class Resource implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4413073805276311318L;
    
    private Integer resourceId;
    private String resourceName;
    private String resourceType;
    private String resourceString;
    private String resourceDesc;
    private boolean enabled;

    // Constructors

    /** default constructor */
    public Resource() {
    }

    /** full constructor */
    public Resource(String resourceName, String resourceType, String resourceString, String resourceDesc, boolean enabled) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.resourceString = resourceString;
        this.resourceDesc = resourceDesc;
        this.enabled = enabled;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "resource_id", unique = true, nullable = false)
    public Integer getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Column(name = "resource_name", length = 100)
    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Column(name = "resource_type", length = 100)
    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "resource_string", length = 200)
    public String getResourceString() {
        return this.resourceString;
    }

    public void setResourceString(String resourceString) {
        this.resourceString = resourceString;
    }

    @Column(name = "resource_desc", length = 200)
    public String getResourceDesc() {
        return this.resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Column(name = "enabled")
    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}