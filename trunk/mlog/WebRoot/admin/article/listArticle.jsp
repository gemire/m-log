<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<script type="text/javascript" src="<%=path %>/script/datepicker/WdatePicker.js" charset="utf-8"></script>
	<body>
		<div id="divMain">
			<div class="Header"><ss:text name="articlelist.title" /></div>
			<div class="SubMenu">
			</div>
			<form name="articleForm" method="post" enctype="application/x-www-form-urlencoded" action="<%=path %>/admin/queryArticle.action">
				<!-- pagination parameter -->
				<input type="hidden" name="articlePage.pageNo" value='<ss:property value="articlePage.pageNo"/>'>
				<input type="hidden" name="articlePage.totalPages" value='<ss:property value="articlePage.totalPages" />' />
				<input type="hidden" name="articlePage.totalCount" value='<ss:property value="articlePage.totalCount" />' />
				
				<!-- sorter parameter -->
				<input type="hidden" name="articlePage.sortEnable" value='<ss:property value="articlePage.sortEnable"/>'>
				<input type="hidden" name="articlePage.sort.field" value='<ss:property value="articlePage.sort.field"/>'>
		   		<input type="hidden" name="articlePage.sort.order" value='<ss:property value="articlePage.sort.order"/>'>
				
				<div id="divMain2">
					<table class="formtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<td class="formtabletdtitle" style="width: 15%">
								<p align="right"><ss:text name="articlelist.label.title" /></p>
							</td>
							<td class="formtabletdcontent" style="width: 35%">
								<input name="article.title" value='<ss:property value="article.title" />' text="text" class="textfield" style="width: 95%" />
							</td>
							
							<td class="formtabletdtitle" style="width: 15%;">
								<p align="right"><ss:text name="articlelist.label.category" /></p>
							</td>
							<td class="formtabletdcontent" style="width: 35%">
								<select id="category_id" name="article.category.id" style="width: 95%" >
									<option value=""><ss:text name="select.option.any" /></option>
									<ss:iterator id="cat" value="categories">
										<option value='<ss:property value="id" />' <ss:if test="id == article.category.id">selected="selected"</ss:if>><ss:property value="name" /></option>
									</ss:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td class="formtabletdtitle" style="width: 15%">
								<p align="right"><ss:text name="articlelist.label.createtime.beg" /></p>
							</td>
							<td class="formtabletdcontent" style="width: 35%">
								<input name="article.createTime.begVal" value='<ss:property value="article.createTime.begVal" />' text="text" class="datefield" style="width: 95%" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" />
							</td>
							
							<td class="formtabletdtitle" style="width: 15%">
								<p align="right"><ss:text name="articlelist.label.createtime.end" /></p>
							</td>
							<td class="formtabletdcontent" style="width: 35%">
								<input name="article.createTime.endVal" value='<ss:property value="article.createTime.endVal" />' text="text" class="datefield" style="width: 95%" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" />
							</td>
						</tr>
						<tr>
							<td class="formtabletdtitle" style="width: 15%">
								<p align="right"><ss:text name="articlelist.label.type" /></p>
							</td>
							<td class="formtabletdcontent" style="width: 35%">
								<select style="width: 95%" >
									<option value=""><ss:text name="select.option.any" /></option>
								</select>
							</td>
							
							<td class="formtabletdtitle" style="width: 15%">
							</td>
							<td class="formtabletdcontent" style="width: 35%">
							</td>
						</tr>
						<tr>
							<td class="formtabletdbutton" colspan="4">
								<input type="submit" class="button" value="<ss:text name="button.submit" />">
							</td>
						</tr>
					</table>
					
					<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<th style="text-align:center;width:50px;"><input type="checkbox" onclick="checkAll(this,'articleItems')" /></th>
					        <jsp:include page="../includes/sortabletablehead.jsp">
					        	<jsp:param name="formName" value="articleForm"/>
					        	<jsp:param name="entityName" value="articlePage" />
					        </jsp:include>
					        <th style="width:60px;"><ss:text name="global.field.edit" /></th>
			      		</tr>
						<ss:iterator id="article" value="articlePage.result" status="rowstatus">
							<tr>
								<td style="text-align:center;width:50px;"><input type="checkbox" name="articleItems" value="<ss:property value="id" />" /></td>
								<td><ss:property value="id" /></td>
								<td>
									<ss:iterator id="category" value="categories" status="item">
										<ss:property value="name"/>
										<%--如果不是最后一个, 加添","--%>
										<ss:if test="!#item.last">,</ss:if>
									</ss:iterator>
								</td>
								<td><a href="<%=path %>/admin/toEditArticle.action?id=<ss:property value="id" />"><ss:property value="title" /></a></td>
								<td><ss:property value="createTime" /></td>
								<td><ss:property value="viewNums" /></td>
								<td><ss:if test="isTop == 1"><ss:text name="articlelist.label.top" /></ss:if></td>
								<td align="center"><a href="<%=path %>/admin/toEditArticle.action?id=<ss:property value="id" />">[<ss:text name="label.edit" />]</a></td>
							</tr>
						</ss:iterator>
					</table>
					<!-- control bar -->
				    <table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				        <tr style="border:none;">
				        	<td align="right" nowrap class="tableControlBarText" height="26">
				        		<div style="float: left;">
				        			<input type="button" class="button" value="<ss:text name="button.deleteselect" />" onclick="submitForm(articleForm,'<%=path %>/admin/deleteArticle.action');" />
				        		</div>
					            <jsp:include page="../includes/pagingnavigator.jsp">
					                <jsp:param name="formName" value="articleForm"/>
					                <jsp:param name="entityName" value="articlePage" />
					            </jsp:include>
				            </td>
				        </tr>
				   </table> 
				</div>
			</form>
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
						
						$(cells[i]).attr("nowrap","nowrap");
					};
				}
			});
		</script>
	</body>
<%@include file="../includes/footer.jsp" %>
