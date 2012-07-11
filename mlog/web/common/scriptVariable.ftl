<#if variables?exists>
	<#assign vars = variables>
	<#list vars?keys as key>
		<#assign value = vars[key]>
		${key}='${value}';
	</#list>
</#if>