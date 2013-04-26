<div class="well" style="padding: 8px 0;">
	<ul class="nav nav-list">
		<li class="nav-header">${catalog.name}</li>
		<#if (posts?exists && posts?size > 0)>
			<#list posts as post>
				<li><a href="<@postUrl post=post />" target="_blank" style="boder-botton:solid 1px;" title="${post.title}"><i class="icon-list"></i><@substring str=post.title endIndex=30 /></a></li>
			</#list>
		</#if>
	</ul>
</div>