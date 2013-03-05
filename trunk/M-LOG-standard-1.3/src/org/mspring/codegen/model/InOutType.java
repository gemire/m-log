/**
 * 
 */
package org.mspring.codegen.model;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO 输入输出类型
 */
public enum InOutType {
    /**
     * file
     */
    FILE("file", "文件"),
    /**
     * text
     */
    TEXT("text", "文本");

    /**
     * 由一个值和标签实例化一个输入输出类型枚举
     * 
     * @param value
     * @param label
     */
    private InOutType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    private final String value;
    private final String label;

    /**
     * 取得枚举的值
     * 
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * 取得枚举的标签
     * 
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * 根据枚举值获取对应的显示标签
     * 
     * @param value
     * @return
     */
    public static String getLabelByValue(String value) {
        InOutType[] types = InOutType.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].value.equals(value))
                return types[i].getLabel();
        }
        return "";
    }
}
