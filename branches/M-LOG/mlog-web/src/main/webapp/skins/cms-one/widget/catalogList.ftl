<#if (catalogs?exists && catalogs?size > 0)>
	<div class="well" style="padding: 8px 0;">
		<ul class="nav nav-list">
			<li class="nav-header"><#if parent?exists>${parent.name}<#else>分类目录</#if></li>
			
			<#list catalogs as catalog>
				<li><a href="${base}/catalog/catalog-${catalog.id}.html" target="_blank" style="boder-botton:solid 1px;" title="${catalog.name}"><i class="icon-list"></i>${catalog.name}</a></li>
			</#list>
			
		</ul>
	</div>
</#if>