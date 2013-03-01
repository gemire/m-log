/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2013-2-28
 * @description
 * @TODO
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SubstringDirectiveModel extends AbstractDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        String str = DirectiveUtils.getString("str", params);
        Integer beginIndex = DirectiveUtils.getInt("beginIndex", params);
        Integer endIndex = DirectiveUtils.getInt("endIndex", params);
        if (beginIndex == null) {
            beginIndex = 0;
        }

        if (str == null) {
            return;
        }

        String result = str;
        if (endIndex == null) {
            if (beginIndex < str.length()) {
                result = StringUtils.substring(str, beginIndex) + "...";
                // result = str.substring(beginIndex) + "...";
            }
        } else {
            if (endIndex < str.length()) {
                result = StringUtils.substring(str, beginIndex, endIndex) + "...";
                // result = str.substring(beginIndex, endIndex) + "...";
            }
        }
        env.getOut().append(result);
    }
}
