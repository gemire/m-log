<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.mspring.mlog.common.Const"%>
<%@page import="org.mspring.mlog.entity.security.User"%>
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
							<%=((User)session.getAttribute(Const.SESSION_LOGINUSER)).getName() %>
						</td>
						<td width="20%">
							当前版本
						</td>
						<td width="30%">
							1.0
						</td>
					</tr>
					<tr>
						<td width="20%">
							文章总数
						</td>
						<td width="30%">
							<ss:property value="overviewMap.articleCount"/>
						</td>
						<td width="20%">
							评论总数
						</td>
						<td width="30%">
							<ss:property value="overviewMap.commentCount"/>
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
							<ss:property value="overviewMap.tagCount"/>
						</td>
						<td width="20%">
							分类总数
						</td>
						<td width="30%">
							<ss:property value="overviewMap.categoryCount"/>
						</td>
					</tr>
					<tr>
						<td width="20%">
							当前主题/当前样式
						</td>
						<td width="30%">
							<ss:property value="overviewMap.currentTheme"/>
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
							http://localhost:8080/mlog/metaweblog.action
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
