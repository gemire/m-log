<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN" lang="zh_CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="Content-Language" content="zh_CN" />
	<link rel="stylesheet" rev="stylesheet" href="${template_url}/style/style.css" type="text/css" media="screen" />
	<#-- <link rel="stylesheet" rev="stylesheet" href="<#ZC_BLOG_HOST#>css/print.css" type="text/css" media="print" /> -->
	<!--[if lt IE 7]>
    <style type="text/css" media="all"> @import "${template_url}/style/fix-ie.css";</style>
    <![endif]-->
	<title>${blogname}</title>
</head>
<body class="multi default">
<!-- Layout -->
<div id="header-region" class="clear-block"></div>
	<div id="wrapper">
		<div id="container" class="clear-block">
	    	<div id="header">
	        	<div id="logo-floater">
	        		<h1><a href="${base}" title="${blogname}"><img src="${template_url}/images/logo.png" alt="${blogname}" id="logo"><span>${blogname}</span></a></h1>
	        	</div>
	         	<ul class="links primary-links"><@widget.placeholder path="/widget/menus" cache=false /></ul> 
	      	</div><!-- /header -->
	      	
	      	<div id="center">
	      		<div id="squeeze">
	      			<div class="right-corner">
	      				<div class="left-corner">
	      					<div id="mission">${notice}</div>