<#include "header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<#if postPage?exists && postPage.result?exists>
	<#list postPage.result as post>
		<div id="node-${post.id}" class="node">
			<h2>
				<a href="${base}/${post.id}.html" title="${post.title}">${post.title}</a>
			</h2>
		
			<span class="submitted">作者：${post.author.alias!post.author.name} 时间:${post.createTime}</span>
		
			<div class="content">
				<@get_postcontent />
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
<#include "footer.ftl" />