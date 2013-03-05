/**
 * 
 */
package org.mspring.codegen;

import org.mspring.codegen.model.ColumnModel;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO 数据列模型处理器。一般在生成表模型并设置好列模型的数据后被调用作后期处理 譬如，可以使用该接口来实现基于不同编程语言的数据列的类型转换
 */
public interface ColumnHandler {
    /**
     * 对生成的列模型的后续处理操作。譬如可以做一些不同编程语言的数据列的类型转换操作
     * 
     * @param columnModel
     */
    public void handle(ColumnModel columnModel);
}
