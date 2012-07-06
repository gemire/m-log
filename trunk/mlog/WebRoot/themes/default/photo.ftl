<#include "header.ftl" />
<!-- photo list -->
<div class="y">
	<a href="${path}/album.action">« 返回相册列表</a>
</div>
<ul class="ml mla cl">
	<#if (photoPage?exists && photoPage.result?exists && photoPage.result?size > 0)>
		<#list photoPage.result as photo>
			<li>
				<div class="c">
					<a href="${path}/photoView.action?albumId=${photo.album.id}&photoId=${photo.photoId}#pic_block">
						<img src="${path}/${photo.previewUrl}" alt="${photo.name}" />
					</a>
				</div>
				<p class="ptn"><a href="http://localhost:10000/discuz/home.php?mod=space&amp;uid=1&amp;do=album&amp;id=2" class="xi2">${photo.name}</a></p>
			</li>
		</#list>
	<#else>
		该相册暂无相片，<a href="${path}/album.action">返回相册列表</a>
	</#if>
</ul>
<#include "footer.ftl" />
