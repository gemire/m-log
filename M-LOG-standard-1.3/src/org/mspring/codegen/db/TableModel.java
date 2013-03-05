/**
 * 
 */
package org.mspring.codegen.db;

import java.util.ArrayList;
import java.util.List;

import org.mspring.codegen.model.ColumnModel;

import com.sun.xml.internal.bind.CycleRecoverable;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public class TableModel implements CycleRecoverable {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表所属类别
     */
    private String catalog;
    /**
     * 表所属模式
     */
    private String schema;

    /**
     * 表注释(中文)
     */
    private String tabComment;
    /**
     * 表标签 也就是表的中文别名
     */
    private String tableLabel;

    /**
     * 列模型集合
     */
    private List<ColumnModel> columnList = new ArrayList<ColumnModel>();

    /**
     * 主键模型集合
     */
    private List<PrimaryKeyModel> primaryKeyList = new ArrayList<PrimaryKeyModel>();

    /**
     * 外键模型集合（引入）
     */
    private List<ForeignKeyModel> importedKeyList = new ArrayList<ForeignKeyModel>();
    /**
     * 外键模型集合（导出）
     */
    private List<ForeignKeyModel> exportedKeyList = new ArrayList<ForeignKeyModel>();

    /**
     * @return 取得表名称
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     *            设置表名称
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return 取得表的目录名称(一般为数据库名，可能为空)
     */
    public String getCatalog() {
        return catalog;
    }

    /**
     * @param catalog
     *            设置表的目录名称(一般为数据库名，可以为空)
     */
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    /**
     * @return 取得表的架构名称(一般为表的所有者，可能为空)
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema
     *            设置表的架构名称(一般为表的所有者，可以为空)
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * @return 取得表的注释
     */
    public String getTabComment() {
        return tabComment;
    }

    /**
     * @param tabComment
     *            设置表的注释
     */
    public void setTabComment(String tabComment) {
        this.tabComment = tabComment;
    }

    /**
     * @return 取得表的显示标签
     */
    public String getTableLabel() {
        return tableLabel;
    }

    /**
     * @param tableLabel
     *            设置表的显示标签
     */
    public void setTableLabel(String tableLabel) {
        this.tableLabel = tableLabel;
    }

    /**
     * @return 取得该表下包含的所有列模型
     */
    public List<ColumnModel> getColumnList() {
        return columnList;
    }

    /**
     * @param columnList
     *            设置该表下包含的所有列模型
     */
    public void setColumnList(List<ColumnModel> columnList) {
        this.columnList = columnList;
    }

    /**
     * @return 取得该表下包含的所有主键模型
     */
    public List<PrimaryKeyModel> getPrimaryKeyList() {
        return primaryKeyList;
    }

    /**
     * @param primaryKeyList
     *            设置该表下包含的所有主键模型
     */
    public void setPrimaryKeyList(List<PrimaryKeyModel> primaryKeyList) {
        this.primaryKeyList = primaryKeyList;
    }

    /**
     * @return 取得该表的外键模型集合（引入）
     */
    public List<ForeignKeyModel> getImportedKeyList() {
        return importedKeyList;
    }

    /**
     * @param importedKeyList
     *            设置该表的外键模型集合（引入）
     */
    public void setImportedKeyList(List<ForeignKeyModel> importedKeyList) {
        this.importedKeyList = importedKeyList;
    }

    /**
     * @return 取得该表的外键模型集合（导出）
     */
    public List<ForeignKeyModel> getExportedKeyList() {
        return exportedKeyList;
    }

    /**
     * @param exportedKeyList
     *            设置该表的外键模型集合（导出）
     */
    public void setExportedKeyList(List<ForeignKeyModel> exportedKeyList) {
        this.exportedKeyList = exportedKeyList;
    }

    /**
     * 根据列名取得列模型
     * 
     * @param name
     *            列名称
     * @return 返回的列模型
     */
    public ColumnModel getColumnByName(String name) {
        for (ColumnModel cm : columnList) {
            if (cm.getColumnName().equalsIgnoreCase(name))
                return cm;
        }
        return null;
    }

    /**
     * 判断是否有主键
     * 
     * @return 如果有则返回true，否则返回false
     */
    public boolean hasPrimaryKey() {
        return primaryKeyList != null && primaryKeyList.size() > 0;
    }

    /**
     * 判断指定列名是否为主键
     * 
     * @param columnName
     *            要判断的列名称
     * @return 如果指定列为主键则返回true，否则返回false
     */
    public boolean isPrimaryKey(String columnName) {
        boolean result = false;
        for (PrimaryKeyModel pkModel : primaryKeyList) {
            if (pkModel.getColumnName().equalsIgnoreCase(columnName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 判断该表是否有外键引入(参照其他表的键)
     * 
     * @return 存在外键引入则返回true，否则返回false
     */
    public boolean hasImportedKey() {
        return importedKeyList != null && importedKeyList.size() > 0;
    }

    /**
     * 判断该表指定列名是否为外键引入(参照其他表的键)
     * 
     * @param columnName
     *            列名称
     * @return 如果该表指定列是引入的外键则返回true，否则返回false
     */
    public boolean isImportedKey(String columnName) {
        boolean result = false;
        for (ForeignKeyModel fkModel : importedKeyList) {
            if (fkModel.getFkColumnName().equalsIgnoreCase(columnName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 判断该表是否有被其他表参照的键
     * 
     * @return 如果存在被其他表参照的键则返回true，否则返回false
     */
    public boolean hasExportedKey() {
        return exportedKeyList != null && exportedKeyList.size() > 0;
    }

    /**
     * 判断该表指定列名是否为外键导出（被其他表参照的键）
     * 
     * @param columnName
     *            列名称
     * @return 如果该表指定列被其他表引用作为外键则返回true，否则返回false
     */
    public boolean isExportedKey(String columnName) {
        boolean result = false;
        for (ForeignKeyModel fkModel : exportedKeyList) {
            if (fkModel.getFkColumnName().equalsIgnoreCase(columnName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 实现该接口以防止在序列化或克隆时带来的循环引用问题
     */
    public Object onCycleDetected(Context arg0) {
        TableModel temp = new TableModel();

        temp.setTableName(tableName);

        return temp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TableModel)) {
            return false;
        }
        TableModel other = (TableModel) obj;
        if (tableName == null) {
            if (other.tableName != null) {
                return false;
            }
        } else if (!tableName.equals(other.tableName)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TableModel [catalog=");
        builder.append(catalog);
        builder.append(", columnList=");
        builder.append(columnList);
        builder.append(", importedKeyList=");
        builder.append(importedKeyList);
        builder.append(", exportedKeyList=");
        builder.append(exportedKeyList);
        builder.append(", primaryKeyList=");
        builder.append(primaryKeyList);
        builder.append(", schema=");
        builder.append(schema);
        builder.append(", tabComment=");
        builder.append(tabComment);
        builder.append(", tableLabel=");
        builder.append(tableLabel);
        builder.append(", tableName=");
        builder.append(tableName);
        builder.append("]");
        return builder.toString();
    }

}
