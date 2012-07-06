<#include "header.ftl" />
<!-- album list -->
<#if (albumPage?exists && albumPage.result?exists && albumPage.result?size > 0)>
	<ul class="ml mla cl">
		<#list albumPage.result as album>
			<li>
				<div class="c">
					<a href="${path}/photo.action?albumId=${album.id}">
						<#if album.cover?exists>
							<img src="" alt="${album.name}" />
						<#else>
							<img src="${path}/images/nophoto.gif" alt="${album.name}" />
						</#if>
					</a>
				</div>
				<p class="ptn"><a href="http://localhost:10000/discuz/home.php?mod=space&amp;uid=1&amp;do=album&amp;id=2" class="xi2">${album.name}</a> (${album.photoCount!"0"}) </p>
			</li>
		</#list>
	</ul>
<#else>
	暂无相册
</#if>
<#include "footer.ftl" />
