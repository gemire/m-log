<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
	<div class="row-fluid">
		<#include "sidebar.ftl" />
		<div class="span9">
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>
						<a href="${blogurl}">首页</a> <span class="divider">/</span>
				  	</li>
				  	<li class="active">搜索关键字：${searchKeyword!""}</li>
				</ul>
				<#if (postPage?exists && postPage.result?size > 0)>
					<#list postPage.result as post>
						<div class="post-entity">
							<div class="post-title"><a href="<@postUrl post="post" />" title="${post.title}">${post.title}</a><#if post.isTop><sup class="tip">置顶！</sup></#if></div>
							<div class="post-meta">
								作者：${post.author.alias}
								<#if (post.catalogs?exists && post.catalogs?size > 0)>
									分类：<#list post.catalogs as catalog><a href="${base}/catalog/catalog-${catalog.id}.html" rel="tag">${catalog.name}</a>&nbsp;&nbsp;</#list>
								</#if>
								<#if (post.tags?exists && post.tags?size > 0)>
									标签：<#list post.tags as tag><a href="${base}/tag/tag-${tag.id}.html" rel="tag">${tag.name}</a>&nbsp;&nbsp;</#list>
								</#if>
								时间：${post.createTime}
								点击量：${post.viewCount}
							</div>
							<div class="post-summary">
								<#if post.password?has_content>
					        		该文章需要密码访问！
					        	<#else>
					        		<#if post.summary?has_content>
					        		${post.summary}
					        		<#else>
					        		<@contentTransform content=post.content removeHtml=true substring=true endIndex=200 />
					        		</#if>
					        	</#if>
							</div>
							<div class="post-meta-bottom">
								<a title="${post.title}" class="btn btn-primary pull-left" href="<@postUrl post="post" />" >继续阅读 →</a>
							</div>
							<div class="line_dashed"></div>
						</div>
					</#list>
					<div class="post-pages">
						<#if (postPage.totalPages > 1)>
	                        <@fun.pagenavi />
	                    </#if>
					</div>
				<#else>
					没有搜索到“${searchKeyword!""}”相关的内容。
				</#if>
				
				
			</div>
		</div>
	</div>
<#include "footer.ftl" />