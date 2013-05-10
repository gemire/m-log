<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<link rel="stylesheet" type="text/css" href="${base}/script/lhgcalendar/skins/lhgcalendar.css">
<script type="text/javascript" src="${base}/script/lhgcalendar/lhgcalendar.min.js"></script>
<div id="error" class="message error" style="display:none;"></div>
<form class="form" id="adForm" action="${base}/admin/ad/edit/save" method="POST">
	<@spring.bind "ad" />
	<@spring.formHiddenInput path="ad.createTime" />
	<table class="formtable" style="width:100%">
		<tr>
			<td class="fieldlabel" style="width:60px;">编号:</td>
			<td>
				<@spring.formInput path="ad.id" attributes='class="textinput" style="width:98%;" readonly="readonly"' defaultValue="自动生成" />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">名称</td>
			<td>
				<@spring.formInput path="ad.name" attributes='class="textinput" style="width:98%;" validate=\'{required: true, messages:{required:"请输入广告名称"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">开始时间</td>
			<td>
				<@spring.formInput path="ad.startDate" attributes='class="textinput" style="width:98%;" readonly="readonly"' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">结束时间</td>
			<td>
				<@spring.formInput path="ad.endDate" attributes='class="textinput" style="width:98%;" readonly="readonly"' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">广告代码</td>
			<td>
				<@spring.formTextarea path="ad.code" attributes='class="textinput" style="width:98%; height:250px;" validate=\'{required:true, messages:{required:"请输入广告代码"}}\'' />
			</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:60px;">描述</td>
			<td>
				<@spring.formTextarea path="ad.description" attributes='class="textinput" style="width:98%;"' />
			</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center;">
				<input type="submit" class="btn" value=" 提交 " />
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	turnHighLight(510015);
	
	$(document).ready(function(){
		$("#startDate").calendar({ maxDate:'#endDate', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
		$("#endDate").calendar({ minDate:'#startDate', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
	});
	
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
			selector : "#adForm",
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