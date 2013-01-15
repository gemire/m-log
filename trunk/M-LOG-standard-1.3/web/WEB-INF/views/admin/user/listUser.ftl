<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<form id="userForm" name="userForm" action="${base}/admin/user/list" method="POST">
	<@spring.bind "userRole" />
	<table class="formtable">
		<tr>
			<td class="fieldlabel" style="width:65px;">角色：</td>
			<td>
				<@spring.formSingleSelect path="userRole.PK.role.id" options=roles attributes='' has_default=true />
			</td>
			<td class="fieldlabel" style="width:65px;">用户名：</td>
			<td>
				<@spring.formInput path="userRole.PK.user.name" attributes='class="textinput"' />
			</td>
			
			<td class="fieldlabel" style="width:65px;">别名：</td>
			<td>
				<@spring.formInput path="userRole.PK.user.alias" attributes='class="textinput"' />
			</td>
			
			<td class="fieldlabel" style="width:65px;">邮箱：</td>
			<td>
				<@spring.formInput path="userRole.PK.user.email" attributes='class="textinput"' />
			</td>
			
			<td><input type="submit" class="btn" value=" 查 询 " /></td>
		</tr>
	</table>
	<@spring.bind "userPage" />
	<!-- pagination parameter -->
	<@spring.formHiddenInput path="userPage.pageNo" />
	<@spring.formHiddenInput path="userPage.totalPages" />
	<@spring.formHiddenInput path="userPage.totalCount" />
	<!-- sorter parameter -->
	<@spring.formHiddenInput path="userPage.sortEnable" />
	<@spring.formHiddenInput path="userPage.sort.field" />
	<@spring.formHiddenInput path="userPage.sort.order" />
	<table class="dtable" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				<input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" />
			</th>
			<th>编号</th>
			<th>名称</th>
			<th>别名</th>
			<th>邮箱</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<#if userPage??>
			<#list userPage.result as item>
				<#assign tdClass = "odd">
				<#if item_index%2 == 0>
					<#assign tdClass = "even">
				</#if>
				<tr>
					<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
					<td class="${tdClass}">${item.id} </td>
					<td class="${tdClass}">${item.name} </td>
					<td class="${tdClass}">${item.alias!""} </td>
					<td class="${tdClass}">${item.email!""} </td>
					<td class="${tdClass}">${item.createTime!""} </td>
					<td class="${tdClass}">
						<a href="${base}/admin/user/edit?id=${item.id}">修改</a>
					</td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<input type="button" class="btn" value=" 删除 " onclick="mlog.form.confirmSubmit('userForm', '${base}/admin/user/delete');" />
			</td>
			<td>
				<@mspring.pagingnavigator page=userPage form_id="userForm" />
			</td>
		</tr>
	</table>
</form>
<#include "../inc/footer.ftl" />