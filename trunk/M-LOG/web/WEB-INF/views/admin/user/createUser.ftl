<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<#if message?has_content>
<div id="error" class="message error">${message}</div>
<#else>
<div id="error" class="message error" style="display:none;"></div>
</#if>
<form class="form" id="userForm" action="${base}/admin/user/create/save" method="POST">
	<@spring.bind "user" />
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">用户基本信息</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">编号：</td>
			<td>
				<@spring.formInput path="user.id" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="自动生成" />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">名称：</td>
			<td>
				<@spring.formInput path="user.name" attributes='class="textinput" style="width:98%;" validate=\'{required: true, userNameExists:true, messages:{required:"请输入用户名称", userNameExists:"用户名称已经存在"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">别名：</td>
			<td>
				<@spring.formInput path="user.alias" attributes='class="textinput" style="width:98%;" validate=\'{required: true, userAliasExists:true, messages:{required:"请输入用户昵称", userAliasExists:"用户昵称已经存在"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">邮箱：</td>
			<td>
				<@spring.formInput path="user.email" attributes='class="textinput" style="width:98%;" validate=\'{required: true, email:true, userEmailExists:true, messages:{required:"请输入邮箱", email:"邮箱格式不正确", userEmailExists:"邮箱已经存在"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">用户组：</td>
			<td>
				<select id="roles_select" multiple="multiple" style="width:90.5%;">
					<#list roles as role>
						<#if role.selected?exists && role.selected>
							<option value="${role.id}" selected="selected">${role.name}</option>
						<#else>
							<option value="${role.id}" >${role.name}</option>
						</#if>
					</#list>
				</select>
				<input type="hidden" id="selectRoles" name="selectRoles" validate='{required:true, messages:{required:"请选择用户角色"}}' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">新密码：</td>
			<td>
				<@spring.formPasswordInput path="user.password" attributes='class="textinput" style="width:98%;" validate=\'{required: true, minlength:6, messages:{required:"请输入密码", minlength:"密码长度必须大于{0}个字符"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">确认密码：</td>
			<td>
				<input type="password" class="textinput" style="width:98%;" name="rePassword" id="rePassword" validate='{equalTo:"#password", messages:{equalTo:"两次输入的密码不相同"}}' />
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
	turnHighLight(305010);
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
		
		$("#roles_select").multiselect({
			header:true,
			selectedList: 4,
			click: function(event, ui){
				var values = $("#selectRoles").val();
				if(ui.checked){
					values += ui.value + ',';
				}
				else{
					values = values.replace(ui.value + ',', '');
				}
				$("#selectRoles").val(values);
			}
		});
		
		
		
		mlog.form.validate({
			selector : "#userForm",
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
	
	//设置选中
	function setSelected(value){
		$("#roles_select option").each(function(){
			if($(this).attr("value") == value){
				$(this).attr("selected", "selected");
				return;
			}
		});
	}
</script>
<#include "../inc/footer.ftl" />