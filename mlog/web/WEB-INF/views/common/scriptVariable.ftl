<#if variables?exists>
	<#assign vars = variables>
	<#list vars?keys as key>
		<#assign value = vars[key]>
		var ${key}='${value}';
	</#list>
</#if>