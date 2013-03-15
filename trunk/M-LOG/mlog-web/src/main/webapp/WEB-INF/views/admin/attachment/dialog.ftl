<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${base}/style/global.css">
	<script type="text/javascript" src="${base}/script/jquery.js"></script>
	<script type="text/javascript" src="${base}/script/script_variable.js"></script>
	
	<#-- lhgdialog -->
	<script type="text/javascript" src="${base}/script/lhgdialog/lhgdialog.min.js"></script>
	
	<link href="${base}/script/uploadify/uploadify.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${base}/script/uploadify/jquery.uploadify.min.js"></script>
	<style type="text/css">
		.uploadify-queue, .uploadify-queue-item {
			width:98%;
			max-width:10000px;
		}
		.imgattachlist {
			width: 100%;
		}
		.imgattachlist img {
			margin: 1px;
			border: 1px solid #999;
			max-width: 135px;
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div style="padding: 10px; display: block;">
		<div style="padding-bottom: 10px !important;border-bottom: 1px dashed #CDCDCD; margin-top: -1px;height: auto !important;max-height: 350px; overflow: auto;overflow-x: hidden;">
			<div id="imgattachlist" class="imgattachlist">
				<#--<table class="img_table" id="img_table"></table>-->
			</div>
			<input type="file" name="uploadify" id="uploadify" />
		</div>
		<div class="fieldlabel" style="text-align:left;">温馨提示：<span style="color:red;">每次最多能上传30张图片，单张图片大小不能超过1024KB。</span></div>
	</div>
	<script type="text/javascript">
		var api = frameElement.api, W = api.opener;
		$(document).ready(function(){
			$("#uploadify").uploadify({
		    	debug            : false,
		        height           : 20,
		        width            : 60,
		        swf              : '${base}/script/uploadify/uploadify.swf',
		        uploader         : '${base}/admin/attachment/upload',
		        method           : 'GET',
		        buttonClass      : 'btn',
		        buttonText       : '选择图片',
		        progressData     : 'speed',
		        auto             : false,
		        fileTypeDesc     : '请选择图片：',
    			fileTypeExts     : '*.gif; *.jpg; *.png; *.bmp',
    			fileSizeLimit    : '1024KB',
    			queueSizeLimit   : 30,
    			onUploadStart    : function(file){
    				/*
    				$('#btn_upload').css('display', 'none');
					$('#btn_stop').css('display', 'inline');
    				$('#btn_clear').attr('disabled', 'disabled');
    				*/
    			},
    			onUploadSuccess : function(file, data, response){
    				var dj = eval('(' + data + ')');
    				appendImageList(dj);
    			},
    			onQueueComplete : function(queueData){
    			},
    			//不执行默认的onSelect事件
    			//overrideEvents  : ['onDialogClose'],
		        onSelectError   :function(file, errorCode, errorMsg){ //返回一个错误，选择文件的时候触发
		            switch(errorCode) {
		                case -100:
		                    this.queueData.errorMsg = "上传的文件数量已经超出系统限制的" + $('#uploadify').uploadify('settings','queueSizeLimit') + "个文件！";
		                    break;
		                case -110:
		                    this.queueData.errorMsg = "文件 ["+file.name+"] 大小超出系统限制的" + $('#uploadify').uploadify('settings','fileSizeLimit') + "大小！";
		                    break;
		                case -120:
		                    this.queueData.errorMsg = "文件 ["+file.name+"] 大小异常！";
		                    break;
		                case -130:
		                    this.queueData.errorMsg = "文件 ["+file.name+"] 类型不正确！";
		                    break;
		            }
		        }
		    });
		    
		    //初始化ImageList
		    initImageList();
		});
		
		//开始上传
		function upload(){
			$('#uploadify').uploadify('upload', '*');
		}
		
		//清空上传队列
		function clearQueue(){
			$('#uploadify').uploadify('cancel', '*');
		}
		
		//暂停上传
		function stop(){
			$('#uploadify').uploadify('stop');
		}
		
		//没上传成功一张图片就在突破列表中追加一张
		function appendImageList(image){
			$("#imgattachlist").append('<img src="' + image.path + '" id="attachment_' + image.id + '" onclick="insertImage(' + image.id + ')" />');
			W.addImage(image);
		}
		
		//向编辑器中插入图片
		function insertImage(id){
			W.insertImage(id);
		}
		
		//初始化已经上传的图片列表
		function initImageList(){
			if(W.images.length > 0) {
				for(var i = 0; i < W.images.length; i++){
					appendImageList(W.images[i]);
				}
			}
		}
	</script>
</body>
</html>