/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.mspring.mlog.api.gravatar.GravatarUtils;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2013-3-4
 * @description
 * @TODO 生成Gravatar图像地址
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GravatarDirectiveModel extends AbstractDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String email = DirectiveUtils.getString("email", params);
        if (StringUtils.isNotBlank(email)) {
            String url = GravatarUtils.getGravatarImage(email);
            env.getOut().append(url);
        }

    }

}
