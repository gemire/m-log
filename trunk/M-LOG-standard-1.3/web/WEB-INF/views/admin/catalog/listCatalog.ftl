<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<form id="catalogForm" name="catalogForm" action="${base}/admin/catalog/list" method="POST">
	<@spring.bind "catalogPage" />
	<!-- pagination parameter -->
	<@spring.formHiddenInput path="catalogPage.pageNo" />
	<@spring.formHiddenInput path="catalogPage.totalPages" />
	<@spring.formHiddenInput path="catalogPage.totalCount" />
	<!-- sorter parameter -->
	<@spring.formHiddenInput path="catalogPage.sortEnable" />
	<@spring.formHiddenInput path="catalogPage.sort.field" />
	<@spring.formHiddenInput path="catalogPage.sort.order" />
	<table class="dtable" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				<input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" />
			</th>
			<#if columnfields??>
				<#list columnfields as field>
					<th>${field.name!""}</th>
				</#list>
			</#if>
			<th>
				操作
			</th>
		</tr>
		<#if catalogPage??>
			<#list catalogPage.result as item>
				<#assign tdClass = "odd">
				<#if item_index%2 == 0>
					<#assign tdClass = "even">
				</#if>
				<tr>
					<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
					<#if columnfields??>
						<#list columnfields as field>
							<#if field??>
								<td class="${tdClass}">${item['${field.id}']!""} </td>
							<#else>
								<td class="${tdClass}"></td>
							</#if>
						</#list>
					</#if>
					<td class="${tdClass}">
						<a href="${base}/admin/catalog/edit?id=${item.id}">修改</a>
					</td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<input type="button" class="btn" value=" 删除 " onclick="mlog.form.confirmSubmit('catalogForm', '${base}/admin/catalog/delete');" />
			</td>
			<td>
				<@mspring.pagingnavigator page=catalogPage form_id="catalogForm" />
			</td>
		</tr>
	</table>
</form>
<#include "../inc/footer.ftl" />