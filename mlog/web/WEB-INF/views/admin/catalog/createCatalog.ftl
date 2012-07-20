<#import "/META-INF/spring.ftl" as spring />
<form class="form" action="${base}/admin/catalog/doCreate">
	<@spring.bind "catalog"/>
	<table class="formtable" action="${base}/admin/catalog/doCreate">
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
			<td class="fieldlabel">修改时间</td>
			<td>
				<@spring.formInput path="catalog.modifyTime" attributes='class="textinput"' />
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
				<input type="button" class="btn" value=" 提交 " />
			</td>
		</tr>
	</table>
</form>