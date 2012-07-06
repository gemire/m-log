<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			validateForm("categoryForm");
		});
	</script>

	<body>
		<div id="divMain">
			<div class="Header">
				<span class="navigate"><a href="<%=path %>/admin/category/queryCategory.action"><ss:text name="categorylist.title" /></a>&gt;&gt;<a href="javascript:void(0);"><ss:text name="categoryedit.createtitle" /></a><span>
			</div>
			<div class="SubMenu"></div>
			<div id="divMain2">
				<form action="<%=path %>/admin/category/doCreateCategory.action" id="categoryForm" method="post">
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="category.field.name" /></p>
							</td>
							<td style="width: 85%">
								<input id="edtName" size="40" name="category.name" type="text" class="textfield" validate="{required:true,ajaxCheck:['<%=path %>/admin/category/categoryWhetherRepeat.action','name'],messages:{required:'请输入分类名称',ajaxCheck:'分类名称已存在'}}" /><span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="category.field.order" /></p>
							</td>
							<td style="width: 85%">
								<input id="edtOrder" size="40" name="category.order" type="text" class="textfield" validate="{digits:true}" />
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="category.field.description" /></p>
							</td>
							<td style="width: 85%">
								<input id="edtAlias" size="80" name="category.intro" type="text" class="textfield" />
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
