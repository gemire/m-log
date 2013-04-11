/**
 * 
 */
package org.mspring.mlog.web.module.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.TagService;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.mlog.support.resolver.PathParam;
import org.mspring.mlog.web.freemarker.widget.stereotype.Widget;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TagService tagService;

    /**
     * 用户名是否存在
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userNameExists")
    @ResponseBody
    public String userNameExists(@PathParam(required = false) String name, @PathParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(name)) {
            return "true";
        }
        try {
            boolean flag = userService.userNameExists(name, id);
            return flag ? "true" : "false";
        } catch (Exception e) {
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
    public String userEmailExists(@PathParam(required = false) String email, @PathParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(email)) {
            return "true";
        }
        try {
            boolean flag = userService.userEmailExists(email, id);
            return flag ? "true" : "false";
        } catch (Exception e) {
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
    public String userAliasExists(@PathParam(required = false) String alias, @PathParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(alias)) {
            return "true";
        }
        try {
            boolean flag = userService.userAliasExists(alias, id);
            return flag ? "true" : "false";
        } catch (Exception e) {
            // TODO: handle exception
            return "true";
        }
    }

    /**
     * 判断分类名称是否存在
     * 
     * @param name
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/catalogNameExists")
    @ResponseBody
    public String catalogNameExists(@PathParam(required = false) String name, @PathParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(name)) {
            return "true";
        }
        boolean flag = catalogService.catalogExists(name, id);
        return flag ? "true" : "false";
    }

    /**
     * 检测角色名字是否存在
     * 
     * @param name
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/roleNameExists")
    @ResponseBody
    public String roleNameExists(@PathParam(required = false) String name, @PathParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(name)) {
            return "true";
        }
        try {
            boolean flag = roleService.checkRoleNameExists(name, id);
            return flag ? "true" : "false";
        } catch (Exception e) {
            // TODO: handle exception
            return "true";
        }
    }

    /**
     * 检测角色名字是否存在
     * 
     * @param name
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/tagNameExists")
    @ResponseBody
    public String tagNameExists(@PathParam(required = false) String name, @PathParam(required = false) Long id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(name)) {
            return "true";
        }
        try {
            boolean flag = tagService.checkTagNameExists(name, id);
            return flag ? "true" : "false";
        } catch (Exception e) {
            // TODO: handle exception
            return "true";
        }
    }
}
