/**
 * 
 */
package org.mspring.mlog.web.module.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.web.api.t.service.impl.TencentService;
import org.mspring.platform.web.widget.stereotype.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gao Youbo
 * @since 2012-8-31
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/t")
public class MacroBlogController {
    @Autowired
    private TencentService tencentService;

    @RequestMapping({ "", "/" })
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        tencentService.list();
        return "skin:/t";
    }

    @RequestMapping("/add")
    public String add(@RequestParam(required = false) String content, HttpServletRequest request, HttpServletResponse response, Model model) {
        String resp = tencentService.add(content);
        return "redirect:/t";
    }
}
