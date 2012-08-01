<#if (posts?exists && posts?size > 0)>
	<#list posts as post>
		<li><a href="${base}/post/${post.title}">${post.title}</a></li>
	</#list>
<#else>
	<li><a href="javascript:void(0);">暂无文章</a></li>
</#if>