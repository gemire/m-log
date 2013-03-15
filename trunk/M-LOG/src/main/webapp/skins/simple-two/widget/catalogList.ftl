<#if (catalogs?exists && catalogs?size > 0)>
	<#list catalogs as catalog>
		<li><a href="${base}/catalog/catalog-${catalog.id}.html" target="_blank" style="boder-botton:solid 1px;" title="${catalog.name}"><i class="icon-list"></i>${catalog.name}</a></li>
	</#list>
</#if>