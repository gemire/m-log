<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
	<link rel="stylesheet" type="text/css" href="${base}/script/lhgcalendar/skins/lhgcalendar.css">
	<script type="text/javascript" src="${base}/script/lhgcalendar/lhgcalendar.min.js"></script>
	<form id="logForm" name="logForm" action="${base}/admin/log/list" method="POST">
		<@spring.bind "log" />
		<table>
			<tr>
				<td class="fieldlabel" style="width:50px;">Class</td>
				<td>
					<@spring.formInput path="log.className" attributes='class="textinput"' />
				</td>
				
				<td class="fieldlabel" style="width:40px;">Method</td>
				<td>
					<@spring.formInput path="log.methodName" attributes='class="textinput"' />
				</td>
				<td class="fieldlabel" style="width:40px;">操作人</td>
				<td>
					<@spring.formInput path="log.user.name" attributes='class="textinput"' />
				</td>
				<td>时间</td>
				<td>
					<input type="text" class="textinput" name="actionTimeBeg" id="actionTimeBeg" readonly="readonly" value="${actionTimeBeg!""}" style="width:85px;" /> - 
					<input type="text" class="textinput" name="actionTimeEnd" id="actionTimeEnd" readonly="readonly" value="${actionTimeEnd!""}" style="width:85px;" />
				</td>
				
				<td><input type="submit" class="btn" value=" 查 询 " /></td>
			</tr>
		</table>
		
		<@spring.bind "logPage" />
		<!-- pagination parameter -->
		<@spring.formHiddenInput path="logPage.pageNo" />
		<@spring.formHiddenInput path="logPage.totalPages" />
		<@spring.formHiddenInput path="logPage.totalCount" />
		<!-- sorter parameter -->
		<@spring.formHiddenInput path="logPage.sortEnable" />
		<@spring.formHiddenInput path="logPage.sort.field" />
		<@spring.formHiddenInput path="logPage.sort.order" />
		<table class="dtable" cellspacing="0" cellpadding="0">
			<tr>
				<th>编号</th>
				<th>时间</th>
				<th>用户</th>
				<th>IP</th>
				<th>Action</th>
				<th>操作</th>
			</tr>
			<#if logPage??>
				<#list logPage.result as item>
					<#assign tdClass = "odd">
					<#if item_index%2 == 0>
						<#assign tdClass = "even">
					</#if>
					<tr>
						<td class="${tdClass}">${item.id}</td>
						<td class="${tdClass}">${item.actionTime}</td>
						<td class="${tdClass}"><#if item.user?exists>${item.user.name}</#if></td>
						<td class="${tdClass}">${item.ip!""}</td>
						<td class="${tdClass}">${item.action!""}</td>
						<td class="${tdClass}"><a href="${base}/admin/log/view?id=${item.id}">明细</a></td>
					</tr>
				</#list>
			</#if>
		</table>
		<table style="width:100%;">
			<tr>
				<td>
					<@mspring.pagingnavigator page=logPage form_id="logForm" />
				</td>
			</tr>
		</table>
	</form>
	<form action="${base}/admin/log/clear" method="POST">
		<table style="width:100%;">
			<tr>
				<td class="fieldlabel" >请填写清理多少天以前的日志(0为情况所有)：</td>
				<td>
					<input type="text" class="textinput" name="days" value="30" />
					<input type="submit" class="btn" value=" 清理日志 " />
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		turnHighLight(495005);
		$(document).ready(function(){
			$("#actionTimeBeg").calendar({ maxDate:'#actionTimeEnd', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
			$("#actionTimeEnd").calendar({ minDate:'#actionTimeBeg', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
		});
	</script>
<#include "../inc/footer.ftl" />