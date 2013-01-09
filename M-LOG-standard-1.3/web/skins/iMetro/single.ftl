<#include "header.ftl" />
    <script type="text/javascript">mlog.stat.postClick('<@post_id />');</script>
    <div id="main">
        <div id="mainContent">
            <div class="forFlow">
                <div id="topics">
                    <div class = "post">
                        <h1 class="postTitle"><@post_title /><#if post.isTop><sup class="tip">置顶！</sup></#if></h1>
                        <div class="clear"></div>
                        <div class="postBody">
                            <div id="post_body">
                                <@post_content />
                            </div>
                        </div>
                        <div class="post-copyright">
                                                                       如非注明，本站文章均为原创，转载请注明出处。<br/>
				                            本站地址：<a href="${blogurl}" target="_blank" title="${blogname}">${blogname}</a> <a href="${blogurl}" target="_blank" title="${blogname}">${blogurl}</a><br/>
				                            本文地址：<a href="<@post_url />" target="_blank" title="<@post_title />"><@absolute_post_url /></a><br/>
                        </div>
                        <div class="postDesc">posted @ <span id="post-date"><@post_time /></span> 阅读(<@post_view_count />) 评论(<span id="post-comment-count"><@post_comment_count /></span>)</div>
                    </div>
                </div>
                <div class="comment">
					<#if post.commentStatus == 'open'>
						<@tldwidget.placeholder path="/comment?post=${post.id}" cache=false />
					</#if>
				</div>
            </div>
        </div>
        <#include "sidebar.ftl" />
    </div>
<#include "footer.ftl" />