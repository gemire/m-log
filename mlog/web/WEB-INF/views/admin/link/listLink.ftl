<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-east">
		<@widget.placeholder path="/admin/link/create" />
	</div>
	<div class="ui-layout-center">
		<form id="linkForm" name="linkForm" action="${base}/admin/link/list" method="POST">
			<@spring.bind "linkPage" />
			<!-- pagination parameter -->
			<@spring.formHiddenInput path="linkPage.pageNo" />
			<@spring.formHiddenInput path="linkPage.totalPages" />
			<@spring.formHiddenInput path="linkPage.totalCount" />
			<!-- sorter parameter -->
			<@spring.formHiddenInput path="linkPage.sortEnable" />
			<@spring.formHiddenInput path="linkPage.sort.field" />
			<@spring.formHiddenInput path="linkPage.sort.order" />
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
					<#--
					<th>
						是否可见
					</th>
					-->
					<th>
						操作
					</th>
				</tr>
				<#if linkPage??>
					<#list linkPage.result as item>
						<#assign tdClass = "odd">
						<#if item_index%2 == 0>
							<#assign tdClass = "even">
						</#if>
						<tr>
							<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" onclick="checkThisCatalog(this, 'id');" /></td>
							<#if columnfields??>
								<#list columnfields as field>
									<#if field??>
										<td class="${tdClass}"><@mspring.fieldValue value=item attribute=field.id /></td>
									<#else>
										<td class="${tdClass}"></td>
									</#if>
								</#list>
							</#if>
							<#--
							<td class="${tdClass}">
								${item.visable?string('是', '否')}
							</td>
							-->
							<td class="${tdClass}">
								<a href="${base}/admin/link/edit?id=${item.id}">修改</a>
							</td>
						</tr>
					</#list>
				</#if>
			</table>
			<table style="width:100%;">
				<tr>
					<td>
						<input type="button" class="btn" value=" 删除 " onclick="mspring.confirmSubmit('linkForm', '${base}/admin/link/delete');" />
					</td>
					<td>
						<@mspring.pagingnavigator page=linkPage form_id="linkForm" />
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
			east__size:250,
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