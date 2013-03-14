/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.album;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-12-27
 * @Description 
 * @TODO 相册封面URL
 */
public class AlbumCoverUrlDirectiveModel extends AbstractDirectiveModel {
    
    public static final Logger log = Logger.getLogger(AlbumCoverUrlDirectiveModel.class);
    
    public static final String KEY = "album_cover";

    /* (non-Javadoc)
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
     */
    @Override
    public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        Object albumObj = DirectiveUtils.getItem(env, FreemarkerVariableNames.ALBUM);
        if (albumObj == null || !(albumObj instanceof Album)) {
            return;
        }
        Album album = (Album) albumObj;
        Photo photo = album.getCover();
        if (photo == null || StringUtils.isBlank(photo.getPreviewUrl())) {
            return;
        }
        String url = photo.getUrl();
        if (url.startsWith("http")) {
            env.getOut().append(url);
        }
        else {
            url = DirectiveUtils.getWebContext(env).getRequest().getContextPath() + url;
            env.getOut().append(url);
        }
    }

    /* (non-Javadoc)
     * @see org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel#getKey()
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return KEY;
    }

}
