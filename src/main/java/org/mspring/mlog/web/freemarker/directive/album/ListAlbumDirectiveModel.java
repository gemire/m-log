/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.album;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.persistence.support.Page;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-12-27
 * @Description
 * @TODO 相册列表
 */
public class ListAlbumDirectiveModel extends AbstractDirectiveModel {

    private static final Logger log = Logger.getLogger(ListAlbumDirectiveModel.class);

    public static final String KEY = "list_album";

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
     * Environment, java.util.Map, freemarker.template.TemplateModel[],
     * freemarker.template.TemplateDirectiveBody)
     */
    @Override
    public void execute(Environment env, Map map, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        Object albumPageObj = DirectiveUtils.getItem(env, FreemarkerVariableNames.ALBUM_PAGE);
        if (albumPageObj == null || !(albumPageObj instanceof Page)) {
            log.warn("count find " + FreemarkerVariableNames.ALBUM_PAGE);
            return;
        }
        Page<Album> albumPage = (Page<Album>) albumPageObj;
        for (Album album : albumPage.getResult()) {
            DirectiveUtils.setItem(env, FreemarkerVariableNames.ALBUM, album);
            body.render(env.getOut());
        }
    }

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

}
