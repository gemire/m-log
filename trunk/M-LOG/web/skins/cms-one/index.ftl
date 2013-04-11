<#include "header.ftl" />
		<div class="navbar">
		    <div class="navbar-inner">
		      	<div class="container">
		        	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		          		<span class="icon-bar"></span>
		          		<span class="icon-bar"></span>
		          		<span class="icon-bar"></span>
		        	</a>
		        	<#--<a class="brand" href="#">项目名称</a>-->
		        	<div class="nav-collapse">
			          	<ul class="nav">
				            <li class="active"><a href="#">首页</a></li>
				            <li><a href="#">开源软件</a></li>
				            <li><a href="#">代码分享</a></li>
				            <li><a href="${base}/catalog/catalog-31.html">资讯</a></li>
				            <li><a href="#">讨论</a></li>
		          		</ul>
		          		<#--
		          		<ul class="nav pull-right">
		          			<li class="divider-vertical"></li>
				            <li class="dropdown">
				              	<a href="#" class="dropdown-toggle" data-toggle="dropdown">我的空间 <b class="caret"></b></a>
				              	<ul class="dropdown-menu">
					                <li><a href="#">消息</a></li>
					                <li><a href="#">博客</a></li>
					                <li><a href="#">留言</a></li>
					                <li><a href="#">个人资料</a></li>
				              	</ul>
			            	</li>
			            	<li class="divider-vertical"></li>
		          		</ul>
		          		-->
		          		<form class="navbar-search pull-right" action="">
		            		<input type="text" class="search-query span2" placeholder="搜索">
		          		</form>
		        	</div>
		      	</div>
	    	</div>
		</div>
		
		<div class="row-fluid">
			<div class="span3">
				<#-- <@m.render_catalogArchive template="/widget/catalogArchive.ftl" catalog=3 /> -->
				<@m.render_catalogList template="/widget/catalogList.ftl" />
				<@m.render_recentComment template="/widget/recentComment.ftl" maxResult=20 />
			</div>
			<div class="span9">
				<div class="span6">
					<ul class="nav nav-list">
						<li class="nav-header">最新咨询</li>
						<@m.render_recentCatalogPost template="/widget/recentPost.ftl" catalog=2 />
					</ul>
				</div>
				<div class="span6">
					<ul class="nav nav-list">
						<li class="nav-header">最高点击咨询</li>
						<@m.render_mostViewCatalogPost template="/widget/mostViewPost.ftl" catalog=2 />
					</ul>
				</div>
				<div class="span12" style="margin:5px 0px 5px 0px;">
					<@m.render_catalogArchive template="/widget/listDetails.ftl" catalog=5 cache=false />
				</div>
			</div>
			<div class="span12" style="margin:0px;">
				<@m.render_link template="/widget/link.ftl"/>
			</div>
			<div class="span12" style="margin:0px;">
				<p>自豪的采用<a href="http://www.mspring.org" target="_blank">M-LOG</a>&nbsp;&nbsp;${copyright!""}</p>
			</div>
		<div>
		
<#include "footer.ftl"/>