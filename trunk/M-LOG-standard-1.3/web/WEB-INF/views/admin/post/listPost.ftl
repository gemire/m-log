<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
	<form id="postForm" name="postForm" action="${base}/admin/post/list" method="POST">
		<@spring.bind "post" />
		<table>
			<tr>
				<td class="fieldlabel" style="width:50px;">状态</td>
				<td>
					<@spring.formSingleSelect path="post.status" options=status attributes='onchange="changeStatus();"' />
				</td>
				
				<td class="fieldlabel" style="width:40px;">标题</td>
				<td>
					<@spring.formInput path="post.title" attributes='class="textinput"' />
				</td>
				
				<td class="fieldlabel" style="width:40px;">发布人</td>
				<td>
					<@spring.formInput path="post.author.alias" attributes='class="textinput"' />
				</td>
				
				<td><input type="submit" class="btn" value=" 查 询 " /></td>
			</tr>
		</table>
		
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
					<input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" />
				</th>
				<th>编号</th>
				<th>标题</th>
				<th>分类</th>
				<th>创建时间</th>
				<th>置顶？</th>
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
						<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
						<td class="${tdClass}">${item.id}</td>
						<td class="${tdClass}"><a href="<@postUrl post="item" />" title="${item.title}" target="_blank"><@mspring.sub_string content=item.title to=20 suffix="..." /></a></td>
						<td class="${tdClass}">
							<#if (item.catalogs?exists && item.catalogs?size > 0)>
								<#list item.catalogs as catalog>
									${catalog.name}&nbsp;
								</#list>
							</#if>
						</td>
						<td class="${tdClass}">${item.createTime}</td>
						<td class="${tdClass}"><#if item.isTop><font color="red">是</font><#else>否</#if></td>
						<td class="${tdClass}">
							<a href="${base}/admin/post/edit?id=${item.id}">修改</a>
						</td>
					</tr>
				</#list>
			</#if>
		</table>
		<table style="width:100%;">
			<tr>
				<td>
					<#-- 当筛选条件为回收站 -->
					<#if post?exists && post.status?has_content && post.status == 'trash'>
						<input type="button" class="btn" value=" 彻底删除 " onclick="mlog.form.confirmSubmit('postForm', '${base}/admin/post/delete', '你确认要彻底删除选中文章吗?');" />
						<input type="button" class="btn" value=" 恢复文章 " onclick="mlog.form.confirmSubmit('postForm', '${base}/admin/post/trash2Publish', '你确认要恢复选中文章吗?');" />
						<input type="button" class="btn" value=" 清空回收站 " onclick="mlog.form.confirmSubmit('postForm', '${base}/admin/post/clearTrash', '你确认要清空回收站吗?');" />
					<#elseif post?exists && post.status?has_content && post.status == 'draft'>
						<input type="button" class="btn" value=" 彻底删除 " onclick="mlog.form.confirmSubmit('postForm', '${base}/admin/post/delete', '你确认要彻底删除选中草稿吗?');" />
					<#else>
						<input type="button" class="btn" value=" 移入回收站 " onclick="mlog.form.confirmSubmit('postForm', '${base}/admin/post/trash', '确认要将选中文章移入回收站吗?');" />
						<input type="button" class="btn" value=" 更新索引 " onclick="updateLuceneIndex();" />
					</#if>
				</td>
				<td>
					<@mspring.pagingnavigator page=postPage form_id="postForm" />
				</td>
			</tr>
		</table>
	</form>

	
	<script type="text/javascript">
	turnHighLight(105005);
	function updateLuceneIndex(){
		$.get("${base}/admin/post/updateIndex", function(data){
			if(data == "true"){
				alert("更新成功");
			}
			else{
				alert("更新失败");
			}
		});
	}
	function changeStatus(){
		$("#postForm").submit();
	}
	</script>
<#include "../inc/footer.ftl" />