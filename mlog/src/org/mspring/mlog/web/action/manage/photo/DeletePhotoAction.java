/**
 * 
 */
package org.mspring.mlog.web.action.manage.photo;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.util.ParameterUtils;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.platform.web.servlet.renderer.JSONRenderer;

import com.google.gson.JsonObject;

/**
 * @author Gao Youbo
 * @since Jan 10, 2012
 */
public class DeletePhotoAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1214781502935818581L;

    private String photoItems;

    public String getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(String photoItems) {
        this.photoItems = photoItems;
    }

    public String execute() {
        Long[] photoIds = ParameterUtils.splitToLongArray(photoItems);
        if (photoIds == null || photoIds.length == 0) {
            setRequestAttribute(Const.REQUEST_PROMPT_INFO, "请选择要删除的图片");
            return PROMPT;
        }
        String basePath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("");
        photoService.deletePhoto(basePath, photoIds);
        
        JsonObject res = new JsonObject();
        res.addProperty("success", true);
        JSONRenderer renderer = new JSONRenderer(res.toString());
        renderer.render(ServletActionContext.getResponse());
        return NONE;
    }
}
