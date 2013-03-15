/**
 * 
 */
package org.mspring.mlog.support.formater;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.service.TagService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.format.Formatter;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO
 */
public class TagFormatter implements Formatter<Object> {

    private static final Logger log = Logger.getLogger(TagFormatter.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.format.Printer#print(java.lang.Object,
     * java.util.Locale)
     */
    @Override
    public String print(Object obj, Locale locale) {
        // TODO Auto-generated method stub
        StringBuffer result = new StringBuffer();
        if (obj != null) {
            if (obj instanceof Tag) { // 如果输出对象是一个Tag对象
                Tag tag = (Tag) obj;
                result.append(tag.getName());
            }
            else if (obj instanceof Set) { // 如果输出对象是一个tag的集合
                Set<Tag> tagSet = (Set<Tag>) obj;
                int i = 0;
                for (Tag tag : tagSet) {
                    result.append(tag.getName());
                    if (i < (tagSet.size() - 1)) {
                        result.append(",");
                    }
                    i++;
                }
            }
            else if (obj instanceof List) { // 如果输出对象是一个tag集合
                List<Tag> tagList = (List<Tag>) obj;
                int i = 0;
                for (Tag tag : tagList) {
                    result.append(tag.getName());
                    if (i < (tagList.size() - 1)) {
                        result.append(",");
                    }
                    i++;
                }
            }
        }
        return result.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.format.Parser#parse(java.lang.String,
     * java.util.Locale)
     */
    @Override
    public Object parse(String name, Locale locale) throws ParseException {
        // TODO Auto-generated method stub
        TagService tagService = ServiceFactory.getTagService();
        List<Tag> tags = new ArrayList<Tag>();
        if (StringUtils.isNotBlank(name)) {
            String[] tagArray = StringUtils.split(name, ",");
            Tag tag = null;
            for (String tagName : tagArray) {
                if (StringUtils.isNotBlank(tagName)) {
                    tagName = tagName.trim();
                    tag = tagService.findUniqueByName(tagName);
                    // 如果不存在,那么插入
                    if (tag == null) {
                        tag = new Tag();
                        tag.setCreateTime(new Date());
                        tag.setName(tagName);
                        tag = tagService.createTag(tag);
                    }
                    if (!tags.contains(tag)) { //不再tag集合中的才添加，避免tag和post关联表中联合主键冲突
                        tags.add(tag);
                    }
                }
            }
        }
        return tags;
    }

}
