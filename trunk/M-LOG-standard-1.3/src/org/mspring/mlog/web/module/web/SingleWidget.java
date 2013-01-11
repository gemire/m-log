/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.utils.PermissionUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
@Widget("webSingleWidget")
@RequestMapping({ "/", "" })
public class SingleWidget extends AbstractWebWidget {

    @RequestMapping("/post")
    public String single(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        Object postObj = request.getAttribute("post");
        if (postObj != null) {
            Post post = (Post) postObj;
            if (!PermissionUtils.hasPostPermission(post, session)) {
                model.addAttribute("postId", post.getId());
                model.addAttribute("postUrl", post.getUrl());
                return "skin:/post-token";
            }
            model.addAttribute(FreemarkerVariableNames.POST, postObj);
        }
        setCurrnetPage(model, PageNames.SINGLE);
        return "skin:/single";
    }

    @RequestMapping("/post/token")
    public String token(@RequestParam(required = true) Long postId, @RequestParam(required = true) String postUrl, @RequestParam(required = false) String password, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        boolean has_permission = PermissionUtils.hasPostPermission(postId, password);
        if (!has_permission) {
            model.addAttribute("postId", postId);
            model.addAttribute("postUrl", postUrl);
            model.addAttribute("not_token", false);
            return "skin:/post-token";
        }
        PermissionUtils.setPostPermission(postId, password, request);
        return "redirect:" + postUrl;
    }
}
