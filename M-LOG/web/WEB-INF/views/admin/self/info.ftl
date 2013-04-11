<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div class="message notice" style="margin:10px;">提示：不修改密码时，请留空密码框</div>
<form id="userForm" name="userForm" action="${base}/admin/self/info/save" method="POST">
	<@spring.bind "user" />
	<@mspring.show_errors />
	<@spring.formHiddenInput path="user.id" />
	<@spring.formHiddenInput path="user.createTime" />
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">用户信息修改</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">用户名</td>
			<td>
				<@spring.formInput path="user.name" attributes='readonly="readonly" class="textinput" style="width:98%;"'/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：不能修改</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">昵称</td>
			<td>
				<@spring.formInput path="user.alias" attributes='class="textinput" style="width:98%;" validate=\'{required:true,maxlength:20,messages:{required:"请输入昵称", maxlength:"昵称最大长度不能超过{0}"}}\'' />
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度20</td>
		</tr>
		<tr>
			<td class="fieldlabel">E-mail</td>
			<td>
				<@spring.formInput path="user.email" attributes='class="textinput" style="width:98%;" validate=\'{required:true,maxlength:50,email:true, messages:{required:"请输入E-mail", maxlength:"E-mail最大长度不能超过{0}"}}\'' />
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度50、email格式</td>
		</tr>
		<tr>
			<td class="fieldlabel">密码</td>
			<td>
				<@spring.formPasswordInput path="user.password" attributes='class="textinput" style="width:98%;" validate=\'{rangelength:[6, 18], messages:{rangelength:"密码的长度必须在{0}到{1}之间"}}\'' />
			</td>
			<td class="fieldnotice" style="width:300px;">规则：长度介于6到18之间</td>
		</tr>
		<tr>
			<td class="fieldlabel">确认密码</td>
			<td>
				<input type="password" id="repassword" name="repassword" value="" class="textinput" style="width:98%;" validate="{equalTo:'#password', messages:{equalTo:'两次输入的密码不匹配'}}">
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必须和密码相同</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:center;">
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
</script>
<#include "../inc/footer.ftl" />