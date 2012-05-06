/**
 * 
 */
package org.mspring.mlog.web.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.service.PhotoService;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.web.servlet.renderer.JSONRenderer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gao Youbo
 * @since Dec 31, 2011
 */
public class PhotoUploadServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -7550596333783212259L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            String album = request.getParameter("album");
            if (!StringUtils.isBlank(album)) {
                String path = this.getServletConfig().getServletContext().getRealPath("") + File.separator;
                ServiceFactory.getPhotoService().createPhoto(request, path, new Long(album));
                JSONRenderer renderer = new JSONRenderer("succeed: {\"success\":true}");
                renderer.render(response);
            }
        } catch (Exception e) {
            // TODO: handle exception
            JSONRenderer renderer = new JSONRenderer("{\"success\":false,\"error\":\"Reason for error!\"}");
            renderer.render(response);
        }
    }

}
