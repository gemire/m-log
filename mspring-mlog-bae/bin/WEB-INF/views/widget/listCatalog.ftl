<#if (catalogs?exists && catalogs?size > 0)>
	<#list catalogs as catalog>
		<li><a href="<@catalog_url />"><@catalog_name /></a></li>
	</#list>
<#else>
	<li><a href="javascript:void(0);">暂无分类</a></li>
</#if>