/**
 * 
 */
package org.mspring.mlog.web.action.manage.album;

import org.mspring.mlog.entity.Album;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;

/**
 * @author Gao Youbo (http://www.mspring.org)
 * @since Dec 18, 2011
 */
public class QueryAlbumAction extends AbstractManageAction {
    private Page<Album> albumPage = new Page<Album>();

    public Page<Album> getAlbumPage() {
        return albumPage;
    }

    public void setAlbumPage(Page<Album> albumPage) {
        this.albumPage = albumPage;
    }

    public String execute() {
        if (albumPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            albumPage.setSort(sort);
        }
        albumPage = albumService.queryAlbum(albumPage, "select album from Album album ");
        return SUCCESS;
    }
}
