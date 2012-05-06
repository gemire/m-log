/**
 * 
 */
package org.mspring.mlog.web.action.manage.tag;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.web.action.CommonActionSupport;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.servlet.renderer.JSONRenderer;
import org.mspring.platform.web.servlet.renderer.TextRenderer;

/**
 * @author Gao Youbo
 * @since Apr 20, 2012
 */
public class TagForAjaxAction extends CommonActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -2681257677286038813L;
    
    private String name;
    private List<Tag> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    /**
     * 查找所有tag
     * @return
     */
    public String findAllTagsByAjax(){
        tags = tagService.findAllTag();
        JSONRenderer render = new JSONRenderer(tags, true);
        render.render(ServletActionContext.getResponse());
        return NONE;
    }
    
    /**
     * 创建tag
     * @return
     */
    public String createTagByAjax(){
        if (!StringUtils.isBlank(name)) {
            Tag tag = new Tag();
            tag.setName(name);
            tag = tagService.createTag(tag);
            JSONRenderer render = new JSONRenderer(tag);
            render.render(ServletActionContext.getResponse());
        }
        return NONE;
    }
    
    /**
     * 检测tag名称是否重复
     * @return
     */
    public String tagWhetherRepeat(){
        Tag tag = tagService.getTagByName(name);
        String flag = "true";
        if (tag != null) {
            flag = "false";
        }
        TextRenderer renderer = new TextRenderer(flag);
        renderer.render(ServletActionContext.getResponse());
        return NONE;
    }
}
