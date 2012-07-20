/**
 * 
 */
package org.mspring.platform.web.view.freemarker;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author Gao Youbo
 * @since 2012-7-13
 * @Description
 * @TODO
 */
public class ExtendsFreeMarkerViewResolver extends FreeMarkerViewResolver {

    private static final String DEFAULT_FOLDER = "/WEB-INF/views/";
    private static final String DEFAULT_SUFFIX = ".ftl";

    public ExtendsFreeMarkerViewResolver() {
        setPrefix(DEFAULT_FOLDER);
        setSuffix(DEFAULT_SUFFIX);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Class requiredViewClass() {
        return ExtendsFreeMarkerView.class;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.view.AbstractTemplateViewResolver#buildView(java.lang.String)
     */
    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        // TODO Auto-generated method stub
        AbstractUrlBasedView view = super.buildView(viewName);
        if (view instanceof ExtendsFreeMarkerView) {
            ExtendsFreeMarkerView extendsView = (ExtendsFreeMarkerView) view;
            return extendsView;
        }
        return view;
    }
}