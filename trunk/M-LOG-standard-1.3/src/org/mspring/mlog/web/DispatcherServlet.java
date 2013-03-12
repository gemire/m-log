/**
 * 
 */
package org.mspring.mlog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.TemplateModelException;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class DispatcherServlet extends org.mspring.platform.spring.web.DispatcherServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6829172243642413052L;

    private static TaglibFactory factory = null;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.platform.web.servlet.DispatcherServlet#render(org.springframework
     * .web.servlet.ModelAndView, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        exportWidgetTld(request, mv);
        super.render(mv, request, response);
    }

    /**
     * 将tag标签添加到环境变量
     * 
     * @param request
     * @param mv
     */
    private void exportWidgetTld(HttpServletRequest request, ModelAndView mv) {
        try {
            if (factory == null) {
                factory = new TaglibFactory(request.getSession().getServletContext());
            }
            mv.getModel().put("m", factory.get("/WEB-INF/tld/mspring.tld"));
        } catch (TemplateModelException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
