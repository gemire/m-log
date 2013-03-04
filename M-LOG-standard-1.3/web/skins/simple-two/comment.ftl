<style type="text/css">
.commentlist {
	list-style: none;
	margin: 1em 0 3em;
	padding: 0;
}
.commentlist li {
	border:solid #d3e7f4;
	border-width:1px 0;
	list-style: none;
	padding: 1em 2em;
}
.commentlist .odd {
	background-color:#EDF5FA;
}
.commentlist .even {
	background-color:#ddecf5;
}
.commentlist .content blockquote {
	color: #66a;
	font-weight: bold;
	quotes:"\201C""\201D""\2018""\2019";
	background:#f9f9f9;
	border-left:10px solid #ccc;
	margin:1.5em 10px;
	padding:.5em 10px;
}
.commentlist .content blockquote:before {
	color:#ccc;
	content:open-quote;
	font-size:4em;
	line-height:.1em;
	margin-right:.25em;
	vertical-align:-.4em;
}
.commentlist .content blockquote p {
	display:inline;
}
.commentlist .avatar {
	border: 1px dotted #ccc;
	float: left;
	margin-right: 10px;
	padding: 2px;
	height: 32px;
	width: 32px;
}
.commentlist .comment-author {
	font-weight: bold;
}
.comment-form {
	width:100%;
	margin:0px 10px 0px 10px;
}
.comment-form .item {
	margin:10px 0px 10px 0px;
}
</style>
<script type="text/javascript">
function quote(id){
	var url = mlog.variable.base + "/comment/quote?id=" + id;
	$.getJSON(url, function(cmt){
		//mlog.editor.KindEditor.setHtmlContent("comment_content", cmt.content);
		$("#replycontainer").ScrollTo();
		$("#replymsg").html("<font color='red'><b>@" + cmt.author + "</b></font>&nbsp;&nbsp;<a href='JavaScript:cancel_quote();'>取消回复</a>");
		$("#replycontainer").fadeIn("normal");
		$("#reply_comment").val(id);
	});
}
function cancel_quote(){
	$("#replymsg").html("");
	$("#replycontainer").fadeOut("normal");
	$("#reply_comment").val("");
}
</script>
<h2>评论</h2>
<ol class="commentlist">
	<#assign style="odd" />
	<@list_comment>
		<li class="comment ${style}" id="comment-<@comment_id />">
			<div id="div-comment-<@comment_id />" class="comment-body">
				<div class="comment-author">
					<img class="avatar" alt="<@comment_author />" src="<@comment_gravatar />" height="32" width="32"  /> 
					<a href="<@comment_author_url />" rel="external nofollow" target="_blank" class="url"><@comment_author /></a>
					<span class="says">说道：</span>
				</div>
				<div class="comment-meta commentmetadata">
					<@comment_time />
					<a class="comment-reply-link" href="javascript:quote(<@comment_id />);">回复</a>
				</div>
				<div class="content">
					<#--
					<#if comment.parent?exists>
						<blockquote>@<a href="<@comment_author_url />" rel="external nofollow" target="_blank" class="url">${comment.parent.author}</a>:${comment.parent.content}</blockquote >
					</#if>
					-->
					<#if comment.replyCommentContent?has_content>
						<blockquote>@${comment.replyUser!"蒙面大侠"}：${comment.replyCommentContent}</blockquote>
					</#if>
					<@comment_content />
				</div>
			</div>
		</li>
		<#if style == "odd">
			<#assign style="even" />
		<#else>
			<#assign style="odd" />
		</#if>
	</@list_comment>
</ol>

<#if post.commentStatus == "open">
<div class="box" id="divCommentPost">
	<form id="comment-form" class="comment-form" target="_self" method="post" action="${base}/comment/post">
		<input type="hidden" name="postId" value="${postId}" />
		<input type="hidden" name="reply_comment" id="reply_comment" />
		<div class="item" id="replycontainer" style="display:none;">
			<div id="replymsg"></div>
		</div>
		<div class="item">
			作者：<input name="author" type="text" class="text" value="${author!""}" style="width:150px;" />&nbsp;&nbsp;
			主页：<input name="url" type="text" class="text" value="${url!""}" style="width:150px;" />&nbsp;&nbsp;
			邮箱：<input name="email" type="text" class="text" value="${email!""}" style="width:150px;" />&nbsp;&nbsp;
		</div>
		<div class="item">
			<textarea name="content" id="comment_content"></textarea>
		</div>
		<div class="item">
			<input type="submit" class="btn" valueu="提交" />
		</div>
	</form>
</div>
<#else>
<h2>评论已关闭</h2>
</#if>
<script type="text/javascript" src="${base}/script/kindeditor/kindeditor.js" charset="utf-8"></script>
<script type="text/javascript">
	mlog.initEditor({
		selector : "comment_content",
		width : "700px"
	});
</script>
  
<script type="text/javascript">
/*960*90，创建于2012-11-8*/
var cpro_id = "u1116753";
</script>
<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>