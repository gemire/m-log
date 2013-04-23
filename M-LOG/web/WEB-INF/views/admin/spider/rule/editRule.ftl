<#include "/WEB-INF/views/admin/inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div id="error" class="message error" style="display:none;"></div>
<form class="form" id="ruleForm" action="${base}/admin/spider/rule/edit/save" method="POST">
	<@spring.bind "rule" />
	<@spring.formHiddenInput path="rule.id" />
	<table class="infotable">
		<tr>
			<td class="fieldlabel" style="width:60px;">名称</td>
			<td>
				<@spring.formInput path="rule.name" attributes='class="textinput" style="width:98%;" validate=\'{required: true, messages:{required:"规则标题"}}\'' />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="partition">第一步：列表规则</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">列表页URL</td>
			<td>
				<@spring.formInput path="rule.url" attributes='class="textinput" style="width:98%;" validate=\'{required: true, messages:{required:"请输入URL"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">列表规则</td>
			<td>
				<@spring.formInput path="rule.listRule" attributes='class="textinput" style="width:98%;" validate=\'{required: true, messages:{required:"请输入列表规则"}}\'' />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="partition">第二步：内容采集规则</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">标题规则</td>
			<td>
				<@spring.formInput path="rule.titleRule" attributes='class="textinput" style="width:98%;" validate=\'{required: true, messages:{required:"请输入正文规则"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">正文规则</td>
			<td>
				<@spring.formInput path="rule.contentRule" attributes='class="textinput" style="width:98%;" validate=\'{required: true, messages:{required:"请输入正文规则"}}\'' />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="partition">第三步：发布规则</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center;">
				<input type="submit" class="btn" value=" 提交 " />
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	turnHighLight(815005015);
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
			selector : "#ruleForm",
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
<#include "/WEB-INF/views/admin/inc/footer.ftl" />