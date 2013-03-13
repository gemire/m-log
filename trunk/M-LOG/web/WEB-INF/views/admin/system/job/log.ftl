<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../../inc/header.ftl" />
	<form id="jobLogForm" name="jobLogForm" action="${base}/admin/system/job/log" method="POST">
		<@spring.bind "jobLogPage" />
		<!-- pagination parameter -->
		<@spring.formHiddenInput path="jobLogPage.pageNo" />
		<@spring.formHiddenInput path="jobLogPage.totalPages" />
		<@spring.formHiddenInput path="jobLogPage.totalCount" />
		<!-- sorter parameter -->
		<@spring.formHiddenInput path="jobLogPage.sortEnable" />
		<@spring.formHiddenInput path="jobLogPage.sort.field" />
		<@spring.formHiddenInput path="jobLogPage.sort.order" />
		<table class="dtable" cellspacing="0" cellpadding="0">
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>执行时间</th>
				<th>耗时(毫秒)</th>
				<th>是否成功</th>
			</tr>
			<#if jobLogPage??>
				<#list jobLogPage.result as item>
					<#assign tdClass = "odd">
					<#if item_index%2 == 0>
						<#assign tdClass = "even">
					</#if>
					<tr>
						<td class="${tdClass}">${item.id}</td>
						<td class="${tdClass}">${item.name}</td>
						<td class="${tdClass}">${item.time}</td>
						<td class="${tdClass}">${item.useTime}</td>
						<td class="${tdClass}"><#if item.success>是<#else><font color="red">否</font></#if></td>
					</tr>
				</#list>
			</#if>
		</table>
		<table style="width:100%;">
			<tr>
				<td>
					<@mspring.pagingnavigator page=jobLogPage form_id="jobLogForm" />
				</td>
			</tr>
		</table>
	</form>
	<form action="${base}/admin/system/job/log/clear" method="POST">
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
<#include "../../inc/footer.ftl" />