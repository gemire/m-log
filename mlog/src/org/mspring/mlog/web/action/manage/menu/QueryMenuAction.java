/**
 * @since Jul 10, 2011
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.action.manage.menu;

import java.util.ArrayList;
import java.util.Map;

import org.mspring.mlog.entity.Menu;
import org.mspring.mlog.web.action.manage.AbstractManageAction;
import org.mspring.mlog.web.action.manage.menu.query.MenuQueryCriterion;
import org.mspring.platform.persistence.query.QueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.model.field.ColumnField;
import org.mspring.platform.web.xwork.interceptor.QueryParameterAware;

/**
 * 
 * @author Gao Youbo
 */
public class QueryMenuAction extends AbstractManageAction implements QueryParameterAware {
    /**
     * 
     */
    private static final long serialVersionUID = -559117425895454722L;
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

    private Page<Menu> menuPage = new Page<Menu>();

    public Page<Menu> getMenuPage() {
        return menuPage;
    }

    public void setMenuPage(Page<Menu> menuPage) {
        this.menuPage = menuPage;
    }

    public String execute() {
        fieldColumns = new ArrayList<ColumnField>();
        fieldColumns.add(new ColumnField("id", "编号"));
        fieldColumns.add(new ColumnField("name", "名称"));
        fieldColumns.add(new ColumnField("type", "类型"));
        fieldColumns.add(new ColumnField("parent", "父类"));
        fieldColumns.add(new ColumnField("url", "URL"));
        fieldColumns.add(new ColumnField("createTime", "创建时间"));

        if (menuPage.getSort() == null) {
            Sort sort = new Sort("id", Sort.DESC);
            menuPage.setSort(sort);
        }

        queryCriterion = new MenuQueryCriterion(queryParameters);
        menuPage = menuService.queryMenu(menuPage, queryCriterion);
        return SUCCESS;
    }
}
