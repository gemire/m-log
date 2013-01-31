<#if (comments?exists && comments?size > 0)>
	<#list comments as comment>
		<li><a href="<@post_url id=comment.post.id />#comment-${comment.id}">${comment.content}</a></li>
	</#list>
<#else>
	<li><a href="javascript:void(0);">暂无评论</a></li>
</#if>