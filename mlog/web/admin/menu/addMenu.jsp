<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.mspring.mlog.common.MenuType"%>
<%@include file="../includes/header.jsp" %>
	<body>
		<div id="divMain">
			<div class="Header">
				<span class="navigate"><a href="<%=path %>/admin/menu/queryMenu.action">菜单管理</a>&gt;&gt;创建菜单<span>
			</div>
			<div class="SubMenu"></div>
			<div id="divMain2">
				<form action="<%=path %>/admin/menu/doCreateMenu.action" method="post">
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 15%'>
								<p align='left'>类型</p>
							</td>
							<td style="width: 85%">
								<input type="radio" name="menu.type" onclick="changeMenuType();" value="<%=MenuType.CATEGORY %>" checked="checked"/>分类菜单
								&nbsp;&nbsp;&nbsp;
								<input type="radio" name="menu.type" onclick="changeMenuType();" value="<%=MenuType.URL %>"/>固定链接
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'>父级菜单</p>
							</td>
							<td style="width: 85%">
								<select name="menu.parent">
									<option value="">--请选择--</option>
									<ss:iterator var="parentMenu" value="parentmenus">
										<option value="<ss:property value="id" />"><ss:property value="name" /></option>
									</ss:iterator>
								</select>
							</td>
						</tr>
						<tr name="categoryItem">
							<td style='width: 15%'>
								<p align='left'>分类</p>
							</td>
							<td style="width: 85%">
								<select name="menu.category">
									<option value="">--请选择--</option>
									<ss:iterator var="category" value="categories">
										<option value="<ss:property value="id" />"><ss:property value="name" /></option>
									</ss:iterator>
								</select>
							</td>
						</tr>
						
						<tr name="urlItem" style="display: none;">
							<td style='width: 15%'>
								<p align='left'>菜单名称</p>
							</td>
							<td style="width: 85%">
								<input size="40" name="menu.name" type="text" class="textfield" />
							</td>
						</tr>
						<tr name="urlItem" style="display: none;">
							<td style='width: 15%'>
								<p align='left'>固定链接</p>
							</td>
							<td style="width: 85%">
								<input size="40" name="menu.url" type="text" class="textfield"/>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'>排序</p>
							</td>
							<td style="width: 85%">
								<input size="40" name="menu.order" type="text" class="textfield"/>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'>描述</p>
							</td>
							<td style="width: 85%">
								<input size="40" name="menu.description" type="text" class="textfield"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" class="button" value="提    交" />
								<input type="reset" class="button" value="重    置" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		//切换菜单
		function changeMenuType(){
			var radios = document.getElementsByName("menu.type");
			var radio_value = "";
			for(var i = 0; i < radios.length; i++){
				if(radios[i].checked){
					radio_value = radios[i].value;
					break;
				}
			}
			/*
			var categoryItems = document.getElementsByName("categoryItem");
			var urlItems = document.getElementsByName("urlItem");
			*/
			var categoryItems = $("table").find("tr[name=categoryItem]");
			var urlItems = $("table").find("tr[name=urlItem]");
			if(radio_value == "<%=MenuType.CATEGORY %>"){
				for(var i = 0; i < categoryItems.length; i++){
					categoryItems[i].style.display = "";
				}
				for(var i = 0; i < urlItems.length; i++){
					urlItems[i].style.display = "none";
				}
			}
			else{
				for(var i = 0; i < categoryItems.length; i++){
					categoryItems[i].style.display = "none";
				}
				for(var i = 0; i < urlItems.length; i++){
					urlItems[i].style.display = "block";
				}
			}
		}
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
