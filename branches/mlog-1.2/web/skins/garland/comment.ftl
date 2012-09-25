<ol class="commentlist">
	<@list_comment>
	<li class="comment odd alt thread-odd thread-alt" id="comment-<@comment_id />">
		<div id="div-comment-<@comment_id />" class="comment-body">
			<div class="comment-author vcard">
				<img class="avatar" alt="<@comment_author />" src="<@comment_gravatar />" height="32" width="32"  /> 
					<cite class="fn">
						<a href="http://www.mspring.org" rel="external nofollow" class="url">慕春博客</a></cite>
						<span class="says">说道：</span>
			</div>
			<div class="comment-meta commentmetadata">
				<@comment_time />
			</div>

			<p><@comment_content /></p>

			<div class="reply">
				<a class="comment-reply-link" href="javascript:void(0);"
					onclick="return addComment.moveForm(&quot;div-comment-3756&quot;, &quot;3756&quot;, &quot;respond&quot;, &quot;755&quot;)">回复</a>
			</div>
		</div>
	</li>
	</@list_comment>
</ol>

<#if post.commentStatus == "open">
<script type="text/javascript" src="${base}/script/tiny_mce/tiny_mce.js" charset="utf-8"></script>
<div class="box" id="divCommentPost">
	<h2>发表评论</h2>
	<div class="content">
		<form id="comment-form" target="_self" method="post" action="${base}/comment/post">
			<input type="hidden" name="postId" value="${postId}" />
			<table cellpadding="10" cellspacing="10">
				<tr>
					<td align="right" style="width:100px;"><span>作者</span></td>
					<td align="left" >
						<input name="author" type="text" value="${author!""}" validate="{required:true}">
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:100px;"><span>邮箱</span></td>
					<td align="left" >
						<input name="email" type="text" value="${email!""}" validate="{required:true,email:true}">
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:100px;"><span>主页</span></td>
					<td align="left" >
						<input name="url" type="text" value="${url!""}" validate="{required:true,url:true}">
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:100px;"><span>内容</span></td>
					<td align="left">
						<textarea name="content" id="comment_content" validate="{required:true}"></textarea>
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: center;">
						<input type="submit" name="btnSumbit" id="btn_postcomment" class="button" value=' 发   表 '/>
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<#else>
<h2>评论已关闭</h2>
</#if>
<script type="text/javascript" src="${base}/script/kindeditor/kindeditor.js" charset="utf-8"></script>
<script type="text/javascript">
	mlog.initEditor("comment_content");
</script>