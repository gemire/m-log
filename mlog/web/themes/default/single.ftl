<#include "header.ftl" />
<!-- article node -->
<#if article?exists>
	<!--更新文章点击-->
	${widget("SingleStatisticWidget", article.id)}
	<div id="node-${article.id}" class="node">
		<h2>${article.title}</h2>
		<span class="submitted">${article.createTime} - ${mspring_config_base_nickname}</span>
		<div class="content">
			${article.content}
		</div>
		
		<div class="clear-block clear">
			<div class="meta">
				<div class="terms">
					<ul class="links inline">
						<#if (article.categories?exists && article.categories.size() > 0)>
							${category}
							<#list article.categories as category>
								<a href="${path}/category/${category.id}">${category.name}</a>
							</#list>
						</#if>
						
					</ul>
				</div>
			</div>
			<div class="links">
				<ul class="links inline">
					<li class="first blog_usernames_blog">
						<li class="first last taxonomy_term_2">
							<#if (article.tags?exists && article.tags.size() > 0)>
								${tag}
								<#list article.tags as tag>
									<a href="javascript:void(0);">${tag.name}</a>
								</#list>
							</#if>
						</li>
					</li>
					<li class="last comment_add">
						<a id="p_comments${article.id}"  title='${createNewComment}' href="#divCommentPost">${article.commentNums} ${commentNums}</a>
						<script type="text/javascript">if(${article.commentNums}==0){document.getElementById("p_comments${article.id}").innerHTML='${createNewComment}'}</script>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<br />
	<div>
		<#include "comment.ftl">
	</div>
<#else>
	<h2>${postNotFound}</h2>
</#if>
<#include "footer.ftl" />