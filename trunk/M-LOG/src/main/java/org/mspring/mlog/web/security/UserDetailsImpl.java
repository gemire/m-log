/**
 * 
 */
package org.mspring.mlog.web.security;

import java.util.Collection;

import org.mspring.mlog.entity.security.User;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Gao Youbo
 * @since 2013-1-15
 * @Description
 * @TODO
 */
public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

    /**
     * 
     */
    private static final long serialVersionUID = 3470857431731007743L;

    /**
     * 
     * @param username
     * @param password
     * @param authorities
     */
    @Deprecated
    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }

    /**
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    @Deprecated
    public UserDetailsImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        // TODO Auto-generated constructor stub
    }
    
    
    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities, User userEntity) {
        this(username, password, true, true, true, true, authorities, userEntity);
    }
    
    public UserDetailsImpl(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, User userEntity) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userEntity = userEntity;
    }
    

    /**
     * 该对象为数据库实体类对象
     */
    private User userEntity;

    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }

}
