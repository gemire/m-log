<#include "header.ftl" />

	<div id="content">

		<#if article?exists>
	
		<div class="navigation">
			<div class="alignleft">上一篇</div>
			<div class="alignright">下一篇</div>
		</div>

		<div class="post" id="post-${article.id}">
			<h2 class="posttitle"><a href="#" rel="bookmark" title="Permanent Link to ${article.title}">${article.title}</a></h2>
			<div class="postmetadata">${postBy}：${mspring_config_base_nickname?default("")}  ${postAt}${article.createTime?default("")}</div>
			<div class="postentry">
				${article.content}
			</div>
	
			<br />
			<div class="postmeta">
				<span>
					标签：<@mspring_tag.getTag articleId="${article.id}" />
					分类：${article.category.name}
				</span>
			 </div>
		</div>
		
	comment
	<#else>

		<div class="post">
			<h2 class="posttitle">Not Found</h2>
			<div class="postentry">Sorry, 没有找到符合您条件的文章.</p></div>
		</div>

	</#if>

	</div>

<#include "sidebar.ftl" />
<#include "footer.ftl" />
	
