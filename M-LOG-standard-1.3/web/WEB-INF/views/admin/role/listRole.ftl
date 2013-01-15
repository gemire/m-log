<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<form id="roleForm" name="roleForm" action="${base}/admin/role/list" method="POST">
	<@spring.bind "rolePage" />
	<!-- pagination parameter -->
	<@spring.formHiddenInput path="rolePage.pageNo" />
	<@spring.formHiddenInput path="rolePage.totalPages" />
	<@spring.formHiddenInput path="rolePage.totalCount" />
	<!-- sorter parameter -->
	<@spring.formHiddenInput path="rolePage.sortEnable" />
	<@spring.formHiddenInput path="rolePage.sort.field" />
	<@spring.formHiddenInput path="rolePage.sort.order" />
	<table class="dtable" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				<input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" />
			</th>
			<th>编号</th>
			<th>名称</th>
			<th>是否可用</th>
			<th>操作</th>
		</tr>
		<#if rolePage??>
			<#list rolePage.result as item>
				<#assign tdClass = "odd">
				<#if item_index%2 == 0>
					<#assign tdClass = "even">
				</#if>
				<tr>
					<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
					<td class="${tdClass}">${item.id} </td>
					<td class="${tdClass}">${item.name} </td>
					<td class="${tdClass}">${item.enabled?string('是', '否')} </td>
					<td class="${tdClass}">
						<a href="${base}/admin/role/edit?id=${item.id}">修改</a> | 
						<a href="${base}/admin/role/authorize?id=${item.id}">授权</a>
					</td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<input type="button" class="btn" value=" 删除 " onclick="mlog.form.confirmSubmit('roleForm', '${base}/admin/role/delete');" />
			</td>
			<td>
				<@mspring.pagingnavigator page=rolePage form_id="roleForm" />
			</td>
		</tr>
	</table>
</form>
<#include "../inc/footer.ftl" />