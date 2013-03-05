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
public class ForeignKeyModel {

    private String pkTableCat;
    private String pkTableSchem;
    private String pkTableName;
    private String pkColumnName;
    private String fkTableCat;
    private String fkTableSchem;
    private String fkTableName;
    private String fkColumnName;
    private short keySeq;
    private short updateRule;
    private short deleteRule;
    private String fkName;
    private String pkName;
    private short Deferrability;

    /**
     * @return 取得主键表的类别(一般为数据库名，可能为空)
     */
    public String getPkTableCat() {
        return pkTableCat;
    }

    /**
     * @param pkTableCat
     *            设置主键表的类别(一般为数据库名，可以为空)
     */
    public void setPkTableCat(String pkTableCat) {
        this.pkTableCat = pkTableCat;
    }

    /**
     * @return 取得主键表的架构(一般为所有者名称，可能为空)
     */
    public String getPkTableSchem() {
        return pkTableSchem;
    }

    /**
     * @param pkTableSchem
     *            设置主键表的架构(一般为所有者名称，可以为空)
     */
    public void setPkTableSchem(String pkTableSchem) {
        this.pkTableSchem = pkTableSchem;
    }

    /**
     * @return 取得主键表的名称
     */
    public String getPkTableName() {
        return pkTableName;
    }

    /**
     * @param pkTableName
     *            设置主键表的名称
     */
    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    /**
     * @return 取得该外键所对应的主键列名称
     */
    public String getPkColumnName() {
        return pkColumnName;
    }

    /**
     * @param pkColumnName
     *            设置该外键所对应的主键列名称
     */
    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    /**
     * @return 取得该外键所在表的类别(一般为数据库名，可能为空)
     */
    public String getFkTableCat() {
        return fkTableCat;
    }

    /**
     * @param fkTableCat
     *            设置该外键所在表的类别(一般为数据库名，可以为空)
     */
    public void setFkTableCat(String fkTableCat) {
        this.fkTableCat = fkTableCat;
    }

    /**
     * @return 取得该外键所在表的架构(一般为用户名，可能为空)
     */
    public String getFkTableSchem() {
        return fkTableSchem;
    }

    /**
     * @param fkTableSchem
     *            设置该外键所在表的架构(一般为用户名，可能为空)
     */
    public void setFkTableSchem(String fkTableSchem) {
        this.fkTableSchem = fkTableSchem;
    }

    /**
     * @return 取得该外键所对应的表名
     */
    public String getFkTableName() {
        return fkTableName;
    }

    /**
     * @param fkTableName
     *            设置该外键所对应的表名
     */
    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }

    /**
     * @return 取得外键列的名称
     */
    public String getFkColumnName() {
        return fkColumnName;
    }

    /**
     * @param fkColumnName
     *            设置外键列的名称
     */
    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    /**
     * @return 取得该键在键序列中的顺序（多个键的时候）
     */
    public short getKeySeq() {
        return keySeq;
    }

    /**
     * @param keySeq
     *            设置该键在键序列中的顺序（多个键的时候）
     */
    public void setKeySeq(short keySeq) {
        this.keySeq = keySeq;
    }

    /**
     * @return 取得该键的更新规则
     */
    public short getUpdateRule() {
        return updateRule;
    }

    /**
     * @param updateRule
     *            设置该键的更新规则
     */
    public void setUpdateRule(short updateRule) {
        this.updateRule = updateRule;
    }

    /**
     * @return 取得该键的删除规则
     */
    public short getDeleteRule() {
        return deleteRule;
    }

    /**
     * @param deleteRule
     *            设置该键的删除规则
     */
    public void setDeleteRule(short deleteRule) {
        this.deleteRule = deleteRule;
    }

    /**
     * @return 取得外键名
     */
    public String getFkName() {
        return fkName;
    }

    /**
     * @param fkName
     *            设置外键名
     */
    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    /**
     * @return 取得主键名
     */
    public String getPkName() {
        return pkName;
    }

    /**
     * @param pkName
     *            设置主键名
     */
    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    /**
     * @return 取得指示是否可延迟约束检查。
     */
    public short getDeferrability() {
        return Deferrability;
    }

    /**
     * @param deferrability
     *            设置是否可延迟约束检查。
     */
    public void setDeferrability(short deferrability) {
        Deferrability = deferrability;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ForeignKeyModel [Deferrability=");
        builder.append(Deferrability);
        builder.append(", deleteRule=");
        builder.append(deleteRule);
        builder.append(", fkColumnName=");
        builder.append(fkColumnName);
        builder.append(", fkName=");
        builder.append(fkName);
        builder.append(", fkTableCat=");
        builder.append(fkTableCat);
        builder.append(", fkTableName=");
        builder.append(fkTableName);
        builder.append(", fkTableSchem=");
        builder.append(fkTableSchem);
        builder.append(", keySeq=");
        builder.append(keySeq);
        builder.append(", pkColumnName=");
        builder.append(pkColumnName);
        builder.append(", pkName=");
        builder.append(pkName);
        builder.append(", pkTableCat=");
        builder.append(pkTableCat);
        builder.append(", pkTableName=");
        builder.append(pkTableName);
        builder.append(", pkTableSchem=");
        builder.append(pkTableSchem);
        builder.append(", updateRule=");
        builder.append(updateRule);
        builder.append("]");
        return builder.toString();
    }
}
