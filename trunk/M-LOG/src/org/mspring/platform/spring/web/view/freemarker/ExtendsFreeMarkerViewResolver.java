/**
 * 
 */
package org.mspring.platform.spring.web.view.freemarker;

import org.mspring.platform.utils.StringUtils;
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
    private static final String DEFAULT_SKIN_FOLDER = "/skins/";

    private static String skinfolder = DEFAULT_SKIN_FOLDER;

    public static String getSkinfolder() {
        return skinfolder;
    }

    public void setSkinfolder(String skinfolder) {
        if (StringUtils.isBlank(skinfolder)) {
            this.skinfolder = DEFAULT_SKIN_FOLDER;
        }
        else {
            this.skinfolder = skinfolder;
        }
    }

    public ExtendsFreeMarkerViewResolver() {
        setPrefix(DEFAULT_FOLDER);
        setSuffix(DEFAULT_SUFFIX);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Class requiredViewClass() {
        return ExtendsFreeMarkerView.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.view.AbstractTemplateViewResolver#buildView
     * (java.lang.String)
     */
    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        // TODO Auto-generated method stub
        AbstractUrlBasedView view = super.buildView(viewName);
        if (view instanceof ExtendsFreeMarkerView) {
            ExtendsFreeMarkerView extendsView = (ExtendsFreeMarkerView) view;
            extendsView.setThemePrefix(DEFAULT_FOLDER);
            extendsView.setThemeSuffix(DEFAULT_SUFFIX);
            extendsView.setSkinfolder(skinfolder);
            return extendsView;
        }
        return view;
    }
}