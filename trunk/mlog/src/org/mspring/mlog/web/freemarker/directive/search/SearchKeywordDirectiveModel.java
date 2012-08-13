/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.search;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.freemarker.FreemarkerVariableNames;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-8-13
 * @Description
 * @TODO
 */
public class SearchKeywordDirectiveModel extends AbstractDirectiveModel {
    private static final Logger log = Logger.getLogger(SearchKeywordDirectiveModel.class);

    public static final String KEY = "search_keyword";

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
        Object keywork = env.__getitem__(FreemarkerVariableNames.SEARCH_KEYWORD);
        if (keywork == null) {
            log.warn("################key can't be found");
            return;
        }
        env.getOut().append(keywork.toString());
    }

}
