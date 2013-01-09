<#--
<#if (links?exists && links?size > 0)>
	<#list links as link>
		<li><a href="${link.url!""}" target="${link.target!"_blank"}" alt="${link.description!""}">${link.name!""}</a></li>
	</#list>
<#else>
	<li><a href="javascript:void(0);">暂无链接</a></li>
</#if>
-->
<@list_link_type>
	<li>
		<b>${linkType.name}</b>
		<ul>
			<@list_link linkType=linkType.id>
				<li><a href="${link.url!""}" target="${link.target!"_blank"}" alt="${link.description!""}">${link.name!""}</a></li>
			</@list_link>
		</ul>
	</li>
</@list_link_type>