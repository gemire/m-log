<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/resources/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/admin/resources/button.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/script/extjs/resources/css/ext-all.css" />
 		
    <script type="text/javascript" src="<%=path %>/script/extjs/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=path %>/script/extjs/ext-all.js"></script>
	    
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.js"></script>
	<!-- <script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.queue.js"></script> -->
	<script type="text/javascript" src="<%=path %>/script/swfupload/handlers.js"></script>
	
	<script type="text/javascript">
		var swfu;
		window.onload = function () {
			swfu = new SWFUpload({
				upload_url: "<%=path %>/admin/uploadPhoto.servlet",
				
				// File Upload Settings
				file_size_limit : "1000 MB",	// 1000MB
				file_types : "*.jpg;*.gif;*.bmp;",
				file_types_description : "图片文件",
				file_upload_limit : "100",
				use_query_string : true,
								
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
				file_queued_handler : fileQueued,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : "<%=path%>/admin/resources/images/SmallSpyGlassWithTransperancy_17x18.png",
				button_placeholder_id : "spanButtonPlaceholder",
				button_width: 180,
				button_height: 18,
				button_text : '<span class="button_select">选择图片 <span class="buttonSmall">(10 MB Max)</span></span>',
				button_text_style : '.button_select { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
				button_text_top_padding: 0,
				button_text_left_padding: 18,
				button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
				button_cursor: SWFUpload.CURSOR.HAND,
				
				// Flash Settings
				flash_url : "<%=path%>/script/swfupload/swfupload.swf",

				custom_settings : {
					upload_target : "divFileProgressContainer"
				},
				// Debug Settings
				debug: false  //是否显示调试窗口
			});
		};
		function startUploadFile(){
			var album = document.getElementById("combo_album").value;
			if(album){
				swfu.setPostParams({"album" : album});
				swfu.startUpload();
			}
			else{
				alert("请选择相册");
			}
		}
		var win = new Ext.Window({
			title : 'SwfUpload',
			closeAction : 'hide',
			width : 750,
			height : 360,
			resizable : false,
			modal : true,
			html : '<iframe src="index.jsp" width="100%" height="100%"></iframe>'
		});
		function showExtShow(){
			win.show();
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
								·选择相册
							</td>
							<td style="width: 85%">
								<select name="album" id="combo_album">
									<option>--请选择--</option>
									<ss:iterator id="album" value="albums">
										<option value='<ss:property value="id" />'><ss:property value="name" /></option>
									</ss:iterator>
								</select>
							</td>
						</tr>
					</table>
					<br />
					<div style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 5px;">
						<span id="spanButtonPlaceholder"></span>
						<input id="btnUpload" type="button" value="上  传"
							onclick="startUploadFile();" class="btn3_mouseout" onMouseUp="this.className='btn3_mouseup'"
							onmousedown="this.className='btn3_mousedown'"
							onMouseOver="this.className='btn3_mouseover'"
							onmouseout="this.className='btn3_mouseout'"/>
						<input id="btnCancel" type="button" value="取消所有上传"
							onclick="cancelUpload();" disabled="disabled" class="btn3_mouseout" onMouseUp="this.className='btn3_mouseup'"
							onmousedown="this.className='btn3_mousedown'"
							onMouseOver="this.className='btn3_mouseover'"
							onmouseout="this.className='btn3_mouseout'"/>
					</div>
					<div id="divFileProgressContainer"></div>
					<div id="thumbnails">
						<table id="infoTable" border="0" width="530" style="display: none; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;margin-top:8px;">
						</table>
					</div>
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
