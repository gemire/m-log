<!DOCTYPE html>
<!--[if IE 6]>
<html class="ie6" dir="ltr" lang="zh-CN">
<![endif]-->
<!--[if IE 7]>
<html class="ie7" dir="ltr" lang="zh-CN">
<![endif]-->
<!--[if IE 8]>
<html class="ie8" dir="ltr" lang="zh-CN">
<![endif]-->
<!--[if !(IE 6) | !(IE 7) | !(IE 8)  ]><!-->
<html dir="ltr" lang="zh-CN">
<!--<![endif]-->
<head>
    <meta charset="UTF-8" />
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width" />
	<title><@mlog_title /></title>
	<link rel="alternate" type="application/rss+xml" href="${base}/rss.xml" title="${blogname}" />
	<link rel="alternate" type="application/atom+xml" href="${base}/atom.xml" title="${blogname}" />
	<link rel="Shortcut Icon" href="${base}/images/favicon.ico">
	<meta name="description" content="${description!""}" />
	<meta name="keywords" content="${keyword!""}" />
	<link rel="canonical" href="${blogurl!""}" />
	<link rel="stylesheet" rev="stylesheet" href="${template_url}/style/style.css" type="text/css" media="screen" />
	<script type="text/javascript" src="${base}/script/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script/jquery-scrollto/jquery.scrollto.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script_variable.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script/mlog.utils.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script/mlog.stat.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script/custom.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//加载
			mlog.load({
				contentSelector : ".post-summary"
			});
		});
	</script>
<#-- <#if currentPage == 'single'><body class="theme single-post"><#else><body></#if> -->
<body>
	<div class="bg"></div>
	<div class="navbar navbar-fixed-top">
		<div class="container navbar-inner">
            <h1 class="nav-title">
                <a href="${blogurl}">${blogname}</a>
            </h1>
            <ul class="nav-menu">
            	<div class="menu"></div>
			</ul>
			<!--
			<div class="navbar-col" title="切换显示咧">
                <a class="navbar-col2-demo ib" title="两栏" rel="two" href="javascript:;">
                    
                </a>
                <a class="navbar-col3-demo ib" title="三栏" rel="three" href="javascript:;">
                </a>
            </div>
            -->
            <div class="navbar-search">
                <form action="${base}/search" method="get" id="searchForm">
					<input type="text" value="${searchKeyword!""}" placeholder="请输入搜索的关键字" name="keyword">
					<button type="submit"></button>
                </form>
            </div>
        </div>
	</div>