/**
 * 
 */
package org.mspring.codegen.model;

import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public interface BuildConfig {
    /**
     * 获取输出编码类型
     * 
     * @return
     */
    String getOutputEncoding();

    /**
     * 获取数据模型
     * 
     * @return
     */
    Map<String, Object> getDataModel();

    /**
     * 获取输出模型
     * 
     * @return
     */
    Map<String, OutputModel> getOutputModel();
}
