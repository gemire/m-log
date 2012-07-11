<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
	<style type="text/css">
	.photo_name {
		padding: 3px;
		font: 8pt "Tahoma";
		color: #444444;
		background: #ddd;
		vertical-align: bottom;
		text-align: center;
		height: 15px;
	}
	
	.album_list ul {
		list-style: none;
		width: 100%;
		border: none;
		float: left
	}
	
	.album_list li {
		width: 140px;
		height: 140px;
		float: left;
		text-align: center;
		position: relative
	}
	
	.album_list li table img {
		width: 120px;
		height: 100px;
		border: 1px solid #ddd;
		padding: 5px;
		background: #f0f0f0;
	}
	</style>

	<body>
		<div id="divMain">
			<div class="Header">
				我的相册
			</div>
			<div class="SubMenu" id="SubMenu">
			
			</div>
			<div id="divMain2">
				<div class="album_list">
					<ul>
						<ss:iterator id="album" value="albumPage.result">
							<li>
								<table cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<a href="<%=path%>/admin/photo/queryPhoto.action?albumId=<ss:property value="id" />"
												alt="进入 <ss:property value="name" />"><img
													src="<%=path%>/admin/resources/images/noface.jpg"
													border="0" />
											</a>
										</td>
									</tr>
									<tr>
										<td class="photo_name">
											<a href="<%=path%>/admin/photo/queryPhoto.action?albumId=<ss:property value="id" />" alt="进入 <ss:property value="name" />"><ss:property value="name" />
											<a href="<%=path %>/admin/photo/deleteAlbum.action?id=<ss:property value="id" />">删除</a>
											</a>
										</td>
									</tr>
								</table>
							</li>
						</ss:iterator>
					</ul>
				</div>
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
<%@include file="../includes/footer.jsp"%>
