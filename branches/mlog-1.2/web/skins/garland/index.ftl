<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<#include "header.ftl" />
<#if (postPage?exists && postPage.result?size > 0)>
	<@list_post>
		<div id="node-<@post_id />" class="node">
			<h2><a href="<@post_url />" title="<@post_title />"><@post_title /></a></h2>
			<span class="submitted"><@post_time /> - <@post_author_alias /> - <@post_view_count />查看</span>						  
			<div class="content"><@post_content /></div>
			<div class="clear-block clear">
				<div class="meta">
					<div class="terms">
						<ul class="links inline">
							<li class="first last taxonomy_term_2">
								Tags:
								<@list_post_tag>
									<a href="<@tag_url />"><@tag_name /></a>
								</@list_post_tag>
							</li>
						</ul>
					</div>
				</div>
			    <div class="links">
					<ul class="links inline">
						<li class="first blog_usernames_blog">
							分类:
							<@list_post_catalog>
								<a href="<@catalog_url />"><@catalog_name /></a>
							</@list_post_catalog>
						</li>
						<li class="last comment_add">发表评论</li>
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
				<@spring.formHiddenInput path="postPage.pageNo" />
				<@mspring.pagingnavigator page=postPage form_id="postForm" />
			</form>
		</span>
	</div>
	</#if>
<#else>
	暂无文章
</#if>
<#include "footer.ftl" />