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
public class UserRolePK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1273346661722018724L;

    private User user;
    private Role role;

    /**
     * 
     */
    public UserRolePK() {
        super();
    }

    /**
     * @param user
     * @param role
     */
    public UserRolePK(User user, Role role) {
        super();
        this.user = user;
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof UserRolePK))
            return false;
        UserRolePK castOther = (UserRolePK) other;

        return ((this.getUser() == castOther.getUser()) || (this.getUser() != null && castOther.getUser() != null && this.getUser().equals(castOther.getUser()))) && ((this.getRole() == castOther.getRole()) || (this.getRole() != null && castOther.getRole() != null && this.getRole().equals(castOther.getRole())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getUser() == null ? 0 : this.getUser().hashCode());
        result = 37 * result + (getRole() == null ? 0 : this.getRole().hashCode());
        return result;
    }
}
