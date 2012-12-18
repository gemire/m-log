<#if (posts?exists && posts?size > 0)>
	<#list posts as post>
		<li><a href="<@absolute_post_url />"><@post_title /></a></li>
	</#list>
<#else>
	<li><a href="javascript:void(0);">暂无文章</a></li>
</#if>