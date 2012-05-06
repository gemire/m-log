/**
 * 
 */
package org.mspring.mlog.web.action.manage.album;

import java.util.Date;

import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.entity.Album;
import org.mspring.platform.common.MD5;
import org.mspring.platform.utils.DateUtils;

/**
 * @author Gao Youbo (http://www.mspring.org)
 * @since Dec 18, 2011
 */
public class CreateAlbumAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 4891736134506073655L;
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String toCreateAlbum() {
        return SUCCESS;
    }

    public String doCreateAlbum() {
        album.setCreateTime(DateUtils.parse(DateUtils.YYYY_MM_DD_HH_MM_SS));
        if (Album.TYPE_VERIFIED.equals(album.getType())) {
            album.setVerifycode(MD5.getMD5(album.getVerifycode()));
        }
        albumService.createAlbum(album);
        return SUCCESS;
    }
}
