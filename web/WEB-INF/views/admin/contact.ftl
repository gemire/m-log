<#include "inc/header.ftl" />
<style type="text/css">
#members img {
	width: 52px;
	height: 52px;
	padding: 1px;
	margin-bottom: 3px;
	max-width: 100%;
	vertical-align: middle;
	border: 1px solid #e8e8e8;
}
</style>
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
	<table class="infotable" id="members">
		<tr>
			<td colspan="2" class="partition">团队成员</td>
		</tr>
		<tr>
			<td>加入我们:</td>
			<td style="color:red;">
				只要有兴趣，有奉献精神，就可加入为M-LOG的开发工作，为M-LOG源码做贡献！
			</td>
		</tr>
		<tr>
			<td style="width:120px;">M-LOG交流群:</td>
			<td>100231009</td>
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
	
	$(document).ready(function(){
		$.getJSON('${base}/common/teamtoy/team_members', function(JSON){
			for(var i = 0; i < JSON.data.length; i++){
				var cls = i % 2 === 0 ? 'color2' : 'color3';
				var img = JSON.data[i].avatar_small;
				if(!img){
					img = 'http://team.mspring.org/static/image/user.avatar.png';
				}
				var email = JSON.data[i].email;
				var tr = '<tr><td colspan="2" class="' + cls + '"><img src="' + img + '"/>' + JSON.data[i].name + ' &nbsp;&nbsp;E-Mail:<a href="mailto:' + email + '">' + email + '</a></td></tr>';
				addRow('members', tr);
			}
		});
		
		function addRow(table_id, tr){
			var rows=$("#"+table_id);
			var vNum=$("#"+table_id+" tr").size();
			var tr_Num = vNum - 1;
			$(tr).insertAfter($("#"+table_id+" tr:eq("+tr_Num+")"));  
		}
	});
</script>
<#include "inc/footer.ftl" />