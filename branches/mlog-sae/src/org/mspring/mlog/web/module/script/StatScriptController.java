/**
 * 
 */
package org.mspring.mlog.web.module.script;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.script.stat.AbstractStatCmd;
import org.mspring.mlog.web.module.script.stat.StatCmdFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-11-16
 * @Description
 * @TODO 提供统计的Controller
 */
@Widget
@RequestMapping("/script")
public class StatScriptController extends AbstractScriptController {
    private static final Logger log = Logger.getLogger(StatScriptController.class);

    @RequestMapping("/stat")
    @ResponseBody
    public String stat(HttpServletRequest request, HttpServletResponse response) {
        if (!validateRequest(request)) {
            log.warn("can't validate request!");
            return "false";
        }
        String cmdName = request.getParameter("cmd");
        AbstractStatCmd cmd = StatCmdFactory.getCmd(cmdName);
        boolean flag = cmd.execute(request);
        return flag ? "true" : "false";
    }
}
