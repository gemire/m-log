<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<form id="commentForm" name="commentForm" action="${base}/admin/comment/list" method="POST">
			<table class="formtable" style="width:100%">
				<tr>
					<td class="fieldlabel" style="width:50px;">状态</td>
					<td>
						<select name="comment.status" width="98%">
							<option value="">--请选择--</option>
							<option value="wait_for_approve">待审核</option>
							<option value="approved">审核通过</option>
							<option value="spam">垃圾评论</option>
							<option value="recycle">回收站</option>
						</select>
					</td>
					
					<td class="fieldlabel" style="width:40px;">内容</td>
					<td><input type="text" class="textinput" style="width:98%" name="comment.content" /></td>
					
					<td class="fieldlabel" style="width:40px;">发布人</td>
					<td><input type="text" class="textinput" style="width:98%" name="comment.author" /></td>
					
					<td class="fieldlabel" style="width:40px;">文章</td>
					<td><input type="text" class="textinput" style="width:98%" name="comment.post.title" /></td>
					
					<td><input type="submit" class="btn" value=" 查 询 " /></td>
				</tr>
			</table>
		
			<@spring.bind "commentPage" />
			<!-- pagination parameter -->
			<@spring.formHiddenInput path="commentPage.pageNo" />
			<@spring.formHiddenInput path="commentPage.totalPages" />
			<@spring.formHiddenInput path="commentPage.totalCount" />
			<!-- sorter parameter -->
			<@spring.formHiddenInput path="commentPage.sortEnable" />
			<@spring.formHiddenInput path="commentPage.sort.field" />
			<@spring.formHiddenInput path="commentPage.sort.order" />
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
				<#if commentPage??>
					<#list commentPage.result as item>
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
								<a href="javascript:void(0);">查看详情</a>
							</td>
						</tr>
					</#list>
				</#if>
			</table>
			<table style="width:100%;">
				<tr>
					<td>
						<input type="button" class="btn" value="审核通过" />
						<input type="button" class="btn" value="标记为垃圾评论" />
						<input type="button" class="btn" value="移入回收站" />
						<input type="button" class="btn" value=" 彻底删除 " onclick="mspring.confirmDelete('commentForm', '${base}/admin/comment/delete');" />
					</td>
					<td>
						<@mspring.pagingnavigator page=commentPage form_id="commentForm" />
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
		resizerClass: 'ui-state-default',
		//west__spacing_closed:10,
		west__onresize: function (pane, $Pane) {  
            
        }
	});
});
</script>
<#include "../inc/footer.ftl" />