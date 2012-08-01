<#if (menus?exists && menus?size > 0)>
	<#list menus as menu>
		<li>${menu}</li>
	</#list>
</#if>