/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.link;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Link;
import org.mspring.mlog.service.LinkService;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.utils.ValidatorUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-12-3
 * @Description
 * @TODO
 */
public class ListLinkDirectiveModel extends AbstractDirectiveModel {

    private static final Logger log = Logger.getLogger(ListLinkDirectiveModel.class);

    public static final String KEY = "list_link";

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
        Long linkType = null;
        if (params != null) {
            String temp = params.get("linkType").toString();
            if (ValidatorUtils.isNumber(temp)) {
                linkType = new Long(temp);
            }
            else {
                log.debug(String.format("count parse linkType == %s to Long", linkType));
            }
        }
        LinkService linkService = ServiceFactory.getLinkService();
        List<Link> links = null;
        if (linkType == null) {
            links = linkService.findVisableLinks();
        }
        else {
            links = linkService.findVisableLinks(linkType);
        }
        env.__setitem__(FreemarkerVariableNames.LINKS, links);
        for (Link link : links) {
            env.__setitem__(FreemarkerVariableNames.LINK, link);
            if (body != null) {
                body.render(env.getOut());
            }
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
