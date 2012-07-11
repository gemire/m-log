<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			validateForm("createTagForm");
		});
	</script>
	<body>
		<div id="divMain">
			<div class="Header"><ss:text name="taglist.title" /></div>
			<div class="SubMenu"></div>
			
			<div id="divMain2">
				<form id="createTagForm" method="post" action="<%=path %>/admin/createTag.action">
					<table class="formtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<td class="formtabletdcontent" style="width: 100%" colspan="4">
								<p align="center"><ss:text name="taglist.label.create" /><p>
							</td>
						</tr>
						<tr>
							<td class="formtabletdtitle" style="width: 15%">
								<p align='right'><ss:text name="tag.field.name" /></p>
							</td>
							<td class="formtabletdcontent" style="width: 35%">
								<input type="text" name="tag.name" id="tag_name" class="textfield" style="width: 90%" validate="{required:true,ajaxCheck:['<%=path %>/admin/tag/tagWhetherRepeat.action','name'],messages:{required:'请输入标签名称',ajaxCheck:'该标签已经存在'}}" /><span class="spanerror">(*)</span>
							</td>
							<td class="formtabletdtitle" style="width: 15%">
								<p align='right'><ss:text name="tag.field.intro" /></p>
							</td>
							<td class="formtabletdcontent" style="width: 35%">
								<input type="text" class="textfield" name="tag.intro" style="width: 95%" />
							</td>
						</tr>
						<tr>
							<td class="formtabletdcontent" style="width: 100%" colspan="4">
								<p align="center">
									<input type="submit" value="<ss:text name="button.create" />" class="button" />
								<p>
							</td>
						</tr>
					</table>
				</form>
				
				<form name="tagForm" method="post" enctype="application/x-www-form-urlencoded" action="<%=path %>/admin/queryTag.action">
					<!-- pagination parameter -->
					<input type="hidden" name="tagPage.pageNo" value='<ss:property value="tagPage.pageNo"/>'>
					<input type="hidden" name="tagPage.totalPages" value='<ss:property value="tagPage.totalPages" />' />
					<input type="hidden" name="tagPage.totalCount" value='<ss:property value="tagPage.totalCount" />' />
					
					<!-- sorter parameter -->
					<input type="hidden" name="tagPage.sortEnable" value='<ss:property value="tagPage.sortEnable"/>'>
					<input type="hidden" name="tagPage.sort.field" value='<ss:property value="tagPage.sort.field"/>'>
			   		<input type="hidden" name="tagPage.sort.order" value='<ss:property value="tagPage.sort.order"/>'>
			   		
					<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<th style="text-align:center;width:50px;"><input type="checkbox" onclick="checkAll(this,'tagItems')" /></th>
					        <jsp:include page="../includes/sortabletablehead.jsp">
					        	<jsp:param name="formName" value="tagForm"/>
					        	<jsp:param name="entityName" value="tagPage" />
					        </jsp:include>
			      		</tr>
						<ss:iterator id="tag" value="tagPage.result" status="rowstatus">
							<tr>
								<td style="text-align:center;width:50px;"><input type="checkbox" name="tagItems" value="<ss:property value="id" />" /></td>
								<td><ss:property value="id" /></td>
								<td><a href="<%=path %>/admin/toEditArticle.action?id=<ss:property value="id" />"><ss:property value="name" /></a></td>
								<td><ss:property value="order" /></td>
							</tr>
						</ss:iterator>
					</table>
					<!-- control bar -->
				    <table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				        <tr style="border:none;">
				        	<td align="right" nowrap class="tableControlBarText" height="26">
				        		<div style="float: left;">
				        			<input type="button" class="button" value="<ss:text name="button.deleteselect" />" onclick="submitForm(tagForm,'<%=path %>/admin/deleteTag.action');" />
				        		</div>
					            <jsp:include page="../includes/pagingnavigator.jsp">
					                <jsp:param name="formName" value="tagForm"/>
					                <jsp:param name="entityName" value="tagPage" />
					            </jsp:include>
				            </td>
				        </tr>
				   </table>
			   </form>
			</div>
		</div>
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
	</body>
<%@include file="../includes/footer.jsp" %>

