/**
 * 
 */
package org.mspring.mlog.web.module.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.security.UserService;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gao Youbo
 * @since 2013-1-15
 * @Description
 * @TODO
 */
@Widget
@RequestMapping("/common/validate")
public class ValidateMethodWidget {

    @Autowired
    private UserService userService;

    /**
     * 用户名是否存在
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userNameExists")
    @ResponseBody
    public String userNameExists(@RequestParam(required = false) String name, @RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(name)) {
            return "true";
        }
        try {
            boolean flag = userService.userNameExists(name, id);
            return flag ? "true" : "false";
        }
        catch (Exception e) {
            // TODO: handle exception
            return "true";
        }
    }

    /**
     * 用户Email是否存在
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userEmailExists")
    @ResponseBody
    public String userEmailExists(@RequestParam(required = false) String email, @RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(email)) {
            return "true";
        }
        try {
            boolean flag = userService.userEmailExists(email, id);
            return flag ? "true" : "false";
        }
        catch (Exception e) {
            // TODO: handle exception
            return "true";
        }
    }

    /**
     * 用户别名是否存在
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userAliasExists")
    @ResponseBody
    public String userAliasExists(@RequestParam(required = false) String alias, @RequestParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(alias)) {
            return "true";
        }
        try {
            boolean flag = userService.userAliasExists(alias, id);
            return flag ? "true" : "false";
        }
        catch (Exception e) {
            // TODO: handle exception
            return "true";
        }
    }
}
