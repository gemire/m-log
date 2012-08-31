/**
 * 
 */
package org.mspring.mlog.web.api.t.service;

import java.util.List;

/**
 * @author Gao Youbo
 * @since 2012-8-29
 * @Description
 * @TODO
 */
public interface TService {
    /**
     * 发布一条微博
     * 
     * @param conent
     * @return
     */
    public String add(String conent);
    
    public List<String> list();
}
