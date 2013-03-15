/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.global;

import java.io.IOException;
import java.util.Map;

import org.mspring.mlog.common.PageNames;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Album;
import org.mspring.mlog.entity.Photo;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-8-9
 * @Description
 * @TODO 设置当前页面的title
 */
@SuppressWarnings("rawtypes")
public class MLogTitleDirectiveModel extends AbstractDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String title = ServiceFactory.getOptionService().getOption("blogname");
        Object obj = env.__getitem__(FreemarkerVariableNames.CURRENT_PAGE);
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            String currentPage = obj.toString();
            if (currentPage.equals(PageNames.INDEX)) {
            } else if (currentPage.equals(PageNames.SINGLE)) {
                Object postObj = env.__getitem__(FreemarkerVariableNames.POST);
                if (postObj != null && (postObj instanceof Post)) {
                    title = ((Post) postObj).getTitle() + " - " + title;
                }
            } else if (currentPage.equals(PageNames.SEARCH)) {
                Object keyword = env.__getitem__(FreemarkerVariableNames.SEARCH_KEYWORD);
                if (keyword != null && StringUtils.isNotBlank(keyword.toString())) {
                    title = keyword + " 搜索 -" + title;
                }
            } else if (currentPage.equals(PageNames.CATALOG_ARCHIVE)) {
                Object catalogName = env.__getitem__(FreemarkerVariableNames.CATALOG_ARCHIVE_NAME);
                if (catalogName != null && StringUtils.isNotBlank(catalogName.toString())) {
                    title = "" + catalogName + " - " + title;
                }
            } else if (currentPage.equals(PageNames.TAG_ARCHIVE)) {
                Object tagname = env.__getitem__(FreemarkerVariableNames.TAG_ARCHIVE_NAME);
                if (tagname != null && StringUtils.isNotBlank(tagname.toString())) {
                    title = "" + tagname + " - " + title;
                }
            } else if (currentPage.equals(PageNames.ALBUM)) {
                title = "相册 - " + title;
            } else if (currentPage.equals(PageNames.PHOTO_LIST)) {
                Object albumObj = env.__getitem__(FreemarkerVariableNames.ALBUM);
                if (albumObj != null && albumObj instanceof Album) {
                    title = ((Album) albumObj).getName() + " - " + title;
                }
            } else if (currentPage.equals(PageNames.PHOTO_VIEW)) {
                Object photoObj = env.__getitem__(FreemarkerVariableNames.PHOTO);
                if (photoObj != null && photoObj instanceof Photo) {
                    title = ((Photo) photoObj).getName() + " - " + title;
                }
            }
        }
        env.getOut().append(title);
    }

}
