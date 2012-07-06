/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.util.Map;

import org.mspring.mlog.entity.Album;
import org.mspring.mlog.web.action.web.query.AlbumQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * @author Gao Youbo
 * @since May 21, 2012
 */
public class AlbumAction extends CommonWebActionSupport implements QueryParameterAware {

    /**
     * 
     */
    private static final long serialVersionUID = -4375871360452481638L;
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

    private Page<Album> albumPage = new Page<Album>();

    public Page<Album> getAlbumPage() {
        return albumPage;
    }

    public void setAlbumPage(Page<Album> albumPage) {
        this.albumPage = albumPage;
    }

    public String execute() {
        queryCriterion = new AlbumQueryCriterion(queryParameters);
        albumPage = albumService.queryAlbum(albumPage, queryCriterion);
        return SUCCESS;
    }
}
