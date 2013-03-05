/**
 * 
 */
package org.mspring.codegen.db;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public class PrimaryKeyModel {
    private String tableCat;
    private String tableSchem;
    private String tableName;
    private String columnName;
    private short keySeq;
    private String pkName;

    /**
     * @return 取得表目录(一般为数据库名，可能为空)
     */
    public String getTableCat() {
        return tableCat;
    }

    /**
     * @param tableCat
     *            设置表目录(一般为数据库名，可以为空)
     */
    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    /**
     * @return 取得表架构(一般为所有者名称，可能为空)
     */
    public String getTableSchem() {
        return tableSchem;
    }

    /**
     * @param tableSchem
     *            设置表架构(一般为所有者名称，可以为空)
     */
    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

    /**
     * @return 取得提供其主键信息的表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     *            设置提供其主键信息的表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return 取得该主键对应的列名称
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName
     *            设置该主键对应的列名称
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return 取得该主键在键序列中的序号（多个键的时候）
     */
    public short getKeySeq() {
        return keySeq;
    }

    /**
     * @param keySeq
     *            设置该主键在键序列中的序号（多个键的时候）
     */
    public void setKeySeq(short keySeq) {
        this.keySeq = keySeq;
    }

    /**
     * @return 取得该主键名称
     */
    public String getPkName() {
        return pkName;
    }

    /**
     * @param pkName
     *            设置该主键名称
     */
    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PrimaryKeyModel [columnName=");
        builder.append(columnName);
        builder.append(", keySeq=");
        builder.append(keySeq);
        builder.append(", pkName=");
        builder.append(pkName);
        builder.append(", tableCat=");
        builder.append(tableCat);
        builder.append(", tableName=");
        builder.append(tableName);
        builder.append(", tableSchem=");
        builder.append(tableSchem);
        builder.append("]");
        return builder.toString();
    }
}
