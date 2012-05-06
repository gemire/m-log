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
public class RoleAuthorityPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3556696612128176583L;

    private Role role;
    private Authority authority;

    /**
     * 
     */
    public RoleAuthorityPK() {
        super();
    }

    /**
     * @param role
     * @param authority
     */
    public RoleAuthorityPK(Role role, Authority authority) {
        super();
        this.role = role;
        this.authority = authority;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Authority.class)
    @JoinColumn(name = "authority_id")
    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof RoleAuthorityPK))
            return false;
        RoleAuthorityPK castOther = (RoleAuthorityPK) other;

        return ((this.getAuthority() == castOther.getAuthority()) || (this.getAuthority() != null && castOther.getAuthority() != null && this.getAuthority().equals(castOther.getAuthority()))) && ((this.getRole() == castOther.getRole()) || (this.getRole() != null && castOther.getRole() != null && this.getRole().equals(castOther.getRole())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getAuthority() == null ? 0 : this.getAuthority().hashCode());
        result = 37 * result + (getRole() == null ? 0 : this.getRole().hashCode());
        return result;
    }
}
