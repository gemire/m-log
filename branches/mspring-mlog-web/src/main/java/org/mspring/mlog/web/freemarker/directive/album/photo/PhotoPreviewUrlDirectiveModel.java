/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.album.photo;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-12-27
 * @Description
 * @TODO 图片缩略图URL
 */
public class PhotoPreviewUrlDirectiveModel extends AbstractDirectiveModel {
    
    private static final Logger log = Logger.getLogger(PhotoPreviewUrlDirectiveModel.class);
    
    public static final String KEY = "photo_preview_url";
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel#getKey()
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return KEY;
    }


    /*
     * (non-Javadoc)
     * 
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
     * Environment, java.util.Map, freemarker.template.TemplateModel[],
     * freemarker.template.TemplateDirectiveBody)
     */
    @Override
    public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        Object photoObject = DirectiveUtils.getItem(env, FreemarkerVariableNames.PHOTO);
        if (photoObject == null || !(photoObject instanceof Photo)) {
            return;
        }
        String url = ((Photo)photoObject).getPreviewUrl();
        if (url.startsWith("http")) {
            env.getOut().append(url);
        }
        else {
            url = DirectiveUtils.getWebContext(env).getRequest().getContextPath() + url;
            env.getOut().append(url);
        }
    }

 
}
