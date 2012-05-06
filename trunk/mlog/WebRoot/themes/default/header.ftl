<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN" lang="zh_CN">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="Content-Language" content="zh_CN" />
		<title>${mspring_config_base_title} ${mspring_config_base_secondtitle}</title>
		<link rel="stylesheet" type="text/css" href="${stylesheet_url}"  media="screen" />
		<link rel="stylesheet" type="text/css" href="${stylesheet_url}" media="print" />
		<link rel="alternate" type="application/rss+xml" href="${path}/rss.xml" title="${mspring_config_base_title}" />
		<link rel="alternate" type="application/atom+xml" href="${path}/atom.xml" title="${mspring_config_base_title}" />
		<link rel="shortcut icon" href="${template_url}/images/favicon.ico" />
		${widget("HeaderWidget")}
	</head>
	<body class="multi default">
		${widget("AdminBarWidget")}
		<!-- Layout -->
		<div id="header-region" class="clear-block"></div>
		<div id="wrapper">
			<div id="container" class="clear-block">
				<div id="header">
					<div id="logo-floater">
						<h1>
							<a href="${path}" title="${mspring_config_base_title}"><img src="${path}/themes/${mspring_config_base_theme}/images/logo.png" alt="${mspring_config_base_title}" id="logo"><span>${mspring_config_base_title}</span></a>
						</h1>
					</div>
					<div id="primary-menu">
						<ul class="links primary-links">
							${widget("MenuWidget")}
						</ul>
					</div>
				</div>
				<!-- /header -->
				<div id="center">
					<div id="squeeze">
						<div class="right-corner">
							<div class="left-corner">
								<div id="mission"></div>