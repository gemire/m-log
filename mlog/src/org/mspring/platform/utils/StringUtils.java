/**
 * 
 */
package org.mspring.platform.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static String joinAndWrap(String[] array, String separator, String wrapWith) {
        List tmp = new ArrayList();
        for (String str : array) {
            tmp.add(wrapWith + str + wrapWith);
        }
        return StringUtils.join(tmp, separator);
    }

    public static String substringMaxLength(String str, int maxLength) {
        if (str.length() > maxLength) {
            return StringUtils.substring(str, 0, maxLength);
        }
        return str;
    }

    public static String buildPath(Object[] parts) {
        StringBuffer pathBuffer = new StringBuffer();
        boolean first = true;
        for (Object part : parts) {
            if (first) {
                pathBuffer.append(part);
                first = false;
            } else if (StringUtils.startsWith(part.toString(), ".")) {
                pathBuffer.append(part);
            } else {
                pathBuffer.append("/").append(part);
            }
        }
        return pathBuffer.toString();
    }

    /**
     * retrive the extend name of the given filename
     * 
     * @param fn
     * @return
     */
    public static String getFileExtend(String fn) {
        if (isEmpty(fn))
            return null;
        int idx = fn.lastIndexOf('.') + 1;
        if (idx == 0 || idx >= fn.length())
            return null;
        return fn.substring(idx);
    }

    /**
     * 根据时间戳获取唯一的文件名称
     * 
     * @return
     */
    public static String getFileName() {
        return new Date().getTime() + "";
    }

    /**
     * 将字符串用ch分割并放入队列
     * 
     * @param tags
     * @param ch
     * @return
     */
    public static List stringToList(String tags, String ch) {
        if (tags == null)
            return null;
        ArrayList tagList = new ArrayList();
        StringTokenizer st = new StringTokenizer(tags, ch);
        while (st.hasMoreElements()) {
            tagList.add(st.nextToken());
        }
        return tagList;
    }

    /**
     * 反转字符串
     * 
     * @param s
     * @return
     */
    public static String reverse(String s) {
        if (s == null) {
            return null;
        }
        char[] c = s.toCharArray();
        char[] reverse = new char[c.length];
        for (int i = 0; i < c.length; i++) {
            reverse[i] = c[c.length - i - 1];
        }
        return new String(reverse);
    }

    /**
     * 提取字符
     * @param s
     * @return
     */
    public static String extractChars(String s) {
        if (s == null) {
            return "";
        }

        char[] c = s.toCharArray();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < c.length; i++) {
            if (ValidatorUtils.isChar(c[i])) {
                sb.append(c[i]);
            }
        }

        return sb.toString();
    }

    /**
     * 提取数字
     * @param s
     * @return
     */
    public static String extractDigits(String s) {
        if (s == null) {
            return "";
        }

        char[] c = s.toCharArray();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < c.length; i++) {
            if (ValidatorUtils.isDigit(c[i])) {
                sb.append(c[i]);
            }
        }

        return sb.toString();
    }

    /**
     * 提取开头
     * @param s
     * @param delimiter
     * @return
     */
    public static String extractFirst(String s, String delimiter) {
        if (s == null) {
            return null;
        } else {
            String[] array = split(s, delimiter);

            if (array.length > 0) {
                return array[0];
            } else {
                return null;
            }
        }
    }

    /**
     * 提取结尾
     * @param s
     * @param delimiter
     * @return
     */
    public static String extractLast(String s, String delimiter) {
        if (s == null) {
            return null;
        } else {
            String[] array = split(s, delimiter);

            if (array.length > 0) {
                return array[array.length - 1];
            } else {
                return null;
            }
        }
    }
}
