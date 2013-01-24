<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<table class="formtable">
	<tr>
		<td>当前位置：<a href="${base}/admin/photo/list?album.id=${album.id}">${album.name}</a>&gt;&gt;<a href="${base}/admin/photo/edit?id=${photo.id}">${photo.name}</a></td>
	</tr>
</table>
<div id="error" class="message error" style="display:none;"></div>
<form class="photoForm" id="photoForm" action="${base}/admin/photo/edit/save" method="POST">
	<@spring.bind "photo" />
	<#-- <@spring.formHiddenInput path="photo.id" /> -->
	<@spring.formHiddenInput path="photo.album.id" />
	<@spring.formHiddenInput path="photo.width" />
	<@spring.formHiddenInput path="photo.height" />
	<@spring.formHiddenInput path="photo.fileName" />
	<@spring.formHiddenInput path="photo.url" />
	<@spring.formHiddenInput path="photo.previewUrl" />
	<@spring.formHiddenInput path="photo.previewFileName" />
	<@spring.formHiddenInput path="photo.photoYear" />
	<@spring.formHiddenInput path="photo.photoMonth" />
	<@spring.formHiddenInput path="photo.photoDate" />
	<@spring.formHiddenInput path="photo.size" />
	
	<@spring.formHiddenInput path="photo.colorBit" />
	<@spring.formHiddenInput path="photo.createTime" />
	<@spring.formHiddenInput path="photo.manufacturer" />
	<@spring.formHiddenInput path="photo.model" />
	<@spring.formHiddenInput path="photo.ISO" />
	<@spring.formHiddenInput path="photo.aperture" />
	<@spring.formHiddenInput path="photo.shutter" />
	<@spring.formHiddenInput path="photo.exposureBias" />
	<@spring.formHiddenInput path="photo.exposureTime" />
	<@spring.formHiddenInput path="photo.focalLength" />
	<@spring.formHiddenInput path="photo.colorSpace" />
	<table class="formtable" style="width:100%">
		<tr>
			<td class="fieldlabel" style="width:60px;">图片编号</td>
			<td>
				<@spring.formInput path="photo.id" attributes='class="textinput" style="width:98%;" readonly="readonly"' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">图片名称</td>
			<td>
				<@spring.formInput path="photo.name" attributes='class="textinput" style="width:98%;" validate="{required:true,maxlength:30}"' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">图片描述</td>
			<td>
				<@spring.formTextarea path="photo.description" attributes='class="textinput" style="width:98%;" validate="{maxlength:1000}"' />
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
				<input type="submit" class="btn" value=" 提交 " />
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
				<img src="${photo.url}" />
			</td>
		</tr>
	</table>
</form>

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
		
		
		mlog.form.validate({
			selector : "#photoForm",
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
</script>
<#include "../inc/footer.ftl" />