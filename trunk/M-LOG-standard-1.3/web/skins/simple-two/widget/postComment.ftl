<h5>评论</h5>
<#if (comments?exists && comments?size > 0)>
	<ul class="comment-list">
		<#list comments as comment>
			<li>
				<div id="comment-${comment.id}" class="comment-body">
					<img class="avatar" alt="${comment.author}" src="<@gravatar email=comment.email />"/>
					<div class="comment-author">
						<a href="${comment.url!""}" id="comment-author-${comment.id}" rel="external nofollow" target="_blank">${comment.author}</a>
						<span class="says">说道：</span>
					</div>
					<div class="comment-meta">
						${comment.createTime}
						<a class="comment-reply-link" href="javascript:quote(${comment.id});">回复</a>
					</div>
					<div class="comment-content">
						<#if comment.parent?exists && comment.parent.id != 0>
							<#assign parent = comment.parentEager />
							<div class="quotewap">
								<div class="quotetop">@${parent.author!"蒙面大侠"}</div>
								<div class="quotemain">
									${parent.content!""}
								</div>
							</div>
						</#if>
						${comment.content!""}
					</div>
				</div>
			</li>	
		</#list>
	</ul>
</#if>
<#if post.commentStatus == "open">
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
			<input type="button" class="btn btn-primary" value="发表评论" id="btnPostComment" onclick="doSubmit()" />
		</div>
	</form>
	<script type="text/javascript" src="${base}/script/kindeditor/kindeditor.js" charset="utf-8"></script>
	<script type="text/javascript">
		mlog.initEditor({
			selector : "comment_content",
			width : "700px"
		});
		
		function quote(id){
			var commentAuthor = $("#comment-author-" + id).text();
			$("#replymsg").html("<font color='red'><b>@" + commentAuthor + "</b></font>&nbsp;&nbsp;<a href='JavaScript:cancel_quote();'>取消回复</a>");
			$("#replycontainer").fadeIn("normal");
			$("#reply_comment").val(id);
			$.scrollTo('#replycontainer', 300);
		}
		
		function cancel_quote(){
			$("#replymsg").html("");
			$("#replycontainer").fadeOut("normal");
			$("#reply_comment").val("");
		}
		
		function doSubmit(){
			lockSubmit();
			$("#comment-form").submit();
		}
		
		function lockSubmit(){
			$("#btnPostComment").val('正在提交...');
			$("#btnPostComment").attr('disabled', 'disabled');
			$("#btnPostComment").addClass('disabled');
		}
	</script>
<#else>
	<h2>评论已关闭</h2>
</#if>