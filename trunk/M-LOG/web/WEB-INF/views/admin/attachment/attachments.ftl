<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Attachment upload</title>
	<script type="text/javascript" src="${base}/script/jquery.js"></script>
	<script type="text/javascript" src="${base}/script/mlog.editor.js"></script>
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
		function deleteAttach(id){
			$.get('${base}/admin/attachment/delete?id=' + id, function(response){
				if(response === 'true'){
					showAttachs();
				}
				else {
					alert('附件删除失败');				
				}
			});
		}
	</script>
</head>
<body>
<div id="media-upload-header">
	<span><a href="javascript:showUpload();">批量上传</a></span>
	<span id="curtab"><a href="javascript:showAttachs();">附件库</a></span>
</div>
<div id="media-upload-body">
	<#if (attachments?exists && attachments?size > 0)>
		<#list attachments as attach>
			<li id="attlist">
				<a href="${blogurl}/${attach.path}" target="_blank" title="${attach.id}">
					<img src="${blogurl}/${attach.path}" width="90" height="90" border="0" align="absmiddle"/>
				</a>
				<br />
				<a href="javascript:deleteAttach(${attach.id});">删除</a>
				<a href="javascript:parent.addAttachImage('${attach.path}', ${attach.id});">插入 </a>
			</li>
		</#list>
	<#else>
	<p id="attmsg">该文章没有附件</p>
	</#if>
	<div id="custom-bt">
		<input width="120" type="file" height="30" name="Filedata" id="custom_file_upload" style="display: none;">
	</div>
	<div id="custom-queue" class="uploadifyQueue"></div>
</div>
</body>
</html>