/**
 * 
 */
package org.mspring.codegen.model;

import org.mspring.codegen.db.TableModel;

import com.sun.xml.internal.bind.CycleRecoverable;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public class ColumnModel implements CycleRecoverable {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列注释
     */
    private String colComment;
    /**
     * 列标签，列注释的标签部分。用于打印输出和显示的指定列的建议标题（中文）
     */
    private String columnLabel;
    /**
     * 列注释的备注部分
     */
    private String colRemark;
    /**
     * 面向具体编程语言中类的简单类名，非全限定名，，默认的编程语言为Java，如String
     */
    private String columnSimpleClassName;

    /**
     * 面向具体编程语言中类的完全限定名称，默认的编程语言为Java，如：java.lang.String。
     */
    private String columnClassName;

    /**
     * 面向具体编程语言中类的所在的包(命名空间)，默认的编程语言为Java，如：java.lang
     */
    private String columnClassPackage;

    /**
     * 列的 SQL 类型。
     */
    private int columnType;

    /**
     * 列的 SQL类型名称
     */
    private String columnTypeName;

    /**
     * 获取指定列的指定列宽。对于数值型数据，是指最大精度。对于字符型数据，是指字符串长度。 对于日期时间的数据类型，是指 String
     * 表示形式的字符串长度（假定为最大允许的小数秒组件）。 对于二进制型数据，是指字节长度。对于 ROWID 数据类型，是指字节长度。
     * 对于其列大小不可用的数据类型，则返回 0。
     */
    private int precision = 0;
    /**
     * 列的小数点右边的位数。对于其标度不可用的数据类型，默认为 0。
     */
    private int scale = 0;

    /**
     * 列的最大标准宽度，以字符为单位。
     */
    private int columnDisplaySize;
    /**
     * 标识该列是否为主键
     */
    private boolean primaryKey = false;
    /**
     * 标识该列是否外键列（参照其他表的键）
     */
    private boolean importedKey = false;
    /**
     * 标识该列是否外键列（被其他表参照的键）
     */
    private boolean exportedKey = false;
    /**
     * 标识该列的值能否为空
     */
    private boolean nullable = true;
    /**
     * 标识该列是否为自增列
     */
    private boolean autoIncrement = false;
    /**
     * 标识该列是否为货币类型
     */
    private boolean currency = false;
    /**
     * 标识该列是否为只读列
     */
    private boolean readonly = false;
    /**
     * 标识该列能否作为搜索列，出现在where条件里
     */
    private boolean searchable = true;

    private TableModel tableModel;

    /**
     * @return 取得列名称
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName
     *            要设置的列名称
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return 取得列注释
     */
    public String getColComment() {
        return colComment;
    }

    /**
     * @param colComment
     *            要设置的列注释
     */
    public void setColComment(String colComment) {
        this.colComment = colComment;
    }

    /**
     * @return 取得列备注信息
     */
    public String getColRemark() {
        return colRemark;
    }

    /**
     * @param colRemark
     *            要设置的列备注信息
     */
    public void setColRemark(String colRemark) {
        this.colRemark = colRemark;
    }

    /**
     * @return 取得面向具体编程语言中类的简单类名，非全限定名，如编Java中的String
     */
    public String getColumnSimpleClassName() {
        return columnSimpleClassName;
    }

    /**
     * @param columnSimpleClassName
     *            要设置的面向具体编程语言中类的简单类名，非全限定名，如编java中的String
     */
    public void setColumnSimpleClassName(String columnSimpleClassName) {
        this.columnSimpleClassName = columnSimpleClassName;
    }

    /**
     * @return 取得面向具体编程语言中类的完全限定名称，如java中的java.lang.String。
     */
    public String getColumnClassName() {
        return columnClassName;
    }

    /**
     * @param columnClassName
     *            要设置的面向具体编程语言中类的完全限定名称，如java中的java.lang.String。
     */
    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    /**
     * @return 取得面向具体编程语言中类的所在的包(命名空间)，如java中的java.lang
     */
    public String getColumnClassPackage() {
        return columnClassPackage;
    }

    /**
     * @param columnClassPackage
     *            要设置的面向具体编程语言中类的所在的包(命名空间)，如java中的java.lang
     */
    public void setColumnClassPackage(String columnClassPackage) {
        this.columnClassPackage = columnClassPackage;
    }

    /**
     * @return 获取指定列的指定列宽。 <br>
     *         对于数值型数据，是指最大精度。对于字符型数据，是指字符串长度。 <br>
     *         对于日期时间的数据类型，是指 String 表示形式的字符串长度（假定为最大允许的小数秒组件）。 <br>
     *         对于二进制型数据，是指字节长度。 <br>
     *         对于 ROWID 数据类型，是指字节长度。 <br>
     *         对于其列大小不可用的数据类型，则返回 0。
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * @param precision
     *            要设置的指定列的指定列宽。 <br>
     *            对于数值型数据，是指最大精度。对于字符型数据，是指字符串长度。 <br>
     *            对于日期时间的数据类型，是指 String 表示形式的字符串长度（假定为最大允许的小数秒组件）。 <br>
     *            对于二进制型数据，是指字节长度。 <br>
     *            对于 ROWID 数据类型，是指字节长度。 <br>
     *            对于其列大小不可用的数据类型，则为 0。
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    /**
     * @return 获取列的小数点右边的位数。对于其标度不可用的数据类型，默认为 0。
     */
    public int getScale() {
        return scale;
    }

    /**
     * @param scale
     *            要设置的列的小数点右边的位数。对于其标度不可用的数据类型，默认为 0。
     */
    public void setScale(int scale) {
        this.scale = scale;
    }

    /**
     * @return 判断该列是否为主键列
     */
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey
     *            设置该列为主键列
     */
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return 判断该列是否为外键（参照其他表的键）
     */
    public boolean isImportedKey() {
        return importedKey;
    }

    /**
     * @param importedKey
     *            设置该列为外键列（参照其他表的键）
     */
    public void setImportedKey(boolean importedKey) {
        this.importedKey = importedKey;
    }

    /**
     * @return 判断该列是否被其他表引用（被其他表参照的键）
     */
    public boolean isExportedKey() {
        return exportedKey;
    }

    /**
     * @param exportedKey
     *            设置该列被其他表引用（被其他表参照的键）
     */
    public void setExportedKey(boolean exportedKey) {
        this.exportedKey = exportedKey;
    }

    /**
     * @return 判断该列是否允许空
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * @param nullable
     *            设置该列能否为空
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * 取得在java.sql.Types定义的类型
     * 
     * @return 获取指定列的 SQL 类型。
     */
    public int getColumnType() {
        return columnType;
    }

    /**
     * 设置在java.sql.Types定义的类型
     * 
     * @param columnType
     *            the columnType to set
     */
    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    /**
     * @return 获取指定列的 在java.sql.Types定义的类型的名称。
     */
    public String getColumnTypeName() {
        return columnTypeName;
    }

    /**
     * @param columnTypeName
     *            设置在java.sql.Types定义的类型的名称
     */
    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    /**
     * @return 允许作为指定列宽度的最大标准字符数
     */
    public int getColumnDisplaySize() {
        return columnDisplaySize;
    }

    /**
     * @param 指定列宽度的最大标准字符数
     */
    public void setColumnDisplaySize(int columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    /**
     * @return 取得列标签，列注释的标签部分。用于打印输出和显示的指定列的建议标题
     */
    public String getColumnLabel() {
        return columnLabel;
    }

    /**
     * @param columnLabel
     *            要设置的列标签，列注释的标签部分。用于打印输出和显示的指定列的建议标题
     */
    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    /**
     * @return 判断是否为自动增长列
     */
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    /**
     * @param autoIncrement
     *            设置该列是否为自动增长列
     */
    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    /**
     * @return 判断该列是否为货币类型
     */
    public boolean isCurrency() {
        return currency;
    }

    /**
     * @param currency
     *            设置该列是否为货币类型
     */
    public void setCurrency(boolean currency) {
        this.currency = currency;
    }

    /**
     * @return 判断该列是否为只读列
     */
    public boolean isReadonly() {
        return readonly;
    }

    /**
     * @param readonly
     *            设置该列是否为只读列
     */
    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    /**
     * @return 判断该列能否作为搜索列，出现在where条件里
     */
    public boolean isSearchable() {
        return searchable;
    }

    /**
     * @param searchable
     *            设置该列能否作为搜索列，出现在where条件里
     */
    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
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
        if (!(obj instanceof ColumnModel)) {
            return false;
        }
        ColumnModel other = (ColumnModel) obj;
        if (columnName == null) {
            if (other.columnName != null) {
                return false;
            }
        } else if (!columnName.equalsIgnoreCase(other.columnName)) {
            return false;
        }
        return true;
    }

    /**
     * @return 取得当前列模型所属的表模型对象
     */
    public TableModel getTableModel() {
        return tableModel;
    }

    /**
     * @param tableModel
     *            设置当前列模型所属的表模型对象
     */
    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    /**
     * 实现该接口以防止在序列化或克隆时带来的循环引用问题
     */
    public Object onCycleDetected(Context arg0) {
        ColumnModel temp = new ColumnModel();

        temp.setColumnName(columnName);

        return temp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ColumnModel [autoIncrement=");
        builder.append(autoIncrement);
        builder.append(", colComment=");
        builder.append(colComment);
        builder.append(", colRemark=");
        builder.append(colRemark);
        builder.append(", columnClassName=");
        builder.append(columnClassName);
        builder.append(", columnClassPackage=");
        builder.append(columnClassPackage);
        builder.append(", columnDisplaySize=");
        builder.append(columnDisplaySize);
        builder.append(", columnLabel=");
        builder.append(columnLabel);
        builder.append(", columnName=");
        builder.append(columnName);
        builder.append(", columnSimpleClassName=");
        builder.append(columnSimpleClassName);
        builder.append(", columnType=");
        builder.append(columnType);
        builder.append(", columnTypeName=");
        builder.append(columnTypeName);
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", importedKey=");
        builder.append(importedKey);
        builder.append(", exportedKey=");
        builder.append(exportedKey);
        builder.append(", nullable=");
        builder.append(nullable);
        builder.append(", precision=");
        builder.append(precision);
        builder.append(", primaryKey=");
        builder.append(primaryKey);
        builder.append(", readonly=");
        builder.append(readonly);
        builder.append(", scale=");
        builder.append(scale);
        builder.append(", searchable=");
        builder.append(searchable);
        builder.append("]");
        return builder.toString();
    }
}
