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
public class UserRolePK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3047122696690398123L;

    private User user;
    private Role role;

    /**
     * 
     */
    public UserRolePK() {
        // TODO Auto-generated constructor stub
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

}
