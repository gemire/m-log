<#include "header.ftl" />
		<script type="text/javascript">mlog.stat.postClick('${post.id}');</script>
		<div class="row-fluid">
			<#include "sidebar.ftl" />
			<div class="span9">
				<div class="row-fluid">
					<#if post?exists>
						<ul class="breadcrumb">
							<li>
						    	<a href="${blogurl}">首页</a> <span class="divider">/</span>
						  	</li>
						  	<#if (post.catalogs?exists && post.catalogs?size > 0)>
								<li>
									分类：<#list post.catalogs as catalog><a href="${base}/catalog/catalog-${catalog.id}.html" rel="tag">${catalog.name}</a>&nbsp;&nbsp;</#list>
									<span class="divider">/</span>
								</li>
							</#if>
							<#if (post.tags?exists && post.tags?size > 0)>
								<li>
									标签：<#list post.tags as tag><a href="${base}/tag/tag-${tag.id}.html" rel="tag">${tag.name}</a>&nbsp;&nbsp;</#list>
									<span class="divider">/</span>
								</li>
							</#if>
						  	<li class="active">${post.title}</li>
						</ul>
						<div class="post-entity">
							<div class="post-title">${post.title}<#if post.isTop><sup class="tip">置顶！</sup></#if></div>
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
							<div class="post-content">
								${post.content}
							</div>
							
							<div class="post-meta-bottom">
								<div class="post-relative-div">
                                    <div  class="post-relative">
                                        <h4>相关文章</h4>				
                                        <#assign catalogs=post.catalogs />
                                        <@sql_query var="about_posts" sql="select pc.PK.post from PostCatalog pc where pc.PK.catalog.name in (@catalogs.name) and pc.PK.post.id <> @post.id order by pc.PK.post.viewCount desc" max="10" cache=true expiry="3600000"/>
                                        <#if about_posts?exists>
                                        <ul>
                                            <#list about_posts as p>
                                            <li><a href="<@postUrl post="p" />" target="_blank">${p.title}</a></li>
                                            </#list>
                                        </ul>
                                        </#if>
                                    </div>
                                    <div  class="post-relative">
                                        <h4>随机文章</h4>				
                                        <#assign tags=post.tags />
                                        <@sql_query var="rand_posts" sql="from Post post order by rand()" max="10" cache="true" />
                                        <#if rand_posts?exists>
                                        <ul>
                                            <#list rand_posts as p>
                                            <li><a href="<@postUrl post="p" />" target="_blank">${p.title}</a></li>
                                            </#list>
                                        </ul>
                                        </#if>
                                    </div>
                                </div>
                                
								<div class="post-copyright">
									如非注明，本站文章均为原创，转载请注明出处。<br/>
									本站地址：<a href="${blogurl}" target="_blank" title="${blogname}">${blogname}</a> <a href="${blogurl}" target="_blank" title="${blogname}">${blogurl}</a><br/>
									本文地址：<a href="<@postUrl post="post" />" target="_blank" title="${post.title}"><@absolute_post_url /></a><br/>
								</div>
                              
							</div>
							<div class="line_dashed"></div>
							<div class="comment">
								
								<@m.render_postComment template="/widget/postComment.ftl" post=post.id cache=false />
								
							</div>
						</div>
					<#else>
						<div class="alert alert-error" style="padding:30px;font-size:24px;">
							404, 没找到您想要的文章。
						</div>
					</#if>
				</div>
			</div>
		</div>
<#include "footer.ftl" />
