<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
  	<script type="text/javascript">
		function deleteCategory(id){
			if(window.confirm("<ss:text name="confirm.okcancel" />")){
				document.categoryForm.action = "<%=path %>/admin/category/deleteCategory.action?id=" + id;
				document.categoryForm.submit();
			}
		}
	</script>
  
  <body>
    <div id="divMain">
		<div class="Header"><ss:text name="categorylist.title" /></div>
		<div class="SubMenu"></div>
		<div id="divMain2">
			<form id="edit" method="post" action="">
				<p><ss:text name="categorylist.label.create" />: </p><p><a href="<%=path %>/admin/category/toCreateCategory.action">[<ss:text name="categorylist.label.createlink" />]</a></p>
			</form>
			
			<form name="categoryForm" method="post" action="<%=path %>/admin/category/queryCategory.action">
				<!-- pagination parameter -->
				<input type="hidden" name="categoryPage.pageNo" value='<ss:property value="categoryPage.pageNo"/>'>
				<input type="hidden" name="categoryPage.totalPages" value='<ss:property value="categoryPage.totalPages" />' />
				<input type="hidden" name="categoryPage.totalCount" value='<ss:property value="categoryPage.totalCount" />' />
				
				<!-- sorter parameter -->
				<input type="hidden" name="categoryPage.sortEnable" value='<ss:property value="categoryPage.sortEnable"/>'>
				<input type="hidden" name="categoryPage.sort.field" value='<ss:property value="categoryPage.sort.field"/>'>
		   		<input type="hidden" name="categoryPage.sort.order" value='<ss:property value="categoryPage.sort.order"/>'>
				
			
				<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
					<tr>
						<th style="text-align:center;width:50px;"><input type="checkbox" onclick="checkAll(this,'cbCategory')" /></th>
				        <jsp:include page="../includes/sortabletablehead.jsp">
				        	<jsp:param name="formName" value="categoryForm"/>
				        	<jsp:param name="entityName" value="categoryPage" />
				        </jsp:include>
				        <th style="width:60px;"><ss:text name="global.field.edit" /></th>
		      		</tr>
					<ss:iterator id="cate" value="categoryPage.result" status="rowstatus">
						<tr>
							<td style="text-align:center;width:50px;"><input type="checkbox" name="cbCategory" value="<ss:property value="id" />" /></td>
							<td><ss:property value="id" /></td>
							<td><a href="<%=path %>/admin/category/toEditCategory.action?id=<ss:property value="id" />" ><ss:property value="name" /></a></td>
							<td><ss:property value="order" /></td>
							<td><ss:property value="intro" /></td>
							<td align="center"><a href="<%=path %>/admin/category/toEditCategory.action?id=<ss:property value="id" />">[<ss:text name="label.edit" />]</a></td>
						</tr>
					</ss:iterator>
				</table>
				
				<!-- control bar -->
			    <table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
			        <tr style="border:none;">
			        	<td align="right" nowrap class="tableControlBarText" height="26">
			        		<div style="float: left;">
			        			<input type="button" class="button" value="<ss:text name="button.deleteselect" />" onclick="submitForm(categoryForm,'<%=path %>/admin/category/deleteCategory.action');" />
			        		</div>
				            <jsp:include page="../includes/pagingnavigator.jsp">
				                <jsp:param name="formName" value="categoryForm"/>
				                <jsp:param name="entityName" value="categoryPage" />
				            </jsp:include>
			            </td>
			        </tr>
			   </table> 
				
			</form>
		</div>
	</div>
  </body>
  
  <script>
		$(document).ready(function(){
			//斑马线
			var tables=document.getElementsByTagName("table");
			var b=false;
			for (var j = 0; j < tables.length; j++){
		
				var cells = tables[j].getElementsByTagName("tr");
		
				cells[0].className="color1";
				for (var i = 1; i < cells.length; i++){
					if(b){
						cells[i].className="color2";
						b=false;
					}
					else{
						cells[i].className="color3";
						b=true;
					};
				};
			}
		
		});
	</script>
<%@include file="../includes/footer.jsp" %>
