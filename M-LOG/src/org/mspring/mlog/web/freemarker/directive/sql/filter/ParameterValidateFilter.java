/**
 * 
 */
package org.mspring.mlog.web.freemarker.directive.sql.filter;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.mspring.mlog.web.freemarker.DirectiveUtils;
import org.mspring.platform.utils.StringUtils;

import freemarker.core.Environment;

/**
 * @author Gao Youbo
 * @since 2013-1-4
 * @Description
 * @TODO
 */
public class ParameterValidateFilter extends AbstractSQLValidateFilter {
    
    private static final Logger log = Logger.getLogger(ParameterValidateFilter.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.mspring.mlog.web.freemarker.directive.sql.filter.SQLValidateFilter
     * #doFilter(java.util.Map)
     */
    @Override
    public void doFilter(Map params) {
        // TODO Auto-generated method stub
        String sql = (String) params.get(SQLValidateParamNames.PARAM_SQL);
        Environment env = (Environment) params.get(SQLValidateParamNames.ENVIROMENT_NAME);
        
        try {
            sql = replace(sql, env);
            params.put(SQLValidateParamNames.PARAM_SQL, sql);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        doNextFilter(params);
    }
    
    public String replace(String sql, Environment env) throws Exception{
        Matcher m = Pattern.compile("@([\\w\\.]+)").matcher(sql);
        while (m.find()) {
            String value = getParamValue(env, m.group(1));
            sql = StringUtils.replace(sql, m.group(), value);
        }
        return sql;
    }
    
    
    public String getParamValue(Environment env, String name) throws Exception{
        String value = "";
        Object root = null;
        if (name.indexOf(".") > 0) {
            String rootName = StringUtils.substringBefore(name, ".");
            root = DirectiveUtils.getItem(env, rootName); 
            
            if (root == null) {
                log.warn("[name = " + name + "]Can't found variable from environment.");
                return "";
            }
            
            String propertyName = StringUtils.substringAfter(name, ".");
            if (root instanceof Collection) {
                Collection coll = (Collection) root;
                Iterator it = coll.iterator();
                while (it.hasNext()) {
                    Object property = it.next();
                    String propertyValue = BeanUtils.getProperty(property, propertyName);
                    value += "'" + propertyValue + "',";
                }
                value = StringUtils.substringBeforeLast(value, ",");
            }
            else {
                value = BeanUtils.getProperty(root, propertyName);
            }
        }
        else {
            root = DirectiveUtils.getItem(env, name);
            if (root == null) {
                log.warn("[name = " + name + "]Can't found variable from environment.");
                return "";
            }
            value = "'" + root + "'";
        }
        
        return value;
    }
    
    

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String sql = "select pt.PK.post from PostTag pt where pt.PK.tag.name = @tag.user.name order by pt.PK.post.viewCount desc";
        Matcher m = Pattern.compile("@([\\w\\.]+)").matcher(sql);
        while (m.find()) {
            String name = m.group(1);
            System.out.println(name);
            System.out.println(m.group());
            
            String rootName = StringUtils.substringBefore(name, ".");
            System.out.println("rootName:" + rootName);
        }
    }
    
//    public static void main(String[] args) {
//        PostTagPK pk = new PostTagPK();
//        pk.setPost(new Post(new Long(111)));
//        PostTag postTag = new PostTag(pk);
//        
//        PostTagPK pk2 = new PostTagPK();
//        pk2.setPost(new Post(new Long(222)));
//        PostTag postTag2 = new PostTag(pk);
//        
//        List list = new ArrayList();
//        list.add(postTag);
//        list.add(postTag2);
//        
//        PostTag[] pl = new PostTag[]{postTag, postTag2};
//        
//        
//        //String value = BeanUtils.getProperty(postTag, "PK.post.id");
//        String[] value = BeanUtils.getArrayProperty(pl, "PK.post.id");
//        //String value = BeanUtils.getSimpleProperty(postTag, "PK.post.id");
//        System.out.println(value.length);
//    }

}
