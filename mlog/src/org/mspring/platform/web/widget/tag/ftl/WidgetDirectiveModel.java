/**
 * 
 */
package org.mspring.platform.web.widget.tag.ftl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.util.HttpUtil;
import org.mspring.platform.core.ContextManager;
import org.mspring.platform.utils.HttpUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Gao Youbo
 * @since 2012-7-17
 * @Description
 * @TODO
 */
public class WidgetDirectiveModel implements TemplateDirectiveModel {
    
    private static final String PATH = "path";
    
    /*
     * (non-Javadoc)
     * 
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
     * Environment, java.util.Map, freemarker.template.TemplateModel[],
     * freemarker.template.TemplateDirectiveBody)
     */
    @Override
    public void execute(final Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // TODO Auto-generated method stub
        if(params.get(PATH) == null || StringUtils.isBlank(params.get(PATH).toString())){
            env.getOut().append("<div style='font-size:12px; color:blue;'>path can't be null</div>");
            return;
        }
        
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
    }

}
