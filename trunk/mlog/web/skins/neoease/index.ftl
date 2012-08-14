<#include "header.ftl" />
<div class="body">
    <div class="wrapper">
        <div class="main">
            <@list_post>
			<div class="article">
			    <h2>
			        <span class="left">
			        <a rel="bookmark" class="article-title" href="<@post_url />">
			            <@post_title />
			        </a>
			        <sup class="tip">有更新！</sup>
			        <sup class="tip">置顶！</sup>
			        </span>
			        <span class="expand-ico" onclick="getArticle(this, '<@post_id />');"></span>
			        <span class="clear"></span>
			    </h2>
			    <div class="left article-element">
			        <span class="date-ico" title="">  
			            <@post_time />
			        </span>
			        <span class="user-ico" title="作者">
			            <a rel="nofollow" href="javascript:void(0);"><@post_author_alias /></a>
			        </span>
			    </div>
			    <div class="right article-element">
			        <a rel="nofollow" href="<@post_url />#comments">
			            <@post_comment_count />&nbsp;&nbsp;评论
			        </a>&nbsp;&nbsp;
			        <a rel="nofollow" href="<@post_url />">
			            <@post_view_count />&nbsp;&nbsp;浏览
			        </a>
			    </div>
			    <div class="clear"></div>
			    <div class="article-body">
			        <div id="abstract<@post_id />">
			            <@post_content max_length=300 />
			        </div>
			        <div id="content<@post_id />" class="none">
			        	<@post_content />
			        </div>
			    </div>
			   	<div class="article-element">
			    	<span class="tag-ico" title="tags">
			            <@list_post_catalog>
							<a href="<@catalog_url />"><@catalog_name /></a>
						</@list_post_catalog>
			        </span>
			    </div>
			</div>
			</@list_post>
        </div>
        <#include "sidebar.ftl">
        <div class="clear"></div>
    </div>
</div>
<#include "footer.ftl" />