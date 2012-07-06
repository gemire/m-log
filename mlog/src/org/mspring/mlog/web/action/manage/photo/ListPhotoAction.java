/**
 * 
 */
package org.mspring.mlog.web.action.manage.photo;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.photo.query.PhotoQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.web.servlet.renderer.JSONRenderer;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Gao Youbo
 * @since Jun 14, 2012
 */
public class ListPhotoAction extends AbstractManageAction implements QueryParameterAware {

    private Map queryParameters;
    private QueryCriterion queryCriterion;

    public String getEncodedQueryParams() {
        if (queryCriterion != null) {
            return queryCriterion.getQueryParamsAsString();
        }
        return null;
    }

    public void setQueryParameters(Map queryParameters) {
        this.queryParameters = queryParameters;
    }

    private Page<Photo> photoPage = new Page<Photo>();
    private Photo photo;

    public Page<Photo> getPhotoPage() {
        return photoPage;
    }

    public void setPhotoPage(Page<Photo> photoPage) {
        this.photoPage = photoPage;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String execute() {
        if (photoPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            photoPage.setSort(sort);
        }
        photoPage.setPageSize(15);

        queryCriterion = new PhotoQueryCriterion(queryParameters);
        photoPage = photoService.queryPhoto(photoPage, queryCriterion);

        JsonObject res = new JsonObject();
        res.addProperty("success", true);
        JsonElement jsonElement = new Gson().toJsonTree(photoPage.getResult());
        res.add("datas", jsonElement);
        res.addProperty("totalCount", photoPage.getTotalCount());
        res.addProperty("pageNo", photoPage.getPageNo());
        res.addProperty("pageSize", photoPage.getPageSize());
        
        System.out.println(res.toString());
        JSONRenderer renderer = new JSONRenderer(res.toString());
        renderer.render(ServletActionContext.getResponse());
        return NONE;
    }

}
