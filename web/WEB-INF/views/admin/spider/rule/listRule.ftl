<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "/WEB-INF/views/admin/inc/header.ftl" />
<form id="ruleForm" name="ruleForm" action="${base}/admin/spider/rule/list" method="POST">
	<@spring.bind "rulePage" />
	<@spring.formHiddenInput path="rulePage.pageNo" />
	<@spring.formHiddenInput path="rulePage.totalPages" />
	<@spring.formHiddenInput path="rulePage.totalCount" />
	
	<@spring.formHiddenInput path="rulePage.sortEnable" />
	<@spring.formHiddenInput path="rulePage.sort.field" />
	<@spring.formHiddenInput path="rulePage.sort.order" />
	<table class="dtable" cellspacing="0" cellpadding="0">
		<tr>
			<th><input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" /></th>
			<th>编号</th>
			<th>名称</th>
			<th>操作</th>
		</tr>
		<#if rulePage?exists>
			<#list rulePage.result as item>
				<#assign tdClass = "odd">
				<#if item_index%2 == 0>
					<#assign tdClass = "even">
				</#if>
				<tr>
					<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
					<td class="${tdClass}">${item.id} </td>
					<td class="${tdClass}">${item.name} </td>
					<td class="${tdClass}">
						<a href="${base}/admin/spider/rule/edit?id=${item.id}">修改</a> | 
						<a href="${base}/admin/spider/rule/run_view?id=${item.id}">运行</a>
					</td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<input type="button" class="btn" value=" 删除 " onclick="mlog.form.confirmSubmit('ruleForm', '${base}/admin/rule/delete', '确认要将删除选中广告吗?');" />
			</td>
			<td>
				<@mspring.pagingnavigator page=rulePage form_id="ruleForm" />
			</td>
		</tr>
	</table>
</form>
<#include "/WEB-INF/views/admin/inc/footer.ftl" />