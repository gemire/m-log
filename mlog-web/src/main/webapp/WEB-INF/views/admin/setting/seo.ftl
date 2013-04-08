<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div class="message notice" style="margin:10px;">提示：合理的SEO信息设置有助于百度、谷歌等搜索引擎的收录</div>
<div id="error" class="message error" style="display:none;"></div>
<form id="seoForm" name="seoForm" method="post" action="${base}/admin/setting/saveSeo">
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">SEO设置</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">关键字:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="keyword" value="${keyword!""}" validate="{maxlength:1000, messages:{maxlength:'关键字最大长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度1000，默认：M-LOG</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">描述:</td>
			<td>
				<textarea class="textinput" style="width:95%;height:50px;" name="description" validate="{maxlength:2000, messages:{maxlength:'描述最大长度不能超过{0}'}}">${description!""}</textarea>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度2000，默认：M-LOG简洁不简单</td>
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
			selector : "#seoForm",
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