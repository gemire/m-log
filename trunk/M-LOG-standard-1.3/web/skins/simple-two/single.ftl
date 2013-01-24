<#include "header.ftl" />
		<script type="text/javascript">mlog.stat.postClick('<@post_id />');</script>
		<div class="row-fluid">
			<#include "sidebar.ftl" />
			<div class="span9">
				<div class="row-fluid">
					<#if post?exists>
						<div class="post-entity">
							<div class="post-title"><@post_title /><#if post.isTop><sup class="tip">置顶！</sup></#if></div>
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
							<div class="post-content">
								<@post_content />
							</div>
							<div class="post-meta-bottom">
								<div class="post-relative-div">
                                    <div  class="post-relative">
                                        <h4>相关文章</h4>				
                                        <#assign catalogs=post.catalogs />
                                        <@sql_query var="about_posts" sql="select pc.PK.post from PostCatalog pc where pc.PK.catalog.name in (@catalogs.name) and pc.PK.post.id <> @post.id order by pc.PK.post.viewCount desc" max="10" expiry="3600000"/>
                                        <#if about_posts?exists>
                                        <ul>
                                            <#list about_posts as p>
                                            <li><a href="${base}${p.url}" target="_blank">${p.title}</a></li>
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
                                            <li><a href="${base}${p.url}" target="_blank">${p.title}</a></li>
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
							</div>
							<div class="line_dashed"></div>
							<div class="comment">
								<#if post.commentStatus == 'open'>
									<@tldwidget.placeholder path="/comment?post=${post.id}" cache=false />
								</#if>
							</div>
						</div>
					</#if>
				</div>
			</div>
		</div>
		

<#include "footer.ftl" />
