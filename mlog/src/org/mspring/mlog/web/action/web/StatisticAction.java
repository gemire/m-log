/**
 * 
 */
package org.mspring.mlog.web.action.web;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.action.CommonActionSupport;

/**
 * @author Gao Youbo
 * @since May 5, 2012
 * 
 */
public class StatisticAction extends CommonActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -4867194233364227839L;

    private static final Logger log = Logger.getLogger(StatisticAction.class);

    private Long articleId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 文章点击量统计
     * 
     * @return
     */
    public String singleStatistic() {
        try {
            articleService.updateArticleClick(articleId);
        } catch (Exception e) {
            // TODO: handle exception
            log.warn("update article click error, articleId = " + articleId, e);
        }
        return NONE;
    }

    /**
     * 网站访问量统计
     * 
     * @return
     */
    public String visitStatistic() {
        return NONE;
    }
}
