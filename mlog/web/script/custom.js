/*
 * 前台页面通用JavaScript文件
 * @author GaoYoubo
 * @since 2012-07-28
 * 
 */


/**
 * 获取Cookie值
 */
function getCookie(sName) {
	var arr = document.cookie.match(new RegExp("(^| )"+sName+"=([^;]*)(;|$)"));
	if(arr !=null){return unescape(arr[2])};
	return null;
}

/**
 * 设置cookie值
 * @param sName 名字
 * @param sValue 值
 * @param iExpireDays cookie保存时间(单位：天)
 */
function setCookie(sName, sValue,iExpireDays) {
	if (iExpireDays){
		var dExpire = new Date();
		dExpire.setTime(dExpire.getTime()+parseInt(iExpireDays*24*60*60*1000));
		document.cookie = sName + "=" + escape(sValue) + "; expires=" + dExpire.toGMTString()+ "; path=/";
	}
	else{
		document.cookie = sName + "=" + escape(sValue)+ "; path=/";
	}
}

/**
 * 获取cookie中保存的评论作者
 * @returns
 */
function getCookieCommentAuthor(){
	return getCookie("comment_author_cookie");
}

/**
 * 获取cookie中保存的评论邮箱
 * @returns
 */
function getCookieCommentEmail(){
	return getCookie("comment_email_cookie");
}

/**
 * 获取评论中保存的评论作者主页地址
 * @returns
 */
function getCookieCommentUrl(){
	return getCookie("comment_url_cookie");
}