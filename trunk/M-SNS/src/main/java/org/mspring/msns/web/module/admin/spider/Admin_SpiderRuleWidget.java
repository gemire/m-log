/**
 * 
 */
package org.mspring.msns.web.module.admin.spider;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mspring.msns.api.spider.Spider;
import org.mspring.msns.api.spider.crawler.DefaultCrawler;
import org.mspring.msns.api.spider.service.SpiderPostService;
import org.mspring.msns.api.spider.service.SpiderRuleService;
import org.mspring.msns.api.spider.utils.DocumentUtils;
import org.mspring.msns.api.spider.vo.Rule;
import org.mspring.msns.api.spider.vo.SpiderPost;
import org.mspring.msns.support.log.Log;
import org.mspring.msns.support.resolver.QueryParam;
import org.mspring.msns.web.query.RuleQueryCriterion;
import org.mspring.platform.persistence.support.Page;
import org.mspring.platform.persistence.support.Sort;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 1994-4-14
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin/spider/rule")
public class Admin_SpiderRuleWidget extends AbstractSpiderWidget {
    @Autowired
    private SpiderRuleService spiderRuleService;
    @Autowired
    private SpiderPostService spiderPostService;

    @SuppressWarnings("rawtypes")
    @RequestMapping("/list")
    @Log
    public String list(@ModelAttribute Page<Rule> rulePage, @ModelAttribute Rule rule, @QueryParam Map queryParams, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (rulePage == null) {
            rulePage = new Page<Rule>();
        }
        rulePage.setSort(new Sort("id", Sort.DESC));
        rulePage = spiderRuleService.findRulePage(new RuleQueryCriterion(queryParams), rulePage);
        model.addAttribute("rulePage", rulePage);
        return "/admin/spider/rule/listRule";
    }

    @RequestMapping("/create")
    public String create_view(@ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/admin/spider/rule/createRule";
    }

    @RequestMapping("/create/save")
    @Log
    public String create_save(@ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        spiderRuleService.createRule(rule);
        return prompt(model, "系统提示", "规则创建成功", "/admin/spider/rule/list");
    }

    @RequestMapping("/edit")
    public String edit_view(@RequestParam(required = false) Long id, @ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "SpiderRuleWidget_edit_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请先选择要修改的规则");
        }

        rule = spiderRuleService.getRuleById(id);
        if (rule == null) {
            return prompt(model, "请先选择要修改的规则");
        }
        model.addAttribute("rule", rule);
        setSessionAttribute(request, "SpiderRuleWidget_edit_id", id);
        return "/admin/spider/rule/editRule";
    }

    @RequestMapping("/edit/save")
    @Log
    public String edit_save(@RequestParam(required = false) Long id, @ModelAttribute Rule rule, HttpServletRequest request, HttpServletResponse response, Model model) {
        spiderRuleService.updateRule(rule);
        return prompt(model, "系统提示", "规则修改成功", "/admin/spider/rule/list");
    }

    @RequestMapping("/run_view")
    public String run_view(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            Object obj = getSessionAttribute(request, "SpiderRuleWidget_run_view_id");
            if (obj != null) {
                id = (Long) obj;
            }
        }
        if (id == null) {
            return prompt(model, "请选择要运行的规则");
        }
        Rule rule = spiderRuleService.getRuleById(id);

        if (!rule.getEnabled()) {
            return prompt(model, "该规则已被禁用");
        }

        model.addAttribute("rule", rule);
        setSessionAttribute(request, "SpiderRuleWidget_run_view_id", id);
        return "/admin/spider/rule/run";
    }

    @RequestMapping("/run")
    public void run(@RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (id == null) {
            System.out.println("请选择要运行的规则");
            return;
        }
        Rule rule = spiderRuleService.getRuleById(id);

        if (!rule.getEnabled()) {
            System.out.println("<font style='color:red;'><b>该规则已被禁用</b></font>");
            return;
        }

        Spider spilder = new Spider(new DefaultCrawler());

        System.out.println("开始...");
        System.out.println("开始获取列表：" + rule.getUrl());

        Document listPageDoc = DocumentUtils.getDocument(rule.getUrl());
        List<String> urls = spilder.getUrls(listPageDoc, rule);

        Document contentDoc = null;
        SpiderPost spiderPost = null;
        for (String url : urls) {
            if (StringUtils.isNotBlank(url)) {
                System.out.println("分析网址：" + url);

                String title = null;
                Element content = null;

                try {
                    contentDoc = DocumentUtils.getDocument(url);
                    title = spilder.getTitle(contentDoc, rule);
                    content = spilder.getContent(contentDoc, rule);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("<font style='color:red;'>抓取文章失败</font>");
                    System.out.println("<font style='color:green;'>******************************************************************************************************************************************************</font><br/>");
                    continue;
                }

                if (StringUtils.isBlank(title) || content == null) {
                    System.out.println("<font style='color:red;'>抓取文章失败</font>");
                    System.out.println("<font style='color:green;'>******************************************************************************************************************************************************</font><br/>");
                    continue;
                }

                System.out.println("文章：" + title);
                try {
                    spiderPost = new SpiderPost();
                    spiderPost.setRule(rule);
                    spiderPost.setTitle(title);
                    spiderPost.setContent(content.html());
                    spiderPostService.createSpiderPost(spiderPost);

                    System.out.println("<font style='color:green;'>导入成功</font>");
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("<font style='color:red;'>导入失败</font>");
                    System.out.println("<font style='color:green;'>******************************************************************************************************************************************************</font><br/>");
                    continue;
                }

                System.out.println("<font style='color:green;'>******************************************************************************************************************************************************</font><br/>");
            }
        }
        System.out.println("<font style='color:blue;'>全部执行完成。</font><br/>");
    }
}
