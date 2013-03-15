/**
 * 
 */
package org.mspring.mlog.web.module.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.api.metaweblog.MetaWeblogAPI;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.WebContentGenerator;

/**
 * @author Gao Youbo
 * @since 2012-8-6
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/")
public class MetaWeblogController {

    @RequestMapping({ "/metaweblog.do", "/metaweblog" })
    public void metaweblog(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if (WebContentGenerator.METHOD_GET.equals(request.getMethod())) {
            PrintWriter out = response.getWriter();
            out.println("Not support get method.");
            out.flush();
        }
        try {
            MetaWeblogAPI api = new MetaWeblogAPI();
            api.metaWeblog(request, response);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
