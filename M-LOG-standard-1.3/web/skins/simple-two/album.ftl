<#import "include/fun.ftl" as simple>
<#include "header.ftl" />
	<style type="text/css">
		.main-albums{
			/*padding:10px;*/
			width:100%; margin:0 auto; font-size:0;
		}
		
		.album-item {
			display:inline-block; 
			margin:4px; 
			width:158px; 
			*display:inline; 
			zoom:1; 
			vertical-align:middle; 
			text-align:center;
		}
		
		.album-item .item-image{
			border:solid 2px #efefef; 
			padding:2px; 
			max-height: 150px; 
			max-width: 150px;
		}
		
		.album-meta {
			display:block; 
			width:100%; 
			text-align:center; 
			font-size:12px; 
			color:#666;  
			line-height:20px;
		}
		.album-meta .album-title{
			font-size:14px;
		}
		.album-meta .album-title{
			padding-bottom: 3px;
		}
		.album-meta .album-title:hover{
			text-decoration: none;
    		border-bottom: 2px solid #e93e2f;
		}
	</style>
	<div class="row-fluid">
		<#include "sidebar.ftl" />
		<div class="span9">
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>
						<a href="${blogurl}">首页</a> <span class="divider">/</span>
				  	</li>
				  	<li class="active">相册列表</li>
				</ul>
				<#if (albumPage?exists && albumPage.result?size > 0)>
					<div class="main-albums">
						<#list albumPage.result as album>
							<div class="album-item">
								<#if album.cover?exists>
									<a href="${blogurl}/album/album-${album.id}-1.html" title="${album.description!album.name}"><img class="item-image" src="${base}${album.cover.url}" alt="${album.description!album.name}" /></a>
								<#else>
									<a href="${blogurl}/album/album-${album.id}-1.html" title="${album.description!album.name}"><img class="item-image" src="${base}/images/nophoto.gif" alt="${album.description!album.name}" /></a>
								</#if>
								<div class="album-meta">
									<a href="#" class="album-title">${album.name!""}</a>
								</div>
							</div>
						</#list>
					</div>
					<#if (albumPage.totalPages > 1)>
						<@simple.albumPageNavi />
					</#if>
				<#else>
					<div style="margin:20px; padding:20px;">暂无相册...</div>
				</#if>
			
			</div>
		</div>
	</div>
<#include "footer.ftl" />