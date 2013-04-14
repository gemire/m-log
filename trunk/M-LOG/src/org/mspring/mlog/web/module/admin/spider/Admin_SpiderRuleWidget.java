/**
 * 
 */
package org.mspring.mlog.web.module.admin.spider;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.api.spider.service.SpiderRuleService;
import org.mspring.mlog.api.spider.vo.Rule;
import org.mspring.mlog.support.log.Log;
import org.mspring.mlog.support.resolver.QueryParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.query.RuleQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 1994-4-14
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/spider/rule")
public class Admin_SpiderRuleWidget extends AbstractSpiderWidget {
    @Autowired
    private SpiderRuleService spiderRuleService;

    @RequestMapping("/list")
    @Log
    public String list(@ModelAttribute Page<Rule> rulePage, @ModelAttribute Rule rule, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (rulePage == null) {
            rulePage = new Page<Rule>();
        }
        rulePage.setSort(new Sort("id", Sort.DESC));
        rulePage = spiderRuleService.findRulePage(new RuleQueryCriterion(queryParams), rulePage);
        model.addAttribute("rulePage", rulePage);
        return "/spider/rule/list";
    }
}
