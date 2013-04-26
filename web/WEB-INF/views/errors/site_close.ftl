<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>站点维护中</title>
	<meta name="description" content="${description!""}" />
	<meta name="keywords" content="${keyword!""}" />
	
	<link rel="alternate" type="application/rss+xml" href="${base}/rss.xml" title="${blogname}" />
	<link rel="alternate" type="application/atom+xml" href="${base}/atom.xml" title="${blogname}" />
	<link rel="Shortcut Icon" href="${base}/images/favicon.ico">
	<link rel="canonical" href="${blogurl!""}" />
	
	<script type="text/javascript" src="${base}/script/jquery.js" charset="utf-8"></script>
	<style type="text/css">
	    a{color:#a2a2a2;text-decoration:none;}
	    #container{width:600px;margin:100px auto;border:1px dashed #a2a2a2;padding:10px;}
    </style>
</head>
<body>
	<div id="container">
		<div id="desc" style="font-size:16px;font-weight:bold;margin:10px auto;color:#827f7a">
			当前站点正在维护中，请您稍后访问...
			<a href="javascript:void(0);" onclick="document.getElementById('error').style.display='block'" style="font-size:12px;font-weight:normal">详情&#62;</a>
		</div>
		<div id="error" style="font-size:12px;color:#a2a2a2;display:none;margin:10px auto;">
			系统正在维护中，给您带来不变敬请谅解。
		</div> 
		<div id="footer" style="text-align:right;font-size:12px;color:#c7c9c6;">
			Powered By <a href="${blogurl}" target="_blank">${blogname}</a>
		<div>
	</div>
	<div style="display:none">
	</div>
</body>
</html>