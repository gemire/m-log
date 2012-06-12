package org.mspring.mlog.web.action.manage.tag;

import java.util.ArrayList;
import java.util.Map;

import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.tag.query.TagQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.model.field.ColumnField;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

public class QueryTagAction extends AbstractManageAction implements QueryParameterAware {

    /**
     * 
     */
    private static final long serialVersionUID = -276195505243714747L;
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

    private Page<Tag> tagPage = new Page<Tag>();

    public Page<Tag> getTagPage() {
        return tagPage;
    }

    public void setTagPage(Page<Tag> tagPage) {
        this.tagPage = tagPage;
    }

    public String execute() {
        fieldColumns = new ArrayList<ColumnField>();
        fieldColumns.add(new ColumnField("id", getText("tag.field.id")));
        fieldColumns.add(new ColumnField("name", getText("tag.field.name")));
        fieldColumns.add(new ColumnField("order", getText("tag.field.order")));

        if (tagPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            tagPage.setSort(sort);
        }
        queryCriterion = new TagQueryCriterion(queryParameters);
        tagPage = tagService.queryTag(tagPage, queryCriterion);
        return SUCCESS;
    }
}
