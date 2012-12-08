/**
 * 
 */
package org.mspring.mlog.web.module.install;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-9-9
 * @Description
 * @TODO
 */
@Controller
@RequestMapping("/install")
public class InstallController {
    /**
     * 安装第一步,初始化数据库
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping({ "", "/", "/setup1" })
    public String setup1_view(HttpServletRequest request, HttpServletResponse response, Model model) {
        
        return "/install/setup1";
    }

    /**
     * 安装第一步,初始化数据库
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/setup1.do")
    public String setup1_exec(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/install/setup1";
    }

    /**
     * 检测是否已经安装
     * 
     * @return
     */
    private boolean checkHasInstalled() {
        return true;
    }
}
