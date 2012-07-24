<#if posts?exists>
	<#list posts as post>
		<li><a href="${base}/${post.id}.html">${post.title}</a></li>
	</#list>
</#if>