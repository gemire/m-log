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
	<div id="main">
		<div id="mainContent">
			<div class="forFlow">
				<div class="post-interactive">
					当前位置：<a href="${blogurl}/album/list.html">相册</a> &gt;&gt; 
					<a href="javascript:void(0);">${album.name}</a>
				</div>
				<#if (photoPage?exists && photoPage.result?size > 0)>
					<div class="main-albums">
						<@list_photo>
							<div class="album-item">
								<a href="${blogurl}/album/photo-${photo.id}.html" title="${photo.description!photo.name}" target="_blank"><img class="item-image" src="<@photo_preview_url />" alt="${photo.description!photo.name}" /></a>
							</div>
						</@list_photo>
					</div>
					<#if (photoPage.totalPages > 1)>
						<@simple.photoPageNavi />
					</#if>
				<#else>
					<div style="margin:20px; padding:20px;">该相册暂时没有照片...</div>
				</#if>
			</div>
		</div>
		<#include "sidebar.ftl" />
	</div>
<#include "footer.ftl" />