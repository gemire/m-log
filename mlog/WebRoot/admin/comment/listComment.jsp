<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<script type="text/javascript" src="<%=path %>/script/datepicker/WdatePicker.js" charset="utf-8"></script>
	<body>
		<div id="divMain">
			<div class="Header"><ss:text name="commentlist.title" /></div>
			<div class="SubMenu">
				
			</div>
			<form name="commentForm" id="commentForm" method="post" action="<%=path %>/admin/comment/queryComment.action">
				<!-- pagination parameter -->
				<input type="hidden" name="commentPage.pageNo" value='<ss:property value="commentPage.pageNo"/>'>
				<input type="hidden" name="commentPage.totalPages" value='<ss:property value="commentPage.totalPages" />' />
				<input type="hidden" name="commentPage.totalCount" value='<ss:property value="commentPage.totalCount" />' />
				
				<!-- sorter parameter -->
				<input type="hidden" name="commentPage.sortEnable" value='<ss:property value="commentPage.sortEnable"/>'>
				<input type="hidden" name="commentPage.sort.field" value='<ss:property value="commentPage.sort.field"/>'>
		   		<input type="hidden" name="commentPage.sort.order" value='<ss:property value="commentPage.sort.order"/>'>
				
				<div id="divMain2">
					<table class="formtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<td class="formtabletdtitle">
								<p align="right"><ss:text name="commentlist.label.author" /></p>
							</td>
							<td class="formtabletdcontent">
								<p><input name="comment.author" value='<ss:property value="comment.author" />' text="text" class="textfield" style="width: 95%" /></p>
							</td>
							
							<td class="formtabletdtitle">
								<p align="right"><ss:text name="commentlist.label.content" /></p>
							</td>
							<td class="formtabletdcontent">
								<p><input name="comment.content" value='<ss:property value="comment.content" />' text="text" class="textfield" style="width: 95%" /></p>
							</td>
						</tr>
						<tr>
							<td class="formtabletdtitle">
								<p align="right"><ss:text name="commentlist.label.createtime.beg" /></p>
							</td>
							<td class="formtabletdcontent">
								<p><input name="comment.createTime.begVal" value='<ss:property value="comment.createTime.begVal" />' text="text" class="datefield" style="width: 95%" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" /></p>
							</td>
							<td class="formtabletdtitle">
								<p align="right"><ss:text name="commentlist.label.createtime.end" /></p>
							</td>
							<td class="formtabletdcontent">
								<p><input name="comment.createTime.endVal" value='<ss:property value="comment.createTime.endVal" />' text="text" class="datefield" style="width: 95%" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" /></p>
							</td>
						</tr>
						<tr>
							<td class="formtabletdtitle">
								<p align="right"><ss:text name="commentlist.label.status" /></p>
							</td>
							<td class="formtabletdcontent">
								<select name="comment.status" id="comment_status" style="width: 95%">
									<option value=""><ss:text name="select.option.pleaseselect" /></option>
									<option value="0"><ss:text name="comment.status.check" /></option>
									<option value="1"><ss:text name="comment.status.pass" /></option>
									<option value="2"><ss:text name="comment.status.reject" /></option>
									<option value="3"><ss:text name="comment.status.dust" /></option>
								</select>
								<script type="text/javascript">
									var status = '<ss:property value="comment.status" />';
									var options = document.getElementById("comment_status").options;
									for(var i = 0; i < options.length; i++){
										if(options[i].value == status){
											options[i].selected = "selected";
										}
									}
								</script>
							</td>
							
							<td class="formtabletdtitle">
							</td>
							<td class="formtabletdcontent">
							</td>
						</tr>
						<tr>
							<td class="formtabletdbutton" colspan="4">
								<input type="submit" class="button" value="<ss:text name="button.submit" />" />
							</td>
						</tr>
					</table>
					
					<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<th style="text-align:center;width:50px;"><input type="checkbox" onclick="checkAll(this,'commentItems')" /></th>
					        <jsp:include page="../includes/sortabletablehead.jsp">
					        	<jsp:param name="formName" value="commentForm"/>
					        	<jsp:param name="entityName" value="commentPage" />
					        </jsp:include>
			      		</tr>
						<ss:iterator id="comment" value="commentPage.result" status="rowstatus">
							<tr>
								<td style="text-align:center;width:50px;"><input type="checkbox" name="commentItems" value="<ss:property value="id" />" /></td>
								<td><ss:property value="id" /></td>
								<td><ss:property value="author" /></td>
								<td><ss:property value="createTime"/></td>
								<td><ss:property value="ip" /></td>
								<td><ss:property value="content" /></td>
								<td>
									<ss:if test="status == 0"><ss:text name="comment.status.check" /></ss:if>
									<ss:elseif test="status == 1"><ss:text name="comment.status.pass" /></ss:elseif>
									<ss:elseif test="status == 2"><ss:text name="comment.status.reject" /></ss:elseif>
									<ss:elseif test="status == 3"><ss:text name="comment.status.dust" /></ss:elseif>
								</td>
							</tr>
						</ss:iterator>
					</table>
					<!-- control bar -->
				    <table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				        <tr style="border:none;">
				        	<td align="right" nowrap height="26">
				        		<div style="float: left;">
				        			<input type="button" class="button" value="<ss:text name="button.deleteselect" />" onclick="deleteSelected();" />
				        			<input type="button" class="button" value="<ss:text name="comment.status.pass" />" onclick="auditPass();" />
				        			<input type="button" class="button" value="<ss:text name="comment.status.reject" />" onclick="auditReject();" />
				        			<input type="button" class="button" value="<ss:text name="comment.status.dust" />" onclick="auditDust();" />
				        		</div>
					            <jsp:include page="../includes/pagingnavigator.jsp">
					                <jsp:param name="formName" value="commentForm"/>
					                <jsp:param name="entityName" value="commentPage" />
					            </jsp:include>
				            </td>
				        </tr>
				   </table>
				</div>
			</form>
		</div>
		<script>
			
			function checkSelected(){
				var items = document.getElementsByName("commentItems");
				var flag = true;
				for(var i = 0; i < items.length; i++){
					if(items[i].checked){
						flag = false;
						break;
					}
				}
				return flag;
			}
			
			
			//删除选中
			function deleteSelected(){
				var flag = checkSelected();
				
				if(flag){
					alert("请选择要删除的条目");
				}
				else{
					if(confirm("确定删除选中项吗？删除之后将无法恢复。")) 
						submitForm(commentForm,'<%=path %>/admin/comment/deleteComment.action');
				}
			}
			
			//审核通过
			function auditPass(){
				var flag = checkSelected();
				if(flag){
					alert("请选择审核条目");
				}
				else {
					submitForm(commentForm, '<%=path %>/admin/comment/auditPass.action');
				}
			}
			//审核驳回
			function auditReject(){
				var flag = checkSelected();
				if(flag){
					alert("请选择审核条目");
				}
				else {
					submitForm(commentForm, '<%=path %>/admin/comment/auditReject.action');
				}
			}
			//垃圾评论
			function auditDust(){
				var flag = checkSelected();
				if(flag){
					alert("请选择审核条目");
				}
				else {
					submitForm(commentForm, '<%=path %>/admin/comment/auditDust.action');
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
	</body>
<%@include file="../includes/footer.jsp" %>
