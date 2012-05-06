<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
  
  <body>
  	<div id="divMain">
		<div class="Header">
			<ss:text name="linklist.title" />
		</div>
		<div id="divMain2">
			<!-- 
			<form id="edit" method="post" action="">
				<p><a href="<%=path %>/admin/toCreateLink.action">[<ss:text name="linklist.label.create" />]</a></p>
			</form>
			-->
			
			<form name="linkForm" method="post" action="<%=path %>/admin/queryLink.action">
				<!-- pagination parameter -->
				<input type="hidden" name="linkPage.pageNo" value='<ss:property value="linkPage.pageNo"/>'>
				<input type="hidden" name="linkPage.totalPages" value='<ss:property value="linkPage.totalPages" />' />
				<input type="hidden" name="linkPage.totalCount" value='<ss:property value="linkPage.totalCount" />' />
				
				<!-- sorter parameter -->
				<input type="hidden" name="linkPage.sortEnable" value='<ss:property value="linkPage.sortEnable"/>'>
				<input type="hidden" name="linkPage.sort.field" value='<ss:property value="linkPage.sort.field"/>'>
		   		<input type="hidden" name="linkPage.sort.order" value='<ss:property value="linkPage.sort.order"/>'>
			
				<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
					<tr>
						<th style="text-align:center;width:50px;"><input type="checkbox" onclick="checkAll(this,'id')" /></th>
				        <jsp:include page="../includes/sortabletablehead.jsp">
				        	<jsp:param name="formName" value="linkForm"/>
				        	<jsp:param name="entityName" value="linkPage" />
				        </jsp:include>
				        <th style="width:60px;"><ss:text name="label.edit" /></th>
		      		</tr>
					<ss:iterator id="link" value="linkPage.result" status="rowstatus">
						<tr>
							<td style="text-align:center;width:50px;"><input type="checkbox" name="id" value="<ss:property value="id" />" /></td>
							<td><ss:property value="id" /></td>
							<td><a href="<%=path %>/admin/toEditLink.action?id=<ss:property value="id" />" ><ss:property value="name" /></a></td>
							<td><ss:property value="url" /></td>
							<td><ss:property value="description" /></td>
							<td><ss:property value="target" /></td>
							<td><ss:if test="display"><ss:text name="label.yes" /></ss:if><ss:else><ss:text name="label.no" /></ss:else></td>
							<td align="center"><a href="<%=path %>/admin/toEditLink.action?id=<ss:property value="id" />">[<ss:text name="label.edit" />]</a></td>
						</tr>
					</ss:iterator>
				</table>
				
				<!-- control bar -->
			    <table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
			        <tr style="border:none;">
			        	<td align="right" nowrap class="tableControlBarText" height="26">
			        		<div style="float: left;">
			        			<input type="button" class="button" value="<ss:text name="button.deleteselect" />" onclick="submitForm(linkForm,'<%=path %>/admin/deleteLink.action');" />
			        		</div>
				            <jsp:include page="../includes/pagingnavigator.jsp">
				                <jsp:param name="formName" value="linkForm"/>
				                <jsp:param name="entityName" value="linkPage" />
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