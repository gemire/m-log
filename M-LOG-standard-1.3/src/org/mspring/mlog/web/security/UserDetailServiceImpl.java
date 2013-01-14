/**
 * 
 */
package org.mspring.mlog.web.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mspring.mlog.entity.User;
import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.service.UserService;
import org.mspring.mlog.service.security.ResourceService;
import org.mspring.mlog.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#
     * loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);

        boolean enables = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userdetail = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
        return userdetail;
    }

    /**
     * 取得用户的权限
     * @param user
     * @return
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        List<Role> roles = roleService.getRolesByUser(user.getId());

        for (Role role : roles) {
            List<Resource> tempRes = resourceService.findResourceByRole(role.getId());
            for (Resource res : tempRes) {
                authSet.add(new GrantedAuthorityImpl(res.getName()));
            }
        }
        return authSet;
    }

}
