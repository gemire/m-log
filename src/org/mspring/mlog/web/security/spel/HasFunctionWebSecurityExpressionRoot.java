/**
 * 
 */
package org.mspring.mlog.web.security.spel;

import java.util.Iterator;
import java.util.List;

import org.mspring.mlog.entity.security.TreeItem;
import org.mspring.mlog.service.security.TreeItemSecurityService;
import org.mspring.mlog.web.security.UserDetailsImpl;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.util.AntPathRequestMatcher;

/**
 * @author Gao Youbo
 * @since 2013-2-17
 * @description
 * @TODO
 */
public class HasFunctionWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

    @Autowired
    private TreeItemSecurityService treeItemSecurityService;

    private Authentication authentication;
    private FilterInvocation filterInvocation;

    /**
     * @param a
     * @param fi
     */
    public HasFunctionWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
        // TODO Auto-generated constructor stub
        this.authentication = a;
        this.filterInvocation = fi;
    }

    public boolean isHasFunction() {
//        Object principal = authentication.getPrincipal();
//        if (principal == null) {
//            return false;
//        }
//        // UserDetailsImpl userDetailsImpl = (UserDetailsImpl) principal;
//
//        String url = filterInvocation.getRequestUrl();
//        if (StringUtils.startsWith(url, "/admin/redirect")) {
//            return true;
//        } else if (StringUtils.startsWith(url, "/admin/index")) {
//            return true;
//        }
//        List<TreeItem> items = treeItemSecurityService.getPremissions(new Long(1));
//        Iterator<TreeItem> it = items.iterator();
//        while (it.hasNext()) {
//            TreeItem item = it.next();
//            if (StringUtils.isNotBlank(item.getCall())) {
//                String pattern = item.getCall() + "/**";
//                AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(pattern);
//                if (requestMatcher.matches(filterInvocation.getRequest())) {
//                    return true;
//                }
//            }
//        }
//        return false;
        return true;
    }

}
