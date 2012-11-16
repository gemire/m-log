/**
 * 
 */
package org.mspring.platform.persistence.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import org.mspring.platform.utils.Assert;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 * @author Gao Youbo
 * @since 2012-11-15
 * @Description
 * @TODO
 */
public class DefaultConnectionManager implements ConnectionManager {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 
     */
    public DefaultConnectionManager() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param dataSource
     */
    public DefaultConnectionManager(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.persistence.jdbc.ConnectionManager#getConnection()
     */
    @Override
    public Connection getConnection() {
        // TODO Auto-generated method stub
        Assert.notNull(this.dataSource);
        return DataSourceUtils.getConnection(this.dataSource);
    }

}
