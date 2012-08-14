<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
		<title>${blogname}</title>
		<meta name="author" content="B3log Team" />
		<meta name="generator" content="mlog" />
		<meta name="copyright" content="mlog" />
		<meta http-equiv="Window-target" content="_top" />
		<link rel="alternate" type="application/rss+xml" href="${base}/rss.xml" title="${blogname}" />
		<link rel="alternate" type="application/atom+xml" href="${base}/atom.xml" title="${blogname}" />
		<link rel="stylesheet" rev="stylesheet" href="${template_url}/style/neoease.css" type="text/css" media="screen" />
		
		<script type="text/javascript" src="${base}/script/jquery.js" charset="utf-8"></script>
		<script type="text/javascript" src="${base}/script/custom.js" charset="utf-8"></script>
		<script type="text/javascript" src="${template_url}/script/neoease.js" charset="utf-8"></script>
	</head>
	<body>
		<div class="header">
		    <div class="wrapper">
		        <div class="left">
		            <h1>
		                <a class="title" href="${base}">
		                    ${blogname}
		                </a>
		            </h1>
		            <span class="sub-title">${blogsubname}</span>
		        </div>
		        <form method="get" action="${base}/search">
		            <input id="search" type="text" name="keyword" value="<@search_keyword />" />
		        </form>
		        <div class="clear"></div>
		    </div>
		</div>
		<div class="nav">
		    <div class="wrapper">
		        <ul>
		            <@widget.placeholder path="/widget/menus" cache=false />
		        </ul>
		        <div class="right">
		            <#--<span class="translate-ico" onclick="goTranslate()"></span>-->
		            <div class="right">
						浏览次数：
		                <span class="tip"><@stat_click_count /></span>
		                &nbsp;&nbsp;
						文章数量：
		                <span class="tip"><@stat_post_count /></span>
		                &nbsp;&nbsp;
						评论数量：
		                <span class="tip"><@stat_comment_count /></span>
		            </div>
		        </div>
		        <div class="clear"></div>
		    </div>
		</div>