<!DOCTYPE html>
<html lang="zh-cn"><head>
        <meta charset="utf-8">
        <title><@mlog_title /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="alternate" type="application/rss+xml" href="${base}/rss.xml" title="${blogname}" />
		<link rel="alternate" type="application/atom+xml" href="${base}/atom.xml" title="${blogname}" />
		<link rel="Shortcut Icon" href="${base}/images/favicon.ico">
		<meta name="keywords" content="${keyword!""}" />
		<meta name="description" content="${description!""}" />

        <link href="${template_url}/style/bootstrap.min.css" rel="stylesheet">
        <link href="${template_url}/style/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="${template_url}/style/skin.css" rel="stylesheet">
        
        <script type="text/javascript" src="${base}/script/jquery.js" charset="utf-8"></script>
		<script type="text/javascript" src="${base}/script/jquery-scrollto/jquery.scrollto.js" charset="utf-8"></script>
		<script type="text/javascript" src="${base}/script/script_variable.js" charset="utf-8"></script>
		<script type="text/javascript" src="${base}/script/mlog.utils.js" charset="utf-8"></script>
		<script type="text/javascript" src="${base}/script/mlog.stat.js" charset="utf-8"></script>
		<script type="text/javascript" src="${base}/script/custom.js" charset="utf-8"></script>
        <!--[if lt IE 9]>
        <script src="${template_url}/script/html5.js"></script>
        <![endif]-->
        <script type="text/javascript">
			$(document).ready(function(){
				//加载
				mlog.load({
					contentSelector : "body"
				});
			});
		</script>
    <body>
        <div class="main-body">
			
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container-fluid">
						<a class="brand" href="${blogurl}">${blogname}</a>
						<div class="nav-collapse">
							<ul class="nav">
								<@m.render_menu template="/widget/menus.ftl" />
							</ul>
							<form class="navbar-search pull-right" action="${base}/search" method="get">
					            <input type="text" class="search-query span2" value="${searchKeyword!""}" placeholder="请输入搜索的关键字" name="keyword">
					        </form>
						</div>
					</div>
				</div>
			</div>
			<div class="container-fluid">