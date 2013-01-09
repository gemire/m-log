<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form id="bloginfoForm" name="bloginfoForm" method="post" action="${base}/admin/setting/saveBloginfo">
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">统计任务</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">是否开启:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="blogname" value="${blogname!""}" validate="{required:true, maxlength:100, messages:{required:'博客标题必填', maxlength:'博客标题长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度100</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">执行类型:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="blogsubname" value="${blogsubname!""}" validate="{required:true, maxlength:300, messages:{required:'博客子标题必填', maxlength:'博客子标题长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度300</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">执行规则:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="blogsubname" value="${blogsubname!""}" validate="{required:true, maxlength:300, messages:{required:'博客子标题必填', maxlength:'博客子标题长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度300</td>
		</tr>
		<tr>
			<td colspan="3" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
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
			selector : "#bloginfoForm",
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