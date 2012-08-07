/**
 * mlog自定义的验证方法
 * @author Gao Youbo
 */

/**
 * 验证文章标题是否存在
 */
$.validator.addMethod('existsPostTitle', function(value, element, params){
	var postId = params[0];
	var url = base + "/admin/post/titleExists?title=" + value;
	if (postId != undefined) {
		url += "&id=" + postId;
	}
	var text = $.ajax({
		url : url,
		async : false
	}).responseText;
	
	alert(text);
	
	var validationResult = eval('('+text+')');
	$.validator.messages["existsPostTitle"] = validationResult.message != undefined ? validationResult.message : $.validator.messages["existsPostTitle"];
	var result = false;
	if(validationResult.result === true){
		result = true;
	}
	return result;
});

/**
 * 验证文章链接是否存在
 */
$.validator.addMethod('existsPostUrl', function(value, element, params){
	var postId = params[0];
	var url = base + "/admin/post/urlExists?url=" + value;
	if (postId != undefined) {
		url += "&id=" + postId;
	}
	var text = $.ajax({
		url : url,
		async : false
	}).responseText;
	
	var validationResult = eval('('+text+')');
	$.validator.messages["existsPostUrl"] = validationResult.message != undefined ? validationResult.message : $.validator.messages["existsPostUrl"];
	var result = false;
	if(validationResult.result === true){
		result = true;
	}
	return result;
});