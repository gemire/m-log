<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="${base}/admin/job/list" class="here">列表</a></li>
			    <li><a href="${base}/admin/job/log">执行日志</a></li>
			</ul>
		</div>
		<div class="tab-container">
			<form id="jobForm" name="jobForm" action="${base}/admin/job/list" method="POST">
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
						<th>操作</th>
					</tr>
					<#if jobPage??>
						<#list jobPage.result as item>
							<#assign tdClass = "odd">
							<#if item_index%2 == 0>
								<#assign tdClass = "even">
							</#if>
							<tr>
								<td class="${tdClass}">
									<input type="checkbox" name="enabledIds" value="${item.id}" />
									<input type="hidden" name="ids" value="${item.id}" />
								</td>
								<td class="${tdClass}">
									${item.id}
								</td>
								<td class="${tdClass}">
									${item.name!""}
								</td>
								<td class="${tdClass}">
									<input type="radio" name="execType" value="simple" id="simple"/> <label for="simple">simple</label>
									<input type="radio" name="execType" value="cron" id="cron"/> <label for="cron">cron</label>
								</td>
								<td class="${tdClass}">
									<input type="text" class="textinput" style="width:180px;" name="names" value="${item.expression}" />
								</td>
								<td class="${tdClass}">
									${item.jobClass!""}
								</td>
								<td class="${tdClass}">
									${item.lastExec!""}
								</td>
								<td class="${tdClass}">
									<a href="${base}/admin/job/edit?id=${item.id}">执行</a>
								</td>
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
		<div>
	</div>
	<script type="text/javascript">
		function doSubmit(){
			var flagOrders = validateOrders();
			if(!flagOrders){
				return;
			}
			var flagNames = validateNames();
			if(!flagNames){
				return;
			}
			mlog.form.submitForm('jobForm', '${base}/admin/job/ctrl');
		}
		
		function validateOrders(){
			var orders = document.getElementsByName('orders');
			for(var i = 0; i < orders.length; i++){
				var value = $(orders[i]).val();
				if($.trim(value).length > 0 && !(/^\d+$/.test(value))){
					mlog.dialog.tip('排序号必须为数字');
					$(orders[i]).focus();
					return false;
				}
				if($.trim(value).length > 0){
					if(value < 1 || value > 1000){
						mlog.dialog.tip('排序号的范围必须在1-1000之间');
						$(orders[i]).focus();
						return false;
					}
				}
			}
			return true;
		}
		
		function validateNames(){
			var names = document.getElementsByName('names');
			for(var i = 0; i < names.length; i++){
				var value = $(names[i]).val();
				if($.trim(value).length < 1){
					mlog.dialog.tip('请输入分类名称');
					$(names[i]).focus();
					return false;
				}
				if($.trim(value).length > 10){
					mlog.dialog.tip('分类名字长度不能超过10');
					$(names[i]).focus();
					return false;
				}
			}
			return true;
		}
	</script>
<#include "../inc/footer.ftl" />