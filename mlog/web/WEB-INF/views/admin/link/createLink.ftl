<#import "/META-INF/spring.ftl" as spring />
<div class="tab" style="height:20px;font-weight:bold;">新增链接</div>
<div id="add-view">
	<@spring.bind "link" />
	<form class="form" name="linkForm" action="${base}/admin/link/doCreate" method="POST">
		<table class="formtable">
			<tr>
				<td class="fieldlabel">编号</td>
				<td>
					<@spring.formInput path="link.id" attributes='class="textinput" disabled="disabled"' defaultValue="自定生成"  />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel">名称</td>
				<td>
					<@spring.formInput path="link.name" attributes='class="textinput"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel">URL地址</td>
				<td>
					<@spring.formInput path="link.url" attributes='class="textinput"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel">Target</td>
				<td style="font-size:12px;">
					<@spring.formRadioButtons path="link.target" options=target defaultValue="_blank" separator="<br />" />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel">是否可见</td>
				<td style="font-size:12px;">
					<@spring.formRadioButtons path="link.visable" options=visable defaultValue="1" separator="&nbsp;" />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel">描述</td>
				<td>
					<@spring.formInput path="link.description" attributes='class="textinput"' />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" class="btn" value=" 提交 " />
				</td>
			</tr>
		</table>
	</form>
</div>