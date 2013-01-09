<#include "inc/header.ftl" />
<div>
	<table class="infotable">
		<tr>
			<td colspan="2" class="partition">关于M-LOG</td>
		</tr>
		<tr>
			<td style="width:120px;">应用名称:</td>
			<td>${app.productName}</td>
		</tr>
		<tr>
			<td>版本号:</td>
			<td>${app.version}</td>
		</tr>
		<tr>
			<td>LICENSE:</td>
			<td><a href="http://www.apache.org/licenses/LICENSE-2.0.html" target="_blank">${app.license}</a></td>
		</tr>
		<tr>
			<td>官网地址:</td>
			<td><a href="http://www.mspring.org" target="_blank">${app.homePage}</a></td>
		</tr>
		<tr>
			<td colspan="2"><a href="https://me.alipay.com/gaoyoubo" target="_blank"><img src="${base}/images/alipay-me.png"></a></td>
		</tr>
	</table>
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
<#include "inc/footer.ftl" />