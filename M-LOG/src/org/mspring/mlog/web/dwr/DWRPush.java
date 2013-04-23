/**
 * 
 */
package org.mspring.mlog.web.dwr;

import java.util.Collection;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

/**
 * @author Gao Youbo
 * @since 2013-4-15
 * @description
 * @TODO
 */
public class DWRPush {
    public static WebContext wctx = null;

    public static void sendMessage(String content) {
        if (wctx == null) {
            wctx = WebContextFactory.get();
        }
        ScriptBuffer script = new ScriptBuffer();
        // 执行js 方法
        if (content != null) {
            // script.appendScript("receiveMessages('").appendData(content).appendScript("');");
            script.appendScript("receiveMessages('").appendData(content).appendScript("');");
        }
        ServerContext sctx = ServerContextFactory.get(wctx.getServletContext());
        Collection<ScriptSession> scriptSessions = sctx.getScriptSessionsByPage(wctx.getCurrentPage());
        // Util util = new Util(scriptSessions);
        // 可以设置样式等
        // util.setStyle("test", "display", "none");
        for (ScriptSession session : scriptSessions) {
            session.addScript(script);
        }
    }
}
