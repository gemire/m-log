<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<link href="${base}/script/uploadify/uploadify.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${base}/script/uploadify/jquery.uploadify.min.js"></script>
	<style type="text/css">
          #wall{ /*width:100%; margin:0 auto; font-size:0;*/}
          .item{ display:inline-block; margin:4px; width:158px; *display:inline; zoom:1; vertical-align:top;}
          .itemimage{ border:solid 2px #efefef; padding:2px; max-height: 150px; max-width: 150px;}
          .itemmeta{ display:block; width:100%; text-align:center; font-size:12px; color:#666;  line-height:20px; }
          .itemmeta .itemtitle{ text-decoration:none; color: #666600;}
          .itemmeta .itemtitle:hover{ text-decoration:none; color:red;}
          .itemmeta .ctrl, .itemmeta .ctrl:hover { text-decoration:none; color:blue;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$(function() {
			    $("#uploadify").uploadify({
			        height           : 20,
			        width            : 60,
			        swf              : '${base}/script/uploadify/uploadify.swf',
			        uploader         : '${base}/admin/photo/doUpload',
			        method           : 'GET',
			        buttonClass      : 'btn',
			        buttonText       : '选择图片',
			        progressData     : 'speed',
			        auto             : false,
			        fileTypeDesc     : 'Image Files',
        			fileTypeExts     : '*.gif; *.jpg; *.png; *.bmp',
        			fileSizeLimit    : '3072KB',
        			onUploadStart    : function(file){
        				//$('#btn_upload').fadeOut(1);
        				//$('#btn_stop').fadeIn(1);
        				$('#btn_upload').css('display', 'none');
						$('#btn_stop').css('display', 'inline');
        				$('#btn_clear').attr('disabled', 'disabled');
        			},
        			onUploadSuccess : function(file, data, response){
        			}
			    });
			});
			
			mlog.form.validate({
				selector : "#uploadForm",
				errorLabelContainer : "#error",
				wrapper: 'li',
				onfocusout : false,
				onkeyup : false,
				onclick : false,
				success : function(){
					mlog.utils.scrollTop();
				}
			});
		});
		
		//开始上传
		function upload(){
			if($("#uploadForm").valid()){
				$('#uploadify').uploadify('settings', 'formData', {'album':$("#album").val()});
				$('#uploadify').uploadify('upload', '*');
			}
		}
		
		//清空上传队列
		function clearQueue(){
			$('#uploadify').uploadify('cancel', '*');
		}
		
		//暂停上传
		function stop(){
			$('#uploadify').uploadify('stop');
			$('#btn_stop').css('display', 'none');
			$('#btn_upload').css('display', 'inline');
			$('#btn_clear').removeAttr('disabled');
		}
	</script>
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="${base}/admin/album/list">列表</a></li>
			    <li><a href="${base}/admin/album/create">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			    <li><a href="javascript:void(0);" class="here">图片上传</a></li>
			    <li><a href="${base}/admin/album/config">相册设置</a></li>
			</ul>
		</div>
		<div class="tab-container">
			<div id="error" class="message error" style="display:none;"></div>
			<div style="margin:10px;">
				<form name="uploadForm" id="uploadForm">
					选择相册:
					<select id="album" validate="{required:true, messages:{required:'请选择相册'}}">
						<option value="">--请选择--</option>
						<#list albums as album>
							<option value="${album.id}">${album.name}</option>
						</#list>
					</select>
					<input type="button" class="btn" value="开始上传" id="btn_upload" onclick="upload();" />
					<input type="button" class="btn" value="暂停上传" id="btn_stop" onclick="stop();" style="display:none;" />
					<input type="button" class="btn" value="清空列表" id="btn_clear" onclick="clearQueue();" />
					<input type="file" name="uploadify" id="uploadify" />
				</form>
			</div>
		</div>
	</div>
<#include "../inc/footer.ftl" />