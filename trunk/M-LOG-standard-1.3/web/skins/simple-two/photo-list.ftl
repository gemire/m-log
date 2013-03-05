<#import "include/fun.ftl" as simple>
<#include "header.ftl" />
	<style type="text/css">
		.main-albums{
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
			<ul class="breadcrumb">
				<li>
					<a href="${blogurl}">首页</a> <span class="divider">/</span>
			  	</li>
			  	<li>
					<a href="${blogurl}/album/list.html">相册列表</a> <span class="divider">/</span>
			  	</li>
			  	<li class="active">${album.name}</li>
			</ul>
			<div class="row-fluid">
				<#if (photoPage?exists && photoPage.result?size > 0)>
					<div class="main-albums">
						<#list photoPage.result as photo>
							<div class="album-item">
								<a href="${blogurl}/album/photo-${photo.id}.html" title="${photo.description!photo.name}"><img class="item-image" src="${base}${photo.previewUrl}" alt="${photo.description!photo.name}" /></a>
							</div>
						</#list>
					</div>
					<div style="margin:20px;">
						<#if (photoPage.totalPages > 1)>
							<@simple.photoPageNavi />
						</#if>
					</div>
				<#else>
					<div style="margin:20px; padding:20px;">该相册暂时没有照片...</div>
				</#if>
				<#--
				<#if (photoPage?exists && photoPage.result?size > 0)>
					<div class="scrolltab">
						<span id="sLeftBtnA" class="sLeftBtnABan"></span>
						<span id="sRightBtnA" class="sRightBtnA"></span>
						<ul class="ulBigPic">
							<#list photoPage.result as photo>
								<li <#if photo_index == 0>class="liSelected"</#if>>
									<span class="sPic">
										<i class="iBigPic">
											<a href="${blogurl}/album/photo-${photo.id}.html" target="_blank" title="${photo.description!photo.name}">
												<img width="560" height="420" src="${base}${photo.url}">
											</a>
										</i>			
									</span>
									<span class="sSideBox">
										<span class="sTitle"><a href="javascript:void(0)" target="_blank" title="${photo.name}">${photo.name}</a></span>
										<span class="sIntro">
											上传时间：${photo.createTime}<br/>
											图片名称：${photo.name}<br/>
											图片描述：${photo.description!""}<br/>
											简介： ${photo.description!""}
										</span>
										<span class="sMore"><a href="${blogurl}/album/photo-${photo.id}.html" target="_blank">查看大图&gt;&gt;</a></span>
									</span>
								</li>
							</#list>
						</ul>
						<div class="dSmallPicBox">
							<div class="dSmallPic">
								<ul class="ulSmallPic" style="width: 2646px; left: -294px;" rel="stop">
									<#list photoPage.result as photo>
										<li <#if photo_index == 0>class="liSelected"</#if>>
											<span class="sPic"><img alt="${photo.description!photo.name}" src="${base}${photo.previewUrl}" width="135" height="100"></span>
											<span class="sTitle">${photo.description!photo.name}</span>
										</li>
									</#list>
								</ul>
							</div>
							<span id="sLeftBtnB" class="sLeftBtnB"></span>
							<span id="sRightBtnB" class="sRightBtnBBan"></span>
						</div>
					</div>
					<script type="text/javascript" src="${template_url}/script/images.js"></script>
				<#else>
					<div style="margin:20px; padding:20px;">该相册暂时没有照片...</div>
				</#if>
				-->
				
			</div>
		</div>
	</div>
<#include "footer.ftl" />