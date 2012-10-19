<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div class="ui-layout-center">
	<div class="tab">
		<ul>
		    <li><a href="${base}/admin/album/list">列表</a></li>
		    <li><a href="${base}/admin/album/create">增加</a></li>
		    <li><a href="javascript:void(0);">修改</a></li>
		    <li><a href="${base}/admin/photo/upload">图片上传</a></li>
		    <li><a href="javascript:void(0);" class="here">相册设置</a></li>
		</ul>
	</div>
	<div class="tab-container">
		<div id="error" class="message error" style="display:none;"></div>
		<form id="albumConfigForm" name="albumConfigForm" action="${base}/admin/album/saveConfig" method="POST">
			<table class="formtable" style="width:100%">
				<tr>
					<td class="fieldlabel" style="width:120px;">单次上传最大文件数</td>
					<td>
						<input type="input" class="textinput" style="width:95%;" name="photo_limit" value="${photo_limit!""}" validate="{required:true, digits: true, max:15, messages:{required:'请输入单次上传最大文件数', digits:'单次最大上传文件数必须为正整数', max:'单次最大上传文件数不得超过{0}'}}" />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel" style="width:120px;">是否限制图片大小</td>
					<td>
						<input type="checkbox" <#if photo_islimit_size?exists && photo_islimit_size == "true">checked="checked"</#if> onclick='$("#photo_islimit_size").val(this.checked); changeIsLimitSize(this.checked);' />
						<input type="hidden" id="photo_islimit_size" name="photo_islimit_size" value="${photo_islimit_size!"false"}" />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel" style="width:120px;">图片最大宽度(PX)</td>
					<td>
						<input type="input" class="textinput" style="width:95%;" name="photo_max_width" id="photo_max_width" value="${photo_max_width!""}" validate="{digits: true, messages:{digits:'图片最大宽度必须为正整数'}}"/>
					</td>
				</tr>
				<tr>
					<td class="fieldlabel" style="width:120px;">图片最大高度(PX)</td>
					<td>
						<input type="input" class="textinput" style="width:95%;" name="photo_max_height" id="photo_max_height" value="${photo_max_height!""}" validate="{digits: true, messages:{digits:'图片最大高度必须为正整数'}}"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value=" 提交 " />
						<input type="reset" class="btn" value=" 重置 " />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<script type="text/javascript">
	function changeIsLimitSize(checked){
		if(checked){
			document.getElementById("photo_max_width").disabled = "";
			document.getElementById("photo_max_height").disabled = "";
		}
		else {
			document.getElementById("photo_max_width").disabled = "disabled";
			document.getElementById("photo_max_height").disabled = "disabled";
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
		
		if($("photo_islimit_size").val() === "true"){
			changeIsLimitSize(true);
		}
		else {
			changeIsLimitSize(false);
		}
		
		mlog.form.validate({
			selector : "#albumConfigForm",
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