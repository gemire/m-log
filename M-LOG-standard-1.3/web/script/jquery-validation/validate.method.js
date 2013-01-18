$(document).ready(function() {
	/**
	 * 验证文章标题是否存在
	 */
	$.validator.addMethod("postTitleExists", function(value, element, params) {
		var data = {};
		if (params.id != undefined) {
			data["id"] = params.id;
		}
		data["title"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/postTitleExists",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return false;
		} else {
			return true;
		}
	});

	/**
	 * 验证文章链接是否存在
	 */
	$.validator.addMethod("postUrlExists", function(value, element, params) {
		var data = {};
		if (params.id != undefined) {
			data["id"] = params.id;
		}
		data["url"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/postUrlExists",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return false;
		} else {
			return true;
		}
	});

	/**
	 * 验证文章链接的合法性
	 */
	$.validator.addMethod("postUrlIllegal", function(value, element, params) {
		var data = {};
		data["url"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/postUrlIllegal",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return true;
		} else {
			return false;
		}
	});

	/**
	 * 验证分类名称是否存在
	 */
	$.validator.addMethod("catalogNameExists", function(value, element, params) {
		var data = {};
		if (params.id != undefined) {
			data["id"] = params.id;
		}
		data["name"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/catalogNameExists",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return false;
		} else {
			return true;
		}
	});

	/**
	 * 验证博客地址的合法性
	 */
	$.validator.addMethod("blogurl", function(value, element, params) {
		if (!value.startWith("http://") && !value.startWith("https://")) {
			return false;
		}
		if (value.endWith('/') || value.endWith('\\')) {
			return false;
		}
		return true;
	});

	/**
	 * 检查角色名称是否存在
	 */
	$.validator.addMethod("roleNameExists", function(value, element, params) {
		var data = {};
		if (params.id != undefined) {
			data["id"] = params.id;
		}
		data["name"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/roleNameExists",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return false;
		} else {
			return true;
		}
	});

	////////////////////////////////用户验证 start
	/**
	 * 用户名是否存在
	 */
	$.validator.addMethod("userNameExists", function(value, element, params) {
		var data = {};
		if (params.id != undefined) {
			data["id"] = params.id;
		}
		data["name"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/userNameExists",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return false;
		} else {
			return true;
		}
	});
	
	/**
	 * 用户Email是否存在
	 */
	$.validator.addMethod("userEmailExists", function(value, element, params) {
		var data = {};
		if (params.id != undefined) {
			data["id"] = params.id;
		}
		data["email"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/userEmailExists",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return false;
		} else {
			return true;
		}
	});
	
	/**
	 * 用户别名是否存在
	 */
	$.validator.addMethod("userAliasExists", function(value, element, params) {
		var data = {};
		if (params.id != undefined) {
			data["id"] = params.id;
		}
		data["alias"] = value;
		var result = $.ajax({
			url : mlog.variable.base + "/common/validate/userAliasExists",
			async : false,
			data : data
		}).responseText;
		if (result == "true") {
			return false;
		} else {
			return true;
		}
	});
	
	////////////////////////////////用户验证 end
});
