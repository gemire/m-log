/**
 * 
 */
package org.mspring.mlog.web.module.admin.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.utils.CatalogUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-2-26
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/admin/tools/movecatalog")
public class MoveCatalogWidget extends AbstractAdminWidget {
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private PostService postService;

    @RequestMapping({ "/", "" })
    public String move(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("catalogs", CatalogUtils.getTreeList(catalogService.findAllCatalog()));
        return "/admin/tools/movecatalog/move";
    }

    @RequestMapping("/move")
    @Log
    public String doMove(HttpServletRequest request, HttpServletResponse response, Model model) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        if (StringUtils.isBlank(from)) {
            model.addAttribute("message", "请选择需要被移动的分类");
            return move(request, response, model);
        }
        if (StringUtils.isBlank(to)) {
            model.addAttribute("message", "请选择移动到的分类");
            return move(request, response, model);
        }
        if (StringUtils.equals(from, to)) {
            model.addAttribute("message", "不能将文章移动到自己");
            return move(request, response, model);
        }
        postService.movePostCatalog(new Long(from), new Long(to));
        return move(request, response, model);
    }
}
