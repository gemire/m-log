<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			validateForm("albumConfigForm");
		});
	</script>
	<body>
		<div id="divMain">
			<div class="Header">
				相册设置
			</div>
			<form id="albumConfigForm" method="post" action="<%=path %>/admin/album/doEditAlbumConfig.action">
				<div id="divMain2">
					<div title="基础设置" tabid="基础设置">
						<table class='gridtable' width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
							<tr>
								<td style='width: 32%'><p align='left'>·单次上传最大文件数</p></td>
								<td style="width: 68%">
									<p><input name="albumConfig.mspring_album_limit" style="width: 95%" type="text" class="textfield" value="<ss:property value="albumConfig.mspring_album_limit" />" validate="{required:true, digits:true, max:30, min:5 }" /></p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'>·是否限制图片大小</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="albumConfig.mspring_album_islimit_size" type="radio" value="1" <ss:if test="albumConfig.mspring_album_islimit_size == 1">checked="checked"</ss:if>>是&nbsp;&nbsp;
										<input name="albumConfig.mspring_album_islimit_size" type="radio" value="0" <ss:if test="albumConfig.mspring_album_islimit_size == 0">checked="checked"</ss:if>>否&nbsp;&nbsp;
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'>·图片大小限制(PX)</p>
								</td>
								<td style="width: 68%">
									<p>
										宽度：<input name="albumConfig.mspring_album_islimit_width" type="text" class="textfield" value='<ss:property value="albumConfig.mspring_album_islimit_width" />' validate="{required:true, digits:true, max:2000, min:0 }" />
										高度：<input name="albumConfig.mspring_album_islimit_height" type="text" class="textfield" value='<ss:property value="albumConfig.mspring_album_islimit_height" />' validate="{required:true, digits:true, max:2000, min:0 }" />
									</p>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div style="text-align: center;"><input type="submit" class="button" value="提交" id="btnPost" style="width:120px;" /></div>
			</form>
		</div>
		<script language="javascript">
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
			
			function ChangeValue(cb,hid){
				var hidobj = document.getElementById(hid);
				if(cb.checked){
					hidobj.value = "true";
				}
				else{
					hidobj.value = "false";
				}
			}
		</script>
	</body>
<%@ include file="../includes/footer.jsp" %>