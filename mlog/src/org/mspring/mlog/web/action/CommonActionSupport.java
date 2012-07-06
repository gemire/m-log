/**
 * 
 */
package org.mspring.mlog.web.action;

import java.util.Map;

import org.mspring.mlog.service.AlbumService;
import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.CacheService;
import org.mspring.mlog.service.CategoryService;
import org.mspring.mlog.service.CommentService;
import org.mspring.mlog.service.CommonService;
import org.mspring.mlog.service.LinkService;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.mlog.service.MailService;
import org.mspring.mlog.service.MenuService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.PhotoService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.service.ThemeService;
import org.mspring.mlog.service.search.ArticleSearchService;
import org.mspring.mlog.service.security.TreeService;
import org.mspring.mlog.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gao Youbo
 * @since Mar 17, 2012
 */
public class CommonActionSupport extends AbstractActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -6179305892490695817L;
    protected UserService userService;
    protected CategoryService categoryService;
    protected ArticleService articleService;
    protected TagService tagService;
    protected MenuService menuService;
    protected CommentService commentService;
    // protected CompassService compassService;
    protected ArticleSearchService articleSearchService;
    protected LinkService linkService;
    protected LinkTypeService linkTypeService;
    protected AlbumService albumService;
    protected PhotoService photoService;
    protected OptionService optionService;
    protected TreeService treeService;
    protected ThemeService themeService;
    protected MailService mailService;
    protected CacheService cacheService;
    protected CommonService commonService;
    protected Map<String, String> config;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    // @Autowired
    // private void setCompassService(CompassService compassService) {
    // this.compassService = compassService;
    // }

    @Autowired
    public void setArticleSearchService(ArticleSearchService articleSearchService) {
        this.articleSearchService = articleSearchService;
    }

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @Autowired
    public void setLinkTypeService(LinkTypeService linkTypeService) {
        this.linkTypeService = linkTypeService;
    }

    @Autowired
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Autowired
    public void setTreeService(TreeService treeService) {
        this.treeService = treeService;
    }

    @Autowired
    public void setThemeService(ThemeService themeService) {
        this.themeService = themeService;
    }

    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public Map<String, String> getConfig() {
        return optionService.getOptions();
    }

}
