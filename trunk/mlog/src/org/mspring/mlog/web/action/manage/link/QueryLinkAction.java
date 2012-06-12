/**
 * 
 */
package org.mspring.mlog.web.action.manage.link;

import java.util.ArrayList;
import java.util.Map;

import org.mspring.mlog.entity.Link;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.link.query.LinkQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.model.field.ColumnField;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class QueryLinkAction extends AbstractManageAction implements QueryParameterAware {
    /**
     * 
     */
    private static final long serialVersionUID = -2307181411690868293L;
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

    private Page<Link> linkPage = new Page<Link>();

    public Page<Link> getLinkPage() {
        return linkPage;
    }

    public void setLinkPage(Page<Link> linkPage) {
        this.linkPage = linkPage;
    }

    public String execute() {
        fieldColumns = new ArrayList<ColumnField>();
        fieldColumns.add(new ColumnField("id", getText("link.field.id")));
        fieldColumns.add(new ColumnField("name", getText("link.field.name")));
        fieldColumns.add(new ColumnField("url", getText("link.field.url")));
        fieldColumns.add(new ColumnField("description", getText("link.field.description")));
        fieldColumns.add(new ColumnField("target", getText("link.field.target")));
        fieldColumns.add(new ColumnField("display", getText("link.field.display")));

        if (linkPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            linkPage.setSort(sort);
        }

        queryCriterion = new LinkQueryCriterion(queryParameters);
        linkPage = linkService.queryLink(linkPage, queryCriterion);
        return SUCCESS;
    }
}
