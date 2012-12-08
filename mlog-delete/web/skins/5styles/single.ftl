<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "header.ftl" />
	    <!-- 导航位置 -->
		<div class="navigation-top">
			<span>您的位置：</span>
			<a href="${base}">首页</a> &gt;&gt;
			<@list_post_catalog>
				<a href="<@catalog_url />" title="查看 <@catalog_name /> 的全部文章"><@catalog_name /></a>&nbsp;
			</@list_post_catalog>&gt;&gt;
			<a href="<@post_url />" title="<@post_title />"><@post_title /></a>
		</div>
		
		<!-- 文章内容 -->
		<div class="post" id="post-<@post_id />"> 
		    <h2 class="post-tltle"><a href="<@post_url />" rel="bookmark" title="<@post_title />"><@post_title /></a></h2> 
		    <div class="postmetadata-top"> 
		        <span class="post-comments"><a href="#respond"><@post_comment_count /> comments</a></span> 
		        <span class="post-date"><@post_time /></span> 
		        <span class="post-views"><span id="spn<@post_id />"><@post_view_count /></span> views</span> 
		    </div> 
		    <div class="entry"> 
		       <@post_content />
		    </div> 
		    <div class="postmetadata">
		        <span class="post-cat">
			        <@list_post_catalog>
						<a href="<@catalog_url />" title="查看 <@catalog_name /> 的全部文章"><@catalog_name /></a>&nbsp;
					</@list_post_catalog>
		        </span> 
		        <span class="post-author"><a href="javascript:void(0);" title="由 <@post_author_alias /> 发表"><@post_author_alias /></a></span> 
		        <span class="post-tag">
		        	<@list_post_tag>
						<a href="<@tag_url />"><@tag_name /></a>
					</@list_post_tag>
		        </span> 
		    </div> 
		</div>
		
		<!-- 上下篇 -->
		<div class="navigation"> 
		    <#-- <span class="previous"><#template:article_navbar_l#></span> -->
		</div>
		
		<!-- 相关文章 -->
		<div class="related"> 
			<h4>相关文章</h4> 
			<ul class="related_post">
		        <#-- <#template:article_mutuality#> -->
		    </ul>
		</div>
		
		<!-- 评论 -->
		<div id="comments-wrap"> 
			<h2 id="respond"><@post_comment_count /> 条留言</h2>
		    <span class="goto-comment"><a href="#commentform">我要留言</a></span> 
				<ul class=""> 
					<!-- 评论列表 -->
					<#-- <#template:article_comment#> -->
				</ul> 
				<!-- 发表评论 -->
		        <#-- <#template:article_commentpost#> -->
		</div>
              
<#include "footer.ftl" />