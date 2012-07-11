<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ss" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>M-Log Manager</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/script/extjs/resources/css/ext-all.css" />
  		<link rel="stylesheet" type="text/css" href="<%=path %>/admin/resources/main.css" />
  		
  		<script type="text/javascript" src="<%=path %>/common/scriptVariable.action"></script>
	    <script type="text/javascript" src="<%=path %>/script/extjs/adapter/ext/ext-base.js"></script>
	    <script type="text/javascript" src="<%=path %>/script/extjs/ext-all.js"></script>
	    <script type="text/javascript" src="<%=path %>/script/extjs/ux/TabCloseMenu.js"></script>
	    
	    <script type="text/javascript" src="<%=path %>/script/extjs/ux/TreePanelState.js"></script>
	    <script type="text/javascript" src="<%=path %>/script/extjs/ux/miframe.js"></script>
	    
	    <script type="text/javascript" src="<%=path %>/admin/js/MSpring.js"></script>
  	
	    <script type="text/javascript">
		    Ext.onReady(function(){
				Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
				Ext.QuickTips.init();//开启提示功能
				
				MSpring.initUI();
		    });
	    </script>
	</head>

	<body>
		<div id="header">
			<a href="<%=path %>/admin/logout.action" style="float:right;margin-right:10px;" title="安全退出系统">
				<img src="<%=path %>/admin/resources/images/exit.gif" style="width:83px;height:24px;margin-top:1px;"/>
			</a>
			<div class="top-title">M-Log 系统管理</div>
		</div>
		<div id="welcome">欢迎使用M-Log</div>
		<div id="bottom" style="text-align: center;padding-top: 10px;">
			Copyright (c) 2011 <a href="http://www.mspring.org" target="_blank">http://www.mspring.org</a>
		</div>
	</body>
</html>
