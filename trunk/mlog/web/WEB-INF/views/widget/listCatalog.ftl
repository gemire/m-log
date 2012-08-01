<#if (catalogs?exists && catalogs?size > 0)>
	<#list catalogs as catalog>
		<li><a href="${base}/catalog/${catalog.name!""}">${catalog.name!""}</a></li>
	</#list>
<#else>
	<li><a href="javascript:void(0);">暂无分类</a></li>
</#if>