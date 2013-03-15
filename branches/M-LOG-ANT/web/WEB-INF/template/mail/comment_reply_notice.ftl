<#--
邮件通知模板
-->
<style type="text/css">
blockquote {
	color: #66a;
	font-weight: bold;
	/*font-style: italic;*/
	quotes:"\201C""\201D""\2018""\2019";
	background:#f9f9f9;
	border-left:10px solid #ccc;
	margin:1.5em 10px;
	padding:.5em 10px;
}
blockquote:before {
	color:#ccc;
	content:open-quote;
	font-size:4em;
	line-height:.1em;
	margin-right:.25em;
	vertical-align:-.4em;
}
blockquote p {
	display:inline;
}
</style>
<div>
	${comment.replyUser!"来宾"},您好:<br/>
	您的评论
	<blockquote>${comment.replyCommentContent}</blockquote>
	被用户<a href="${comment.url!"#"}" target="_blank">${comment.author!""}</a>回复.</br> 
	回复内容:<p>${comment.content}<p>
	详情请<a href="${commentUrl}#comment-${comment.id}" target="_blank">点击这里查看...</a>
</dvi>