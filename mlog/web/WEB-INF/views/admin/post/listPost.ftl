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
							<#if columnfields??>
								<#list columnfields as field>
									<#if field??>
										<#--<td class="${tdClass}">${item['${field.id}']!""}</td>-->
										<td class="${tdClass}"><@mspring.fieldValue value=item attribute=field.id /></td>
									<#else>
										<td class="${tdClass}"><@mspring.fieldValue value=item attribute=field.id /></td>
									</#if>
								</#list>
							</#if>
							<td class="${tdClass}">
								<a href="#">修改</a>
							</td>
						</tr>
					</#list>
				</#if>
			</table>
			<table style="width:100%;">
				<tr>
					<td>
						<input type="button" class="btn" value=" 删除 " onclick="mspring.confirmDelete('postForm', '${base}/admin/post/delete');" />
					</td>
					<td>
						<@mspring.pagingnavigator page=postPage form_id="postForm" />
					</td>
				</tr>
			</table>
		</form>
	</div>
<#include "../inc/footer.ftl" />