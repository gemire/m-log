<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<@list_post>
	<div class="post" id="post-<@post_id />">
		<h2 class="post-tltle"><a href="<@post_url />" rel="bookmark" title="<@post_title />"><@post_title /></a></h2>
      	<div class="postmetadata-top">
			<span class="post-comments"><a href="<@post_url />#comments" title="<@post_title /> 上的评论"><@post_comment_count /> comments</a></span>
          	<span class="post-date"><@post_time /></span>
          	<span class="post-views"><span id="spn<@post_id />"></span> views</span>
      	</div>
      	<div class="entry">
          	<@post_content max_length="300" />
      	</div>
      	<div class="postmetadata">
          	<span class="post-cat">
          		<@list_post_catalog>
					<a href="<@catalog_url />"><@catalog_name /></a>
				</@list_post_catalog>
          	</span>
          	<span class="post-author"><a href="javascript:;" title="由 <@post_author_alias /> 发表"><@post_author_alias /></a></span>
          	<span class="post-tag">
	          	<@list_post_tag>
					<a href="<@tag_url />"><@tag_name /></a>
				</@list_post_tag>
			</span>
		</div>
	</div>
</@list_post>
<#if (postPage.totalPages > 1)>
	<div class="navigation">
		<div class="pagenavi">
		<form id="postForm" name="postForm">
			<@spring.bind "postPage" />
			<input type="hidden" name="keyword" style="width:300px;" value="${keyword!""}" />
			<@spring.formHiddenInput path="postPage.pageNo" />
			<@mspring.pagingnavigator page=postPage form_id="postForm" />
		</form>
		</div>
	</div>
</#if>