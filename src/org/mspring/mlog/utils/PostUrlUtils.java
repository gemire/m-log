/**
 * 
 */
package org.mspring.mlog.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;

/**
 * @author Gao Youbo
 * @since 2013-2-28
 * @description
 * @TODO
 */
public class PostUrlUtils {
    /**
     * 获取文章的链接
     * 
     * @param post
     * @return
     */
    public static String getPostUrl(Post post) {
        String postUrl = "/post?id=" + post.getId();
        String permalinkType = ServiceFactory.getOptionService().getOption("permalink");
        /*
         * 自定义链接功能渐渐抛弃 if (StringUtils.isNotBlank(post.getUrl())) { postUrl =
         * post.getUrl(); } else
         */
        if (Rule.ID.equals(permalinkType)) {
            postUrl = String.format("/post/%s.html", post.getId());
        } else if (Rule.MONTH_ID.equals(permalinkType)) {
            if (post.getCreateTime() == null) {
                post = ServiceFactory.getPostService().getPostById(post.getId());
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(post.getCreateTime());
            postUrl = String.format("/post/%s/%s/%s.html", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, post.getId());
        } else if (Rule.DATE_ID.equals(permalinkType)) {
            if (post.getCreateTime() == null) {
                post = ServiceFactory.getPostService().getPostById(post.getId());
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(post.getCreateTime());
            postUrl = String.format("/post/%s/%s/%s/%s.html", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), post.getId());
        }
        return postUrl;
    }

    public static class Rule {
        public static final String DEFAULT = "default";
        public static final String ID = "id";
        public static final String MONTH_ID = "month_id";
        public static final String DATE_ID = "date_id";

        public static final List<String> RULES = Arrays.asList(new String[] { DEFAULT, ID, MONTH_ID, DATE_ID });

        public static final Map<String, String> getRuleMap() {
            Map<String, String> map = new HashMap<String, String>();
            map.put(DEFAULT, "默认(http://localhost:8080/post?id=编号)");
            map.put(ID, "编号(http://localhost:8080/post/编号.html)");
            map.put(MONTH_ID, "月份和编号(http://localhost:8080/post/2012/12/编号.html)");
            map.put(DATE_ID, "日期和编号(http://localhost:8080/post/2012/12/21/编号.html)");
            return map;
        }
    }
}
