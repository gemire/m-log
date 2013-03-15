<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<form id="catalogForm" name="catalogForm" action="${base}/admin/catalog/list" method="POST">
	<@spring.bind "catalog" />
	<table>
		<tr>
			<td class="fieldlabel" style="width:65px;">分类名称：</td>
			<td>
				<@spring.formInput path="catalog.name" attributes='class="textinput"' />
			</td>
			<td class="fieldlabel" style="width:65px;">父级：</td>
			<td>
				<select name="parent.id">
					<option value="">--请选择--</option>
					<option value="0">--无父级--</option>
					<#list catalogs as c>
					<#assign c_name = c.name>
					<#list 1..c.deep as i>
					<#assign c_name = "—" + c_name>
					</#list>
					<option value="${c.id}" <#if catalog.parent?has_content && catalog.parent.id?has_content && catalog.parent.id = c.id>selected="selected"</#if>>${c_name}</option>
					</#list>
				</select>
			</td>
			<td><input type="submit" class="btn" value=" 查 询 " /></td>
		</tr>
	</table>
		
	<@spring.bind "catalogPage" />
	<!-- pagination parameter -->
	<@spring.formHiddenInput path="catalogPage.pageNo" />
	<@spring.formHiddenInput path="catalogPage.totalPages" />
	<@spring.formHiddenInput path="catalogPage.totalCount" />
	<!-- sorter parameter -->
	<@spring.formHiddenInput path="catalogPage.sortEnable" />
	<@spring.formHiddenInput path="catalogPage.sort.field" />
	<@spring.formHiddenInput path="catalogPage.sort.order" />
	<table class="dtable" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				<input type="checkbox" onclick="mlog.form.checkAll(this, 'deleteIds');" />删除
			</th>
			<th>名称</th>
			<th>父级</th>
			<th>排序</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<#if catalogPage??>
			<#list catalogPage.result as item>
				<#assign tdClass = "odd">
				<#if item_index%2 == 0>
					<#assign tdClass = "even">
				</#if>
				
				<#assign deep = "">
				<#list 1..item.deep as i>
				<#assign deep = deep + "―">
				</#list>
				<tr>
					<td class="${tdClass}"><input type="checkbox" name="deleteIds" value="${item.id}" />&nbsp;&nbsp;${item.id}</td>
					<td class="${tdClass}">${deep}${item.name}</td>
					<td class="${tdClass}">
						<select onchange="setParent('${item.id}', this.value);">
							<option value="">--请选择--</option>
							<#list catalogs as catalog>
							<#assign c_name = catalog.name>
							<#list 1..catalog.deep as i>
							<#assign c_name = "—" + c_name>
							</#list>
							<option value="${catalog.id}" <#if item.parent?exists && item.parent.id = catalog.id>selected="selected"</#if>>${c_name}</option>
							</#list>
						</select>
					</td>
					<td class="${tdClass}">
						<input type="text" class="textinput" style="width:45px;" name="orders" value="${item.order!""}"/>
					</td>
					<td class="${tdClass}">${item.createTime}</td>
					<td class="${tdClass}">
						<input type="hidden" name="ids" value="${item.id}" />
						<a href="${base}/admin/catalog/edit?id=${item.id}">修改</a>
					</td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<input type="button" class="btn" value=" 提交 " onclick="ctrl();" />
			</td>
			<td>
				<@mspring.pagingnavigator page=catalogPage form_id="catalogForm" />
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	function ctrl(){
		var flagOrders = validateOrders();
		if(!flagOrders){
			return;
		}
		mlog.form.submitForm('catalogForm', '${base}/admin/catalog/ctrl');
	}
	
	function validateOrders(){
		var orders = document.getElementsByName('orders');
		for(var i = 0; i < orders.length; i++){
			var value = $(orders[i]).val();
			if($.trim(value).length > 0 && !(/^\d+$/.test(value))){
				mlog.dialog.tip({msg:'排序号必须为数字', type:'warn'});
				$(orders[i]).focus();
				return false;
			}
			if($.trim(value).length > 0){
				if(value < 1 || value > 1000){
					mlog.dialog.tip({msg:'排序号的范围必须在1-1000之间', type:'warn'});
					$(orders[i]).focus();
					return false;
				}
			}
		}
		return true;
	}
	
	function setParent(id, parent){
		var data = {};
		data["id"] = id;
		data["parent"] = parent;
		var result = $.ajax({
			url : mlog.variable.base + "/admin/catalog/setParent",
			async : false,
			data : data
		}).responseText;
		mlog.dialog.tip({msg:'设置父级成功', type:'success'});
	}
</script>
<#include "../inc/footer.ftl" />