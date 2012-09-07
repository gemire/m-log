<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

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
</table>