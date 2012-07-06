/**
 * Mar 17, 201110:03:49 AM
 * www.mspring.org
 * @author (gaoyb)mspring
 */
package org.mspring.mlog.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Gao Youbo
 * 
 */
public class ParameterUtils {
    private static final Logger log = Logger.getLogger(ParameterUtils.class);

    public static final String DEFAULT_SPLIT_CHAR = ",";

    public static String[] splitToStringArray(String str) {
        return splitToStringArray(str, DEFAULT_SPLIT_CHAR);
    }

    public static String[] splitToStringArray(String str, String splitChar) {
        if (!StringUtils.isBlank(str)) {
            if (str.indexOf(",") > 0) {
                return str.split(",");
            } else {
                return new String[] { str };
            }
        }
        return null;
    }

    public static Long[] splitToLongArray(String str) {
        return splitToLongArray(str, DEFAULT_SPLIT_CHAR);
    }

    public static Long[] splitToLongArray(String str, String splitChar) {
        String[] str_arr = splitToStringArray(str, splitChar);
        if (str_arr != null) {
            Long[] long_arr = new Long[str_arr.length];
            for (int i = 0; i < str_arr.length; i++) {
                Long long_val = new Long(str_arr[i]);
                long_arr[i] = long_val;
            }
            return long_arr;
        }
        return null;
    }
    
    public static String arrayToString(Object[] array){
        String str = "";
        for(int i = 0; i < array.length; i++) {
            str += array[i];
            if(i < array.length - 1){
                str += ",";
            }
        }
        return str;
    }
}
