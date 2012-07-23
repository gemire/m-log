<#import "/META-INF/spring.ftl" as spring />
<form class="form" action="${base}/admin/catalog/doCreate" method="POST">
	
	<table class="formtable">
		<tr>
			<td class="fieldlabel">编号</td>
			<td>
				<@spring.formInput path="catalog.id" attributes='class="textinput" disabled="disabled"' defaultValue="自定生成"  />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel">名称</td>
			<td>
				<@spring.formInput path="catalog.name" attributes='class="textinput"' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel">创建时间</td>
			<td>
				<@spring.formInput path="catalog.createTime" attributes='class="textinput" disabled="disabled"' defaultValue="当前时间" />
			</td>
		</tr>
		
		<tr>
			<td class="fieldlabel">排序</td>
			<td>
				<@spring.formInput path="catalog.order" attributes='class="textinput"' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel">描述</td>
			<td>
				<@spring.formTextarea path="catalog.description" attributes='class="textinput"' />
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