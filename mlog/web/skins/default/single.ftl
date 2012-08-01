<#include "header.ftl" />
<!-- article node -->
<#if post?exists>
	<div id="node-${post.id}" class="node">
		<h2>${post.title}</h2>
		<span class="submitted">${post.createTime} - ${post.author.alias ! post.author.name}</span>
		<div class="content">
			${post.content}
		</div>
		
		<div class="clear-block clear">
			<div class="meta">
				<div class="terms">
					<ul class="links inline">
						<#if (post.catalog?exists)>
							<a href="${base}/category/${post.catalog.id}">${post.catalog.name}</a>
						</#if>
					</ul>
				</div>
			</div>
			<div class="links">
				<ul class="links inline">
					<li class="first blog_usernames_blog">
						<li class="first last taxonomy_term_2">
							
						</li>
					</li>
					<li class="last comment_add">
						<a id="p_comments${post.id}"  title='发表评论' href="#divCommentPost">${post.commentCount!"0"}条评论</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<br />
	<div>
		<#-- <#include "comment.ftl"> -->
		<@widget.placeholder path="/comment?post=${post.id}" />
	</div>
<#else>
	<h2>文章未找到</h2>
</#if>
<#include "footer.ftl" />