/**
 * 
 */
package org.mspring.mlog.entity;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * @author Gao Youbo
 * @since 2012-7-12
 * @Description
 * @TODO
 */
public class PrefixImprovedNamingStrategy extends ImprovedNamingStrategy {
    /**
     * 
     */
    private static final long serialVersionUID = -1319770630294281011L;
    private String tablePrefix = "mlog_";
    private String columnPrefix = "_";

    public String tableName(String tableName) {
        return this.tablePrefix + super.tableName(tableName);
    }

    public String columnName(String columnName) {
        return this.columnPrefix + super.columnName(columnName);
    }

    public String propertyToColumnName(String propertyName) {
        return this.columnPrefix + super.propertyToColumnName(propertyName);
    }
}
