/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.global;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.common.PageNames;
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
public class MLogTitleDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(MLogTitleDirectiveModel.class);

    public static final String KEY = "mlog_title";

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
    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String title = ServiceFactory.getOptionService().getOption("blogname");
        Object obj = env.__getitem__(FreemarkerVariableNames.CURRENT_PAGE);
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            String currentPage = obj.toString();
            if (currentPage.equals(PageNames.INDEX)) {
            }
            else if (currentPage.equals(PageNames.SINGLE)) {
                Object postObj = env.__getitem__(FreemarkerVariableNames.POST);
                if (postObj != null && (postObj instanceof Post)) {
                    title = ((Post) postObj).getTitle() + " - " + title;
                }
            }
            else if (currentPage.equals(PageNames.SEARCH)) {
                Object keyword = env.__getitem__(FreemarkerVariableNames.SEARCH_KEYWORD);
                if (keyword != null && StringUtils.isNotBlank(keyword.toString())) {
                    title = "Search \"" + keyword + "\" -" + title;
                }
            }
            else if (currentPage.equals(PageNames.CATALOG_ARCHIVE)) {
                Object catalogName = env.__getitem__(FreemarkerVariableNames.CATALOG_ARCHIVE_NAME);
                if (catalogName != null && StringUtils.isNotBlank(catalogName.toString())) {
                    title = "\"" + catalogName + "\" Archive -" + title;
                }
            }
        }
        env.getOut().append(title);
    }

}
