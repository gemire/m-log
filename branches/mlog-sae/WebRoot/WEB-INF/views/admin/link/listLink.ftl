<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="javascript:void(0);" class="here">列表</a></li>
			    <li><a href="${base}/admin/link/create">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			    <li><a href="${base}/admin/linkType/list">链接分类</a></li>
			    <li><a href="${base}/admin/linkType/create">新增分类</a></li>
			    <li><a href="${base}/admin/linkType/edit">修改分类</a></li>
			</ul>
		</div>
		<div class="tab-container">
			<form id="linkForm" name="linkForm" action="${base}/admin/link/list" method="POST">
				<@spring.bind "link" />
				<table class="formtable">
					<tr>
						<td class="fieldlabel" style="width:65px;">链接分类：</td>
						<td>
							<@spring.formSingleSelect path="link.type.id" options=types attributes='style="width:98%"' has_default=true />
						</td>
						<td class="fieldlabel" style="width:65px;">是否显示：</td>
						<td>
							<@spring.formSingleSelect path="link.visable" options=visable attributes='style="width:98%"' has_default=true />
						</td>
						<td class="fieldlabel" style="width:65px;">名称：</td>
						<td>
							<@spring.formInput path="link.name" attributes='class="textinput" style="width:98%"' />
						</td>
						
						<td class="fieldlabel" style="width:65px;">地址：</td>
						<td>
							<@spring.formInput path="link.url" attributes='class="textinput" style="width:98%"' />
						</td>
						
						<td><input type="submit" class="btn" value=" 查 询 " /></td>
					</tr>
				</table>
				
				<@spring.bind "linkPage" />
				<!-- pagination parameter -->
				<@spring.formHiddenInput path="linkPage.pageNo" />
				<@spring.formHiddenInput path="linkPage.totalPages" />
				<@spring.formHiddenInput path="linkPage.totalCount" />
				<!-- sorter parameter -->
				<@spring.formHiddenInput path="linkPage.sortEnable" />
				<@spring.formHiddenInput path="linkPage.sort.field" />
				<@spring.formHiddenInput path="linkPage.sort.order" />
				<table class="dtable" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							<input type="checkbox" onclick="mlog.form.checkAll(this, 'deleteIds');" />删除
						</th>
						<th>排序</th>
						<th>名称</th>
						<th>地址</th>
						<th>TARGET</th>
						<th>
							<input type="checkbox" onclick="mlog.form.checkAll(this, 'visableIds');" />是否可见
						</th>
						<th>
							操作
						</th>
					</tr>
					<#if linkPage??>
						<#list linkPage.result as item>
							<#assign tdClass = "odd">
							<#if item_index%2 == 0>
								<#assign tdClass = "even">
							</#if>
							<tr>
								<td class="${tdClass}"><input type="checkbox" name="deleteIds" value="${item.id}" /></td>
								<td class="${tdClass}">
									<input type="text" class="textinput" style="width:40px;" name="orders" value="${item.order!""}" />
								</td>
								<td class="${tdClass}">${item.name}</td>
								<td class="${tdClass}">${item.url}</td>
								<td class="${tdClass}">${item.target}</td>
								<td class="${tdClass}">
									<input type="checkbox" name="visableIds" <#if item.visable>checked="checked"</#if> value="${item.id}" />
								</td>
								<td class="${tdClass}">
									<input type="hidden" name="ids" value="${item.id}" />
									<a href="${base}/admin/link/edit?id=${item.id}">修改</a>
								</td>
							</tr>
						</#list>
					</#if>
				</table>
				<table style="width:100%;">
					<tr>
						<td>
							<input type="button" class="btn" value=" 提交 " onclick="doSubmit();"/>
						</td>
						<td>
							<@mspring.pagingnavigator page=linkPage form_id="linkForm" />
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
			mlog.form.submitForm('linkForm', '${base}/admin/link/ctrl');
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
	</script>
<#include "../inc/footer.ftl" />