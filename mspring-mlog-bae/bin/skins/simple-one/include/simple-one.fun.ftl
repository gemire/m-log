<#macro pagenavi>
	<#if postPage?exists>
		<style type="text/css">
			.page,.page-current{
			   display:inline-block;
			   padding:0 7px;
			   border:1px solid #fff;
			   line-height:20px;
			   height:20px;
			   cursor:pointer;
			   text-decoration:none;
			}
			.page:hover{
			   border-color:#4787ee;
			   color:#4787ee;
			   }
			.page-current{
			   background:#4787ee;
			   border-color:#4787ee;
			   color:#fff;
			}
			.page-info{
			    text-align:right;
			    margin:0 10px;
			    padding-bottom:30px;
			}
		</style>
		<div class="page-info">
			<span class="page">共${postPage.totalPages}页/${postPage.getTotalCount()}条记录</span>
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
		</div>
	</#if>
</#macro>

<#macro getPostPageUrl page=1 label="${page}" title="第${page}页">
	<#if catalogId?exists && catalogId?has_content>
		<#if postPage.getPageNo() == page>
			<span class="page-current">${page}</span>
		<#else>
			<a class="page" href="${blogurl}/catalog/catalog-${catalogId}-${page}.html" title="${title}">${label}</a>
		</#if>
	<#elseif tagId?exists && tagId?has_content>
		<#if postPage.getPageNo() == page>
			<span class="page-current">${page}</span>
		<#else>
			<a class="page" href="${blogurl}/tag/tag-${tagId}-${page}.html" title="${title}">${label}</a>
		</#if>
	<#else>
		<#if postPage.getPageNo() == page>
			<span class="page-current">${page}</span>
		<#else>
			<a class="page" href="${blogurl}/post/page-${page}.html" title="${title}">${label}</a>
		</#if>
	</#if>
</#macro>

<#-- 相册列表页的分页 -->
<#macro albumPageNavi>
	<#if albumPage?exists>
		<style type="text/css">
			.page,.page-current{
			   display:inline-block;
			   padding:0 7px;
			   border:1px solid #fff;
			   line-height:20px;
			   height:20px;
			   cursor:pointer;
			   text-decoration:none;
			}
			.page:hover{
			   border-color:#4787ee;
			   color:#4787ee;
			   }
			.page-current{
			   background:#4787ee;
			   border-color:#4787ee;
			   color:#fff;
			}
			.page-info{
			    text-align:right;
			    margin:0 10px;
			    padding-bottom:30px;
			}
		</style>
		<div class="page-info">
			<span class="page">共${albumPage.totalPages}页/${albumPage.getTotalCount()}条记录</span>
			<#if (albumPage.getPageNo()>1)>
			    <@getAlbumPageUrl 1, "&lt;&lt;", "首页" />
			</#if>
			<#--如果前面页数过多,显示"..."-->
			<#if (albumPage.getPageNo() > 5)>
			    <#assign prevPages = albumPage.getPageNo() - 9>
			    <#if (prevPages < 1)>
			    	<#assign prevPages=1>
			    </#if>
			    <#assign start=albumPage.getPageNo() - 4>
			    <@getAlbumPageUrl prevPages, "...", "向前5页" />
			<#else>
			    <#assign start = 1>
			</#if>
			
			<#-- 显示当前页附近的页-->
			<#assign end = albumPage.getPageNo() + 4>
			<#if (end > albumPage.totalPages)>
				<#assign end = albumPage.totalPages>
			</#if>
			<#-- start:${start} end:${end} -->
			<#list start..end as index>
				<@getAlbumPageUrl index />
			</#list>
			<#--如果后面页数过多,显示"...":-->
			<#if (end < albumPage.totalPages)>
				<#assign end=end+5>
				<#if (end>albumPage.totalPages)>
					<#assign end=albumPage.totalPages>
				</#if>
				<@getAlbumPageUrl end, "...", "向后5页" />
			</#if>
			<#-- 显示"下一页":-->
			<#if (albumPage.getPageNo() < albumPage.totalPages)>
				<@getAlbumPageUrl albumPage.totalPages, "&gt;&gt;", "末页" />
			</#if>
		</div>
	</#if>
</#macro>
<#macro getAlbumPageUrl page=1 label="${page}" title="第${page}页">
	<#if albumPage.getPageNo() == page>
		<span class="page-current">${page}</span>
	<#else>
		<#if page == 1>
			<a class="page" href="${blogurl}/album/list.html" title="${title}">${label}</a>
		<#else>
			<a class="page" href="${blogurl}/album/list-${page}.html" title="${title}">${label}</a>
		</#if>
	</#if>
</#macro>


<#-- 图片列表页的分页 -->
<#macro photoPageNavi>
	<#if photoPage?exists>
		<style type="text/css">
			.page,.page-current{
			   display:inline-block;
			   padding:0 7px;
			   border:1px solid #fff;
			   line-height:20px;
			   height:20px;
			   cursor:pointer;
			   text-decoration:none;
			}
			.page:hover{
			   border-color:#4787ee;
			   color:#4787ee;
			   }
			.page-current{
			   background:#4787ee;
			   border-color:#4787ee;
			   color:#fff;
			}
			.page-info{
			    text-align:right;
			    margin:0 10px;
			    padding-bottom:30px;
			}
		</style>
		<div class="page-info">
			<span class="page">共${photoPage.totalPages}页/${photoPage.getTotalCount()}条记录</span>
			<#if (photoPage.getPageNo()>1)>
			    <@getPhotoPageUrl 1, "&lt;&lt;", "首页" />
			</#if>
			<#--如果前面页数过多,显示"..."-->
			<#if (photoPage.getPageNo() > 5)>
			    <#assign prevPages = photoPage.getPageNo() - 9>
			    <#if (prevPages < 1)>
			    	<#assign prevPages=1>
			    </#if>
			    <#assign start=photoPage.getPageNo() - 4>
			    <@getPhotoPageUrl prevPages, "...", "向前5页" />
			<#else>
			    <#assign start = 1>
			</#if>
			
			<#-- 显示当前页附近的页-->
			<#assign end = photoPage.getPageNo() + 4>
			<#if (end > photoPage.totalPages)>
				<#assign end = photoPage.totalPages>
			</#if>
			<#-- start:${start} end:${end} -->
			<#list start..end as index>
				<@getPhotoPageUrl index />
			</#list>
			<#--如果后面页数过多,显示"...":-->
			<#if (end < photoPage.totalPages)>
				<#assign end=end+5>
				<#if (end>photoPage.totalPages)>
					<#assign end=photoPage.totalPages>
				</#if>
				<@getPhotoPageUrl end, "...", "向后5页" />
			</#if>
			<#-- 显示"下一页":-->
			<#if (photoPage.getPageNo() < photoPage.totalPages)>
				<@getPhotoPageUrl photoPage.totalPages, "&gt;&gt;", "末页" />
			</#if>
		</div>
	</#if>
</#macro>

<#macro getPhotoPageUrl page=1 label="${page}" title="第${page}页">
	<#if photoPage.getPageNo() == page>
		<span class="page-current">${page}</span>
	<#else>
		<a class="page" href="${blogurl}/album/album-${album.id}-${page}.html" title="${title}">${label}</a>
	</#if>
</#macro>