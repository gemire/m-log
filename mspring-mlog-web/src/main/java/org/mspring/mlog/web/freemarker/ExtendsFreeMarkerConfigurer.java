/**
 * 
 */
package org.mspring.mlog.web.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.web.freemarker.directive.AbstractDirectiveModel;
import org.mspring.platform.utils.PropertiesUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * @author Gao Youbo
 * @since 2012-7-26
 * @Description
 * @TODO
 */
public class ExtendsFreeMarkerConfigurer extends FreeMarkerConfigurer {

    private static final Logger log = Logger.getLogger(ExtendsFreeMarkerConfigurer.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ui.freemarker.FreeMarkerConfigurationFactory#
     * createConfiguration()
     */
    @Override
    public Configuration createConfiguration() throws IOException, TemplateException {
        // TODO Auto-generated method stub
        // 往freemarkerconfiguration中注入variables
        Map<String, Object> variables = addVariables();
        setFreemarkerVariables(variables);
        return super.createConfiguration();
    }

    /**
     * 获取variables.properties中配置的variable
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Map<String, Object> addVariables() {
        Map<String, Object> variables = new HashMap<String, Object>();
        Map<String, String> map = PropertiesUtils.getPropertyMap("variables.properties");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (StringUtils.isNotBlank(key)) {
                try {
                    Class clazz = ClassUtils.getClass(value);
                    //if (clazz.getSuperclass().equals(AbstractDirectiveModel.class)) {
                    if(AbstractDirectiveModel.class.isAssignableFrom(clazz)){
                        AbstractDirectiveModel model = (AbstractDirectiveModel) clazz.newInstance();
                        variables.put(key, model);
                    }
                    else {
                        variables.put(key, value);
                    }
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    log.debug("can't found AbstractDirectiveModel named [" + value + "], set string variable.");
                    variables.put(key, value);
                }
            }
        }
        return variables;
    }
}
