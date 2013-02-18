<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div id="error" class="message error" style="display:none;"></div>
<form class="form" id="roleForm" action="${base}/admin/role/edit/save" method="POST">
	<@spring.bind "role" />
	<@spring.formHiddenInput path="role.id" />
	<table class="formtable" style="width:100%">
		<tr>
			<td class="fieldlabel" style="width:60px;">编号:</td>
			<td>
				<@spring.formInput path="role.id" attributes='class="textinput" style="width:98%;" disabled="disabled"' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">名称</td>
			<td>
				<@spring.formInput path="role.name" attributes='class="textinput" style="width:98%;" validate=\'{required: true, roleNameExists:{id:"${role.id}"}, messages:{required:"请输入角色名称", roleNameExists:"角色名称已经存在"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">是否可用</td>
			<td>
				<@spring.formCheckbox path="role.enabled" />
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
	turnHighLight(310015);
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
			selector : "#roleForm",
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