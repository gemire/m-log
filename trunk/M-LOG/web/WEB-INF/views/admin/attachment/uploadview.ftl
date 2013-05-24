<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Attachment upload</title>
	<link href="${base}/script/uploadify/uploadify.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		body{font-size:12px; color:#000; line-height:1.6; font-family:arial,helvetica,sans-serif; padding:0; margin:0;}
		a{color: #999999; text-decoration:none;outline:none; }
		a:hover{color:#333; text-decoration:none}
		#media-upload-header{background-color: #F9F9F9; border-top:solid #F0F0F0 1px; margin-top:0px; height:20px;}
		#media-upload-header span{margin:0px 5px 0px;padding:1px 5px; list-style-type:none; display:block; float:left; zoom:1; height:17px;}
		#curtab{background-color:#FFF; border-left:1px solid #F0F0F0; border-right:1px solid #F0F0F0; border-top:1px solid #F0F0F0}
		#attlist{float:left; border:1px solid #CCC; list-style:none; text-align:center; margin:5px; padding:3px}
		#attmsg{margin:40px 0px 0px 250px}
		#media-upload-body{margin:0px 1px 5px 1px}
		#custom-queue {height: 192px;width: 695px;}
		#custom-bt{margin:5px 0px;}
	</style>
	<script type="text/javascript">
		function showUpload(){
			window.location.href = '${base}/admin/attachment/uploadview?post=${post}';
		}
		function showAttachs(){
			window.location.href = '${base}/admin/attachment/attachments?post=${post}';	
		}
	</script>
</head>
<body>
<div id="media-upload-header">
	<span id="curtab"><a href="javascript:;">批量上传</a></span>
	<span><a href="javascript:showAttachs();">附件库</a></span>
</div>
<form enctype="multipart/form-data" method="post" name="upload" action="">
	<div id="media-upload-body">
		<div id="custom-bt">
			<input width="120" type="file" height="30" name="Filedata" id="custom_file_upload" style="display: none;">
		</div>
		<div id="custom-queue" class="uploadifyQueue"></div>
	</div>
</form>
<script type="text/javascript" src="${base}/script/jquery.js"></script>
<script type="text/javascript" src="${base}/script/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#custom_file_upload").uploadify({
		//id              : $(this).attr('id'),
		swf             : '${base}/script/uploadify/uploadify.swf',
		uploader        : '${base}/admin/attachment/upload?fid=${post?default("")}',
		checkExisting   : false,
		buttonText      : '选择文件',
		auto            : true,
		multi           : true,
		progressData    : 'speed',
		fileTypeExts    : '*.jpg;*.gif;*.png;*.jpeg;*.rar;*.zip',
		queueID         : 'custom-queue',
		queueSizeLimit	: 100,
		removeCompleted : false,
		fileSizeLimit	: 20971520,
		fileObjName     : 'attach',
		onQueueComplete : function() {
			showAttachs();
		}
	});
});
</script>
</body>
</html>