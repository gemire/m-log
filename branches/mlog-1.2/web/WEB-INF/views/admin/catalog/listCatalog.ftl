<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="javascript:void(0);" class="here">列表</a></li>
			    <li><a href="${base}/admin/catalog/create">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			</ul>
		</div>
		<form id="catalogForm" name="catalogForm" action="${base}/admin/catalog/list" method="POST">
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
						<input type="checkbox" onclick="mspring.checkAll(this, 'id');" />
					</th>
					<#if columnfields??>
						<#list columnfields as field>
							<th>${field.name!""}</th>
						</#list>
					</#if>
					<th>
						操作
					</th>
				</tr>
				<#if catalogPage??>
					<#list catalogPage.result as item>
						<#assign tdClass = "odd">
						<#if item_index%2 == 0>
							<#assign tdClass = "even">
						</#if>
						<tr>
							<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" onclick="checkThisCatalog(this, 'id');" /></td>
							<#if columnfields??>
								<#list columnfields as field>
									<#if field??>
										<td class="${tdClass}">${item['${field.id}']!""} </td>
									<#else>
										<td class="${tdClass}"></td>
									</#if>
								</#list>
							</#if>
							<td class="${tdClass}">
								<a href="${base}/admin/catalog/edit?id=${item.id}">修改</a>
							</td>
						</tr>
					</#list>
				</#if>
			</table>
			<table style="width:100%;">
				<tr>
					<td>
						<input type="button" class="btn" value=" 删除 " onclick="mspring.confirmSubmit('catalogForm', '${base}/admin/catalog/delete');" />
					</td>
					<td>
						<@mspring.pagingnavigator page=catalogPage form_id="catalogForm" />
					</td>
				</tr>
			</table>
		</form>
	</div>

<script type="text/javascript">
$(document).ready(function(){
	$('body').layout({
		north__closable:false,
		north__size:62,
		north__resizable:false,
		south__closable:false,
		south__size:50,
		south__resizable:false,
		togglerTip_open : "关闭",
		togglerTip_closed : "打开",
		resizerTip:"调整宽度",
		//west__spacing_closed:10,
		west__onresize: function (pane, $Pane) {  
            
        }
	});
});
</script>
<#include "../inc/footer.ftl" />