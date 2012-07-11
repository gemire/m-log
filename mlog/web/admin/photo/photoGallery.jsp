<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=path %>/script/extjs/ux/css/fileuploadfield.css" />
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/Reorderer.js"></script>
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/ToolbarReorderer.js"></script>
	
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.queue.js"></script>
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.speed.js"></script>
	<script type="text/javascript" src="<%=path %>/script/swfupload/fileprogress.js"></script>
	
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/FileUploadField.js"></script>
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/DataView-more.js"></script>
	
	<%
		String baseUrl = request.getContextPath();
		String index = request.getParameter("index");
	%>
	<script type="text/javascript">
		// 当前查看图片下标
		var currentIndex = "<%=index%>";
		alert(window.parent.pictureListStore);
		
	</script>

	<body>
		
	</body>
<%@include file="../includes/footer.jsp" %>
