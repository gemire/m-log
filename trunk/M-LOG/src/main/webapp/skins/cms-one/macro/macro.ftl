<#macro pagenavi>
	<#if postPage?exists>
		<div class="pagination">
		  	<ul>
		    	<#if (postPage.getPageNo()>1)>
				    <@getPostPageUrl 1, "&lt;&lt;", "首页" />
				</#if>
				<#--如果前面页数过多,显示"..."-->
				<#if (postPage.getPageNo() > 5)>
				    <#assign prevPages = postPage.getPageNo() - 9>
				    <#if (prevPages < 1)>
				    	<#assign prevPages=1>
				    </#if>
				    <#assign start=postPage.getPageNo() - 4>
				    <@getPostPageUrl prevPages, "...", "向前5页" />
				<#else>
				    <#assign start = 1>
				</#if>
				
				<#-- 显示当前页附近的页-->
				<#assign end = postPage.getPageNo() + 4>
				<#if (end > postPage.totalPages)>
					<#assign end = postPage.totalPages>
				</#if>
				<#-- start:${start} end:${end} -->
				<#list start..end as index>
					<@getPostPageUrl index />
				</#list>
				<#--如果后面页数过多,显示"...":-->
				<#if (end < postPage.totalPages)>
					<#assign end=end+5>
					<#if (end>postPage.totalPages)>
						<#assign end=postPage.totalPages>
					</#if>
					<@getPostPageUrl end, "...", "向后5页" />
				</#if>
				<#-- 显示"下一页":-->
				<#if (postPage.getPageNo() < postPage.totalPages)>
					<@getPostPageUrl postPage.totalPages, "&gt;&gt;", "末页" />
				</#if>
			</ul>
		</div>
	</#if>
</#macro>

<#macro getPostPageUrl page=1 label="${page}" title="第${page}页">
	<#if catalogId?exists && catalogId?has_content>
		<#if postPage.getPageNo() == page>
			<li class="active"><a href="javascript:void(0);">${page}</a></li>
		<#else>
			<li><a href="${blogurl}/catalog/catalog-${catalogId}-${page}.html" title="${title}">${label}</a>
		</#if>
	<#elseif tagId?exists && tagId?has_content>
		<#if postPage.getPageNo() == page>
			<li class="active">${page}</li>
		<#else>
			<li><a href="${blogurl}/tag/tag-${tagId}-${page}.html" title="${title}">${label}</a></li>
		</#if>
	<#else>
		<#if postPage.getPageNo() == page>
			<li class="active">${page}</li>
		<#else>
			<li><a href="${blogurl}/post/page-${page}.html" title="${title}">${label}</a></li>
		</#if>
	</#if>
</#macro>