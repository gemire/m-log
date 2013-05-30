<div class="well" style="padding: 8px 0;">
	<ul class="nav nav-list">
		<li class="nav-header">最新评论</li>
		<#if (comments?exists && comments?size > 0)>
			<#list comments as comment>
				<#assign post=comment.post />
				<li><a href="<@postUrl post="post" />" target="_blank" title="<@contentTransform content=comment.content removeHtml=true />"><i class="icon-comment"></i><@contentTransform content=comment.content removeHtml=true substring=true endIndex=30 /></a></li>
			</#list>
		</#if>
	</ul>
</div>