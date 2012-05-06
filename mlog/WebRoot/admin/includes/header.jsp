<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mspring" uri="/WEB-INF/mspring.tld" %>
<%@ taglib prefix="ss" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/REC-html40/strict.dtd">
<!--
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
-->
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Manage</title>
		<link rel="stylesheet" rev="stylesheet" href="<%=path%>/admin/resources/admin.css" type="text/css" media="screen" />
		<!--[if IE]><link rel="stylesheet" href="<%=path%>/admin/resources/admin-ie.css" type="text/css" /><![endif]-->
		<link rel="stylesheet" href="<%=path%>/css/jquery.bettertip.css" type="text/css" media="screen" />
		
		<script language="JavaScript" src="<%=path %>/script/jquery/jquery.js" type="text/javascript"></script>
		<script language="JavaScript" src="<%=path %>/script/jquery/plugin/jquery.bettertip.pack.js" type="text/javascript"></script>
		<script language="JavaScript" src="<%=path %>/script/jquery/plugin/jquery-validation/jquery.validate.js" type="text/javascript"></script>
		<script language="JavaScript" src="<%=path %>/script/jquery/plugin/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
		
		<!-- extjs -->
		<link rel="stylesheet" type="text/css" href="<%=path %>/script/extjs/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=path %>/script/extjs/adapter/ext/ext-base.js"></script>
	    <script type="text/javascript" src="<%=path %>/script/extjs/ext-all.js"></script>
		
	    <script type="text/javascript" src="<%=path %>/script/lhgdialog/lhgdialog.js"></script>
	    
	    <script language="JavaScript" src="<%=path %>/admin/js/common.js" type="text/javascript"></script>
	    
	    <script type="text/javascript" src="<%=path %>/admin/js/tree-loader.js"></script>
		<script type="text/javascript" src="<%=path %>/admin/js/frame.js"></script>
		
		<script type="text/javascript">
			$(function(){
				BT_setOptions({openWait:250, closeWait:0, cacheEnabled:true});
			})
		</script>
		<%@include file="scripts.jsp" %>
	</head>