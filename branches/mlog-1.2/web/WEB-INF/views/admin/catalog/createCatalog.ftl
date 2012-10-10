<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div class="ui-layout-center">
	<div class="tab">
		<ul>
		    <li><a href="${base}/admin/catalog/list">列表</a></li>
		    <li><a href="javascript:void(0);" class="here">增加</a></li>
		    <li><a href="javascript:void(0);">修改</a></li>
		</ul>
	</div>
	<div id="error" class="message error" style="display:none;">提示：不修改密码时，请留空密码框</div>
	<form class="form" id="catalogForm" action="${base}/admin/catalog/doCreate" method="POST">
		<@spring.bind "catalog" />
		<@mspring.show_errors />
		<table class="formtable" style="width:100%">
			<tr>
				<td class="fieldlabel" style="width:60px;">编号</td>
				<td>
					<@spring.formInput path="catalog.id" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="自定生成"  />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width:60px;">名称</td>
				<td>
					<@spring.formInput path="catalog.name" attributes='class="textinput" style="width:98%;"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width:60px;">创建时间</td>
				<td>
					<@spring.formInput path="catalog.createTime" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="当前时间" />
				</td>
			</tr>
			
			<tr>
				<td class="fieldlabel" style="width:60px;">排序</td>
				<td>
					<@spring.formInput path="catalog.order" attributes='class="textinput" style="width:98%;"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width:60px;">描述</td>
				<td>
					<@spring.formTextarea path="catalog.description" attributes='class="textinput" style="width:98%;"' />
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center;">
					<input type="submit" class="btn" value=" 提交 " />
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		//斑马线
		var tables=document.getElementsByTagName("table");
		var b=false;
		for (var j = 0; j < tables.length; j++){
			var cells = tables[j].getElementsByTagName("tr");
			//cells[0].className="color3";
			b=false;
			for (var i = 0; i < cells.length; i++){
				if(b){
					cells[i].className="color2";
					b=false;
				}
				else{
					cells[i].className="color3";
					b=true;
				};
			};
		}
		
		//$.metadata.setType("attr", "validate");
		//$("#catalogForm").validate();
		mlog.form.validateForm("catalogForm");
	});
</script>
<#include "../inc/footer.ftl" />