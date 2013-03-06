<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
	<form id="tagForm" name="tagForm" action="${base}/admin/tag/list" method="POST">
		<@spring.bind "tag" />
		<table>
			<tr>
				<td class="fieldlabel" style="width:70px;">tag名称</td>
				<td>
					<@spring.formInput path="tag.name" attributes='class="textinput"' />
				</td>
				<td><input type="submit" class="btn" value=" 查 询 " /></td>
			</tr>
		</table>
		
		<@spring.bind "tagPage" />
		<!-- pagination parameter -->
		<@spring.formHiddenInput path="tagPage.pageNo" />
		<@spring.formHiddenInput path="tagPage.totalPages" />
		<@spring.formHiddenInput path="tagPage.totalCount" />
		<!-- sorter parameter -->
		<@spring.formHiddenInput path="tagPage.sortEnable" />
		<@spring.formHiddenInput path="tagPage.sort.field" />
		<@spring.formHiddenInput path="tagPage.sort.order" />
		
		<table class="dtable" cellspacing="0" cellpadding="0">
			<tr>
				<th>
					<input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" />
				</th>
				<th>编号</th>
				<th>名称</th>
				<th>使用次数</th>
				<th>创建时间</th>
				<th>
					操作
				</th>
			</tr>
			<#if tagPage??>
				<#list tagPage.result as item>
					<#assign tdClass = "odd">
					<#if item_index%2 == 0>
						<#assign tdClass = "even">
					</#if>
					<tr>
						<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
						<td class="${tdClass}">${item.id}</td>
						<td class="${tdClass}">${item.name}</td>
						<td class="${tdClass}">${item.count!'0'}</td>
						<td class="${tdClass}">${item.createTime}</td>
						<td class="${tdClass}">
							<a href="${base}/admin/tag/edit?id=${item.id}">修改</a>
						</td>
					</tr>
				</#list>
			</#if>
		</table>
		<table style="width:100%;">
			<tr>
				<td>
					<input type="button" class="btn" value=" 删除tag" onclick="mlog.form.confirmSubmit('tagForm', '${base}/admin/tag/delete', '确认要将删除选中tag吗?');" />
				</td>
				<td>
					<@mspring.pagingnavigator page=tagPage form_id="tagForm" />
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	turnHighLight(125005);
	
	</script>
<#include "../inc/footer.ftl" />