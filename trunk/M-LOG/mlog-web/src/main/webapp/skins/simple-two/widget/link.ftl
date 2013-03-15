<#if (links?exists && links?size > 0)>
	<#list links as link>
		<li><a href="${link.url!""}" target="${link.target!"_blank"}" alt="${link.description!""}">${link.name!""}</a></li>
	</#list>
</#if>