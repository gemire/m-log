/**
 * 
 */
package org.mspring.mlog.web.validator;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.utils.PermaLinkUtils;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.validation.AbstractValidator;
import org.mspring.platform.web.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gao Youbo
 * @since 2012-7-31
 * @Description
 * @TODO Post对象验证
 */
@Component
public class PostValidator extends AbstractValidator {

    private static final Logger log = Logger.getLogger(PostValidator.class);

    private PostService postService;

    /**
     * @param postService
     *            the postService to set
     */
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.validation.Validator#validate(java.lang.Object)
     */
    @Override
    public Errors validate(Object target) {
        // TODO Auto-generated method stub
        if (target == null) {
            log.error("validation failure, target object is null.");
            return null;
        }
        Post post = (Post) target;
        Errors errors = getErrorsInstance();

        if (StringUtils.isBlank(post.getTitle())) {
            errors.addErrors("title", "文章标题不能为空");
        }
        if (post.getCatalogs() == null || post.getCatalogs().size() == 0) {
            errors.addErrors("catalog.id", "请选择文章分类");
        }
        if (StringUtils.isBlank(post.getContent())) {
            errors.addErrors("content", "文章内容不能为空");
        }
        if (postService.titleExists(post.getTitle(), post.getId())) {
            errors.addErrors("title", "文章标题已经存在");
        }
        // 验证链接是否存在非法字符串
        if (PermaLinkUtils.hasIllegalCharacter(post.getUrl())) {
            errors.addErrors("url", "链接含有非法字符串");
        }
        // 当填写链接地址时，验证
        if (StringUtils.isNotBlank(post.getUrl()) && PermaLinkUtils.invalidParamLink(post.getUrl())) {
            errors.addErrors("url", "链接不合法");
        }
        // 当填写链接地址时，验证
        if (StringUtils.isNotBlank(post.getUrl()) && postService.urlExists(post.getUrl(), post.getId())) {
            errors.addErrors("url", "文章链接已经存在");
        }
        return errors;
    }
}
