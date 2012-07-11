<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
  <body>
    <div id="divMain">
		<div class="Header"><ss:text name="categorylist.title" /></div>
		<div class="SubMenu"></div>
		<div id="divMain2">
			<form id="edit" method="post" action="">
				<p><ss:text name="categorylist.label.create" />: </p><p><a href="<%=path %>/admin/menu/toCreateMenu.action">[<ss:text name="categorylist.label.createlink" />]</a></p>
			</form>
			
			<form name="menuForm" method="post" action="<%=path %>/admin/menu/queryCategory.action">
				<!-- pagination parameter -->
				<input type="hidden" name="menuPage.pageNo" value='<ss:property value="menuPage.pageNo"/>'>
				<input type="hidden" name="menuPage.totalPages" value='<ss:property value="menuPage.totalPages" />' />
				<input type="hidden" name="menuPage.totalCount" value='<ss:property value="menuPage.totalCount" />' />
				
				<!-- sorter parameter -->
				<input type="hidden" name="menuPage.sortEnable" value='<ss:property value="menuPage.sortEnable"/>'>
				<input type="hidden" name="menuPage.sort.field" value='<ss:property value="menuPage.sort.field"/>'>
		   		<input type="hidden" name="menuPage.sort.order" value='<ss:property value="menuPage.sort.order"/>'>
				
			
				<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
					<tr>
						<th style="text-align:center;width:50px;"><input type="checkbox" onclick="checkAll(this,'menuItems')" /></th>
				        <jsp:include page="../includes/sortabletablehead.jsp">
				        	<jsp:param name="formName" value="menuForm"/>
				        	<jsp:param name="entityName" value="menuPage" />
				        </jsp:include>
				        <th style="width:60px;"><ss:text name="global.field.edit" /></th>
		      		</tr>
					<ss:iterator id="cate" value="menuPage.result" status="rowstatus">
						<tr>
							<td style="text-align:center;width:50px;"><input type="checkbox" name="menuItems" value="<ss:property value="id" />" /></td>
							<td><ss:property value="id" /></td>
							<td><a href="<%=path %>/admin/menu/toEditMenu.action?id=<ss:property value="id" />" ><ss:property value="name" /></a></td>
							<td>
								<ss:if test="type == 0">系统菜单</ss:if>
								<ss:elseif test="type == 1">分类菜单</ss:elseif>
								<ss:elseif test="type == 2">固定链接</ss:elseif>
							</td>
							<td><ss:property value="parent" /></td>
							<td><ss:property value="url" /></td>
							<td><ss:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
							<td align="center">
								<ss:if test="type != 0">
									<a href="<%=path %>/admin/menu/toEditMenu.action?menuId=<ss:property value="id" />">[<ss:text name="label.edit" />]</a>
								</ss:if>
							</td>
						</tr>
					</ss:iterator>
				</table>
				
				<!-- control bar -->
			    <table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
			        <tr style="border:none;">
			        	<td align="right" nowrap class="tableControlBarText" height="26">
			        		<div style="float: left;">
			        			<input type="button" class="button" value="<ss:text name="button.deleteselect" />" onclick="deleteMenu();" />
			        		</div>
				            <jsp:include page="../includes/pagingnavigator.jsp">
				                <jsp:param name="formName" value="menuForm"/>
				                <jsp:param name="entityName" value="menuPage" />
				            </jsp:include>
			            </td>
			        </tr>
			   </table> 
				
			</form>
		</div>
	</div>
  </body>
  
  <script>
  		function deleteMenu(){
  			if(hasChecked("menuItems")){
  				confirmDelete(menuForm,'<%=path %>/admin/menu/deleteMenu.action');
  			}
  		}
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
