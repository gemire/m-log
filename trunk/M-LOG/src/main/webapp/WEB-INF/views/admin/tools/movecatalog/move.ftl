<#include "../../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form id="moveForm" name="moveForm" method="post" action="${base}/admin/tools/movecatalog/move">
	<#if message?exists && message?has_content>
	<div class="message error">${message}</div>
	</#if>
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">分类搬家</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:150px;">从分类:</td>
			<td>
				<select style="width:80%;" name="from" validate="{required:true, messages:{required:'请选择需要被移动的分类'}}">
					<option value="">--请选择--</option>
					<option value="0">--无父级--</option>
					<#list catalogs as c>
						<#assign c_name = c.name>
						<#list 1..c.deep as i>
							<#assign c_name = "—" + c_name>
						</#list>
						<option value="${c.id}">${c_name}</option>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:200px;">移动到:</td>
			<td>
				<select style="width:80%;" name="to" validate="{required:true, messages:{required:'请选择移动到的分类'}}">
					<option value="">--请选择--</option>
					<option value="0">--无父级--</option>
					<#list catalogs as c>
						<#assign c_name = c.name>
						<#list 1..c.deep as i>
							<#assign c_name = "—" + c_name>
						</#list>
						<option value="${c.id}">${c_name}</option>
					</#list>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
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
			selector : "#moveForm",
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
<#include "../../inc/footer.ftl" />