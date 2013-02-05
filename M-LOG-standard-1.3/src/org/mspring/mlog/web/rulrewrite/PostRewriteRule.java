/**
 * 
 */
package org.mspring.mlog.web.rulrewrite;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.entity.Post;
import org.mspring.mlog.web.rulrewrite.match.SimpleRewriteMatch;
import org.mspring.platform.utils.StringUtils;
import org.mspring.platform.utils.ValidatorUtils;
import org.tuckey.web.filters.urlrewrite.extend.RewriteMatch;
import org.tuckey.web.filters.urlrewrite.extend.RewriteRule;

/**
 * @author Gao Youbo
 * @since 2013-1-28
 * @description
 * @TODO
 */
public class PostRewriteRule extends RewriteRule {

    @Override
    public RewriteMatch matches(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        if (!request.getRequestURI().startsWith("/post/"))
            return null;
        
        String id = "";
        String permalinkType = ServiceFactory.getOptionService().getOption("permalink");
        if (Rule.DEFAULT.equals(permalinkType)) {
            
        }
        else if (Rule.ID.equals(permalinkType)) {
            Matcher matcher = Pattern.compile("/post/([0-9]+).html").matcher(request.getRequestURI());
            while (matcher.find()) {
                id = matcher.group(1);
            }
        }
        else if (Rule.MONTH_ID.equals(permalinkType)) {
            Matcher matcher = Pattern.compile("/post/[0-9]+/[0-9]+/([0-9]+).html").matcher(request.getRequestURI());
            while (matcher.find()) {
                id = matcher.group(1);
            }
        }
        else if (Rule.DATE_ID.equals(permalinkType)) {
            Matcher matcher = Pattern.compile("/post/[0-9]+/[0-9]+/[0-9]+/([0-9]+).html").matcher(request.getRequestURI());
            while (matcher.find()) {
                id = matcher.group(1);
            }
        }
        
        if (StringUtils.isBlank(id) || !ValidatorUtils.isNumber(id)) {
            return null;
        }
        
        Map<String,Object> queryParams = new HashMap<String, Object>();
        queryParams.put("id", id);
        return new SimpleRewriteMatch("/post", queryParams);
    }
    
    
    /**
     * 获取文章的链接
     * @param post
     * @return
     */
    public static String getPostUrl(Post post){
        String postUrl = "/post?id=" + post.getId();
        String permalinkType = ServiceFactory.getOptionService().getOption("permalink");
        if (StringUtils.isNotBlank(post.getUrl())) {
            postUrl = post.getUrl();
        } else if (Rule.ID.equals(permalinkType)) {
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
