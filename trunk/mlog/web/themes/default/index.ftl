<#include "header.ftl"/>
<#if articlePage?exists && articlePage.result?exists>
	<#list articlePage.result as article>
	<!-- article node -->
	<div id="node-${article.id}" class="node">
		<h2>
			<a href="${path}/${mspring_config_global_rebuildfolder}/${article.id?trim}.html" title="${article.title}">${article.title}</a>
		</h2>
	
		<span class="submitted">${postBy}：${mspring_config_base_nickname?default("")}  ${postAt}${article.createTime?default("")}</span>
	
		<div class="content">
			<@mspring_tag.splitArticleContent content="${article.content}" intro="${article.intro}" />
		</div>
	
		<div class="clear-block clear">
			<#--
			<div class="meta">
				<div class="terms">
					<ul class="links inline">
						<li class="first last taxonomy_term_2">
							<@mspring_tag.getTag articleId="${article.id}" />
						</li>
					</ul>
				</div>
			</div>
			-->
	
			<div class="links">
				<ul class="links inline">
					<li>
						<#if (article.categories?exists && article.categories.size() > 0)>
							${category}
							<#list article.categories as category>
								<a href="${path}/category/${category.id}">${category.name}</a>
							</#list>
						</#if>
					</li>
					<li>
						<#if (article.tags?exists && article.tags.size() > 0)>
							${tag}
							<#list article.tags as tag>
								<a href="javascript:void(0);">${tag.name}</a>
							</#list>
						</#if>
					</li>
					<li>
						${article.viewNums?default("0")} ${viewNums}
					</li>
					<li>
						<a id="p_comments${article.id}" title="${createNewComment}" href="${path}/single.action?id=${article.id}#divCommentPost">${article.commentNums}${commentNums?default("0")}</a>
						<script type="text/javascript">
							if(${article.commentNums}==0){
								document.getElementById("p_comments${article.id}").innerHTML="${createNewComment}"
							}
						</script>
					</li>
				</ul>
			</div>
		</div>
	</div>
	</#list>
	<div class="pager">
		<span class="pager-list">
			<#if (articlePage.totalPages > 1)>
				<#if articlePage.hasPre>
					<#--分类查看中分页-->
					<#if article?exists && article.category.id?exists>
						<a href="${path}/category/${article.category.id?trim}/page/${articlePage.prePage}" alt="${prePage}}">${prePage}</a>
					<#else>
						<a href="${path}/page/${articlePage.prePage}" alt="${prePage}}">${prePage}</a>
					</#if>
				</#if>
				<#if articlePage.hasNext>
					<#if article?exists && article.category.id?exists>
						<a href="${path}/category/${article.category.id?trim}/page/${articlePage.nextPage}" alt="${nextPage}">${nextPage}</a>
					<#else>
						<a href="${path}/page/${articlePage.nextPage}" alt="${nextPage}">${nextPage}</a>
					</#if>
				</#if>
			</#if>
		</span>
	</div>
	<span class="clear"></span>
</#if>
<#include "footer.ftl"/>