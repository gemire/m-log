/**
 * 
 */
package org.mspring.mlog.web.theme;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.common.ServiceFactory;
import org.mspring.mlog.exception.ThemeNotFoundException;
import org.mspring.mlog.web.widget.WidgetModel;
import org.mspring.platform.utils.PropertyUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since Jan 17, 2012 http://www.mspring.org 主题操作通用类
 */
public class ThemeUtils {
    private static final Logger log = Logger.getLogger(ThemeUtils.class);

    public static final String KEY_PATH = "path";
    public static final String KEY_THEME = "theme";
    public static final String KEY_STYLESHEET_URL = "stylesheet_url";
    public static final String KEY_TEMPLATE_URL = "template_url";
    public static final String KEY_MSPRING_TAG_LIB = "mspring_tag";
    public static final String FUN_WIDGET = "widget";

    /**
     * 获取当前使用的主题
     * 
     * @return
     */
    public static String getTheme() {
        String theme = ServiceFactory.getOptionService().getOption(ConfigTokens.mspring_config_base_theme);

        if (StringUtils.isBlank(theme)) {
            log.debug("theme not found, use default");
            theme = Const.DEFAULT_THEME;
        }

        String theme_folder = ServletActionContext.getServletContext().getRealPath("/" + Const.THEME_FOLDER + "/" + theme);
        File theme_folder_file = new File(theme_folder);
        if (!theme_folder_file.exists() || !theme_folder_file.isDirectory()) {
            log.error("default theme not found");
            throw new ThemeNotFoundException();
        }
        return theme;
    }

    /**
     * 获取主题语言
     * 
     * @param theme
     * @return
     */
    public static Map<String, String> getThemeLanguage(String theme) {
        String themePath = ServletActionContext.getServletContext().getRealPath("/" + Const.THEME_FOLDER) + File.separator + theme + File.separator;
        String langPath = themePath + Const.THEME_LANG_PATH + File.separator;
        String langFilePath = langPath + "messageResource.properties";
        File lang_file = new File(langFilePath);
        if (lang_file.exists()) {
            return PropertyUtils.getPropertyMap(lang_file);
        }
        return null;
    }

    /**
     * 向模板中注入变量
     * @return
     */
    public static Map<Object, Object> initThemeData() {
        Map<Object, Object> model = new HashMap<Object, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();

        // 网站设置
        String path = request.getContextPath();
        String theme = ThemeUtils.getTheme();
        Map<String, String> config = ServiceFactory.getOptionService().getOptions();

        Map<String, String> hashdata = new HashMap<String, String>();
        hashdata.putAll(config);
        hashdata.putAll(getThemeLanguage(theme));
        hashdata.put(KEY_PATH, path);
        hashdata.put(KEY_THEME, theme);
        hashdata.put(KEY_STYLESHEET_URL, path + "/" + Const.THEME_FOLDER + "/" + theme + "/css/" + Const.THEME_STYLE_SHEET);
        hashdata.put(KEY_TEMPLATE_URL, path + "/" + Const.THEME_FOLDER + "/" + theme);
        try {
            TaglibFactory factory = new TaglibFactory(ServletActionContext.getServletContext());
            TemplateModel templateModel = factory.get("/WEB-INF/mspring.tld");
            model.put(KEY_MSPRING_TAG_LIB, templateModel);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        model.putAll(hashdata);
        model.put(FUN_WIDGET, new WidgetModel());
        return model;
    }

    /**
     * 获取当前主题文件夹
     * 
     * @return
     */
    public static File getThemeFolder() {
        String theme = ServiceFactory.getOptionService().getOption(ConfigTokens.mspring_config_base_theme);

        if (StringUtils.isBlank(theme)) {
            log.debug("theme not found, use default");
            theme = Const.DEFAULT_THEME;
        }

        String theme_folder = ServletActionContext.getServletContext().getRealPath("/" + Const.THEME_FOLDER + "/" + theme);
        File theme_folder_file = new File(theme_folder);
        if (!theme_folder_file.exists() || !theme_folder_file.isDirectory()) {
            log.error("default theme not found");
            throw new ThemeNotFoundException();
        }
        return theme_folder_file;
    }
}
