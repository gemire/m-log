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
				<span class="nav">
					<ss:text name="linkedit.createtitle" />
				<span>
			</div>
			<div id="divMain2">
				<form action="<%=path %>/admin/doCreateLink.action" id="linkForm" method="post">
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.name" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="link.name" type="text" class="textfield" validate="{required:true, maxlength:100, messages:{required:'请输入链接名称'} }" /> <span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.url" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="link.url" type="text" class="textfield" validate="{required:true,url:true, maxlength:100, messages:{required:'请输入链接地址'} }" /> <span class="spanerror">(*)</span>
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
										<option value='<ss:property value="id" />'><ss:property value="name" /></option>
									</ss:iterator>
								</select>
								<span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.imageurl" /></p>
							</td>
							<td style="width: 85%">
								<input size="40" name="link.imageurl" type="text" class="textfield"/>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.target" /></p>
							</td>
							<td style="width: 85%">
								<input type="radio" name="link.target" value="_self" title="_self" />_self&nbsp;&nbsp;&nbsp;
								<input type="radio" name="link.target" value="_blank" title="_blank" checked="checked" />_blank
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'><ss:text name="link.field.display" /></p>
							</td>
							<td style="width: 85%">
								<input type="radio" name="link.display" value="0" title='<ss:text name="linkedit.label.display" />' /><ss:text name="linkedit.label.display" />&nbsp;&nbsp;&nbsp;
								<input type="radio" name="link.display" value="1" title="<ss:text name="linkedit.label.hidden" />" checked="checked" /><ss:text name="linkedit.label.hidden" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" class="button" value='<ss:text name="button.submit" />' />
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
