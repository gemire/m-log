<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<form id="adForm" name="adForm" action="${base}/admin/ad/list" method="POST">
	<@spring.bind "adPage" />
	<!-- pagination parameter -->
	<@spring.formHiddenInput path="adPage.pageNo" />
	<@spring.formHiddenInput path="adPage.totalPages" />
	<@spring.formHiddenInput path="adPage.totalCount" />
	<!-- sorter parameter -->
	<@spring.formHiddenInput path="adPage.sortEnable" />
	<@spring.formHiddenInput path="adPage.sort.field" />
	<@spring.formHiddenInput path="adPage.sort.order" />
	<table class="dtable" cellspacing="0" cellpadding="0">
		<tr>
			<th><input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" /></th>
			<th>编号</th>
			<th>名称</th>
			<th>创建时间</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>操作</th>
		</tr>
		<#if adPage??>
			<#list adPage.result as item>
				<#assign tdClass = "odd">
				<#if item_index%2 == 0>
					<#assign tdClass = "even">
				</#if>
				<tr>
					<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
					<td class="${tdClass}">${item.id} </td>
					<td class="${tdClass}">${item.name} </td>
					<td class="${tdClass}">${item.createTime} </td>
					<td class="${tdClass}">${item.startDate!""} </td>
					<td class="${tdClass}">${item.endDate!""} </td>
					<td class="${tdClass}">
						<a href="${base}/admin/ad/edit?id=${item.id}">修改</a> | <a href="javascript:code(${item.id});">获取代码</a>
					</td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<input type="button" class="btn" value=" 删除 " onclick="mlog.form.confirmSubmit('adForm', '${base}/admin/ad/delete', '确认要将删除选中广告吗?');" />
			</td>
			<td>
				<@mspring.pagingnavigator page=adPage form_id="adForm" />
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	turnHighLight(510005);
	function code(id){
		mlog.dialog.showDialog({
			title: '获取代码',
			content: '&lt;@ad id=' + id + ' /&gt;',
			max: false,
			min: false
		});
	}
</script>
<#include "../inc/footer.ftl" />