<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div id="error" class="message error" style="display:none;"></div>
<form class="form" id="catalogForm" action="${base}/admin/catalog/create/save" method="POST">
	<@spring.bind "catalog" />
	<table class="formtable">
		<tr>
			<td class="fieldlabel" style="width:60px;">编号</td>
			<td>
				<@spring.formInput path="catalog.id" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="自动生成"  />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">名称</td>
			<td>
				<@spring.formInput path="catalog.name" attributes='class="textinput" style="width:98%;" validate=\'{required: true, catalogNameExists:true, messages:{required:"请输入分类名称", catalogNameExists:"分类名字已经存在"}}\'' />
			</td>
		</tr>
		<#--
		<tr>
			<td class="fieldlabel" style="width:60px;">编码</td>
			<td>
				<@spring.formInput path="catalog.code" attributes='class="textinput" style="width:98%;"' />
			</td>
		</tr>
		-->
		<tr>
			<td class="fieldlabel" style="width:60px;">创建时间</td>
			<td>
				<@spring.formInput path="catalog.createTime" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="当前时间" />
			</td>
		</tr>
		
		<tr>
			<td class="fieldlabel" style="width:60px;">排序</td>
			<td>
				<@spring.formInput path="catalog.order" attributes='class="textinput" style="width:98%;" validate=\'{required: false, digits: true, messages:{digits:"排序号必须为正整数"}}\'' />
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
		
		mlog.form.validate({
			selector : "#catalogForm",
			errorLabelContainer : "#error",
			wrapper: 'li',
			onfocusout : false,
			onkeyup : false,
			onclick : false,
			success : function(){
				mlog.utils.scrollTop();
			}
		});
		
	});
</script>
<#include "../inc/footer.ftl" />