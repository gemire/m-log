<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<body>
		<div id="divMain">
			<div class="Header">
				信息摘要
			</div>
			<div class="SubMenu">
				<div id="topmenu"></div>
				<!-- 
				<span class="m-left"><a href="../PLUGIN/ThemeSapper/Xml_ChkVer.asp"><font color="red">!!发现主题的可用更新</font>
				</a>
				</span>
				-->
			</div>
			<div id="divMain2">
				<table class="gridtable" border="0" cellspacing="0" cellpadding="0" align="center"
					width="100%" class="tableBorder">
					<tr>
						<th height=25 colspan=4 align="center">
							&nbsp;站内统计摘要
						</th>
					</tr>
					<tr>
						<td width="20%">
							当前用户
						</td>
						<td width="30%">
							gaoyoubo (管理员)
						</td>
						<td width="20%">
							当前版本
						</td>
						<td width="30%">
							1.8 Walle Build 91204
						</td>
					</tr>
					<tr>
						<td width="20%">
							文章总数
						</td>
						<td width="30%">
							89
						</td>
						<td width="20%">
							评论总数
						</td>
						<td width="30%">
							6
						</td>
					</tr>
					<tr>
						<td width="20%">
							引用总数
						</td>
						<td width="30%">
							0
						</td>
						<td width="20%">
							浏览总数
						</td>
						<td width="30%">
							40
						</td>
					</tr>
					<tr>
						<td width="20%">
							Tags总数
						</td>
						<td width="30%">
							7
						</td>
						<td width="20%">
							分类总数
						</td>
						<td width="30%">
							1
						</td>
					</tr>
					<tr>
						<td width="20%">
							当前主题/当前样式
						</td>
						<td width="30%">
							简单黑白主题 / black
						</td>
						<td width="20%">
							用户总数
						</td>
						<td width="30%">
							1
						</td>
					</tr>
					<tr>
						<td width="20%">
							MetaWeblog API
						</td>
						<td colspan="3" width="80%">
							http://localhost/xml-rpc/index.asp
						</td>
					</tr>
				</table>
			</div>
		</div>
		<script type="text/javascript">
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
