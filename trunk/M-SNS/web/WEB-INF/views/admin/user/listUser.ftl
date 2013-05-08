<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<link rel="stylesheet" type="text/css" href="${base}/script/lhgcalendar/skins/lhgcalendar.css">
<script type="text/javascript" src="${base}/script/lhgcalendar/lhgcalendar.min.js"></script>
<form id="userForm" name="userForm" action="${base}/admin/user/list" method="POST">
	<@spring.bind "userRole" />
	<table class="formtable">
		<tr>
			<td>
				<span class="fieldlabel">角色：</span>
				<@spring.formSingleSelect path="userRole.PK.role.id" options=roles attributes='onchange="changeRole();"' has_default=true />
				
				<span class="fieldlabel">用户名：</span>
				<@spring.formInput path="userRole.PK.user.name" attributes='class="textinput"' />
				
				<span class="fieldlabel">别名：</span>
				<@spring.formInput path="userRole.PK.user.alias" attributes='class="textinput"' />
				
				<span class="fieldlabel">邮箱：</span>
				<@spring.formInput path="userRole.PK.user.email" attributes='class="textinput"' />
				
				<span class="fieldlabel">创建时间</span>
				<input type="text" class="textinput" name="createTimeBeg" id="createTimeBeg" value="${createTimeBeg!""}" style="width:85px;" /> - 
				<input type="text" class="textinput" name="createTimeEnd" id="createTimeEnd" value="${createTimeEnd!""}" style="width:85px;" />
				
				<input type="submit" class="btn" value=" 查 询 " />
			</td>
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
					<td class="${tdClass}">${item.id} </td>
					<td class="${tdClass}">${item.name} </td>
					<td class="${tdClass}">${item.alias!""} </td>
					<td class="${tdClass}">${item.email!""} </td>
					<td class="${tdClass}">${item.createTime!""} </td>
					<td class="${tdClass}">
						<a href="${base}/admin/user/edit?id=${item.id}">修改</a> |
						<a href="javascript:deleteUser(${item.id});" style="color:red;">删除</a> 
					</td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<@mspring.pagingnavigator page=userPage form_id="userForm" />
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#createTimeBeg").calendar({ maxDate:'#createTimeEnd', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
		$("#createTimeEnd").calendar({ minDate:'#createTimeBeg', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
	});
	function changeRole(){
		$('#userForm').submit();
	}
	function deleteUser(userId){
		if(confirm('注意：删除该用户会同时删除该用户对应的文章等信息，且删除后将无法恢复，是否继续？')){
			window.location = "${base}/admin/user/delete?id=" + userId;
		}
	}
</script>
<#include "../inc/footer.ftl" />