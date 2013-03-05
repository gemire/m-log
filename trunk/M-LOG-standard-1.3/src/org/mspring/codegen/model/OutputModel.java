/**
 * 
 */
package org.mspring.codegen.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public class OutputModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private TemplateModel templateModel;
    private InOutType type;
    private String output;

    /**
     * 由一个输出名称实例化一个输出模型
     * 
     * @param name
     *            输出名称
     */
    public OutputModel(String name) {
        super();
        this.name = name;
    }

    /**
     * @return 取得输出名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            设置输出名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 获取产生该输出的模板模型
     */
    public TemplateModel getTemplateModel() {
        return templateModel;
    }

    /**
     * @param templateModel
     *            设置产生该输出的模板模型
     */
    public void setTemplateModel(TemplateModel templateModel) {
        this.templateModel = templateModel;
    }

    /**
     * @return 获取输出类型
     */
    public InOutType getType() {
        return type;
    }

    /**
     * @param type
     *            设置输出类型
     */
    public void setType(InOutType type) {
        this.type = type;
    }

    /**
     * @return 取得需要输出的文件，如果type=text时，在执行构建操作后，取得的是被解析出来的文本内容
     */
    public String getOutput() {
        return output;
    }

    /**
     * @param output
     *            设置需要输出的文件
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * 使用序列化方式深度克隆输出模型
     * 
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public OutputModel deepClone() throws IOException, ClassNotFoundException {
        OutputModel dc = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream bis = new ObjectInputStream(bais);
        dc = (OutputModel) bis.readObject();
        return dc;
    }

}
