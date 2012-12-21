/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.link;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.LinkType;
import org.mspring.mlog.service.LinkTypeService;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-12-3
 * @Description
 * @TODO 循环LinkType
 */
public class ListLinkTypeDirectiveModel extends AbstractDirectiveModel {

    private static final Logger log = Logger.getLogger(ListLinkTypeDirectiveModel.class);

    public static final String KEY = "list_link_type";

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
        LinkTypeService linkTypeService = ServiceFactory.getLinkTypeService();
        List<LinkType> linkTypes = linkTypeService.findAllVisable();
        env.__setitem__(FreemarkerVariableNames.LINK_TYPES, linkTypes);

        for (LinkType linkType : linkTypes) {
            env.__setitem__(FreemarkerVariableNames.LINK_TYPE, linkType);
            if (body != null) {
                body.render(env.getOut());
            }
        }

        // long start = System.currentTimeMillis();
        // StringBuffer sb = new StringBuffer("");
        // StringWriter sw = new StringWriter();
        // body.render(sw);
        // sb.append(sw.getBuffer());
        // long end = System.currentTimeMillis();
        // long use = end - start;
        // System.out.println(use);
        // System.out.println(use);
    }

}
