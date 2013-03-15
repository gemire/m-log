<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form id="albumForm" name="albumForm" action="${base}/admin/album/list" method="POST">
	<@spring.bind "albumPage" />
	<!-- pagination parameter -->
	<@spring.formHiddenInput path="albumPage.pageNo" />
	<@spring.formHiddenInput path="albumPage.totalPages" />
	<@spring.formHiddenInput path="albumPage.totalCount" />
	<!-- sorter parameter -->
	<@spring.formHiddenInput path="albumPage.sortEnable" />
	<@spring.formHiddenInput path="albumPage.sort.field" />
	<@spring.formHiddenInput path="albumPage.sort.order" />
	
	<#if (albumPage?exists && albumPage.result?size > 0)>
		<div id="wall">
			<#list albumPage.result as album>
				<div class="item">
					<div class="itemdiv">
						<a href="${base}/admin/photo/list?album.id=${album.id}" title="${album.name!""}">
							<#if album.cover?exists>
								<img src="${base}${album.cover.previewUrl}" alt="${album.description!album.name}" class="itemimage" />
							<#else>
								<img src="${base}/images/nophoto.gif" alt="${album.description!album.name}" class="itemimage" />
							</#if>
						</a>
					</div>
					<div class="itemmeta">
						<a href="#" class="itemtitle">${album.name!""}</a>
						<a href="${base}/admin/album/edit?id=${album.id}" class="ctrl">编辑</a>
						<a href="javascript:mlog.form.confirmSubmit('albumForm', '${base}/admin/album/delete?id=${album.id}', '确认要删除该相册吗？');" class="ctrl">删除</a>
					</div>
				</div>
			</#list>
		</div>
		<table style="width:100%;">
			<tr>
				<td>
				</td>
				<td>
					<@mspring.pagingnavigator page=albumPage form_id="albumForm" />
				</td>
			</tr>
		</table>
	<#else>
		<div style="margin:10px;">
		暂无相册，<a href="${base}/admin/album/create">点击这里创建>></a>
		</div>
	</#if>
</form>
<script type="text/javascript">
	turnHighLight(120005);
</script>
<#include "../inc/footer.ftl" />