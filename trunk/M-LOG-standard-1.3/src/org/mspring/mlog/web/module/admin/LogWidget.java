/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Log;
import org.mspring.mlog.service.LogService;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.query.LogQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-3-12
 * @description
 * @TODO
 */
@Widget
@RequestMapping("/admin/log")
public class LogWidget extends AbstractAdminWidget {

    @Autowired
    private LogService logService;

    @RequestMapping("/view")
    public String view(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "LogWidget_view_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请选择要查看的日志");
        }
        Log log = logService.getLogById(id);
        if (log == null) {
            return prompt(model, "请选择要查看的日志");
        }
        model.addAttribute("log", log);
        setSessionAttribute(request, "LogWidget_view_id", id);
        return "/admin/log/viewLog";
    }

    @RequestMapping("/list")
    public String list(@ModelAttribute Page<Log> logPage, @ModelAttribute Log log, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (logPage == null) {
            logPage = new Page<Log>();
        }
        logPage.setSort(new Sort("id", Sort.DESC));
        logPage = logService.findLog(new LogQueryCriterion(queryParams), logPage);
        model.addAttribute("logPage", logPage);
        model.addAllAttributes(queryParams);
        return "/admin/log/listLog";
    }

    @RequestMapping("/clear")
    public String log(@RequestParam(required = false) int days, HttpServletRequest request, HttpServletResponse response, Model model) {
        logService.deleteLogs(days);
        return prompt(model, "系统提示", "日志清理成功！", "/admin/log/list");
    }
}
