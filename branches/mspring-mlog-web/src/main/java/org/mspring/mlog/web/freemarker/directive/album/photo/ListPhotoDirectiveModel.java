/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.album.photo;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Photo;
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
 * @TODO 图片列表
 */
public class ListPhotoDirectiveModel extends AbstractDirectiveModel {

    private static final Logger log = Logger.getLogger(ListPhotoDirectiveModel.class);

    public static final String KEY = "list_photo";

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
        Object photoPageObject = DirectiveUtils.getItem(env, FreemarkerVariableNames.PHOTO_PAGE);
        if (photoPageObject == null || !(photoPageObject instanceof Page)) {
            log.warn("count find " + FreemarkerVariableNames.PHOTO_PAGE);
            return;
        }
        Page<Photo> photoPage = (Page<Photo>) photoPageObject;
        for (Photo photo : photoPage.getResult()) {
            DirectiveUtils.setItem(env, FreemarkerVariableNames.PHOTO, photo);
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
