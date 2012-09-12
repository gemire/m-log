/**
 * 
 */
package org.mspring.platform.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Gao Youbo
 * @since Apr 15, 2012
 * @see List操作通用类
 */
public class ListUtils extends org.apache.commons.collections.ListUtils {
    /**
     * 数组转换List
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
}
