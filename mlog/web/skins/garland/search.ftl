<#include "header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div id="search-div" style="text-align: center;">
	<form method="get">
		<input type="text" name="s" style="width:300px;" value="${s!""}" />
		<input type="submit" value=' 搜  索 '/>
	</form>
</div>

<#if postPage?exists && postPage.result?exists>
	<#list postPage.result as post>
		<div id="node-${post.id}" class="node">
			<h2>
				<a href="${base}${post.url}" title="${post.title}">${post.title}</a>
			</h2>
		
			<span class="submitted">作者：${post.author.alias!post.author.name} 时间:${post.createTime}</span>
		
			<div class="content">
				<@get_postcontent max_length="300" />
			</div>
			<div class="clear-block clear">
				<div class="links">
					<ul class="links inline">
						<li>
							<#if (post.catalog?exists)>
								<a href="${base}/catalog/${post.catalog.id}">${post.catalog.name}</a>
							</#if>
						</li>
						<li>
							<#if post.tags?exists>
								<#list post.tags as tag>
									${tag.name!""}
								</#list>
							</#if>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</#list>
	
	<#if (postPage.totalPages > 1)>
		<div class="pager">
			<span class="pager-list">
				<form id="postForm" name="postForm">
					<@spring.bind "postPage" />
					<@spring.formHiddenInput path="postPage.pageNo" />
					<@mspring.pagingnavigator page=postPage form_id="postForm" />
				</form>
			</span>
		</div>
	</#if>
</#if>
<#include "footer.ftl" />