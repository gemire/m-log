/**
 * 
 */
package org.mspring.mlog.service;

import org.mspring.mlog.entity.User;

/**
 * @author Gao Youbo
 * @since 2012-7-12
 * @Description 
 * @TODO 
 */
public interface UserService {
    public User login(String username, String password);
    
    public User getUserByName(String username);
}
