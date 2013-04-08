/**
 * 
 */
package org.mspring.mlog.web.freemarker;

import static org.springframework.web.servlet.view.AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.freemarker.exception.MustBooleanException;
import org.mspring.mlog.web.freemarker.exception.MustDateException;
import org.mspring.mlog.web.freemarker.exception.MustNumberException;
import org.mspring.mlog.web.freemarker.exception.MustSplitNumberException;
import org.mspring.mlog.web.freemarker.exception.MustStringException;
import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.StringUtils;
import org.springframework.web.servlet.support.RequestContext;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * @author Gao Youbo
 * @since 2012-12-4
 * @Description
 * @TODO Freemarker标签工具类
 */
public class DirectiveUtils {
    private static final Logger log = Logger.getLogger(DirectiveUtils.class);

    public static Object getItem(Environment env, String name) {
        Object ret = null;
        try {
            ret = env.__getitem__(name);
        } catch (TemplateModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.debug("get item failure, name = " + name, e);
        }
        return ret;
    }

    public static void setItem(Environment env, String name, Object value) {
        try {
            env.__setitem__(name, value);
        } catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.debug("set item failure, name = " + name + ", value = " + value, e);
        }
    }

    /**
     * 将params的值复制到variable中
     * 
     * @param env
     * @param params
     * @return 原Variable中的值
     * @throws TemplateException
     */
    public static Map<String, TemplateModel> addParamsToVariable(Environment env, Map<String, TemplateModel> params) throws TemplateException {
        Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
        if (params.size() <= 0) {
            return origMap;
        }
        Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
        String key;
        TemplateModel value;
        for (Map.Entry<String, TemplateModel> entry : entrySet) {
            key = entry.getKey();
            value = env.getVariable(key);
            if (value != null) {
                origMap.put(key, value);
            }
            env.setVariable(key, entry.getValue());
        }
        return origMap;
    }

    /**
     * 将variable中的params值移除
     * 
     * @param env
     * @param params
     * @param origMap
     * @throws TemplateException
     */
    public static void removeParamsFromVariable(Environment env, Map<String, TemplateModel> params, Map<String, TemplateModel> origMap) throws TemplateException {
        if (params.size() <= 0) {
            return;
        }
        for (String key : params.keySet()) {
            env.setVariable(key, origMap.get(key));
        }
    }

    /**
     * 获得RequestContext
     * 
     * ViewResolver中的exposeSpringMacroHelpers必须为true
     * 
     * @param env
     * @return
     * @throws TemplateException
     */
    public static RequestContext getRequestContext(Environment env) throws TemplateException {
        TemplateModel ctx = env.getGlobalVariable(SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
        if (ctx instanceof AdapterTemplateModel) {
            return (RequestContext) ((AdapterTemplateModel) ctx).getAdaptedObject(RequestContext.class);
        } else {
            throw new TemplateModelException("RequestContext '" + SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE + "' not found in DataModel.");
        }
    }

    /**
     * 获取String类型参数
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static String getString(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            return ((TemplateScalarModel) model).getAsString();
        } else if ((model instanceof TemplateNumberModel)) {
            return ((TemplateNumberModel) model).getAsNumber().toString();
        } else {
            throw new MustStringException(name);
        }
    }

    /**
     * 获取Long类型参数
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Long getLong(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                throw new MustNumberException(name);
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().longValue();
        } else {
            throw new MustNumberException(name);
        }
    }

    /**
     * 获取int类型参数
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Integer getInt(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new MustNumberException(name);
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().intValue();
        } else {
            throw new MustNumberException(name);
        }
    }

    /**
     * 获取以","分割的int数组类型参数
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Integer[] getIntArray(String name, Map<String, TemplateModel> params) throws TemplateException {
        String str = DirectiveUtils.getString(name, params);
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] arr = StringUtils.split(str, ',');
        Integer[] ids = new Integer[arr.length];
        int i = 0;
        try {
            for (String s : arr) {
                ids[i++] = Integer.valueOf(s);
            }
            return ids;
        } catch (NumberFormatException e) {
            throw new MustSplitNumberException(name, e);
        }
    }

    /**
     * 获取Boolean类型参数 值为true、yes、1时，返回true，其他的返回false
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Boolean getBoolean(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateBooleanModel) {
            return ((TemplateBooleanModel) model).getAsBoolean();
        } else if (model instanceof TemplateNumberModel) {
            return !(((TemplateNumberModel) model).getAsNumber().intValue() == 0);
        } else if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if ("true".equalsIgnoreCase(s) || "yes".equalsIgnoreCase(s) || "1".equals(s)) {
                return true;
            }
            return false;
        } else {
            throw new MustBooleanException(name);
        }
    }

    /**
     * 获取日期类型参数
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Date getDate(String name, Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateDateModel) {
            return ((TemplateDateModel) model).getAsDate();
        } else if (model instanceof TemplateScalarModel) {
            String dateStr = ((TemplateScalarModel) model).getAsString();
            return DateUtils.parse(dateStr);
        } else {
            throw new MustDateException(name);
        }
    }
}
