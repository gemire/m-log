<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<#include "header.ftl" />
<div id="search-div" style="text-align: center;">
	<form method="get">
		<input type="text" name="keyword" style="width:300px;" value="${keyword!""}" />
		<input type="submit" value=' 搜  索 '/>
	</form>
</div>

<#if (keyword?has_content && postPage.result?size == 0)>
<h3>未找到于关键字 <font color="red">${keyword!""}</font> 相关的结果</h3>
</#if>

<@list_post>
	<div id="node-<@post_id />" class="node">
		<h2>
			<a href="<@post_url />" title="<@post_title />"><@post_title /></a>
		</h2>
	
		<span class="submitted">作者：<@post_author_alias /> 时间:<@post_time /></span>
	
		<div class="content">
			<@post_content max_length="300" />
		</div>
		<div class="clear-block clear">
			<div class="links">
				<ul class="links inline">
					<li>
						分类:
						<@list_post_catalog>
							<a href="<@catalog_url />"><@catalog_name /></a>
						</@list_post_catalog>
					</li>
					<li>
						Tags:
						<@list_post_tag>
							<a href="<@tag_url />"><@tag_name /></a>
						</@list_post_tag>
					</li>
				</ul>
			</div>
		</div>
	</div>
</@list_post>
<#if (postPage.totalPages > 1)>
<div class="pager">
	<span class="pager-list">
		<form id="postForm" name="postForm">
			<@spring.bind "postPage" />
			<input type="hidden" name="keyword" style="width:300px;" value="${keyword!""}" />
			<@spring.formHiddenInput path="postPage.pageNo" />
			<@mspring.pagingnavigator page=postPage form_id="postForm" />
		</form>
	</span>
</div>
</#if>
<#include "footer.ftl" />