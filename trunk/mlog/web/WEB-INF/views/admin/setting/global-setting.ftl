<#import "/META-INF/mspring.ftl" as mspring />

<table class="formtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='2'>
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
</table>