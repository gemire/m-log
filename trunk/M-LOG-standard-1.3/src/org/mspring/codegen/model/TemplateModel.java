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
public class TemplateModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private InOutType type;
    private String template;

    /**
     * @return 获取模板名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            设置模板名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 获取模板类型
     */
    public InOutType getType() {
        return type;
    }

    /**
     * @param type
     *            设置模板类型
     */
    public void setType(InOutType type) {
        this.type = type;
    }

    /**
     * @return 获取模板内容或模板路径。如果模板类型为text，返回的是内容，否则返回模板文件路径
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template
     *            设置模板内容或所在的文件路径。如果模板类型为text，则设置的是内容，否则为文件路径
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * 使用序列化方式深度克隆模板对象模型
     * 
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public TemplateModel deepClone() throws IOException, ClassNotFoundException {
        TemplateModel dc = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream bis = new ObjectInputStream(bais);
        dc = (TemplateModel) bis.readObject();
        return dc;
    }

}
