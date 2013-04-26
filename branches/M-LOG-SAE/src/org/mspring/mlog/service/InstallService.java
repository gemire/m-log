/**
 * 
 */
package org.mspring.mlog.service;

/**
 * @author Gao Youbo
 * @since 2012-12-24
 * @Description
 * @TODO
 */
public interface InstallService {
    public boolean hasInstall();

    public void setHasInstalled();

    public boolean hasUser();

    public void initBlogInfo(String blogname, String blogurl, String username, String alias, String password, String email);

    public void initPosts();

    public void initLinks();

    public void initTreeItems();
}
