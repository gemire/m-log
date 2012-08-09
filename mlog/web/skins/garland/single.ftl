<#include "header.ftl" />
<!-- article node -->
<#if post?exists>
	<div id="node-<@post_id />" class="node">
		<h2><@post_title /></h2>
		<span class="submitted"><@post_time /> - <@post_author_alias /></span>
		<div class="content">
			<@post_content />
		</div>
		
		<div class="clear-block clear">
			<div class="meta">
				<div class="terms">
					<ul class="links inline">
						分类:
						<@list_post_catalog>
							<a href="<@catalog_url />"><@catalog_name /></a>
						</@list_post_catalog>
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
						<a id="p_comments<@post_id />"  title='发表评论' href="#divCommentPost"><@post_comment_count />条评论</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<br />
	<div>
		<#-- <#include "comment.ftl"> -->
		<@widget.placeholder path="/comment?post=${post.id}" cache=false />
	</div>
<#else>
	<h2>文章未找到</h2>
</#if>
<#include "footer.ftl" />