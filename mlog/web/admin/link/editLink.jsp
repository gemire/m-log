<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			validateForm("linkForm");
		});
	</script>
	<body>
		<div id="divMain">
			<div class="Header">
				<!-- <ss:text name="linkedit.edittitle"></ss:text> -->
				<span class="navigate">
					<a href="<%=path %>/admin/queryLink.action"><ss:text name="linklist.title" /></a>&gt;&gt;
					<a href="javascript:void(0);" class="current"><ss:text name="linkedit.edittitle" /></a>
				<span>
			</div>
			<div class="SubMenu"></div>
			<div id="divMain2">
				<form action="<%=path %>/admin/doEditLink.action" id="linkForm" method="post">
					<input type="hidden" name="link.id" value='<ss:property value="link.id" />' />
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.name" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="link.name" type="text" class="textfield" value='<ss:property value="link.name" />' validate="{required:true, maxlength:100, messages:{required:'请输入链接名称'} }" /> <span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.url" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="link.url" type="text" class="textfield" value='<ss:property value="link.url" />' validate="{required:true,url:true, maxlength:100, messages:{required:'请输入链接地址'} }" /> <span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.linktype" /></p>
							</td>
							<td style="width: 85%">
								<select name="link.type.id" validate="{required:true, messages: {required: '请选择链接分类'}}">
									<option value=""><ss:text name="select.option.pleaseselect" /></option>
									<ss:iterator id="linkType" value="linkTypes">
										<option value='<ss:property value="id" />' <ss:if test="id == link.type.id">selected="selected"</ss:if>><ss:property value="name" /></option>
									</ss:iterator>
								</select> <span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.imageurl" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="link.imageurl" type="text" class="textfield" value='<ss:property value="link.imageurl" />'/>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.target" /></p>
							</td>
							<td style="width: 85%">
								<input type="radio" name="link.target" value="_self" title="_self" <ss:if test="link.target == '_self'">checked="checked"</ss:if> />_self&nbsp;&nbsp;&nbsp;
								<input type="radio" name="link.target" value="_blank" title="_blank" <ss:if test="link.target == '_blank'">checked="checked"</ss:if> />_blank
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.display" /></p>
							</td>
							<td style="width: 85%">
								<input type="radio" name="link.display" value="false" title='<ss:text name="linkedit.label.hidden" />' <ss:if test="!link.display">checked="checked"</ss:if> /><ss:text name="linkedit.label.hidden" />&nbsp;&nbsp;&nbsp;
								<input type="radio" name="link.display" value="true" title='<ss:text name="linkedit.label.display" />' <ss:if test="link.display">checked="checked"</ss:if> /><ss:text name="linkedit.label.display" />
								<!-- 
								<input type="radio" name="Link_dis_NAME" onclick="changeDisplay(0);" id="link_dis_no" title="<ss:text name="linkedit.label.hidden" />" /><ss:text name="linkedit.label.hidden" />&nbsp;&nbsp;&nbsp;
								<input type="radio" name="Link_dis_NAME" onclick="changeDisplay(1);" id="link_dis_yes" title="<ss:text name="linkedit.label.display" />" /><ss:text name="linkedit.label.display" />
								<input type="hidden" name="link.display" id="link_display" value='<ss:property value="link.display" />' />
								<script type="text/javascript">
									var dis = '<ss:property value="link.display" />';
									if(dis == '1'){
										document.getElementById("link_dis_yes").checked = "checked";
									}
									else {
										document.getElementById("link_dis_no").checked = "checked";
									}
									function changeDisplay(dis){
										document.getElementById("link_display").value = dis;
									}
								</script>
								-->
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" class="button" value="<ss:text name="button.submit" />" />
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
