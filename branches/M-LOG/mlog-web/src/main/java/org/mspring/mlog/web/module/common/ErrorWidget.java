/**
 * 
 */
package org.mspring.mlog.web.module.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-8-24
 * @Description 
 * @TODO 
 */
@Widget
@RequestMapping(value = "/errors")
public class ErrorWidget {
    
    @RequestMapping("/404")
    public String error_404(HttpServletRequest request, HttpServletResponse response, Model model){
        return "/errors/404";
    }
    
    @RequestMapping("/500")
    public String error_500(HttpServletRequest request, HttpServletResponse response, Model model){
        return "/errors/500";
    }
    
    @RequestMapping("/site_close")
    public String error_site_close(HttpServletRequest request, HttpServletResponse response, Model model){
        return "/errors/site_close";
    }
}
