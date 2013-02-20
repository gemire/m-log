<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	<script type="text/javascript" src="${base}/script/script_variable.js" charset="utf-8"></script>
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
</head>
<body>
	<a name="top"></a>

	<!--done-->
	<div id="home">
		<div id="header">
			<div id="blogTitle">
				<a id="lnkBlogLogo" href="${blogurl}">
					<img id="blogLogo" src="/Skins/custom/images/logo.gif" alt="返回主页" />
				</a>

				<!--done-->
				<h1>
					<a id="Header1_HeaderTitle" class="headermaintitle" href="${blogurl}">${blogname}</a>
				</h1>
				<h2></h2>
			</div>
			<!--end: blogTitle 博客的标题和副标题 -->
			<div id="navigator">
				<ul id="navList">
					<@m.widget path="/widget/menus" />
				</ul>
				<div class="blogStats">
					<!--done-->
					文章总数- <@stat_post_count />&nbsp;
					评论总数- <@stat_comment_count />&nbsp;
					总点击量- <@stat_click_count />&nbsp;
				</div><!--end: blogStats -->
			</div><!--end: navigator 博客导航栏 -->
		</div><!--end: header 头部 -->
			