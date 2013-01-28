/**
 * 
 */
package org.mspring.mlog.web.security;

import java.util.Collection;
import java.util.Iterator;

import org.mspring.mlog.service.security.RoleResourceService;
import org.mspring.mlog.service.security.TreeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {
    
    @Autowired
    private TreeItemService treeItemService;
    @Autowired
    private RoleResourceService roleResourceService;

    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }
        
//        String url = ((FilterInvocation) object).getRequestUrl();
//        int firstQuestionMarkIndex = url.indexOf("?");
//        if (firstQuestionMarkIndex != -1) {
//            url = url.substring(0, firstQuestionMarkIndex);
//        }
//        if (StringUtils.startsWith(url, "/admin/")) {
//            Object userObj = authentication.getPrincipal();
//            if (userObj != null) {
//                UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userObj;
//                TreeItem treeItem = treeItemService.findTreeItemByUrl(url);
//                if (treeItem != null) {
//                    boolean hasUserTreeItem = treeItemService.hasUserTreeItem(treeItem.getId(), userDetailsImpl.getUserEntity().getId());
//                    if (!hasUserTreeItem) {
//                        // 没有权限
//                        throw new AccessDeniedException(" 没有权限访问！ " + object);
//                    }
//                }
//            }
//        }
        
        // 所请求的资源拥有的权限(一个资源对多个权限)
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String needPermission = configAttribute.getAttribute();
//            for (GrantedAuthorityImpl ga : (Collection<GrantedAuthorityImpl>)authentication.getAuthorities()) {
//                if (needPermission.equals(ga.getAuthority())) {
//                    return;
//                }
//            }
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(" 没有权限访问！ " + object);
    }

    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

}