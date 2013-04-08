<#import "macro/macro.ftl" as macro />  
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
				            <li class="active"><a href="${blogurl}">首页</a></li>
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
				<@m.render_catalogList template="/widget/catalogList.ftl" parentId=catalogId />
				<@m.render_recentComment template="/widget/recentComment.ftl" maxResult=20 />
			</div>
			<div class="span9">
				<ul class="breadcrumb">
					<li>
				    	<a href="${blogurl}">首页</a> <span class="divider">/</span>
				  	</li>
				  	<li class="active">
				    	<a href="${base}">${catalog.name}</a>
				  	</li>
				</ul>
				<#if (postPage?exists && postPage.result?size > 0)>
					<#list postPage.result as post>
						<div class="media">
							<div class="media-body">
								<h5 class="media-heading">
									<a href="<@postUrl post="post" />" target="_blank">${post.title}</a>
									<#if post.isTop><sup><span class="label label-success">置顶</span></sup></#if>
								</h5>
								<@contentTransform content=post.content removeHtml=true substring=true endIndex=200 />
							</div>
						</div>
					</#list>
				</#if>
				<@macro.pagenavi />
			</div>
			<div class="span12" style="margin:0px;">
				<@m.render_link template="/widget/link.ftl"/>
			</div>
			<div class="span12" style="margin:0px;">
				<p>自豪的采用<a href="http://www.mspring.org" target="_blank">M-LOG</a>&nbsp;&nbsp;${copyright!""}</p>
			</div>
		<div>
		
<#include "footer.ftl"/>