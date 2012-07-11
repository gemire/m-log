<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.mspring.mlog.entity.Album"%>
<%@include file="../includes/header.jsp" %>
	
	<script type="text/javascript">
		$(document).ready(function(){
			validateForm("albumForm");
		});
	</script>

	<body>
		<div id="divMain">
			<div class="Header">
				创建相册
			</div>
			<div class="SubMenu"></div>
			<div id="divMain2">
				<form action="<%=path %>/admin/album/doCreateAlbum.action" id="albumForm" method="post">
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 15%'>
								<p align='left'>·相册名称</p>
							</td>
							<td style="width: 85%">
								<input size="40" name="album.name" type="text" class="textfield" validate="{required:true, messages:{required:'请输入相册名字'}}" /><span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'>·隐私设置</p>
							</td>
							<td style="width: 85%">
								<input type="radio" name="album.type" onclick="changeAlbumType()" value="<%=Album.TYPE_PUBLIC %>" checked="checked"/>完全公开<br/>
								<input type="radio" name="album.type" onclick="changeAlbumType()" value="<%=Album.TYPE_PRIVATE %>"/>私人相册<br/>
								<input type="radio" name="album.type" onclick="changeAlbumType()" value="<%=Album.TYPE_VERIFIED %>"/>需要密码访问
								<input type="text" name="album.verifycode" id="album_verifycode" style="width: 130px;" disabled="disabled" validate="{required:true, messages:{required:'请输入相册密码'}}" />
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'>·排序</p>
							</td>
							<td style="width: 85%">
								<input size="40" name="album.sortOrder" type="text" class="textfield" validate="{digits:true}" />
							</td>
						</tr>
						<tr>
							<td style='width: 15%'>
								<p align='left'>·相册描述</p>
							</td>
							<td style="width: 85%">
								<input size="40" name="album.description" type="text" class="textfield" />
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
		//控制相册验证码框是否可用
		function changeAlbumType(){
			var radios = document.getElementsByName("album.type");
			var radio_value = "";
			for(var i = 0; i < radios.length; i++){
				if(radios[i].checked){
					radio_value = radios[i].value;
					break;
				}
			}
			var verifycode = document.getElementById("album_verifycode");
			if(radio_value == "<%=Album.TYPE_VERIFIED%>"){
				verifycode.disabled = "";
			}
			else{
				verifycode.disabled = "disabled";
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
