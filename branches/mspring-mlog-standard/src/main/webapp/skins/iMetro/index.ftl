<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
<script type="text/javascript">mlog.stat.blogClick();</script>
<div id="main">
    <div id="mainContent">
        <div class="forFlow">
            <!--done-->
            <#if (postPage?exists && postPage.result?size > 0)>
                <@list_post>
                    <div class="day">
                        <div class="dayTitle">
                            <a>你好 M-LOG</a>
                        </div>
                        <div class="postTitle">
                            <a class="postTitle2" href="<@post_url />"><@post_title /></a>
                            <#if post.isTop><sup class="tip">置顶！</sup></#if>
                        </div>
                        <div class="postCon">
                            <div class="c_b_p_desc">
                                <#if post.password?has_content>该文章需要密码访问！<#else><quote><@post_summary /></quote></#if>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="postDesc">
                            posted @ <@post_time /> 阅读(<@post_view_count />) 评论(<@post_comment_count />) &nbsp;
                        </div>
                        <div class="clear"></div>
                    </div>
                </@list_post>
                <div class="topicListFooter">
                    <#if (postPage.totalPages > 1)>
                        <@fun.pagenavi />
                    </#if>
                </div>
            <#else>
                                暂无文章
            </#if>

        </div><!--end: forFlow -->
    </div><!--end: mainContent 主体内容容器-->
    
    <#include "sidebar.ftl" />
    <#--
    <div id="sideBar">
        <div id="sideBarMain">
            <div class="newsItem">
                <h3 class="catListTitle">公告</h3>
                <div id="blog-news"></div>
            </div>

            <div id="blog-calendar" style="displya:none"></div>

            <div id="leftcontentcontainer">
                <div id="blog-sidecolumn"></div>
            </div>
        </div>
    </div>
    -->
    
    <div class="clear"></div>
</div><!--end: main -->

<#include "footer.ftl" />