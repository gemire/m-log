<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
	<div class="row-fluid">
		<#include "sidebar.ftl" />
		<div class="span9">
			<div class="row-fluid">
			
				<#if (postPage?exists && postPage.result?size > 0)>
					<@list_post>
						<div class="post-entity">
							<div class="post-title"><a href="<@post_url />" title="<@post_title />"><@post_title /></a><#if post.isTop><sup class="tip">置顶！</sup></#if></div>
							<div class="post-meta">
								作者：<@post_author_alias />
								<#if (post.catalogs?exists && post.catalogs?size > 0)>
									分类：<@list_post_catalog><a href="<@catalog_url />" rel="tag"><@catalog_name /></a></@list_post_catalog>
								</#if>
								<#if (post.tags?exists && post.tags?size > 0)>
									标签：<@list_post_tag><a href="<@tag_url />" rel="tag"><@tag_name /></a></@list_post_tag>
								</#if>
								时间：<@post_time />
								点击量：<@post_view_count />
							</div>
							<div class="post-summary">
								<#if post.password?has_content>
					        		该文章需要密码访问！
					        	<#else>
					        		<@post_summary />
					        	</#if>
							</div>
							<div class="post-meta-bottom">
								<a title="<@post_title />" class="btn btn-primary pull-left" href="<@post_url />" >继续阅读 →</a>
							</div>
							<div class="line_dashed"></div>
						</div>
					</@list_post>
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