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
 * @since 2013-1-11
 * @Description
 * @TODO
 */
@Embeddable
public class RoleResourcePK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7050619398628144786L;

    private Role role;
    private Resource resource;

    /**
     * 
     */
    public RoleResourcePK() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param role
     * @param resource
     */
    public RoleResourcePK(Role role, Resource resource) {
        super();
        this.role = role;
        this.resource = resource;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Resource.class)
    @JoinColumn(name = "resource_id")
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
