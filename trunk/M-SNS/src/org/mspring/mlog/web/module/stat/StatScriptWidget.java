/**
 * 
 */
package org.mspring.mlog.web.module.stat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.mlog.web.module.stat.factory.AbstractStatCmd;
import org.mspring.mlog.web.module.stat.factory.StatCmdFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2012-11-16
 * @Description
 * @TODO 提供统计的Controller
 */
@Widget
@RequestMapping("/")
public class StatScriptWidget extends AbstractStatWidget {
    private static final Logger log = Logger.getLogger(StatScriptWidget.class);

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
