package org.mspring.mlog.web.result;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.web.theme.ThemeUtils;
import org.mspring.platform.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 13, 2011 org.mspring.core.xwork.view
 */
public class FreemarkerResult extends org.apache.struts2.views.freemarker.FreemarkerResult {

    /**
     * 
     */
    private static final long serialVersionUID = 4068829046936552014L;
    public static final String KEY_PATH = "path";
    public static final String KEY_MSPRING_TAG_LIB = "mspring";
    public static final String KEY_CONFIG = "config";
    public static final String FUN_LAN = "lan";
    public static final String FUN_CATEGORY_SIDEBAR = "categoryViewModel";

    private OptionService optionService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Override
    protected boolean preTemplateProcess(Template template, TemplateModel model) throws IOException {
        // TODO Auto-generated method stub
        super.preTemplateProcess(template, model);
        inject(model);
        return true;
    }

    protected Writer getWriter() throws IOException {
        return new OutputStreamWriter(ServletActionContext.getResponse().getOutputStream(), ServletActionContext.getResponse().getCharacterEncoding());
    }

    private void inject(TemplateModel model) {
        SimpleHash hash = (SimpleHash) model;
        HttpServletRequest request = ServletActionContext.getRequest();
        // String basePath = request.getScheme() + "://" +
        // request.getServerName() + ":" + request.getServerPort() +
        // request.getContextPath();

        // 网站设置
        Map<String, String> config = optionService.getOptions();

        String theme = ThemeUtils.getTheme();
        String themePath = ServletActionContext.getServletContext().getRealPath("/" + Const.THEME_FOLDER) + File.separator + theme + File.separator;
        String langPath = themePath + Const.THEME_LANG_PATH + File.separator;
        String langFilePath = langPath + "messageResource.properties";
        File lang_file = new File(langFilePath);
        if (lang_file.exists()) {
            Map<String, String> language = PropertyUtils.getPropertyMap(lang_file);
            hash.putAll(language);
        }

        hash.putAll(config);
        hash.put(KEY_PATH, request.getContextPath());

        // hash.put(FUN_LAN, new ThemeLanguageModel());

        // try {
        // TaglibFactory factory = (TaglibFactory)
        // hash.get(FreemarkerManager.KEY_JSP_TAGLIBS);
        // if (factory == null) {
        // factory = new
        // TaglibFactory(ServletActionContext.getServletContext());
        // }
        // hash.put(KEY_MSPRING_TAG_LIB, factory.get("/WEB-INF/mspring.tld"));
        // } catch (TemplateModelException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }
}
