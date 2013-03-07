/**
 * 
 */
package org.mspring.mlog.web.rulrewrite;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.core.ServiceFactory;
import org.mspring.mlog.utils.PostUrlUtils.Rule;
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
        if (!request.getRequestURI().startsWith(request.getContextPath() + "/post/"))
            return null;

        String id = "";
        String permalinkType = ServiceFactory.getOptionService().getOption("permalink");
        if (Rule.DEFAULT.equals(permalinkType)) {

        } else if (Rule.ID.equals(permalinkType)) {
            Matcher matcher = Pattern.compile("/post/([0-9]+).html").matcher(request.getRequestURI());
            while (matcher.find()) {
                id = matcher.group(1);
            }
        } else if (Rule.MONTH_ID.equals(permalinkType)) {
            Matcher matcher = Pattern.compile("/post/[0-9]+/[0-9]+/([0-9]+).html").matcher(request.getRequestURI());
            while (matcher.find()) {
                id = matcher.group(1);
            }
        } else if (Rule.DATE_ID.equals(permalinkType)) {
            Matcher matcher = Pattern.compile("/post/[0-9]+/[0-9]+/[0-9]+/([0-9]+).html").matcher(request.getRequestURI());
            while (matcher.find()) {
                id = matcher.group(1);
            }
        }

        if (StringUtils.isBlank(id) || !ValidatorUtils.isNumber(id)) {
            return null;
        }

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("id", id);
        return new SimpleRewriteMatch("/post", queryParams);
    }
}
