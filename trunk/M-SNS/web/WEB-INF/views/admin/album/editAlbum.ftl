<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div id="error" class="message error" style="display:none;"></div>
<form class="albumForm" id="albumForm" action="${base}/admin/album/edit/save" method="POST">
	<@spring.bind "album" />
	<@spring.formHiddenInput path="album.id" />
	<@spring.formHiddenInput path="album.createTime" />
	<input type="hidden" id="albumType" value="${album.type}" />
	<table class="formtable">
		<tr>
			<td class="fieldlabel" style="width:60px;">相册编号</td>
			<td>
				<@spring.formInput path="album.id" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="自动生成"  />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">相册名称</td>
			<td>
				<@spring.formInput path="album.name" attributes='class="textinput" style="width:98%;" validate=\'{required: true, messages:{required:"请输入相册名称"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">创建时间</td>
			<td>
				<@spring.formInput path="album.createTime" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="当前时间" />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">隐私设置</td>
			<td>
				<@spring.formRadioButtons path="album.type" options=types defaultValue="public" separator="<br />" attributes='onclick="changeAlbumType(this)"' />
				<@spring.formInput path="album.verifycode" attributes='class="textinput" style="width:98%;" disabled="disabled" validate=\'{required:true, messages:{required:"请输入相册密码"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">排序</td>
			<td>
				<@spring.formInput path="album.sortOrder" attributes='class="textinput" style="width:98%;" validate=\'{digits:true, messages:{digits:"排序编号必须为正整数"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">相册描述</td>
			<td>
				<@spring.formInput path="album.description" attributes='class="textinput" style="width:98%;"' />
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
				<input type="submit" class="btn" value=" 提交 " />
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	turnHighLight(120015);
	//控制相册验证码框是否可用
	function changeAlbumType(obj){
		if(obj.value === "verified"){
			document.getElementById("verifycode").disabled = "";
		}
		else {
			document.getElementById("verifycode").disabled = "disabled";
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
		
		//如果初始化为需要密码访问，设置密码输入框为可用
		if($("#albumType").val() === "verified"){
			document.getElementById("verifycode").disabled = "";
		}
		
		
		mlog.form.validate({
			selector : "#albumForm",
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