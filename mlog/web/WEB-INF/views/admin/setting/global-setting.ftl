<#import "/META-INF/mspring.ftl" as mspring />

<table class="formtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='2'>
	<tr>
		<td class="fieldlabel" style='width: 20%'>是否开启评论审核:</td>
		<td style='width: 80%'>
			<input type="checkbox" <#if comment_audit == "true">checked="checked"</#if> onclick='$("#comment_audit").val(this.checked);' />
			<input type="hidden" id="comment_audit" name="comment_audit" value="${comment_audit!"false"}" />
		</td>
	</tr>
</table>