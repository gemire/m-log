/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.entity.Catalog;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-8-9
 * @Description
 * @TODO 循环文章分类
 */
public class ListPostCatalogDirectiveModel extends AbstractDirectiveModel {

    private static final Logger log = Logger.getLogger(ListPostCatalogDirectiveModel.class);

    private static final String KEY = "list_post_catalog";

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
    @SuppressWarnings({ "rawtypes" })
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVar, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        // 获取当前模板变量中的文章对象
        Object postObj = env.__getitem__(FreemarkerVariableNames.POST);
        if (postObj == null || !(postObj instanceof Post)) {
            log.warn("################post can't be found");
            return;
        }
        Post post = (Post) postObj;
        List<Catalog> catalogs = new ArrayList<Catalog>(post.getCatalogs());
        Collections.sort(catalogs, new Comparator<Catalog>() {
            @Override
            public int compare(Catalog o1, Catalog o2) {
                // TODO Auto-generated method stub
                return o1.getId().compareTo(o2.getId());
            }
        });
        for (Catalog catalog : catalogs) {
            env.__setitem__(FreemarkerVariableNames.CATALOG, catalog);
            body.render(env.getOut());
        }
    }

}
