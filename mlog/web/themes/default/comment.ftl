<ul>
<#if comments?exists>
	<#list comments as comment >
		<li>
			<a id="cmt${comment.id}"></a>
			<div class="comment even">
	  			<div class="clear-block">
	      			<span class="submitted">@${comment.createTime?default("")}&nbsp;by&nbsp;<a href="${comment.homePage}" target="_blank" >${comment.author}</a></span>
	      			<div class="content">
	      				<p>${comment.content?default("")}</p>
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
	<h2>${createNewComment}</h2>
	<div class="content">
		<form id="comment-form" target="_self" method="post" action="${path}/postcomment.action">
			<input type="hidden" name="comment.article.id" value="${article.id}" />
			<table cellpadding="10" cellspacing="10">
				<tr>
					<td align="right" style="width:100px;"><span>${commentAuthorName}</span></td>
					<td align="left" >
						<input name="comment.author" id="comment_author" type="text" validate="{required:true}">
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:100px;"><span>${commentAuthorEmail}</span></td>
					<td align="left" >
						<input name="comment.email" id="comment_email" type="text" validate="{required:true,email:true}">
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:100px;"><span>${commentAuthorHomepage}</span></td>
					<td align="left" >
						<input name="comment.homePage" id="comment_homePage" type="text" validate="{required:true,url:true}">
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:100px;"><span>${commentContent}</span></td>
					<td align="left">
						<textarea name="comment.content" id="comment_content" validate="{required:true}"></textarea>
            		</td>
            		<td align="left"></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: center;">
						<input type="button" name="btnSumbit" id="btn_postcomment" onclick="submitComment();" class="submitbutton" value='${submitComment}'/>
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</div>