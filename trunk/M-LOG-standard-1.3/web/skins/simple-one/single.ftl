<#include "header.ftl" />
	<script type="text/javascript">mlog.stat.postClick('<@post_id />');</script>
	<div class="container main-wrap clearfix">
		<#include "sidebar.ftl" />
		<#if post?exists>
			<div class="main-content">
				<div class="main-posts main-item">
					<div class="post-header">
						<#-- <a class="btn fr" rel="share">分享</a> -->
						<h2 class="post-title"><@post_title /><#if post.isTop><sup class="tip">置顶！</sup></#if></h2>
						<div class="post-meta">
	        					发表于<@post_time /><span class="split">|</span><@post_comment_count />条评论<span class="split">|</span><@post_view_count /> views                                    
						</div>
					</div>
					<div class="post-summary"><@post_content /></div>
					<div class="post-ft"></div>
				</div>
				
				<#--
				<div class="nav-above">
					<div class="nav-previous">上一篇</div>
					<div class="nav-next">下一篇</div>
				</div>
				-->
				<hr/>
				<div class="post-relative-div">
					<div  class="post-relative">
						<h4>相关文章</h4>				
						<#assign catalogs=post.catalogs />
						<@sql_query var="about_posts" sql="select pc.PK.post from PostCatalog pc where pc.PK.catalog.name in (@catalogs.name) and pc.PK.post.id <> @post.id order by pc.PK.post.viewCount desc" max="10" expiry="3600000"/>
						<#if about_posts?exists>
						<ul>
							<#list about_posts as p>
							<li><a href="<@post_url id=p.id />" target="_blank">${p.title}</a></li>
							</#list>
						</ul>
						</#if>
					</div>
					<div  class="post-relative">
						<h4>随机文章</h4>				
						<#assign tags=post.tags />
						<@sql_query var="rand_posts" sql="from Post post order by rand()" max="10" cache="false" />
						<#if rand_posts?exists>
						<ul>
							<#list rand_posts as p>
							<li><a href="<@post_url id=p.id />" target="_blank">${p.title}</a></li>
							</#list>
						</ul>
						</#if>
					</div>
				</div>
				<div class="post-copyright">
					如非注明，本站文章均为原创，转载请注明出处。<br/>
					本站地址：<a href="${blogurl}" target="_blank" title="${blogname}">${blogname}</a> <a href="${blogurl}" target="_blank" title="${blogname}">${blogurl}</a><br/>
					本文地址：<a href="<@post_url />" target="_blank" title="<@post_title />"><@absolute_post_url /></a><br/>
				</div>
				<div class="post-interactive">
	                <span>觉得有帮助吗？</span>
	                <a class="btn red" href="https://me.alipay.com/gaoyoubo" target="_blank">
	                	<i class="icon icon-heart"></i>打赏1RMB
	                </a>
				</div>
				<div class="line"></div>
				<div class="comment">
					<#if post.commentStatus == 'open'>
						<@tldwidget.placeholder path="/comment?post=${post.id}" cache=false />
					</#if>
				</div>
			</div>
		<#else>
			<div class="main-content">
				<div class="main-posts main-item">
				404...  该文章没有找到
				</div>
			</div>
		</#if>
	</div>
<#include "footer.ftl" />