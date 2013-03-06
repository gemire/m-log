<#include "../../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<div class="message notice" style="margin:10px;">提示：SMTP相关信息请咨询邮件服务提供商</div>
<form id="mailForm" name="mailForm" method="post" action="${base}/admin/system/mail/saveSetting">
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">邮件设置</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">启用SSL:</td>
			<td>
				<input type="checkbox" <#if smtp_ssl_enable?exists && smtp_ssl_enable == "true">checked="checked"</#if> onclick='$("#smtp_ssl_enable").val(this.checked);' />
				<input type="hidden" id="smtp_ssl_enable" name="smtp_ssl_enable" value="${smtp_ssl_enable!"false"}" />
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度100</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">SMTP服务器:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="smtp_host" value="${smtp_host!""}" validate="{maxlength:100, messages:{maxlength:'SMTP服务器最大长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度100</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">SMTP端口:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="smtp_port" value="${smtp_port!""}" validate="{digits: true, range:[1,10000], messages:{digits:'SMTP端口必须为正整数', range:'SMTP端口值范围必须在{0}到{1}之间'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、1 <= 值 <= 10000，提示：一般为25</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">发信人邮件地址:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="mail_from" value="${mail_from!""}" validate="{maxlength:100, messages:{maxlength:'发信人地址最大长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度100</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">SMTP身份验证用户名:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="smtp_username" value="${smtp_username!""}" validate="{maxlength:100, messages:{maxlength:'SMTP身份验证用户名最大长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度100</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">SMTP身份验证密码:</td>
			<td>
				<input type="password" class="textinput" style="width:95%;" name="smtp_password" value="${smtp_password!""}" validate="{maxlength:100, messages:{maxlength:'SMTP身份验证密码最大长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度100</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
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
			selector : "#mailForm",
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
<#include "../../inc/footer.ftl" />