/**
 * 引用
 */
function quote(id){
	var url = mlog.variable.base + "/comment/quote?id=" + id;
	$.getJSON(url, function(cmt){
		//mlog.editor.KindEditor.setHtmlContent("comment_content", cmt.content);
		$("#replycontainer").ScrollTo();
		$("#replymsg").html("<font color='red'><b>@" + cmt.author + "</b></font>&nbsp;&nbsp;<a href='JavaScript:cancel_quote();'>取消回复</a>");
		$("#replycontainer").fadeIn("normal");
		$("#parentId").val(id);
		
	});
}

/**
 * 取消回复
 */
function cancel_quote(){
	$("#replymsg").html("");
	$("#replycontainer").fadeOut("normal");
	$("#parentId").val("");
}