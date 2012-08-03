<#include "header.ftl" />
<#if postPage?exists && postPage.result?exists>
	<#list postPage.result as post>
		<!-- article node -->
		<div id="node-${post.id}" class="node">
			<h2><a href="${base}${post.url}" title="${post.title}">${post.title}</a></h2>
			<span class="submitted">${post.createTime} - ${post.author.alias}</span>						  
			<div class="content">${post.content}</div>
			<div class="clear-block clear">
				<div class="meta">
					<div class="terms">
						<ul class="links inline">
							<li class="first last taxonomy_term_2">
								<#if post.tags?exists>
									<#list post.tags as tag>
										${tag.name!""}
									</#list>
								</#if>
							</li>
						</ul>
					</div>
				</div>
			    <div class="links">
					<ul class="links inline">
						<li class="first blog_usernames_blog">
							<#if (post.catalog?exists)>
								<a href="${base}/catalog/${post.catalog.id}">${post.catalog.name}</a>
							</#if>
						</li>
						<li class="last comment_add">发表评论</li>
					</ul>
				</div>
			</div>
		</div>
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
	</#list>
</#if>
<#include "footer.ftl" />