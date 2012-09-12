package org.mspring.platform.persistence.query.criterion;
import java.io.Serializable;

import org.mspring.platform.persistence.query.QueryBuilder;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public abstract interface Criterion extends Serializable
{
  public abstract String toHqlString(QueryBuilder paramQueryBuilder);
}