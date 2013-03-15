<div class="widget">
	<div class="title">${catalog.name}<a href="${base}/catalog/catalog-${catalog.id}.html" style="float:right;">查看更多&gt;&gt;</a></div>
	<div class="content">
		<#if (posts?exists && posts?size > 0)>
			<#list posts as post>
				<div class="media">
					<div class="media-body">
						<h5 class="media-heading"><a href="<@postUrl post="post" />" target="_blank">${post.title}</a></h5>
						<@contentTransform content=post.content removeHtml=true substring=true endIndex=200 />
					</div>
				</div>
			</#list>
		</#if>
	</div>
</div>
