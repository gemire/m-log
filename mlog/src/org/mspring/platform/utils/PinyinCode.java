/**
 * 
 */
package org.mspring.platform.utils;

/**
 * @author Gao Youbo
 * @since 2012-7-29
 * @Description
 * @TODO 拼音处理信息
 */
public class PinyinCode {
    public PinyinCode(String py, int cd) {
        pinyin = py;
        code = cd;
    }

    public String pinyin = null;
    public int code = 0;
}
