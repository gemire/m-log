<div class="widget">
	<div class="title">友情链接</div>
	<div class="content">
		<#if (links?exists && links?size > 0)>
			<#list links as link>
				<a href="${link.url!""}" target="${link.target!"_blank"}" alt="${link.description!""}">${link.name!""}</a>&nbsp;
			</#list>
		</#if>
	</div>
</div>