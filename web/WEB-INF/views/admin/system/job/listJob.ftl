<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../../inc/header.ftl" />
	<form id="jobForm" name="jobForm" action="${base}/admin/system/job/list" method="POST">
		<@spring.bind "jobPage" />
		<!-- pagination parameter -->
		<@spring.formHiddenInput path="jobPage.pageNo" />
		<@spring.formHiddenInput path="jobPage.totalPages" />
		<@spring.formHiddenInput path="jobPage.totalCount" />
		<!-- sorter parameter -->
		<@spring.formHiddenInput path="jobPage.sortEnable" />
		<@spring.formHiddenInput path="jobPage.sort.field" />
		<@spring.formHiddenInput path="jobPage.sort.order" />
		<table class="dtable" cellspacing="0" cellpadding="0">
			<tr>
				<th>
					<input type="checkbox" onclick="mlog.form.checkAll(this, 'enabledIds');" />是否启用
				</th>
				<th>编号</th>
				<th>名称</th>
				<th>执行类型</th>
				<th>执行规则</th>
				<th>执行类</th>
				<th>最后执行时间</th>
				<#-- <th>操作</th> -->
			</tr>
			<#if jobPage??>
				<#list jobPage.result as item>
					<#assign tdClass = "odd">
					<#if item_index%2 == 0>
						<#assign tdClass = "even">
					</#if>
					<tr>
						<td class="${tdClass}">
							<input type="checkbox" name="enabledIds" value="${item.id}" <#if item.enabled?has_content && item.enabled>checked="checked"</#if> />
							<input type="hidden" name="ids" value="${item.id}" />
						</td>
						<td class="${tdClass}">
							${item.id}
						</td>
						<td class="${tdClass}">
							${item.name!""}
						</td>
						<td class="${tdClass}">
							<select name="execTypes">
								<#list execType?keys as key>
									<option value="${key}" <#if item.execType == key>selected="selected"</#if> >${execType[key]}</option>
								</#list>
							</select>
						</td>
						<td class="${tdClass}">
							<input type="text" class="textinput" style="width:180px;" name="expressions" value="${item.expression}" />
						</td>
						<td class="${tdClass}">
							${item.jobClass!""}
						</td>
						<td class="${tdClass}">
							${item.lastExec!""}
						</td>
						<#--
						<td class="${tdClass}">
							<a href="${base}/admin/system/job/edit?id=${item.id}">执行</a>
						</td>
						-->
					</tr>
				</#list>
			</#if>
		</table>
		<table style="width:100%;">
			<tr>
				<td>
					<input type="button" class="btn" value=" 提交 " onclick="doSubmit();" />
				</td>
				<td>
					<@mspring.pagingnavigator page=jobPage form_id="jobForm" />
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		function doSubmit(){
			mlog.form.submitForm('jobForm', '${base}/admin/system/job/ctrl');
		}
	</script>
<#include "../../inc/footer.ftl" />