<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="javascript:void(0);" class="here">列表</a></li>
			    <li><a href="${base}/admin/post/create">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			</ul>
		</div>
		<form id="postForm" name="postForm" action="${base}/admin/post/list" method="POST">
			<@spring.bind "postPage" />
			<!-- pagination parameter -->
			<@spring.formHiddenInput path="postPage.pageNo" />
			<@spring.formHiddenInput path="postPage.totalPages" />
			<@spring.formHiddenInput path="postPage.totalCount" />
			<!-- sorter parameter -->
			<@spring.formHiddenInput path="postPage.sortEnable" />
			<@spring.formHiddenInput path="postPage.sort.field" />
			<@spring.formHiddenInput path="postPage.sort.order" />
			
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
				<#if postPage??>
					<#list postPage.result as item>
						<#assign tdClass = "odd">
						<#if item_index%2 == 0>
							<#assign tdClass = "even">
						</#if>
						<tr>
							<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" onclick="checkThisCatalog(this, 'id');" /></td>
							<td class="${tdClass}">${item.id}</td>
							<td class="${tdClass}">${item.title}</td>
							<td class="${tdClass}">
								<#if (item.catalogs?exists && item.catalogs?size > 0)>
									<#list item.catalogs as catalog>
										${catalog.name}&nbsp;
									</#list>
								</#if>
							</td>
							<td class="${tdClass}">${item.url}</td>
							<td class="${tdClass}">${item.createTime}</td>
							<td class="${tdClass}">${item.modifyTime!""}</td>
							<td class="${tdClass}">${item.author.alias}</td>
							<td class="${tdClass}">${item.status}</td>
							<td class="${tdClass}">
								<a href="${base}/admin/post/edit?postId=${item.id}">修改</a>
							</td>
						</tr>
					</#list>
				</#if>
			</table>
			<table style="width:100%;">
				<tr>
					<td>
						<input type="button" class="btn" value=" 删除 " onclick="mspring.confirmSubmit('postForm', '${base}/admin/post/delete');" />
					</td>
					<td>
						<@mspring.pagingnavigator page=postPage form_id="postForm" />
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