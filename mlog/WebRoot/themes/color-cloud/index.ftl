<#include "header.ftl"/>
	<div id="content">
	<#if articles?exists>
		<#list articles as article>
			<div class="post" id="post-${article.id}">
				<h2 class="posttitle"><a href="${path}/${mspring_config_global_rebuildfolder}/${article.id}.html" rel="bookmark" title="Permanent Link to ${article.title}">${article.title}</a></h2>
				<div class="postmetadata">
					${postAt} ${article.createTime?default("")}&nbsp;&nbsp;
					${postBy} ${mspring_config_base_nickname?default("")}&nbsp;&nbsp;
				</div>
				<div class="postentry">
					<@mspring_tag.splitArticleContent content="${article.content}" intro="${article.intro}" />
				</div>
			</div>
		</#list>
	<#else>
		<div class="post">
			<h2 class="posttitle">Not Found</h2>
			<div class="postentry"><p>Sorry, no posts matched your criteria.</p></div>
		</div>
	</#if>

	</div>
<#include "sidebar.ftl"/>
<#include "footer.ftl"/>