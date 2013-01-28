/**
 * 
 */
package org.mspring.mlog.web.module.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.service.CatalogService;
import org.mspring.mlog.service.PostService;
import org.mspring.mlog.service.security.RoleService;
import org.mspring.mlog.service.security.UserService;
import org.mspring.mlog.utils.PermaLinkUtils;
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
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private PostService postService;
	@Autowired
	private RoleService roleService;

	/**
	 * 用户名是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userNameExists")
	@ResponseBody
	public String userNameExists(@RequestParam(required = false) String name,
			@RequestParam(required = false) Long id,
			HttpServletRequest request, HttpServletResponse response) {
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
	public String userEmailExists(@RequestParam(required = false) String email,
			@RequestParam(required = false) Long id,
			HttpServletRequest request, HttpServletResponse response) {
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
	public String userAliasExists(@RequestParam(required = false) String alias,
			@RequestParam(required = false) Long id,
			HttpServletRequest request, HttpServletResponse response) {
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
	public String catalogNameExists(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Long id,
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(name)) {
			return "true";
		}
		boolean flag = catalogService.catalogExists(name, id);
		return flag ? "true" : "false";
	}

//	/**
//	 * 判断文章标题是否存在
//	 * 
//	 * @param title
//	 * @param id
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("postTitleExists")
//	@ResponseBody
//	public String postTitleExists(@RequestParam(required = false) String title,
//			@RequestParam(required = false) Long id,
//			HttpServletRequest request, HttpServletResponse response) {
//		if (StringUtils.isBlank(title)) {
//			return "true";
//		}
//		boolean flag = postService.titleExists(title, id);
//		return flag ? "true" : "false";
//	}

	/**
	 * 判断链接的合法性
	 * 
	 * @param url
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("postUrlIllegal")
	@ResponseBody
	public String postUrlIllegal(@RequestParam(required = false) String url,
			HttpServletRequest request, HttpServletResponse response) {
		if ("".equals(url) || url == null) {
			return "true";
		}
		// 验证链接是否含有非法字符串，含有非法字符串返回false，验证不通过
		if (PermaLinkUtils.hasIllegalCharacter(url)) {
			return "false";
		}
		if (PermaLinkUtils.invalidParamLink(url)) {
			return "false";
		}
		return "true";
	}

	/**
	 * 判断文章链接是否存在
	 * 
	 * @param url
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("postUrlExists")
	@ResponseBody
	public String postUrlExists(@RequestParam(required = false) String url,
			@RequestParam(required = false) Long id,
			HttpServletRequest request, HttpServletResponse response) {
		if ("".equals(url) || url == null) {
			return "false";
		}
		boolean flag = postService.urlExists(url, id);
		return flag ? "true" : "false";
	}
	
	/**
     * 检测角色名字是否存在
     * @param name
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/roleNameExists")
    @ResponseBody
    public String roleNameExists(@RequestParam(required = false)String name, @RequestParam(required = false)Long id, HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isBlank(name)) {
            return "true";
        }
        try {
            boolean flag = roleService.checkRoleNameExists(name, id);
            return flag ? "true" : "false";
        }
        catch (Exception e) {
            // TODO: handle exception
            return "true";
        }
    }
}
