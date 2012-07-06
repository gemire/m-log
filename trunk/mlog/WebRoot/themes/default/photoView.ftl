<#include "header.ftl" />
<!-- photo view -->
<#if photo?exists>
	<div class="tbmu" id="pic_block">
		<div class="y">
			<a href="javascript:rotateRight('pic', 90);"><img class="vm" src="http://localhost:10000/discuz/static/image/common/rleft.gif"></a>
			<a href="javascript:rotateLeft('pic', 90);"><img class="vm" src="http://localhost:10000/discuz/static/image/common/rright.gif"></a>
			<span class="pipe">|</span>
			<a href="javascript:void(0);">上一张</a>
			<span class="pipe">|</span>
			<a href="javascript:void(0);" id="nextlink">下一张</a>
			<span class="pipe">|</span>
			<a href="javascript:;" id="playid" class="osld" onclick="playNextPic(true);">幻灯播放</a>
			<span class="pipe">|</span>
			<a href="${path}/photo.action?albumId=${albumId}">« 返回图片列表</a>
		</div>
		
		<!-- <span class="pipe">|</span>当前第 3 张<span class="pipe">|</span>共 12 张图片&nbsp; -->
	</div>
	<div class="vw pic">
		<div id="photo_pic" class="c" style="position: relative;">
			<img src="${path}/${photo.url}" id="pic" alt="${photo.name}">
		</div>
		<br />
		<div class="pns mlnv vm mtm cl" style="overflow-x:auto;vertical-align: middle;">
			<a href="javascript:void(0);" class="btn" title="上一张">
				<img src="http://localhost:10000/discuz/static/image/common/pic_nv_prev.gif" alt="上一张">
			</a>
			<#if photos?exists>
				<#list photos as pre_photo>
					<a href="${path}/photoView.action?albumId=${pre_photo.album.id}&photoId=${pre_photo.photoId}#pic_block"><#if pre_photo.photoId == photo.photoId><img alt="${pre_photo.name}" src="${path}/${pre_photo.previewUrl}" class="a"><#else><img alt="${pre_photo.name}" src="${path}/${pre_photo.previewUrl}"></#if></a>
				</#list>
			</#if>
			<a href="javascript:void(0);" class="btn" title="下一张">
				<img src="http://localhost:10000/discuz/static/image/common/pic_nv_next.gif" alt="下一张">
			</a>
		</div>
	</div>
<#else>
	未找到该照片，<a href="${path}/photo.action?albumId=${albumId}">返回图片列表</a>
</#if>
<#include "footer.ftl" />
