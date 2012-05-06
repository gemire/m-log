/**
 * 
 */
package org.mspring.mlog.web.result;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.apache.struts2.views.freemarker.FreemarkerResult;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.web.theme.ThemeUtils;
import org.mspring.mlog.web.widget.WidgetModel;
import org.mspring.platform.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since Feb 8, 2012
 */
public class ThemeResult extends FreemarkerResult {

    /**
     * 
     */
    private static final long serialVersionUID = 306600280139101562L;

    public static final String KEY_PATH = "path";
    public static final String KEY_BLOG_URL = "blogurl";
    public static final String KEY_THEME = "theme";
    public static final String KEY_STYLESHEET_URL = "stylesheet_url";
    public static final String KEY_TEMPLATE_URL = "template_url";
    public static final String KEY_MSPRING_TAG_LIB = "mspring_tag";
    public static final String FUN_WIDGET = "widget";

    private OptionService optionService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Override
    protected Writer getWriter() throws IOException {
        // TODO Auto-generated method stub
        return new OutputStreamWriter(ServletActionContext.getResponse().getOutputStream(), ServletActionContext.getResponse().getCharacterEncoding());
    }

    @Override
    protected boolean preTemplateProcess(Template template, TemplateModel model) throws IOException {
        // TODO Auto-generated method stub
        super.preTemplateProcess(template, model);
        inject(model);
        return true;
    }

    private void inject(TemplateModel model) {
        SimpleHash hash = (SimpleHash) model;
        HttpServletRequest request = ServletActionContext.getRequest();

        // 网站设置
        String path = request.getContextPath();
        String theme = ThemeUtils.getTheme();
        Map<String, String> config = optionService.getOptions();

        Map<String, String> hashdata = new HashMap<String, String>();
        hashdata.putAll(config);
        hashdata.putAll(getThemeLanguage(theme));
        hashdata.put(KEY_PATH, path);
        hashdata.put(KEY_BLOG_URL, config.get(ConfigTokens.mspring_config_base_blogurl));
        hashdata.put(KEY_THEME, theme);
        hashdata.put(KEY_STYLESHEET_URL, path + "/" + Const.THEME_FOLDER + "/" + theme + "/css/" + Const.THEME_STYLE_SHEET);
        hashdata.put(KEY_TEMPLATE_URL, path + "/" + Const.THEME_FOLDER + "/" + theme);
        try {
            TaglibFactory factory = (TaglibFactory) hash.get(FreemarkerManager.KEY_JSP_TAGLIBS);
            if (factory == null) {
                factory = new TaglibFactory(ServletActionContext.getServletContext());
            }
            TemplateModel templateModel = factory.get("/WEB-INF/mspring.tld");
            hash.put(KEY_MSPRING_TAG_LIB, templateModel);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        hash.putAll(hashdata);
        hash.put(FUN_WIDGET, new WidgetModel());
    }

    private Map<String, String> getThemeLanguage(String theme) {
        String themePath = ServletActionContext.getServletContext().getRealPath("/" + Const.THEME_FOLDER) + File.separator + theme + File.separator;
        String langPath = themePath + Const.THEME_LANG_PATH + File.separator;
        String langFilePath = langPath + "messageResource.properties";
        File lang_file = new File(langFilePath);
        if (lang_file.exists()) {
            return PropertyUtils.getPropertyMap(lang_file);
        }
        return null;
    }
}
