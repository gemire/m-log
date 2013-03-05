/**
 * 
 */
package org.mspring.codegen.builder;

import java.util.Map;

import org.mspring.codegen.model.OutputModel;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public interface Builder {
    /**
     * 构建操作。返回类型Map&lt;输出标识,输出模型&gt;
     * 
     * @return 返回输出模型的映射。
     */
    Map<String, OutputModel> build();
}
