<#include "inc/header.ftl" />
<div>
	<table class="infotable">
		<tr>
			<td colspan="2" class="partition">联系方式</td>
		</tr>
		<tr>
			<td style="width:120px;">地址:</td>
			<td>北京市西城区西直门南小街玉廊西园9号楼</td>
		</tr>
		<tr>
			<td>电话:</td>
			<td>18601067708</td>
		</tr>
		<tr>
			<td>QQ:</td>
			<td>330721072</td>
		</tr>
		<tr>
			<td>邮箱:</td>
			<td><a href="mailto:gaoyoubo@mspring.org">gaoyoubo@mspring.org</a></td>
		</tr>
		<tr>
			<td>网址:</td>
			<td><a href="http://www.mspring.org" target="_blank">http://www.mspring.org</a></td>
		</tr>
		<tr>
			<td colspan="2"><a href="https://me.alipay.com/gaoyoubo" target="_blank"><img src="${base}/images/alipay-me.png"></a></td>
		</tr>
	</table>
	<table class="infotable">
		<tr>
			<td colspan="2" class="partition">团队成员</td>
		</tr>
		<tr>
			<td style="width:120px;">M-LOG交流群:</td>
			<td>100231009</td>
		</tr>
		<tr>
			<td>高尤波:</td>
			<td>E-Mail: <a href="mailto:gaoyoubo@mspring.com">gaoyoubo@mspring.org</a> | QQ: 330721072</td>
		</tr>
		<tr>
			<td>胡洪瑜:</td>
			<td>E-Mail: <a href="mailto:huhongyu@mspring.org">huhongyu@mspring.org</a> | QQ：429527044</td>
		</tr>
		<tr>
			<td>加入我们:</td>
			<td style="color:red;">
				只要有兴趣，就可加入为M-LOG源码做贡献！只要为源码做过贡献的同学，名字就可以出现在这里。 O(∩_∩)O
			</td>
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