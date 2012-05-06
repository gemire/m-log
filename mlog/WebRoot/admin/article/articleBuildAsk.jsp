<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
  <body>
  	<div id="divMain">
		<div class="Header">
			<ss:text name="articlerebuild.title" />
		</div>
		<div class="SubMenu"></div>
		<div id="divMain2">
			<form id="edit" name="edit" method="post"
				action="<%=path %>/admin/article/buildArticle.action">
				<p>
					<ss:text name="articlerebuild.ask" />
				</p>
				<p>
					<input class="button" type="submit" value="提交" id="btnPost" />
				</p>
			</form>
		</div>
	</div>
  </body>
<%@include file="../includes/footer.jsp" %>