<#if (posts?exists && posts?size > 0)>
	<#list posts as post>
		<li>
			<a href="<@postUrl post="post" />" target="_blank" style="boder-botton:solid 1px;" title="${post.title}">
				<i class="icon-fire"></i><@contentTransform content="${post.title}" substring=true endIndex=24 />
			</a>
		</li>
	</#list>
</#if>