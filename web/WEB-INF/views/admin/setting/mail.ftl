<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div class="message notice" style="margin:10px;">提示：SMTP相关信息请咨询邮件服务提供商</div>
<form method="post" action="${base}/admin/setting/saveMail">
	<table class="formtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='2'>
		<tr>
			<td class="fieldlabel" style='width: 20%'>SMTP 服务器:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="smtp_host" value="${smtp_host!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>SMTP 端口:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="smtp_port" value="${smtp_port!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>发信人邮件地址:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="mail_from" value="${mail_from!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>SMTP 身份验证用户名:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="smtp_username" value="${smtp_username!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>SMTP 身份验证密码:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="smtp_password" value="${smtp_password!""}"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
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
	});
</script>
<#include "../inc/footer.ftl" />