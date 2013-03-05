<#import "include/fun.ftl" as simple>  
<#include "header.ftl" />
	<div class="row-fluid">
		<#include "sidebar.ftl" />
		<div class="span9">
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>
						<a href="${blogurl}">首页</a> <span class="divider">/</span>
				  	</li>
				  	<li>
						<a href="${blogurl}/album/list.html">相册列表</a> <span class="divider">/</span>
				  	</li>
				  	<li>
						<a href="${blogurl}/album/album-${photo.album.id}-1.html">${photo.album.name}</a> <span class="divider">/</span>
				  	</li>
				  	<li class="active">${photo.name}</li>
				</ul>
				<div class="main-posts">
					<#if photo?exists>
						<div>
							<img src="${base}${photo.url}" style="max-width:${web_photo_max_width}px;max-height:${web_photo_max_height}px;" alt="${photo.description!photo.name}" />
						</div>
						<div class="post-interactive">
							上传时间：${photo.createTime}<br/>
							图片名称：${photo.name}<br/>
							图片描述：${photo.description!""}<br/>
						</div>
					<#else>
						<div class="alert alert-error" style="padding:30px;font-size:24px;">
							404, 没找到您想要的照片。
						</div>
					</#if>
				</div>
			</div>
		</div>
	</div>
<#include "footer.ftl" />