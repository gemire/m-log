/**
 * 
 */
package org.mspring.mlog.web.action.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.api.metaweblog.MetaWeblogAPI;
import org.mspring.mlog.web.action.CommonActionSupport;
import org.springframework.web.servlet.support.WebContentGenerator;

/**
 * @author Gao Youbo
 * @since Apr 16, 2012
 */
public class MetaweblogAction extends CommonActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 4020653507184325178L;

    public String execute() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        if (WebContentGenerator.METHOD_GET.equals(request.getMethod())) {
            PrintWriter out = response.getWriter();
            out.println("Not support get method.");
            out.flush();
        }
        try {
            MetaWeblogAPI api = new MetaWeblogAPI();
            api.metaWeblog(request, response);
            // xmlRpcBean.getServer().execute(request, response);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return NONE;
    }
}
