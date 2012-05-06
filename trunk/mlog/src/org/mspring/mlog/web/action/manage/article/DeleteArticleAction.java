/**
 * Mar 17, 20112:47:59 PM
 * www.mspring.org
 * @author (gaoyb)mspring
 */
package org.mspring.mlog.web.action.manage.article;

import org.mspring.mlog.web.action.manage.AbstractManageAction;

/**
 * @author Gao Youbo
 * 
 */
public class DeleteArticleAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = -757887202335931468L;
    private Long[] articleItems;

    public Long[] getArticleItems() {
        return articleItems;
    }

    public void setArticleItems(Long[] articleItems) {
        this.articleItems = articleItems;
    }

    public String execute() {
        try {
            articleService.deleteArticle(articleItems);
            addActionMessage(getText("message.success"));
        } catch (Exception e) {
            // TODO: handle exception
            addActionError(getText("message.failure"));
        }
        // 删除静态html文件
        // StaticBuilder sb = new
        // StaticBuilder(ServletActionContext.getServletContext(),ServletActionContext.getRequest(),ServletActionContext.getResponse());
        // for (int i = 0; i < articleItems.length; i++) {
        // sb.remove(articleItems[i]);
        // }
        return SUCCESS;
    }
}
