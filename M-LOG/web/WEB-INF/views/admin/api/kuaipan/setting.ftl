<#include "../../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form id="kuaipanForm" name="kuaipanForm" method="post" action="${base}/admin/api/kuaipan/saveSetting">
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">金山快盘设置</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:150px;">状态:</td>
			<td>
				<#if api_kuaipan_on?exists && api_kuaipan_on == "true">
				已开通
				&nbsp;&nbsp;&nbsp;<a href="${base}/admin/api/kuaipan/close" style="color:blue;text-decoration: underline;">点击这里关闭</a>
				<#else>
				<font style="color:red;">已关闭</font>
				&nbsp;&nbsp;&nbsp;<a href="javascript:requestToken();" style="color:blue;text-decoration: underline;">点击这里开通</a>
				</#if>
			</td>
			<td class="fieldnotice" style="width:300px;"></td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:200px;">消费方密钥(Consumer Key):</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="api_kuaipan_key" value="${api_kuaipan_key!""}" disabled="disabled"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：不可修改</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:200px;">消费方请求令牌(Consumer Secret):</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="api_kuaipan_secret" value="${api_kuaipan_secret!""}" disabled="disabled"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：不可修改</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	function requestToken(){
		window.open ('${base}/admin/api/kuaipan/requestToken','newwindow','height=450,width=750,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 
	}
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
			selector : "#kuaipanForm",
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