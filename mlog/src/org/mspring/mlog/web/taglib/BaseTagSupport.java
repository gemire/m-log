/**
 * 
 */
package org.mspring.mlog.web.taglib;

import javax.servlet.jsp.tagext.TagSupport;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.CategoryService;
import org.mspring.mlog.service.TagService;

/**
 * @author Gao Youbo (http://www.mspring.org)
 * @since 2011-12-3
 */
public class BaseTagSupport extends TagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1589122599642276114L;
    protected TagService tagService = ServiceFactory.getTagService();
    protected ArticleService articleService = ServiceFactory.getArticleService();
    protected CategoryService categoryService = ServiceFactory.getCategoryService();
}
