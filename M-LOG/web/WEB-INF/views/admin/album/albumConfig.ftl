<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div id="error" class="message error" style="display:none;"></div>
<form id="albumConfigForm" name="albumConfigForm" action="${base}/admin/album/config/save" method="POST">
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">上传配置</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">单次上传最大文件数</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="photo_limit" value="${photo_limit!""}" validate="{digits: true, range:[5,300], messages:{digits:'单次最大上传文件数必须为正整数', range:'单次最大上传文件数必须在{0}到{1}之间'}}" />
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、5<= 值 <= 300，默认：100</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">是否限制图片大小</td>
			<td>
				<input type="checkbox" <#if photo_islimit_size?exists && photo_islimit_size == "true">checked="checked"</#if> onclick='$("#photo_islimit_size").val(this.checked);' />
				<input type="hidden" id="photo_islimit_size" name="photo_islimit_size" value="${photo_islimit_size!"false"}" />
			</td>
			<td class="fieldnotice" style="width:300px;"></td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">图片最大宽度(PX)</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="photo_max_width" id="photo_max_width" value="${photo_max_width!""}" validate="{digits: true,range:[50,10000], messages:{digits:'图片最大宽度必须为正整数', range:'图片最大宽度必须在{0}到{1}之间'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、50<= 值 <=10000，默认：1000</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">图片最大高度(PX)</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="photo_max_height" id="photo_max_height" value="${photo_max_height!""}" validate="{digits: true, range:[50,10000], messages:{digits:'图片最大高度必须为正整数',range:'图片最大高度必须在{0}到{1}之间'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、50<= 值 <=10000，默认：1000</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">单张图片最大大小(KB)</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="photo_max_size" id="photo_max_size" value="${photo_max_size!""}" validate="{digits: true, max:20480, messages:{digits:'单张图片最大大小(KB)必须为正整数', max:'单张图片最大大小不能操过{0}KB'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、值<=20480，默认：5120KB(5M)</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
				<input type="submit" class="btn" value=" 提交 " />
				<input type="reset" class="btn" value=" 重置 " />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="partition">前台展示设置</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">相册列表每页显示数量</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="web_albumlist_size" id="web_albumlist_size" value="${web_albumlist_size!""}" validate="{digits: true, range:[1,50], messages:{digits:'相册列表每页显示数量必须为正整数', range:'相册列表每页显示数量必须在{0}到{1}之间'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、1<=值<=50，默认：30</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">图片列表每页显示数量</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="web_photolist_size" id="web_photolist_size" value="${web_photolist_size!""}" validate="{digits: true, range:[1,50], messages:{digits:'图片列表每页显示数量必须为正整数', range:'图片列表每页显示数量必须在{0}到{1}之间'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、1<=值<=50，默认：30</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">图片最大显示宽度</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="web_photo_max_width" id="web_photo_max_width" value="${web_photo_max_width!""}" validate="{digits: true, range:[50, 2000], messages:{digits:'图片最大显示宽度必须为正整数', range:'图片最大显示宽度必须在{0}到{1}之间'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、50<=值<=2000，默认：1000</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">图片最大显示高度</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="web_photo_max_height" id="web_photo_max_height" value="${web_photo_max_height!""}" validate="{digits: true, range:[50, 2000], messages:{digits:'图片最大显示高度必须为正整数', range:'图片最大显示高度必须在{0}到{1}之间'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：正整数、50<=值<=2000，默认：1000</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:center;">
				<input type="submit" class="btn" value=" 提交 " />
				<input type="reset" class="btn" value=" 重置 " />
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	$(document).ready(function(){
		
		turnHighLight(120030);
	
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