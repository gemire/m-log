/**
 * 
 */
package org.mspring.mlog.web.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.mspring.mlog.entity.security.Resource;
import org.mspring.mlog.entity.security.Role;
import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.ResourceSecurityService;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.mlog.service.security.TreeItemSecurityService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
@Component("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TreeItemSecurityService treeItemSecurityService;
    @Autowired
    private ResourceSecurityService resourceSecurityService;

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

        return new UserDetailsImpl(user.getName(), user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths, user);
    }

    /**
     * 取得用户的权限
     * 
     * @param user
     * @return
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

        // 获取用户所有的角色
        List<Role> roles = roleService.getRolesByUser(user.getId());

        for (Role role : roles) {
            // 获取角色所拥有的资源
            List<Resource> resources = resourceSecurityService.findResourceByRole(role.getId());
            for (Resource res : resources) {
                // authSet.add(new GrantedAuthorityImpl(role, res));
                authSet.add(new SimpleGrantedAuthority("resource_" + res.getId()));
            }

            List<TreeItem> items = treeItemSecurityService.getPremissions(role.getId());
            Iterator<TreeItem> it = items.iterator();
            while (it.hasNext()) {
                TreeItem item = it.next();
                if (StringUtils.isNotBlank(item.getCall())) {
                    authSet.add(new SimpleGrantedAuthority("treeitem_" + item.getId()));
                }
            }
        }
        authSet.add(new SimpleGrantedAuthority("resource_1"));
        return authSet;
    }

}
