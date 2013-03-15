/**
 * 
 */
package org.mspring.platform.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gao Youbo
 * @since 2012-12-2
 * @Description
 * @TODO
 */
public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {
    /**
     * 
     * 从第一个数组中剔除第二个数组中的项目，即：t1 - t2
     * 
     * @param <T>
     * @param t1
     * @param t2
     * @param type
     * @return
     */
    public static <T extends Object> T[] removeAll(T[] t1, T[] t2, Class type) {
        if (t1 == null) {
            return null;
        }
        if (t1.length == 0) {
            return t2;
        }
        List<T> ret = new ArrayList<T>();
        for (T t : t1) {
            if (!ArrayUtils.contains(t2, t)) {
                ret.add(t);
            }
        }
        return listToArray(ret, type);
    }

    /**
     * 集合转数组
     * 
     * @param list
     * @param type
     * @return
     */
    public static <T extends Object> T[] listToArray(List<T> list, Class type) {
        ArrayMaker<T> arrayMarker = new ArrayMaker<T>(type);
        if (list != null && list.size() > 0) {
            T[] ret = arrayMarker.createArray(list.size());
            for (int i = 0; i < list.size(); i++) {
                ret[i] = list.get(i);
            }
            return ret;
        }
        return null;
    }

    /**
     * 数组转换List
     * 
     * @param <T>
     * @param array
     * @return
     */
    public static <T extends Object> List<T> arrayToList(T[] array) {
        if (array != null) {
            List<T> list = new ArrayList<T>();
            for (T t : array) {
                list.add(t);
            }
            return list;
        }
        return null;
    }
    
    /**
     * 不区分大小写的Contains
     * @param array
     * @param valueToFind
     * @return
     */
    public static boolean ignoreCaseContains(String[] array, String valueToFind){
        if (array == null || array.length == 0 || valueToFind == null) {
            return false;
        }
        for (String str : array) {
            if (str.equalsIgnoreCase(valueToFind)) {
                return true;
            }
        }
        return false;
    }

}