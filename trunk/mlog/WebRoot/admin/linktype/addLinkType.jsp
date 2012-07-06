<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			validateForm("linkTypeForm");
		});
	</script>
	<body>
		<div id="divMain">
			<div class="Header">
				<span class="navigate">
					<a href="<%=path %>/admin/queryLinkType.action"><ss:text name="linktypelist.title" /></a>&gt;&gt;
					<a href="javascript:void(0);" class="current"><ss:text name="linktype.createtitle" /></a>
				<span>
			</div>
			<div class="SubMenu"></div>
			<div id="divMain2">
				<form action="<%=path %>/admin/doCreateLinkType.action" id="linkTypeForm" method="post">
					<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="linktype.field.name" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="linkType.name" type="text" class="textfield" validate="{required:true, max:200, messages:{required:'请输入链接分类名称'}}" /><span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="linktype.field.description" /></p>
							</td>
							<td style="width: 85%">
								<input size="100" name="linkType.description" type="text" class="textfield" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" class="button" value='<ss:text name="button.submit" />' />
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
