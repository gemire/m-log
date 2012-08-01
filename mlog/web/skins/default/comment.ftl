<ul>
<#if (comments?exists && comments?size > 0)>
	<#list comments as comment >
		<li>
			<a id="comment-${comment.id}"></a>
			<div class="comment even">
	  			<div class="clear-block">
	      			<span class="submitted">@${comment.createTime!""}&nbsp;by&nbsp;<a href="${comment.url}" target="_blank" >${comment.author}</a></span>
	      			<div class="content">
	      				<p>${comment.content!""}</p>
	    			</div>
	  			</div>
	  			<div class="links">
	  				<ul class="links">
	  					<li class="first last comment_reply">
	  						<a href="#divCommentPost" onclick="RevertComment('${comment.id}')" class="comment_reply"></a>
	  					</li>
					</ul>
				</div>
	  		</div>
  		</li>
	</#list>
</#if>
</ul>

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
						<textarea name="content" validate="{required:true}"></textarea>
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