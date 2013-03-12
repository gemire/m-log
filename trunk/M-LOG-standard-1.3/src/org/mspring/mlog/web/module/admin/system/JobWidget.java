/**
 * 
 */
package org.mspring.mlog.web.module.admin.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.entity.Job;
import org.mspring.mlog.entity.JobLog;
import org.mspring.mlog.service.JobLogService;
import org.mspring.mlog.service.JobService;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.admin.AbstractAdminWidget;
import org.mspring.mlog.web.module.admin.system.query.JobLogQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.support.field.ColumnField;
import org.mspring.platform.support.field.Field;
import org.mspring.platform.utils.ArrayUtils;
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
@RequestMapping("/admin/system/job")
public class JobWidget extends AbstractAdminWidget {
    @Autowired
    private JobService jobService;
    @Autowired
    private JobLogService jobLogService;

    @RequestMapping("/list")
    @Log
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
        return "/admin/system/job/listJob";
    }

    @RequestMapping("/ctrl")
    @Log
    public String ctrl(@RequestParam(required = false) Long[] ids, @RequestParam(required = false) Long[] enabledIds, @RequestParam(required = false) String[] expressions, @RequestParam(required = false) String[] execTypes, @ModelAttribute Page<Job> jobPage, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 设置可用
        if (enabledIds != null && enabledIds.length > 0) {
            jobService.setEnabled(true, enabledIds);
        }

        // 设置不可用
        Long[] disabledIds = ArrayUtils.removeAll(ids, enabledIds, Long.class);
        if (disabledIds != null && disabledIds.length > 0) {
            jobService.setEnabled(false, disabledIds);
        }

        // 设置执行方式
        jobService.setExecTypes(ids, execTypes);

        // 设置执行规则
        jobService.setExpressions(ids, expressions);

        // 更新配置后重新加载JOB Server
        jobService.loadJobServer();

        return list(jobPage, queryParams, request, response, model);
    }

    @RequestMapping("/exec")
    @Log
    public String exec(@ModelAttribute Page<Job> jobPage, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        return list(jobPage, queryParams, request, response, model);
    }

    @RequestMapping("/log")
    public String log(@ModelAttribute Page<JobLog> jobLogPage, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (jobLogPage == null) {
            jobLogPage = new Page<JobLog>();
        }
        jobLogPage.setSort(new Sort("id", Sort.DESC));
        jobLogPage = jobLogService.findJobLog(jobLogPage, new JobLogQueryCriterion(queryParams));
        model.addAttribute("jobLogPage", jobLogPage);
        return "/admin/system/job/log";
    }

    @RequestMapping("/log/clear")
    @Log
    public String log(@RequestParam(required = false) int days, HttpServletRequest request, HttpServletResponse response, Model model) {
        jobLogService.removeJobLog(days);
        return prompt(model, "系统提示", "JOB调度日志清理成功！", "/admin/system/job/log");
    }
}
