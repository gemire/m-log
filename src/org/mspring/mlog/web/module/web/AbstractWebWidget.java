/**
 * 
 */
package org.mspring.mlog.web.module.web;

import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.LinkService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.StatService;
import org.mspring.mlog.service.UserService;
import org.mspring.mlog.service.search.PostSearchService;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
public abstract class AbstractWebWidget {
    protected PostService postService;
    protected CatalogService catalogService;
    protected CommentService commentService;
    protected OptionService optionService;
    protected LinkService linkService;
    protected PostSearchService postSearchService;
    protected StatService statService;
    protected UserService userService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @Autowired
    public void setPostSearchService(PostSearchService postSearchService) {
        this.postSearchService = postSearchService;
    }

    @Autowired
    public void setStatService(StatService statService) {
        this.statService = statService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 提示消息
     * 
     * @param model
     *            参数存放的model对象
     * @param message
     *            消息内容
     * @return
     */
    protected String prompt(Model model, String message) {
        return prompt(model, null, message);
    }

    /**
     * 提示消息
     * 
     * @param model
     *            参数存放的model对象
     * @param title
     *            消息提示框标题
     * @param message
     *            消息内容
     * @return
     */
    protected String prompt(Model model, String title, String message) {
        return prompt(model, title, message, null);
    }

    /**
     * 提示消息
     * 
     * @param model
     *            参数存放的model对象
     * @param title
     *            消息提示框标题
     * @param message
     *            消息内容
     * @param dispatcher
     *            消息提示后跳转到的页面，默认为history.go(-1);
     * @return
     */
    protected String prompt(Model model, String title, String message, String dispatcher) {
        model.addAttribute("title", StringUtils.isBlank(title) ? "提示消息" : title);
        model.addAttribute("message", message);
        if (StringUtils.isNotBlank(dispatcher)) {
            model.addAttribute("dispatcher", dispatcher);
        }
        return "/common/prompt";
    }

    protected void setCurrnetPage(Model model, String currentPage) {
        model.addAttribute(FreemarkerVariableNames.CURRENT_PAGE, currentPage);
    }

}
