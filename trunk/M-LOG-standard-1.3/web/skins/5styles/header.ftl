<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN" lang="zh_CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="Content-Language" content="zh_CN" />
	<title><@mlog_title /></title>
	<link rel="alternate" type="application/rss+xml" href="${base}/rss.xml" title="${blogname}" />
	<link rel="alternate" type="application/atom+xml" href="${base}/atom.xml" title="${blogname}" />
	<link rel="stylesheet" rev="stylesheet" href="${template_url}/style/style.css" type="text/css" media="screen" />
	<!--[if lte IE 6]>
	<script src="http://letskillie6.googlecode.com/svn/trunk/letskillie6.zh_CN.pack.js"></script>
	<![endif]-->
	
	<script type="text/javascript" src="${base}/script/jquery.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script/script_variable.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script/mlog.utils.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/script/custom.js" charset="utf-8"></script>
	<script type="text/javascript" src="${template_url}/script/5style.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//加载
			mlog.load({
				contentSelector : ".post .entry"
			});
		});
	</script>
</head>
<body class="multi default">
<div id="wrap">
		<div id="header">
			<div id="header-left">
				<h1><a href="${base}">${blogname}</a></h1>
				<div class="description">${blogsubname}</div>
			</div>
			<div id="header-right">
				<div class="layout" id="StyleController">
				</div>

				<div id="menu">
					<@m.widget path="/widget/menus" cache=false />
				</div>
			</div>
		</div>
        <div id="main">
			<div id="maincontent">
	        	<div class="forFlow">