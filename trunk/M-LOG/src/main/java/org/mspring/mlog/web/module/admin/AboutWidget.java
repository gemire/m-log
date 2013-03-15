/**
 * 
 */
package org.mspring.mlog.web.module.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.Application;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/admin")
public class AboutWidget extends AbstractAdminWidget {

    /**
     * 关于页面
     * 
     * @return
     */
    @RequestMapping("/about")
    public String about(HttpServletRequest request, HttpServletResponse response, Model model) {
        Application app = Application.getInstance();
        model.addAttribute("app", app);

        float fFreeMemory = (float) Runtime.getRuntime().freeMemory();
        float fTotalMemory = (float) Runtime.getRuntime().totalMemory();
        float fUsedMemory = fTotalMemory - fFreeMemory;
        float fPercent = fFreeMemory / fTotalMemory * 100;
        String serverName = request.getServerName();
        String remoteAddr = request.getRemoteAddr();
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");

        model.addAttribute("fFreeMemory", fFreeMemory);
        model.addAttribute("fTotalMemory", fTotalMemory);
        model.addAttribute("fUsedMemory", fUsedMemory);
        model.addAttribute("fPercent", fPercent);
        model.addAttribute("serverName", serverName);
        model.addAttribute("remoteAddr", remoteAddr);
        model.addAttribute("os", os);
        model.addAttribute("javaVersion", javaVersion);
        
        // ServiceFactory.getInstallService().initTreeItems();
        return "/admin/about";
    }
}
