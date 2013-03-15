<#if tabs?exists>
	<#if (tabs?size > 1)>
		<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <#list tabs as tab>
				    <#if tab.id == openTab.id>
				    	<li><a id="${tab.id}" href="${base}/admin/redirect?id=${tab.id}" class="here">${tab.name}</a></li>
				    <#else>
				    	<li><a id="${tab.id}" href="${base}/admin/redirect?id=${tab.id}">${tab.name}</a></li>
				    </#if>
			    </#list>
			</ul>
		</div>
		<div class="tab-container">
	</#if>
</#if>