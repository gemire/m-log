/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Job;
import org.mspring.mlog.service.JobService;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2013-1-8
 * @Description
 * @TODO 定时任务管理
 */
@Widget
@RequestMapping("/admin/job")
public class JobWidget {
    @Autowired
    private JobService jobService;

    @RequestMapping("/list")
    public String list(@ModelAttribute Page<Job> jobPage, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (jobPage == null) {
            jobPage = new Page<Job>();
        }
        jobPage.setSort(new Sort("id", Sort.ASC));

        jobPage = jobService.findJob(jobPage, "select job from Job job");

        List<Field> columnfields = new ArrayList<Field>();
        columnfields.add(new ColumnField("id", "编号"));
        columnfields.add(new ColumnField("name", "名称"));
        columnfields.add(new ColumnField("execType", "执行类型"));
        columnfields.add(new ColumnField("expression", "执行规则"));
        columnfields.add(new ColumnField("jobClass", "执行类"));
        columnfields.add(new ColumnField("lastExec", "最后执行时间"));

        model.addAttribute("columnfields", columnfields);
        model.addAttribute("execType", Job.ExecType.getExecTypeMap());
        model.addAttribute("jobPage", jobPage);
        return "/admin/job/listJob";
    }

    @RequestMapping("/ctrl")
    public String ctrl(@ModelAttribute Page<Job> jobPage, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        
        return list(jobPage, queryParams, request, response, model);
    }

    @RequestMapping("/exec")
    public String exec(@RequestParam(required = false) Long[] ids, @RequestParam(required = false) Long[] enabledIds, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (enabledIds != null && enabledIds.length > 0) {
            
        }
        return "redirect:/admin/job/list";
    }
}
