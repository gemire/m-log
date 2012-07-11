	<div id="sidebar">
		<ul>
			<#if categories?exists>
				<li><h2>${articleCategory}</h2>
					<ul>
						<#list categories as category>
							<li class="cat-item cat-item-${category.id}"><a href="${path}/category/${category.id}" title="${category.name}">${category.name}</a></li>
						</#list>
					</ul>
				</li>
			</#if>
			
			<#if recentArticle?exists>
				<li><h2>${recentArticle}</h2>
					<ul>
					<#list recentEntries as article>
						<li class="page_item page-item-${article.id}"><a href="${path}/${mspring_config_global_rebuildfolder}/${article.id}.html">${article.title}</a></li>
					</#list>
					</ul>
				</li>
			</#if>
			
			
			<#if recentArticle?exists>
				<li><h2>${recentArticle}</h2>
					<ul>
					<#list recentComments as comment>
						<li><a href="#">${comment.content}</a></li>
					</#list>
					</ul>
				</li>
			</#if>
		</ul>
	</div>