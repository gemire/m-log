<#import "include/simple-one.fun.ftl" as simple>  
<#include "header.ftl" />
	<script type="text/javascript">mlog.stat.blogClick();</script>
	<div class="container main-wrap clearfix">
		<#include "sidebar.ftl" />
		<div class="main-content">
			<div class="main-posts">
				<#if (postPage?exists && postPage.result?size > 0)>
					<@list_post>
						<div class="post-item">
					        <h2 class="post-title">
					            <a href="<@post_url />" title="<@post_title />"><@post_title /></a>
					            <#if post.isTop><sup class="tip">置顶！</sup></#if>
					        </h2>
					        <div class="post-summary">
					        	<#if post.password?has_content>
					        		该文章需要密码访问！
					        	<#else>
					        		<@post_summary />
					        	</#if>
					        </div>
					        <div class="post-ft">
					           	分类：
					           	<@list_post_catalog>
									<a href="<@catalog_url />" rel="tag"><@catalog_name /></a>
								</@list_post_catalog>
								标签：
								<@list_post_tag>
									<a href="<@tag_url />" rel="tag"><@tag_name /></a>
								</@list_post_tag>
								于<@post_time />发表该文章<span class="split">|</span><@post_view_count /> views           
					           	<p class="btn-wrap">
					               <a class="btn" href="<@post_url />">More</a>
					           </p>
					        </div>
					    </div>
					</@list_post>
					<#if (postPage.totalPages > 1)>
						<@simple.pagenavi />
					</#if>
				<#else>
					暂无文章
				</#if>
			</div>
		</div>
	</div>
<#include "footer.ftl" />