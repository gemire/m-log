/**
 * 设置cookie
 * 
 * @param {}
 *            sName 名称
 * @param {}
 *            sValue Cookie值
 * @param {}
 *            iExpireDays 保存天数
 */
function SetCookie(sName, sValue, iExpireDays) {
	if (iExpireDays) {
		var dExpire = new Date();
		dExpire.setTime(dExpire.getTime()
				+ parseInt(iExpireDays * 24 * 60 * 60 * 1000));
		document.cookie = sName + "=" + escape(sValue) + "; expires="
				+ dExpire.toGMTString() + "; path=/";
	} else {
		document.cookie = sName + "=" + escape(sValue) + "; path=/";
	}
}

/**
 * 获取Cookie
 * 
 * @param {}
 *            sName Cookie名称
 * @return {} Cookie值
 */
function GetCookie(sName) {
	var arr = document.cookie.match(new RegExp("(^| )" + sName
			+ "=([^;]*)(;|$)"));
	if (arr != null) {
		return unescape(arr[2])
	};
	return null;
}

/**
 * 加载信息
 */
function LoadRememberInfo() {
	var authorObj = document.getElementById("comment_author");
	var emailObj = document.getElementById("comment_email");
	var homePageObj = document.getElementById("comment_homePage");

	var cookie_author = GetCookie("mspring_cookie_comment_author");
	var cookie_email = GetCookie("mspring_cookie_comment_email");
	var cookie_homepage = GetCookie("mspring_cookie_comment_homePage");

	if (authorObj && cookie_author) {
		authorObj.value = cookie_author;
	}
	if (emailObj && cookie_email) {
		emailObj.value = cookie_email;
	}
	if (homePageObj && cookie_homepage) {
		homePageObj.value = cookie_homepage;
	}
}

/**
 * 保存信息
 */
function SaveRememberInfo() {
	var author = document.getElementById("comment_author").value;
	var email = document.getElementById("comment_email").value;
	var homePage = document.getElementById("comment_homePage").value;

	SetCookie("mspring_cookie_comment_author", author, 365);
	SetCookie("mspring_cookie_comment_email", email, 365);
	SetCookie("mspring_cookie_comment_homePage", homePage, 365);
}

/**
 * 文章统计
 * 
 * @param {}
 *            articleId
 */
function singleStatistic(articleId) {
	$.ajax({
				type : "GET",
				url : "singleStatistic.action?articleId=" + articleId,
				async : true
			});
}

/**
 * 网站访问量统计
 */
function visitStatistic() {
	$.ajax({
				type : "GET",
				url : "visitStatistic.action",
				async : true
			});
}

function setSessionAttribute(key, value) {
	var url = "admin/setSessionAttribute.action?key=" + key + "&value=" + value;
	$.post(url, function(data) {
			});
}

function getSessionAttribute(key) {
	var url = "admin/setSessionAttribute.action?key=" + key;
	$.post(url, function(data) {
			});
}

/**
 * 发表评论
 */
function submitComment() {
	$("#comment-form").submit();
	SaveRememberInfo();
}

/**
 * 页面初始化时调用
 */
function init() {
	LoadRememberInfo();
}

window.onload = init;