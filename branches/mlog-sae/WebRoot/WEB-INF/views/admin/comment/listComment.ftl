<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<form id="commentForm" name="commentForm" action="${base}/admin/comment/list" method="POST">
			<@spring.bind "comment" />
			<table class="formtable">
				<tr>
					<td class="fieldlabel" style="width:50px;">状态</td>
					<td>
						<@spring.formSingleSelect path="comment.status" options=commentStatus attributes='style="width:98%" onchange="changeStatus();"' />
					</td>
					
					<td class="fieldlabel" style="width:40px;">内容</td>
					<td>
						<@spring.formInput path="comment.content" attributes='class="textinput" style="width:98%"' />
					</td>
					
					<td class="fieldlabel" style="width:40px;">发布人</td>
					<td><@spring.formInput path="comment.author" attributes='class="textinput" style="width:98%"' /></td>
					
					<td class="fieldlabel" style="width:40px;">文章</td>
					<td><@spring.formInput path="comment.post.title" attributes='class="textinput" style="width:98%"' /></td>
					
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
						<input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" />
					</th>
					<th>编号</th>
					<th>作者</th>
					<th>评论时间</th>
					<th>评论文章</th>
					<th>IP</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<#if commentPage??>
					<#list commentPage.result as item>
						<#assign tdClass = "odd">
						<#if item_index%2 == 0>
							<#assign tdClass = "even">
						</#if>
						<tr>
							<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
							<td class="${tdClass}">${item.id}</td>
							<td class="${tdClass}">${item.author}</td>
							<td class="${tdClass}">${item.createTime}</td>
							<td class="${tdClass}">${item.post.title}</td>
							<td class="${tdClass}">${item.postIp}</td>
							<td class="${tdClass}">
								${item.status}
							</td>
							<td class="${tdClass}">
								<a href="javascript:viewComment('${item.id}');">查看详情</a>
							</td>
						</tr>
					</#list>
				</#if>
			</table>
			<table style="width:100%;">
				<tr>
					<td>
						<input type="button" class="btn" value="审核通过" onclick="mlog.form.confirmSubmit('commentForm', '${base}/admin/comment/audit?type=approved', '确认审核通过？');" />
						<input type="button" class="btn" value="标记为垃圾评论" onclick="mlog.form.confirmSubmit('commentForm', '${base}/admin/comment/audit?type=spam', '确认标记为垃圾评论？');" />
						<input type="button" class="btn" value="移入回收站" onclick="mlog.form.confirmSubmit('commentForm', '${base}/admin/comment/audit?type=recycle', '确认移入回收站？');" />
						<input type="button" class="btn" value=" 彻底删除 " onclick="mlog.form.confirmSubmit('commentForm', '${base}/admin/comment/delete');" />
					</td>
					<td>
						<@mspring.pagingnavigator page=commentPage form_id="commentForm" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function changeStatus(){
			$('#commentForm').submit();
		}
		function viewComment(id){
			mlog.dialog.showModelDialog({
				title : '评论详情',
				content : 'url:${base}/admin/comment/view?id=' + id,
				width : '500px'
			});
		}
	</script>
<#include "../inc/footer.ftl" />