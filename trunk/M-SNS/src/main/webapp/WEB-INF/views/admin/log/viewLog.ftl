<#include "../inc/header.ftl" />
<table class="infotable">
	<tr>
		<td colspan="2" class="partition">日志详情(编号：${log.id})</td>
	</tr>
	<tr>
		<td class="fieldlabel">时间：</td>
		<td>${log.actionTime}</td>
	</tr>
	<tr>
		<td class="fieldlabel">用户：</td>
		<td><#if log.user?exists>${log.user.name}</#if></td>
	</tr>
	<tr>
		<td class="fieldlabel">IP：</td>
		<td>${log.ip!""}</td>
	</tr>
	<tr>
		<td class="fieldlabel">Agent：</td>
		<td>${log.agent!""}</td>
	</tr>
	<tr>
		<td class="fieldlabel">Action：</td>
		<td>${log.action!""}</td>
	</tr>
	<tr>
		<td class="fieldlabel">描述：</td>
		<td>${log.description!""}</td>
	</tr>
	<tr>
		<td class="fieldlabel">Class：</td>
		<td>${log.className!""}</td>
	</tr>
	<tr>
		<td class="fieldlabel">Method：</td>
		<td>${log.methodName!""}</td>
	</tr>
	<tr>
		<td class="fieldlabel">Arguments：</td>
		<td>${log.arguments!""}</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align:center;"><input type="button" class="btn" value=" 返回 " onclick="history.back(1);" /></td>
	</tr>
</table>
<script type="text/javascript">
	turnHighLight(495010);
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