package org.mspring.platform.dao.query.criterion;
import java.io.Serializable;

import org.mspring.platform.dao.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public abstract interface Criterion extends Serializable
{
  public abstract String toHqlString(QueryBuilder paramQueryBuilder);
}