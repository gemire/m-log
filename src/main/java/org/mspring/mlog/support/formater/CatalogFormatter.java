/**
 * 
 */
package org.mspring.mlog.support.formater;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.mspring.mlog.entity.Catalog;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.springframework.format.Formatter;

/**
 * @author Gao Youbo
 * @since 2012-8-6
 * @Description
 * @TODO
 */
public class CatalogFormatter implements Formatter<Object> {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.format.Printer#print(java.lang.Object,
     * java.util.Locale)
     */
    @Override
    public String print(Object obj, Locale locale) {
        // TODO Auto-generated method stub
        StringBuffer result = new StringBuffer();
        if (obj != null) {
            if (obj instanceof Catalog) { // 如果输出对象是一个Tag对象
                Catalog catalog = (Catalog) obj;
                result.append(catalog.getName());
            }
            else if (obj instanceof Set) { // 如果输出对象是一个tag的集合
                Set<Catalog> tagSet = (Set<Catalog>) obj;
                int i = 0;
                for (Catalog catalog : tagSet) {
                    result.append(catalog.getName());
                    if (i < (tagSet.size() - 1)) {
                        result.append(",");
                    }
                    i++;
                }
            }
            else if (obj instanceof List) { // 如果输出对象是一个tag集合
                List<Catalog> tagList = (List<Catalog>) obj;
                int i = 0;
                for (Catalog catalog : tagList) {
                    result.append(catalog.getName());
                    if (i < (tagList.size() - 1)) {
                        result.append(",");
                    }
                    i++;
                }
            }
        }
        return result.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.format.Parser#parse(java.lang.String,
     * java.util.Locale)
     */
    @Override
    public Object parse(String param, Locale locale) throws ParseException {
        // TODO Auto-generated method stub
        Set<Catalog> catalogs = new HashSet<Catalog>();
        if (StringUtils.isNotBlank(param)) {
            String[] params = StringUtils.split(param, ",");
            for (String p : params) {
                if (StringUtils.isNotBlank(p) && ValidatorUtils.isNumber(p.trim())) {
                    Catalog catalog = new Catalog();
                    catalog.setId(new Long(p.trim()));
                    if (!catalogs.contains(catalog)) {
                        catalogs.add(catalog);
                    }
                }
            }
        }
        return catalogs;
    }

}
