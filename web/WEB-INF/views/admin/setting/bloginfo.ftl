<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form method="post" action="${base}/admin/setting/saveBloginfo">
	<table class="formtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='2'>
		<tr>
			<td class="fieldlabel" style='width: 20%'>博客标题:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="blogname" value="${blogname!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>博客子标题:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="blogsubname" value="${blogsubname!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>博客地址:</td>
			<td style='width: 80%'>
				<input type="input" class="textinput" style="width:95%;" name="blogurl" value="${blogurl!""}"/>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>公告:<br />(支持HTML代码)</td>
			<td style='width: 80%'>
				<textarea class="textinput" style="width:95%;height:50px;" name="notice">${notice!""}</textarea>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style='width: 20%'>版权信息:<br /></td>
			<td style='width: 80%'>
				<textarea class="textinput" style="width:95%;" name="copyright">${copyright!""}</textarea>
			</td>
		</tr>
		
		<tr>
			<td class="fieldlabel" style='width: 20%'>是否开启评论审核:</td>
			<td style='width: 80%'>
				<input type="checkbox" <#if comment_audit?exists && comment_audit == "true">checked="checked"</#if> onclick='$("#comment_audit").val(this.checked);' />
				<input type="hidden" id="comment_audit" name="comment_audit" value="${comment_audit!"false"}" />
			</td>
		</tr>
		
		<tr>
			<td class="fieldlabel" style='width: 20%'>菜单:</td>
			<td style='width: 80%'>
				<textarea class="textinput" style="width:95%;height:150px;" name="menu">${menu!""}</textarea><br />
				<span >
					每一行链接代表一个菜单项，示例：&lt;a href="/catalog/默认分类" target="_self"&gt;默认分类&lt;/a&gt;
					<br />全局变量:%base% - 程序当前路径
				</span>
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