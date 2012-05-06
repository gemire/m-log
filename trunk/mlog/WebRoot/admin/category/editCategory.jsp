<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#categoryForm").validate();
		});
	</script>

	<body>
		<div id="divMain">
			<div class="Header">
				<ss:text name="categoryedit.edittitle" />
				<span class="nav"><a href="<%=path %>/admin/category/queryCategory.action"><ss:text name="categorylist.title" /></a>&gt;&gt;<a href="javascript:void(0);"><ss:text name="categoryedit.edittitle" /></a><span>
			</div>
			<div class="SubMenu">
			</div>
			<div id="divMain2">
				<form action="<%=path %>/admin/category/editCategory.action" id="categoryForm" name="categoryForm" method="post">
					<input name="category.id" type="hidden" value="<ss:property value="category.id" />" />
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="category.field.name" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="category.name" type="text" value="<ss:property value="category.name" />" class="textfield" />
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="category.field.order" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="category.order" type="text" value="<ss:property value="category.order" />" class="textfield" />
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="category.field.description" /></p>
							</td>
							<td style="width: 85%">
								<input size="80" name="category.intro" type="text" value="<ss:property value="category.intro" />" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" class="button" value=" <ss:text name="button.submit" /> " />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(document).ready(function(){
			//斑马线
			var tables=document.getElementsByTagName("table");
			var b=false;
			for (var j = 0; j < tables.length; j++){
		
				var cells = tables[j].getElementsByTagName("tr");
		
				//cells[0].className="color3";
				b=false;
				for (var i = 0; i < cells.length; i++){
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
