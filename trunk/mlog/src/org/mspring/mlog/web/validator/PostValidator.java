/**
 * 
 */
package org.mspring.mlog.web.validator;

import org.mspring.mlog.entity.Post;
import org.mspring.platform.utils.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Gao Youbo
 * @since 2012-7-31
 * @Description
 * @TODO Post对象验证
 */
public class PostValidator implements Validator {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> cls) {
        // TODO Auto-generated method stub
        return cls.equals(Post.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     * org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object object, Errors errors) {
        // TODO Auto-generated method stub
        Post post = (Post) object;
        if (StringUtils.isBlank(post.getTitle())) {
            errors.rejectValue("title", "post.title.null", "文章标题不能为空");
        }
        if (post.getCatalog() == null || post.getCatalog().getId() == null) {
            errors.rejectValue("catalog.id", "post.catalog.null", "请选择文章分类");
        }
        if (StringUtils.isBlank(post.getUrl())) {
            errors.rejectValue("url", "post.url.null", "xxx");
        }
    }

}
