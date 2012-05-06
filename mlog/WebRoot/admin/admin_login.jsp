<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="org.mspring.mlog.common.Const"%>
<%@ taglib prefix="ss" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<title><ss:text name="login.title" /></title>
		<link rel="stylesheet" rev="stylesheet" href="<%=path%>/admin/resources/admin.css" type="text/css" media="screen" />
		<link rel="stylesheet" rev="stylesheet" href="<%=path%>/admin/resources/login.css" type="text/css" media="screen" />
		
		<script language="JavaScript" src="<%=path %>/script/jquery/jquery.js" type="text/javascript"></script>
		<script language="JavaScript" src="<%=path %>/admin/js/common.js" type="text/javascript"></script>
		
		<style type="text/css">
			span{
				font-size: 12px;
			}
		</style>
		
		<script type="text/javascript">
			if(window.top.document != document){
	    		window.top.location = document.location;
	    	}
			<%
	    	if(session.getAttribute(Const.SESSION_LOGINUSER) != null){
		        %>
		        window.top.location = "admin_default.jsp";
		        <%
		    }
		    %>
	    </script>
	</head>
	<body style="background:#fbfbfb!important;">
		<div id="login">
			<h1><a href="#" title="">M-LOG 管理登录</a></h1> 
			<form accept-charset="utf-8" action="<%=path %>/admin/login.action" method="post">
				<p>
					<label>帐号：</label>
					<input class="input" name="username" size="20" type="text" value="gaoyoubo" />
				</p>
				<p>
					<label>密码：</label>
					<input class="input" name="password" size="20" type="password" value="gaoyoubo" />
				</p>
				<p>
					<label>验证码：</label>
					<input class="input" name="validateCode" size="20" type="text" />
				</p>
				<p>
					<img style="border: 1px solid black" src="<%=path %>/common/validateCode.action" />
				</p>
				<ss:if test="message != null">
					<font color="red"><ss:property value="message" /></font>
				</ss:if>
				<p class="submit">
					<input class="button-primary" name="commit" type="submit" value="登录" />
				</p>
			</form>
		</div>
	  	<div id="loading-mask" style=""></div>
	  	<div id="loading">
	    	<div class="loading-indicator"><img src="<%=path %>/admin/loading.gif" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>Loading...</div>
	  	</div>
	  	<script type="text/javascript">
		    $(document).ready(function(){
		    	loadend();
			});
	    </script>
	</body>
</html>