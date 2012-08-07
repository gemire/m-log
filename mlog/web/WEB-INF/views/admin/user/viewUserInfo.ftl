<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<div class="message notice" style="margin:10px;">提示：不修改密码时，请留空密码框</div>
		<form id="userForm" name="userForm" action="${base}/admin/user/doEditUserInfo" method="POST">
			<@spring.bind "user" />
			<@mspring.show_errors />
			<@spring.formHiddenInput path="user.id" />
			<@spring.formHiddenInput path="user.name" />
			<@spring.formHiddenInput path="user.createTime" />
			<table class="formtable" style="width:100%;">
				<tr>
					<td class="fieldlabel" style="width:60px;">用户名</td>
					<td>
						<@spring.formInput path="user.name" attributes='class="textinput" style="width:98%;" disabled="disabled"' />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel" style="width:60px;">昵称</td>
					<td>
						<@spring.formInput path="user.alias" attributes='class="textinput" style="width:98%;"' />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel">E-mail</td>
					<td>
						<@spring.formInput path="user.email" attributes='class="textinput" style="width:98%;"' />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel">密码</td>
					<td>
						<@spring.formPasswordInput path="user.password" attributes='class="textinput" style="width:98%;"' />
					</td>
				</tr>
				<#--
				<tr>
					<td class="fieldlabel">确认密码</td>
					<td>
						<@spring.formPasswordInput path="user.password" attributes='class="textinput" style="width:98%;"' />
					</td>
				</tr>
				-->
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
	});
</script>
<script type="text/javascript">
$(document).ready(function(){
	$('body').layout({
		north__closable:false,
		north__size:62,
		north__resizable:false,
		south__closable:false,
		south__size:50,
		south__resizable:false,
		togglerTip_open : "关闭",
		togglerTip_closed : "打开",
		resizerTip:"调整宽度",
		//west__spacing_closed:10,
		west__onresize: function (pane, $Pane) {  
            
        }
	});
});
</script>
<#include "../inc/footer.ftl" />