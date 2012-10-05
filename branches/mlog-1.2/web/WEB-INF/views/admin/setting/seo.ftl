<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div class="message notice" style="margin:10px;">提示：合理的SEO信息设置有助于百度、谷歌等搜索引擎的收录</div>
<form method="post" action="${base}/admin/setting/saveSeo">
	<table class="formtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='2'>
		<tr>
			<td class="fieldlabel" style='width: 20%'>关键字:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="keyword" value="${keyword!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>描述:</td>
			<td style='width: 80%'>
				<textarea class="textinput" style="width:95%;height:50px;" name="description">${description!""}</textarea>
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