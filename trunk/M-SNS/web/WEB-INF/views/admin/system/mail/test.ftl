<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../../inc/header.ftl" />
<form id="mailForm" name="mailForm" method="post" action="${base}/admin/system/mail/sendTestMail">
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="2" class="partition">发送测试邮件</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">收件人:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="mail_to" validate="{required:true, email:true, messages:{required:'请输入收件人'}}" />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">邮件内容:</td>
			<td>
				<textarea class="textinput" style="width:95%;" name="mail_content" validate="{required:true, messages:{required:'请输入邮件内容'}}"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;"><input type="submit" class="btn" value=" 发送 " /></td>
		</tr>
	</table>
</form>
<#if success?exists && success == false>
<div class="message notice" style="margin:10px;">
	邮件发送失败，请检测邮件设置。错误消息如下：	
	<div class="message error">${message}</div>
</div>
<#elseif success?exists && success == true>
	<div class="message success">邮件发送成功</div>
</#if>
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