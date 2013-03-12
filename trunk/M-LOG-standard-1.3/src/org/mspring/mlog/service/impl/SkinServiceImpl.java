/**
 * 
 */
package org.mspring.mlog.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.common.Keys;
import org.mspring.mlog.common.OptionKeys;
import org.mspring.mlog.entity.Skin;
import org.mspring.mlog.service.OptionService;
import org.mspring.mlog.service.SkinService;
import org.mspring.platform.core.AbstractServiceSupport;
import org.mspring.platform.spring.web.view.freemarker.ExtendsFreeMarkerViewResolver;
import org.mspring.platform.utils.PropertiesUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gao Youbo
 * @since 2012-7-24
 * @Description
 * @TODO
 */
@Service
@Transactional
public class SkinServiceImpl extends AbstractServiceSupport implements SkinService, ResourceLoaderAware {
    private static final Logger log = Logger.getLogger(SkinServiceImpl.class);

    private OptionService optionService;

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    private ResourceLoader resourceLoader;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ResourceLoaderAware#setResourceLoader(org
     * .springframework.core.io.ResourceLoader)
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        // TODO Auto-generated method stub
        this.resourceLoader = resourceLoader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.SkinService#getCurrentSkinFolder()
     */
    @Override
    public String getCurrentSkinFolder() {
        // TODO Auto-generated method stub
        String skin = optionService.getOption(OptionKeys.CURRENT_SKIN);
        if (StringUtils.isBlank(skin)) {
            skin = "default";
        }
        return skin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.service.SkinService#getSkinByFolder(java.lang.String)
     */
    @Override
    public Skin getSkinByFolder(String folder) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mspring.mlog.service.SkinService#scrnSkin()
     */
    @Override
    public List<Skin> scrnSkin() {
        // TODO Auto-generated method stub
        // 存放主题的目录, 默认为/skins
        final String skinfolder = ExtendsFreeMarkerViewResolver.getSkinfolder();
        final Resource resource = resourceLoader.getResource(skinfolder);
        List<Skin> list = new ArrayList<Skin>();
        try {
            File skinfolder_file = resource.getFile();
            File[] skinfiles = skinfolder_file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    // TODO Auto-generated method stub
                    if (!pathname.isDirectory()) {
                        return false;
                    }
                    String skin_properties_filename = pathname.getPath() + File.separator + Keys.SKIN_PROPERTIES_NAME;
                    File skin_properties_file = new File(skin_properties_filename);
                    if (!skin_properties_file.exists() || skin_properties_file.isDirectory()) {
                        return false;
                    }
                    return true;
                }
            });
            
            
            for (File skinfile : skinfiles) {
                String skin_properties_filename = skinfile.getPath() + File.separator + Keys.SKIN_PROPERTIES_NAME;
                File skin_properties_file = new File(skin_properties_filename);
                Map<String, String> properties = PropertiesUtils.getPropertyMap(skin_properties_file);
                Skin skin = new Skin();
                skin.setFolder(skinfile.getName());
                skin.setName(properties.get("name"));
                skin.setAuthor(properties.get("author"));
                skin.setUrl(properties.get("url"));
                skin.setEmail(properties.get("email"));
                skin.setDescription(properties.get("description"));
                list.add(skin);
            }
            return list;

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

}
