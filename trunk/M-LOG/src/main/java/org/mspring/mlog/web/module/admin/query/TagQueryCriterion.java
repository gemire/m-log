package org.mspring.mlog.web.module.admin.query;

import java.util.Map;

import org.mspring.platform.persistence.query.AbstractQueryCriterion;
import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * 
 * @author Hu Hongyu
 * @since 2012-8-8
 */
public class TagQueryCriterion extends AbstractQueryCriterion {

	private String queryString;
	private String countString;
	private String whereString;

	/**
	 * 
	 * @param queryParams
	 */
	@SuppressWarnings("rawtypes")
	public TagQueryCriterion(Map queryParams) {
		// TODO Auto-generated constructor stub
		QueryBuilder builder = new QueryBuilder(queryParams);
		builder.startBuild().buildLike("tag.name", "name");
		whereString = builder.endBuild();

		namedQueryParams = builder.getNamedQueryParams();
		queryParamsString = builder.getQueryParamsAsString();

		queryString = "select tag from Tag tag ";
		countString = "select count(*) from Tag tag ";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mspring.platform.persistence.query.QueryCriterion#getQueryString()
	 */
	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return queryString + whereString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mspring.platform.persistence.query.QueryCriterion#getCountString()
	 */
	@Override
	public String getCountString() {
		// TODO Auto-generated method stub
		return countString + whereString;
	}

}
