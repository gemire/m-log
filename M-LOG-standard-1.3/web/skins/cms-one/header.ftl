<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><@mlog_title /> ${blogsubname}</title>
<link rel="alternate" type="application/rss+xml" href="${base}/rss.xml" title="${blogname}" />
<link rel="alternate" type="application/atom+xml" href="${base}/atom.xml" title="${blogname}" />
<link rel="Shortcut Icon" href="${base}/images/favicon.ico">
<meta name="description" content="${description!""}" />
<meta name="keywords" content="${keyword!""}" />
<link rel="canonical" href="${blogurl!""}" />

<link href="${base}/script/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${base}/script/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<script type="text/javascript" src="${base}/script/jquery.js"></script>
</head>
<body>
    <#--
    <style type="text/css">
	body { padding-top: 60px;}
	</style>
    <div class="navbar navbar-fixed-top navbar-inverse">
  		<div class="navbar-inner">
    		<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            		<span class="icon-bar"></span>
            		<span class="icon-bar"></span>
            		<span class="icon-bar"></span>
          		</a>
          		<a class="brand" href="./index.html">JavaOS</a>
          		<div class="nav-collapse">
          			<ul class="nav">
	              		<li class="">
	                		<a href="./index.html">概述</a>
	                	</li>
	              		<li class="">
	            	</ul>
	            	<ul class="nav pull-right">
	            		<li class="">
			                <a href="#">登录</a>
						</li>
						<li class="divider-vertical"></li>
	            	</ul>
          		</div>
			</div>
		</div>
	</div>
    -->
	
	
	<header class="jumbotron subhead" id="overview">
  		<div class="container">
		    <h1><a href="${blogurl}" title="${blogname}">${blogname}</a></h1>
		    <p class="lead">${blogsubname}</p>
  		</div>
	</header>