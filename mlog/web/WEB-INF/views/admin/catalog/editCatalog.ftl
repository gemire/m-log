<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<div class="ui-layout-center">
	<form class="form" action="${base}/admin/catalog/doEdit" method="POST">
		<table class="formtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='2'>
			<tr>
				<td class="fieldlabel" style="width: 20%">编号:</td>
				<td style='width: 80%'>
					<@spring.formInput path="catalog.id" attributes='class="textinput" readonly="readonly"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width: 20%">名称</td>
				<td style='width: 80%'>
					<@spring.formInput path="catalog.name" attributes='class="textinput"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width: 20%">创建时间</td>
				<td style='width: 80%'>
					<@spring.formInput path="catalog.createTime" attributes='class="textinput" readonly="readonly"' />
				</td>
			</tr>
			
			<tr>
				<td class="fieldlabel" style="width: 20%">排序</td>
				<td style='width: 80%'>
					<@spring.formInput path="catalog.order" attributes='class="textinput"' />
				</td>
			</tr>
			<tr>
				<td class="fieldlabel" style="width: 20%">描述</td>
				<td style='width: 80%'>
					<@spring.formTextarea path="catalog.description" attributes='class="textinput"' />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" class="btn" value=" 提交 " />
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$('body').layout({
			north__closable:false,
			north__size:62,
			north__resizable:false,
			south__closable:false,
			south__size:50,
			south__resizable:false,
			east__size:250,
			togglerTip_open : "关闭",
			togglerTip_closed : "打开",
			resizerTip:"调整宽度",
			//west__spacing_closed:10,
			west__onresize: function (pane, $Pane) {  
                
            }
		});
		
	});
</script>
<#include "../inc/footer.ftl" />