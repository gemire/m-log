<#include "../../inc/header.ftl" />
<div>
	<form id="cacheForm" name="cacheForm" method="post" action="${base}/admin/system/permalink/config/save">
		<div id="error" class="message error" style="display:none;"></div>
		<table class="infotable">
			<tr>
				<td colspan="2" class="partition">固定链接设置</td>
			</tr>
			<#list rules?keys as ruleKey>
				<tr>
					<td colspan="2">
						<input name="permalink" type="radio" id="${ruleKey}" value="${ruleKey}" <#if permalink?has_content && permalink == ruleKey>checked="checked"</#if> />
						<label for="${ruleKey}">${rules[ruleKey]}</label>
					</td>
				</tr>
			</#list>
			<tr>
				<td colspan="2" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	$("#btnClear").click(function(){
		$.get("${base}/admin/system/cache/clear", function(response){
			alert('清理完成');
			document.location.reload();
		});
	});
	
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
			selector : "#cacheForm",
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