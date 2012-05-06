package org.mspring.platform.dao.query.criterion;

import org.mspring.platform.dao.query.QueryBuilder;
import org.mspring.platform.dao.query.support.QueryHelper;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class LikeExpression extends SimpleExpression {
	private final MatchMode matchMode;
	
	protected LikeExpression(String hqlName, String paramKey, String operation, MatchMode matchMode, Class paramClass) {
		super(hqlName, paramKey, operation, paramClass);
		this.matchMode = matchMode;
	}

	protected void setParams(QueryBuilder builder, String value, Class clazz) {
		builder.getNamedQueryParams().put(QueryHelper.qualifyHql(hqlName), matchMode.toMatchString(value));
		builder.getValidQueryParams().put(paramKey, value);
	}
	
}
