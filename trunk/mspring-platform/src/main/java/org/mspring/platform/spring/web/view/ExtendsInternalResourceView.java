/**
 * 
 */
package org.mspring.platform.spring.web.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

/**
 * @author Gao Youbo
 * @since 2012-7-13
 * @Description
 * @TODO
 */
public class ExtendsInternalResourceView extends InternalResourceView {

    private String themeSuffix;

    public ExtendsInternalResourceView() {
    }

    public void setThemeSuffix(String suffix) {
        this.themeSuffix = suffix;
    }

    @Override
    protected String prepareForRendering(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.prepareForRendering(request, response);
    }

}