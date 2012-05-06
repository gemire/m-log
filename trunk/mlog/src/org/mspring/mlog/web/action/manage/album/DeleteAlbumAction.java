/**
 * 
 */
package org.mspring.mlog.web.action.manage.album;

import org.mspring.mlog.common.Const;
import org.mspring.mlog.web.action.CommonActionSupport;

/**
 * @author Gao Youbo
 * @since May 4, 2012
 */
public class DeleteAlbumAction extends CommonActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 4167559446898397440L;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String execute() {
        if (id == null) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "请选择需要删除的相册");
            return PROMPT;
        }
        //判断相册中是否有照片, 如果有,不允许删除
        if (photoService.hasPhotoInAlbum(id)) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "该相册下存在照片,不允许删除该相册");
            return PROMPT;
        }
        albumService.deleteAlbum(id);
        return SUCCESS;
    }
}
