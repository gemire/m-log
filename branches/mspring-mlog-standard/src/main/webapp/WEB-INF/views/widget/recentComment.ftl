<#if (comments?exists && comments?size > 0)>
	<#list comments as comment>
		<li><a href="${base}${comment.post.url}#comment-${comment.id}">${comment.content}</a></li>
	</#list>
<#else>
	<li><a href="javascript:void(0);">暂无评论</a></li>
</#if>