<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="${base}/admin/link/list">列表</a></li>
			    <li><a href="${base}/admin/link/create">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			    <li><a href="${base}/admin/linkType/list" class="here">链接分类</a></li>
			    <li><a href="${base}/admin/linkType/create">新增分类</a></li>
			    <li><a href="${base}/admin/linkType/edit">修改分类</a></li>
			</ul>
		</div>
		<div class="tab-container">
			<form id="linkTypeForm" name="linkTypeForm" action="${base}/admin/linkType/list" method="POST">
				<@spring.bind "linkTypePage" />
				<!-- pagination parameter -->
				<@spring.formHiddenInput path="linkTypePage.pageNo" />
				<@spring.formHiddenInput path="linkTypePage.totalPages" />
				<@spring.formHiddenInput path="linkTypePage.totalCount" />
				<!-- sorter parameter -->
				<@spring.formHiddenInput path="linkTypePage.sortEnable" />
				<@spring.formHiddenInput path="linkTypePage.sort.field" />
				<@spring.formHiddenInput path="linkTypePage.sort.order" />
				<table class="dtable" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							<input type="checkbox" onclick="mlog.form.checkAll(this, 'deleteIds');" />删除
						</th>
						<th>排序</th>
						<th>名称</th>
						<th>
							<input type="checkbox" onclick="mlog.form.checkAll(this, 'visableIds');" />是否可见
						</th>
						<th>操作</th>
					</tr>
					<#if linkTypePage??>
						<#list linkTypePage.result as item>
							<#assign tdClass = "odd">
							<#if item_index%2 == 0>
								<#assign tdClass = "even">
							</#if>
							<tr>
								<td class="${tdClass}">
									<input type="checkbox" name="deleteIds" value="${item.id}" />
									<input type="hidden" name="ids" value="${item.id}" />
								</td>
								<td class="${tdClass}">
									<input type="text" class="textinput" style="width:40px;" name="orders" value="${item.order!""}" />
								</td>
								<td class="${tdClass}">
									<input type="text" class="textinput" style="width:180px;" name="names" value="${item.name}" />
								</td>
								<td class="${tdClass}">
									<input type="checkbox" name="visableIds" <#if item.visable>checked="checked"</#if> value="${item.id}" />
								</td>
								<td class="${tdClass}">
									<a href="${base}/admin/linkType/edit?id=${item.id}">修改</a>
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
							<@mspring.pagingnavigator page=linkTypePage form_id="linkTypeForm" />
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
			mlog.form.submitForm('linkTypeForm', '${base}/admin/linkType/ctrl');
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