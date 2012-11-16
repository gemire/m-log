/**
 * 
 */
package org.mspring.platform.persistence.jdbc;

import java.sql.Connection;

/**
 * @author Gao Youbo
 * @since 2012-11-15
 * @Description 
 * @TODO 
 */
public abstract interface ConnectionManager {
    public abstract Connection getConnection();
}
