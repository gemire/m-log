/**
 * 
 */
package org.mspring.platform.spring.web.view;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Gao Youbo
 * @since 2012-7-13
 * @Description
 * @TODO
 */
public class ExtendsInternalResourceViewResolver extends InternalResourceViewResolver {

    private String themePrefix = "/WEB-INF/themes/";
    private String themeSuffix = ".jsp";

    public ExtendsInternalResourceViewResolver() {
        setPrefix("/WEB-INF/views/");
        setSuffix(".jsp");
    }

    public void setThemePrefix(String themePrefix) {
        this.themePrefix = themePrefix;
    }

    public void setThemeSuffix(String themeSuffix) {
        this.themeSuffix = themeSuffix;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class requiredViewClass() {
        return ExtendsInternalResourceView.class;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractUrlBasedView view = super.buildView(viewName);
        if (view instanceof ExtendsInternalResourceView) {
            ExtendsInternalResourceView extendsView = (ExtendsInternalResourceView) view;
            extendsView.setThemeSuffix(themeSuffix);
            return extendsView;
        }
        return view;
    }

}