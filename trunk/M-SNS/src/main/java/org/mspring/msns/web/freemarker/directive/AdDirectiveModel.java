/**
 * 
 */
package org.mspring.msns.web.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.mspring.msns.core.ServiceFactory;
import org.mspring.msns.entity.Ad;
import org.mspring.platform.web.freemarker.DirectiveUtils;
import org.mspring.platform.web.freemarker.directive.AbstractDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2013-3-8
 * @description
 * @TODO
 */
public class AdDirectiveModel extends AbstractDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        Long id = DirectiveUtils.getLong("id", params);
        if (id != null) {
            Ad ad = ServiceFactory.getAdService().getAdById(id);
            if (ad != null) {
                String code = ad.getCode();
                env.getOut().append(code);
            }
        }
    }

}
