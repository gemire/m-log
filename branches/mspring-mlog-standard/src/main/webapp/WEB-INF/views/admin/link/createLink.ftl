<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="${base}/admin/link/list">列表</a></li>
			    <li><a href="javascript:void(0);" class="here">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			    <li><a href="${base}/admin/linkType/list">链接分类</a></li>
			    <li><a href="${base}/admin/linkType/create">新增分类</a></li>
			    <li><a href="${base}/admin/linkType/edit">修改分类</a></li>
			</ul>
		</div>
		<div class="tab-container">
			<div id="error" class="message error" style="display:none;"></div>
			<@spring.bind "link" />
			<form class="form" name="linkForm" id="linkForm" action="${base}/admin/link/doCreate" method="POST">
				<table class="formtable">
					<tr>
						<td class="fieldlabel" style="width:60px;">编号</td>
						<td>
							<@spring.formInput path="link.id" attributes='class="textinput" disabled="disabled" style="width:98%;"' defaultValue="自动生成"  />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel" style="width:60px;">名称</td>
						<td>
							<@spring.formInput path="link.name" attributes='class="textinput" style="width:98%;" validate=\'{required:true, messages:{required:"请输入链接名称"}}\'' />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel" style="width:60px;">分类</td>
						<td>
							<@spring.formSingleSelect path="link.type.id" options=types attributes='style="width:98%" validate=\'{required:true, messages:{required:"请选择链接分类"}}\'' has_default=true />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel" style="width:60px;">URL地址</td>
						<td>
							<@spring.formInput path="link.url" attributes='class="textinput" style="width:98%;" validate=\'{required:true, url:true, messages:{required:"请输入URL地址", url:"URL地址不合法"}}\'' />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel" style="width:60px;">Target</td>
						<td style="font-size:12px;">
							<@spring.formRadioButtons path="link.target" options=target defaultValue="_blank" separator="<br />" />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel" style="width:60px;">是否可见</td>
						<td style="font-size:12px;">
							<@spring.formRadioButtons path="link.visable" options=visable defaultValue="true" separator="&nbsp;" />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel" style="width:60px;">排序</td>
						<td>
							<@spring.formInput path="link.order" attributes='class="textinput" style="width:98%;"' />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel" style="width:60px;">描述</td>
						<td>
							<@spring.formTextarea path="link.description" attributes='class="textinput" style="width:98%;"' />
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align:center;">
							<input type="submit" class="btn" value=" 提交 " />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

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
				selector : "#linkForm",
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