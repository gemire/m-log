if(typeof(mlog) === "undefined") {var mlog = {};}
mlog.variable = function(){};
$.extend(mlog.variable,{
	<#if variables?exists>
		<#list variables?keys as key>
			<#if key_has_next>
				${key} : '${variables[key]}',
			<#else>
				${key} : '${variables[key]}'
			</#if>
		</#list>
	</#if>
});