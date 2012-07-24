<#if catalogs?exists>
	<#list catalogs as catalog>
		<li><a href="${base}/catalog/${catalog.id!""}">${catalog.name!""}</a></li>
	</#list>
</#if>