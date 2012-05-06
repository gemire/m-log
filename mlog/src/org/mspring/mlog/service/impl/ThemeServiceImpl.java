/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.ConfigTokens;
import org.mspring.mlog.common.Const;
import org.mspring.mlog.entity.Theme;
import org.mspring.mlog.service.ArticleService;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.ThemeService;
import org.mspring.platform.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since Feb 8, 2012
 */
@Service
@Transactional
public class ThemeServiceImpl implements ThemeService {

    private static final Logger log = Logger.getLogger(ThemeServiceImpl.class);

    private OptionService optionService;
    private ArticleService articleService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public List<Theme> _findAllTheme(String basePath) {
        // TODO Auto-generated method stub
        String themeforderpath = "";
        if (basePath.endsWith(File.separator))
            themeforderpath = basePath + Const.THEME_FOLDER;
        else
            themeforderpath = basePath + File.separator + Const.THEME_FOLDER;

        File themefolder = new File(themeforderpath);
        if (!themefolder.exists())
            log.error("theme path [" + themeforderpath + "], exist", new NullPointerException());

        if (!themefolder.isDirectory())
            log.error("theme path [" + themeforderpath + "] is not a directory", new RuntimeException());

        // 获取所有的主题目录
        File[] theme = themefolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // TODO Auto-generated method stub
                if (!pathname.isDirectory()) {
                    return false;
                }
                String themeproperties_path = pathname.getPath() + File.separator + Const.THEME_PROPERTIES;
                File themeproperties = new File(themeproperties_path);
                if (!themeproperties.exists() || themeproperties.isDirectory()) {
                    return false;
                }
                return true;
            }
        });

        List<Theme> themes = new ArrayList<Theme>();
        for (File file : theme) {
            String themeproperties_path = file.getPath() + File.separator + Const.THEME_PROPERTIES;
            File themeproperties = new File(themeproperties_path);
            Map<String, String> properties = PropertyUtils.getPropertyMap(themeproperties);
            Theme t = new Theme();
            t.setName(properties.get(Const.THEME_PROPERTIES_NAME));
            t.setVersion(properties.get(Const.THEME_PROPERTIES_VERSION));
            t.setBlogversion(properties.get(Const.THEME_PROPERTIES_BLOGVERSION));
            t.setDescription(properties.get(Const.THEME_PROPERTIES_DESCRIPTION));

            t.setFolderName(file.getName());
            themes.add(t);
        }
        return themes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.ThemeService#useThisTheme(java.lang.String)
     */
    @Override
    public void useThisTheme(String folderName) {
        // TODO Auto-generated method stub
        optionService.setOption(ConfigTokens.mspring_config_base_theme, folderName);

        // 清除所有文章缓存
        articleService._removeAllArticleCache();
    }

    public static void main(String[] args) {
        ThemeService service = new ThemeServiceImpl();
        List<Theme> themes = service._findAllTheme("E:\\workspaces\\M-Log\\WebRoot");
        for (Theme theme : themes) {
            System.out.println(theme.getName());
            System.out.println(theme.getVersion());
            System.out.println(theme.getBlogversion());
            System.out.println(theme.getDescription());

            System.out.println(theme.getFolderName());
        }
    }
}
