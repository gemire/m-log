<#if comments?exists>
	<#list comments as comment>
		<li><a href="#">${comment.content}</a></li>
	</#list>
</#if>