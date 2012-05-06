<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<link href="<%=path %>/script/uploadify/uploadify.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path %>/script/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="<%=path %>/script/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript">
		var album;
		$(document).ready(function() {
			$("#uploadify").uploadify({
				'uploader':"<%=path %>/script/uploadify/uploadify.swf",
				'script':"<%=path %>/admin/uploadPhoto.servlet",
				//'script':"<%=path %>/script/uploadify/uploadFile.jsp",
				'cancelImg':"<%=path %>/script/uploadify/cancel.png",
				'folder':"uploads",//上传文件存放的路径,请保持与uploadFile.jsp中PATH的值相同
				'queueId':"fileQueue",
				'queueSizeLimit':100,//限制上传文件的数量
				'fileDataName':'photo',
				'auto':false,
				'multi':true,//是否允许多文件上传
				'simUploadLimit':2,//同时运行上传的进程数量
				'buttonText':"files",
				'displayData':'speed',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
				'fileExt':'*.jpg;*.gif;*.png',//允许的格式
  				'fileDesc': 'Image Files (.JPG, .GIF, .PNG)',//如果配置了以下的'fileExt'属性，那么这个属性是必须的
                'scriptData':{'album':album},//这个参数用于传递用户自己的参数，此时'method' 必须设置为GET, 后台可以用request.getParameter('name')获取名字的值
				'method':"GET",
				'onComplete' : function(event, queueID, fileObj, response, data){
				}
			});
		});
		
		
		function upload(){
			var combo = document.getElementById("combo_album");
			for(var i = 0; i < combo.length; i++){
				if(combo[i].selected){
					album = combo[i].id;
					break;
				}
			}
			if(album) {
				$('#uploadify').uploadifyUpload();
			}
			else {
				album = "";
				alert("请选择相册");
			}
		}
		
		function clearQueue(){
			$('#uploadify').uploadifyClearQueue();
		}
	</script>

	<body>
		<div id="divMain">
			<div class="Header">
				创建相册
			</div>
			<div class="SubMenu"></div>
			<div id="divMain2">
				<form action="<%=path %>/admin/doCreateAlbum.action" id="albumForm" method="post">
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 15%'>
								<p align='left'>·选择相册</p>
							</td>
							<td style="width: 85%">
								<select name="album" id="combo_album">
									<option>--请选择--</option>
									<ss:iterator id="album" value="albums">
										<option id="<ss:property value="id" />"><ss:property value="name" /></option>
									</ss:iterator>
								</select>
							</td>
						</tr>
					</table>
					<br/>
					<p>
				        <a href="javascript:upload();">开始上传</a>&nbsp;
						<a href="javascript:clearQueue();">取消所有上传</a>
					</p>
					<div id="result"></div>
					<input type="file" name="uploadify" id="uploadify" />
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
