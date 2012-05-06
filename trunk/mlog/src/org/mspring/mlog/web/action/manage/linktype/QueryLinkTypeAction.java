/**
 * 
 */
package org.mspring.mlog.web.action.manage.linktype;

import java.util.ArrayList;
import java.util.Map;

import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.linktype.query.LinkTypeQueryCriterion;
import org.mspring.platform.dao.query.QueryCriterion;
import org.mspring.platform.dao.support.Page;
import org.mspring.platform.dao.support.Sort;
import org.mspring.platform.model.field.ColumnField;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * @author Gao Youbo(http://www.mspring.org)
 * @since 2011-12-6
 */
public class QueryLinkTypeAction extends AbstractManageAction implements QueryParameterAware {
    /**
     * 
     */
    private static final long serialVersionUID = -8854881905013839228L;
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

    private Page<LinkType> linkTypePage = new Page<LinkType>();

    public Page<LinkType> getLinkTypePage() {
        return linkTypePage;
    }

    public void setLinkTypePage(Page<LinkType> linkTypePage) {
        this.linkTypePage = linkTypePage;
    }

    public String execute() {
        fieldColumns = new ArrayList<ColumnField>();
        fieldColumns.add(new ColumnField("id", getText("linktype.field.id")));
        fieldColumns.add(new ColumnField("name", getText("linktype.field.name")));
        fieldColumns.add(new ColumnField("description", getText("linktype.field.description")));

        if (linkTypePage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            linkTypePage.setSort(sort);
        }

        queryCriterion = new LinkTypeQueryCriterion(queryParameters);
        linkTypePage = linkTypeService.queryLinkType(linkTypePage, queryCriterion);
        return SUCCESS;
    }
}
