/**
 * 
 */
package org.mspring.mlog.web.module.web.widget;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/widget")
public class SystemWidget {
    private CatalogService catalogService;
    private PostService postService;

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * 分类列表
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("listCatalog")
    public String listCatalog(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Catalog> catalogs = catalogService.findAllCatalog();
        model.addAttribute("catalogs", catalogs);
        return "/widget/listCatalog";
    }

    @RequestMapping("recentPost")
    public String recentPost(HttpServletRequest request, HttpServletResponse response, Model model) {
        int num = 20;
        String numStr = request.getParameter("num");
        if (!StringUtils.isBlank(numStr) && ValidatorUtils.isNumber(numStr)) {
            num = Integer.parseInt(numStr);
        }
        List<Post> posts = postService.getRecentPost(num);
        model.addAttribute("posts", posts);
        return "/widget/recentPost";
    }
}
