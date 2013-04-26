/**
 * 
 */
package org.mspring.platform.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.web.Keys;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Gao Youbo
 * @since 2012-7-16
 * @Description
 * @TODO
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6524984532025127425L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.DispatcherServlet#render(org.springframework
     * .web.servlet.ModelAndView, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        //exportContextPath(request, mv);
        super.render(mv, request, response);
    }

    /**
     * 将contextPath加入环境变量
     * 
     * @param request
     * @param mv
     */
    protected void exportContextPath(HttpServletRequest request, ModelAndView mv) {
        mv.addObject(Keys.REQUEST_KEY_BASE, request.getContextPath());
        //request.setAttribute(Keys.REQUEST_KEY_BASE, request.getContextPath());
    }

}
