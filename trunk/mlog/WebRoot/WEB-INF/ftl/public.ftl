<#-- <#assign mspring=JspTaglibs["/WEB-INF/mspring.tld"]> -->
<#-- 获取cookie -->
<#function getCookie cookieName>
	<#local cookies = request.getCookies()>
    <#list cookies as cookie>
    	<#if cookie.name == cookieName>
    		<#return cookie.value>
    	</#if>
    </#list>
</#function>