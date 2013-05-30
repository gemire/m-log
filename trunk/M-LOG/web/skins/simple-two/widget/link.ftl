<#if (links?exists && links?size > 0)>
	<#list links as link>
		<li><a href="${link.url?default("")}" target="${link.target?default("_blank")}" alt="${link.description?default("")}"><i class="icon-share"></i>${link.name?default("")}</a></li>
	</#list>
</#if>