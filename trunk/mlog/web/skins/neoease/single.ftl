<#include "header.ftl" />
<div class="body">
    <div class="wrapper">
        <div class="main">
            <div class="page">
                <h2>
                    <a class="article-title" href="<@post_url />">
                        <@post_title />
                    </a>
                    <sup class="tip">有更新！</sup>
                    <sup class="tip">置顶！</sup>
                </h2>
                <div class="left article-element">
                    <span class="date-ico" title="发布时间"><@post_time />></span>
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
                    <@post_content />
                </div>
                <div class="article-element">
                    <span class="tag-ico" title="分类">
                        <@list_post_catalog>
							<a rel="catalog" href="<@catalog_url />"><@catalog_name /></a>
						</@list_post_catalog>
                    </span>
                </div>
                <#--
                <div class="article-panel1">
                    <#if nextArticlePermalink??>
                    <div class="right">
                        <a href="${servePath}${nextArticlePermalink}">${nextArticle1Label}${nextArticleTitle}</a>
                    </div><div class="clear"></div>
                    </#if>                            
                    <#if previousArticlePermalink??>
                    <div class="right">
                        <a href="${servePath}${previousArticlePermalink}">${previousArticle1Label}${previousArticleTitle}</a>
                    </div>
                    </#if>
                    <div class="clear"></div>
                </div>
                -->
                <div class="article-panel2">
                    <div id="relevantArticles" class="left" style="width: 50%;"></div>
                    <div id="randomArticles" class="left"></div>
                    <div class="clear" style="height: 15px;"></div>
                    <div id="externalRelevantArticles"></div>
                </div>
            </div>
            <@widget.placeholder path="/comment?post=${post.id}" cache=false />
        </div>
        <#include "sidebar.ftl">
        <div class="clear"></div>
    </div>
</div>
<#include "footer.ftl" />