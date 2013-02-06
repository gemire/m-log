/**
 * 
 */
package org.mspring.mlog.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.mspring.mlog.core.ServiceFactory;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since 2012-8-3
 * @Description
 * @TODO 固定链接通用类
 */
public class PermaLinkUtils {
    private static final Logger log = Logger.getLogger(PermaLinkUtils.class);

    public static final String DEFAULT_POST_LINK_REGEX = "/post/\\d{4}/\\d{2}/\\d{2}/\\d+\\.html";

    /**
     * 系统保留的链接
     */
    public static final String[] RESERVED_LINKS = { "/", "/admin", "/admin/*", "/script", "/script/*", "/widget", "/widget/*", "/post", "/album", "/album/*", "/catalog", "/catalog/*", "/comment", "/comment/*", "/menu", "/menu/*", "/search", "/search/*", "*.css", "*.js", "*.png", "*.gif", "*.jpg", "*.bmp", "*.", "/install", "/install/*", "/uploads", "/uploads/*", "/album", "/album/*", "/tag", "/tag/*" };

    /**
     * 链接非法字符
     */
    public static final String[] ILLEGAL_CHARS = new String[] { " ", "//", "\\", "/.", "\\.", "*", "?", "#", "%", "!", "@", "$", "^", "&", "(", ")", "+", "=", "|" };

    /**
     * 判断是否是系统默认的固定链接格式 <br>
     * 系统默认的固定链接格式显示为"/post/yyyy/MM/dd/timezone.html"
     * 
     * @param link
     * @return
     */
    public final static boolean matchDefaultPostPermaLink(String permalink) {
        Pattern pattern = Pattern.compile(DEFAULT_POST_LINK_REGEX);
        Matcher matcher = pattern.matcher(permalink);
        return matcher.matches();
    }

    /**
     * 判断是否是无效的固定链接<br>
     * 
     * @param link
     * @return
     */
    public final static boolean invalidParamLink(String link) {
        if (StringUtils.isBlank(link)) {
            return true;
        }
        // 如果格式为系统默认的文章固定链接
        if (matchDefaultPostPermaLink(link)) {
            return false;
        }
        return invalidUserDefinedPermalink(link);
    }

    /**
     * 判断是否是无效的用户自定义链接
     * 
     * @return
     */
    private static boolean invalidUserDefinedPermalink(String link) {
        if (StringUtils.isBlank(link)) {
            return true;
        }
        if (isReservedLink(link)) {
            return true;
        }

        // 用户自定义链接必须以"/"开头，否则判断为无效
        if (!link.startsWith("/")) {
            return true;
        }

        // //判定如果链接中存在超过一个"/"，那么这个链接就为非法的用户自定义链接
        // int slashCnt = 0;
        // for (int i = 0; i < link.length(); i++) {
        // if ('/' == link.charAt(i)) {
        // slashCnt++;
        // }
        // if (slashCnt > 1) {
        // return true;
        // }
        // }
        return false;
    }

    /**
     * 非法字符验证
     * 
     * @return
     */
    public static boolean hasIllegalCharacter(String link) {
        if (StringUtils.isNotBlank(link)) {
            for (String c : ILLEGAL_CHARS) {
                if (link.indexOf(c) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是系统保留的链接
     * 
     * @param link
     * @return
     */
    private static boolean isReservedLink(String link) {
        if (StringUtils.isBlank(link)) {
            return true;
        }
        for (String reservedLink : RESERVED_LINKS) {
            if (reservedLink.startsWith("*")) {
                if (link.endsWith(reservedLink.substring(1))) {
                    return true;
                }
            }
            if (reservedLink.endsWith("*")) {
                if (link.startsWith(reservedLink.substring(0, reservedLink.indexOf("*")))) {
                    return true;
                }
            }
            else {
                if (reservedLink.equals(link)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final static String getDefaultPostURL() {
        Calendar cal = Calendar.getInstance();
        String url = String.format("/post/%s/%s/%s/%s.html", new Object[] { cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), cal.getTimeInMillis() });
        log.debug("Get permalinks [" + url + "]");
        return url;
    }

    public final static boolean exists(String link) {
        return ServiceFactory.getPostService().urlExists(link);
    }
}