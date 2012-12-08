<#import "include/simple-one.fun.ftl" as simple>  
<#include "header.ftl" />
	<div class="container main-wrap clearfix">
		<#include "sidebar.ftl" />
		<div class="main-content">
			<div class="post-interactive">
				当前位置：<a href="${blogurl}/album/list.html">相册</a> &gt;&gt; 
				<a href="${blogurl}/album/album-${photo.album.id}-1.html">${photo.album.name}</a> &gt;&gt;
				<a href="javascript:void(0);">${photo.name}</a>
			</div>
			<div class="main-posts">
				<#if photo?exists>
					<div>
						<img src="${photo.url}" style="max-width:${web_photo_max_width}px;max-height:${web_photo_max_height}px;" alt="${photo.description!photo.name}" />
					</div>
					<div class="post-interactive">
						上传时间：${photo.createTime}<br/>
						图片名称：${photo.name}<br/>
						图片描述：${photo.description!""}<br/>
					</div>
				<#else>
					没找到该图片
				</#if>
			</div>
		</div>
	</div>
<#include "footer.ftl" />