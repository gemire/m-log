/**
 * 
 */
package org.mspring.platform.spring.web.view.freemarker;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.platform.spring.web.view.ExtendsViewUtil;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * @author Gao Youbo
 * @since 2012-7-13
 * @Description
 * @TODO
 */
public class ExtendsFreeMarkerView extends FreeMarkerView {
    private static final String PREFIX_SKIN = "skin:";
    private String themePrefix;
    private String themeSuffix;
    private String skinfolder;

    public void setThemePrefix(String themePrefix) {
        this.themePrefix = themePrefix;
    }

    public void setThemeSuffix(String themeSuffix) {
        this.themeSuffix = themeSuffix;
    }

    public void setSkinfolder(String skinfolder) {
        this.skinfolder = skinfolder;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.view.freemarker.FreeMarkerView#checkResource
     * (java.util.Locale)
     */
    @Override
    public boolean checkResource(Locale locale) throws Exception {
        // TODO Auto-generated method stub
        if (StringUtils.startsWith(getBeanName(), PREFIX_SKIN)) {
            return true;
        }
        return super.checkResource(locale);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.view.freemarker.FreeMarkerView#
     * renderMergedTemplateModel(java.util.Map,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        ExtendsViewUtil viewUtil = new ExtendsViewUtil(themePrefix, themeSuffix, skinfolder);
        String url = viewUtil.getUrl(getBeanName(), request);
        if (url != null) {
            setUrl(url);
        }
        super.renderMergedTemplateModel(model, request, response);
    }

}