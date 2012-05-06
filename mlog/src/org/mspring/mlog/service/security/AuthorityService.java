/**
 * 
 */
package org.mspring.mlog.service.security;

import java.util.List;

import org.mspring.mlog.entity.security.Authority;

/**
 * @author Gao Youbo
 * @since Feb 12, 2012
 */
public interface AuthorityService {
    /**
     * 取得所有权限
     * @return
     */
    public List<Authority> findAllAuthorities();
    /**
     * 根据用户名取得用户权限
     * @param userName
     * @return
     */
    public List<Authority> findUserAuthorities(String userName);
}
