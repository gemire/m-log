/**
 * 
 */
package org.mspring.mlog.web.security;

import java.util.Collection;
import java.util.Iterator;

import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.entity.security.User;
import org.mspring.mlog.service.security.TreeItemService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {
    
    @Autowired
    private TreeItemService treeItemService;

    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }
        // 所请求的资源拥有的权限(一个资源对多个权限)
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String needPermission = configAttribute.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        // 没有权限
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