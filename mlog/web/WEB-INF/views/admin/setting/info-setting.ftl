<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<table class="formtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
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
		<td class="fieldlabel" style='width: 20%'>关键字:</td>
		<td style='width: 80%'>
			<input type="input" class="textinput" style="width:95%;" name="keyword" value="${keyword!""}"/>
		</td>
	</tr>
	<tr>
		<td class="fieldlabel" style='width: 20%'>描述:</td>
		<td style='width: 80%'>
			<input type="input" class="textinput" style="width:95%;" name="description" value="${description!""}"/>
		</td>
	</tr>
	<tr>
		<td class="fieldlabel" style='width: 20%'>公告:</td>
		<td style='width: 80%'>
			<input type="input" class="textinput" style="width:95%;" name="notice" value="${notice!""}"/>
		</td>
	</tr>
</table>