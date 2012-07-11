<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN" lang="zh_CN">

<head profile="http://gmpg.org/xfn/11">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="Content-Language" content="zh_CN" />
	<title>${mspring_config_base_title} ${mspring_config_base_secondtitle}</title>
	<link rel="stylesheet" href="${stylesheet_url}" type="text/css" media="screen" />
	<link rel="stylesheet" href="${template_url}/css/print.css" type="text/css" media="print" />
	<!--[if IE]><link rel="stylesheet" href="${template_url}/css/ie.css" type="text/css" /><![endif]-->
	<script type="text/javascript" src="${template_url}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${template_url}/js/drop-downs.js?ver=1.4"></script>
	<script type="text/javascript" src="${template_url}/js/common.js"></script>
</head>

<body>
<div id="page">
	<div id="top">
		<div id="menu" class="menu">
			<ul id="menu-primary-items" class="menu-item">
				<#list menus as menu>
					<#if menu.type == 1> <!-- category -->
						<li id="menu-item-${menu.id}" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-${menu.id}"><a href="${path}/category/${menu.category.id}">${menu.name}</a></li>
					<#elseif menu.type == 2> <!-- url -->
						<li id="menu-item-${menu.id}" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-${menu.id}"><a href="${menu.url}">${menu.name}</a></li>
					<#else> <!-- system -->
						<li id="menu-item-${menu.id}" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-${menu.id}"><a href="${path}/${menu.url}">${menu.name}</a></li>
					</#if>
				</#list>
			</ul>
		</div>
		
		<div id="header">
			<div id="headertitle">
				<h1><a href="${path}" title="${mspring_config_base_title}">${mspring_config_base_title}</a></h1>
				<p>${mspring_config_base_secondtitle}</p>
			</div>
			<!-- Search box (If you prefer having search form as a sidebar widget, remove this block) -->
			<div class="search">
				<#include "searchform.ftl"/>
			</div> 
			<!-- Search ends here-->
		</div>
	</div>
	<div id="wrapper">
