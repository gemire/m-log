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
					<#if comment.parent?exists>
						<blockquote>@<a href="<@comment_author_url />" rel="external nofollow" target="_blank" class="url">${comment.parent.author}</a>:${comment.parent.content}</blockquote >
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
	<div class="content">
		<form id="comment-form"  target="_self" method="post" action="${base}/comment/post">
			<input type="hidden" name="postId" value="${postId}" />
			<input type="hidden" name="parentId" id="parentId" />
			<table cellpadding="10" cellspacing="10" style="1px solid red;">
				<tr id="replycontainer" style="display:none;">
					<td></td>
					<td id="replymsg"></td>
				</tr>
				<tr>
					<td align="right"><span>作者</span></td>
					<td align="left" >
						<input name="author" type="text" class="text" value="${author!""}" />
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right"><span>邮箱</span></td>
					<td align="left" >
						<input name="email" type="text" class="text" value="${email!""}" />
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right"><span>主页</span></td>
					<td align="left" >
						<input name="url" type="text" class="text" value="${url!""}" />
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right"><span>内容</span></td>
					<td align="left">
						<textarea name="content" id="comment_content"></textarea>
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: center;">
						<input type="submit" id="btn_postcomment" class="button" value=' 发   表 '/>
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