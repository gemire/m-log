<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="mspring" uri="/WEB-INF/mspring.tld" %>

<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.mspring.core.xwork.interceptor.ExceptionInterceptor"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>Errors</title>
    <style>
        .j,.j a:visited,.j a:link, .j a:hover,.j a:active{text-decoration:underline;cursor:hand;cursor:pointer;}
    </style>
    <script language="javascript" src="<%=request.getContextPath()%>/script/common.js"></script>
</head>

<body>
    
    <table>
        <tr>
            <td>错误类型:
            <% 
                ActionContext actionContext = ActionContext.getContext();
                String message = (String) actionContext.get(ExceptionInterceptor.EXCEPTION_MESSAGE_KEY);
                if (message == null || message.trim().length() == 0) {
                    out.print("未知错误!");
                } else {
                    out.print(message);
                }
            %>
            </td>
        </tr>
        
        <tr>
            <td><span class="j" onClick="return toggleDisplay('stacktrace')">错误详细信息:</span></td>
        </tr>    
        
        <tr id="stacktrace" style="display:none">
            <td><%= actionContext.get(ExceptionInterceptor.EXCEPTION_STACKTRACE_KEY) %></td>
        </tr>    
    </table>
    
</body>
</html>
