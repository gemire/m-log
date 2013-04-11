<#include "header.ftl" />
		<div class="navbar">
		    <div class="navbar-inner">
		      	<div class="container">
		        	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		          		<span class="icon-bar"></span>
		          		<span class="icon-bar"></span>
		          		<span class="icon-bar"></span>
		        	</a>
		        	<div class="nav-collapse">
			          	<ul class="nav">
				            <li class="active"><a href="#">首页</a></li>
				            <li><a href="#">开源软件</a></li>
				            <li><a href="#">代码分享</a></li>
				            <li><a href="${base}/catalog/catalog-31.html">资讯</a></li>
				            <li><a href="#">讨论</a></li>
		          		</ul>
		          		<form class="navbar-search pull-right" action="">
		            		<input type="text" class="search-query span2" placeholder="搜索">
		          		</form>
		        	</div>
		      	</div>
	    	</div>
		</div>
		
		<div class="row-fluid">
			<div class="span3">
				<@m.render_catalogList template="/widget/catalogList.ftl" />
				<@m.render_recentComment template="/widget/recentComment.ftl" maxResult=20 />
			</div>
			<div class="span9">
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
				<#if post?exists>
					<div class="media">
						<div class="media-body">
							<h3 class="media-heading">${post.title}<#if post.isTop><sup><span class="label label-success">置顶</span></sup></#if></h3>
							${post.content}
						</div>
					</div>
					<@m.render_postComment template="/widget/postComment.ftl" post=post.id cache=false />
				</#if>
			</div>
			<div class="span12" style="margin:0px;">
				<@m.render_link template="/widget/link.ftl"/>
			</div>
			<div class="span12" style="margin:0px;">
				<p>自豪的采用<a href="http://www.mspring.org" target="_blank">M-LOG</a>&nbsp;&nbsp;${copyright!""}</p>
			</div>
		<div>
		
<#include "footer.ftl"/>