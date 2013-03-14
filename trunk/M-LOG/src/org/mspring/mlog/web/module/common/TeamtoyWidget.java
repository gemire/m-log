/**
 * 
 */
package org.mspring.mlog.web.module.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.api.teamtoy.TeamtoyUtils;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.web.render.JSONRender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-3-14
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/common/teamtoy")
public class TeamtoyWidget {
    @RequestMapping("/team_members")
    public void team_members(HttpServletRequest request, HttpServletResponse response, Model model) {
        String json = TeamtoyUtils.team_members();
        JSONRender render = new JSONRender(json);
        render.render(response);
    }

    @RequestMapping("/todo_list")
    public void todo_list(HttpServletRequest request, HttpServletResponse response, Model model) {
        String json = TeamtoyUtils.todo_list();
        JSONRender render = new JSONRender(json);
        render.render(response);
    }
}
