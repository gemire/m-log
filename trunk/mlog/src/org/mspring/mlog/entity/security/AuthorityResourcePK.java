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
 * @since Apr 18, 2012
 */
@Embeddable
public class AuthorityResourcePK implements Serializable {

    /**
     * 
     */

    private static final long serialVersionUID = -3472929871618228517L;

    private Authority authority;
    private Resource resource;

    /**
     * 
     */
    public AuthorityResourcePK() {
        super();
    }

    /**
     * @param authority
     * @param resource
     */
    public AuthorityResourcePK(Authority authority, Resource resource) {
        super();
        this.authority = authority;
        this.resource = resource;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Authority.class)
    @JoinColumn(name = "authority_id")
    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Resource.class)
    @JoinColumn(name = "resource_id")
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof AuthorityResourcePK))
            return false;
        AuthorityResourcePK castOther = (AuthorityResourcePK) other;

        return ((this.getAuthority() == castOther.getAuthority()) || (this.getAuthority() != null && castOther.getAuthority() != null && this.getAuthority().equals(castOther.getAuthority()))) && ((this.getResource() == castOther.getResource()) || (this.getResource() != null && castOther.getResource() != null && this.getResource().equals(castOther.getResource())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getAuthority() == null ? 0 : this.getAuthority().hashCode());
        result = 37 * result + (getResource() == null ? 0 : this.getResource().hashCode());
        return result;
    }

}
