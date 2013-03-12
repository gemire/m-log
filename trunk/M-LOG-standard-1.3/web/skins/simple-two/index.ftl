<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
		<script type="text/javascript">mlog.stat.blogClick();</script>
		<div class="row-fluid">
			<#include "sidebar.ftl" />
			<div class="span9">
				<div class="row-fluid">
					<#if currentPage == "catalog_archive">
						<ul class="breadcrumb">
							<li>
    							<a href="${blogname}">首页</a> <span class="divider">/</span>
						  	</li>
						  	<li class="active">分类：${catalogArchiveName}</li>
						</ul>
					</#if>
					<#if currentPage == "tag_archive">
						<ul class="breadcrumb">
							<li>
    							<a href="${blogname}">首页</a> <span class="divider">/</span>
						  	</li>
						  	<li class="active">TAG：${tagArchiveName}</li>
						</ul>
					</#if>
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
									<#if post.site?exists && post.site?has_content>
										转载自：${post.site!""}
									</#if>
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
					</#if>
					<div class="post-pages">
						<#if (postPage.totalPages > 1)>
	                        <@fun.pagenavi />
	                    </#if>
					</div>
				</div>
			</div>
		</div>
<#include "footer.ftl" />