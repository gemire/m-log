<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<script type="text/javascript" src="${base}/script/kindeditor/kindeditor.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			mlog.editor.ins.createPostEditor = mlog.editor.init({
				type : "kindeditor",
				model : "all",
				id : "content"
			});
			mlog.editor.ins.createPostSummaryEditor = mlog.editor.init({
				type : "kindeditor",
				model : "simple",
				id : "summary"
			});
			
			$("#catalogs_select").multiselect({
				header:false,
				selectedList: 4,
				click: function(event, ui){
					var values = $("#catalogs").val();
					if(ui.checked){
						values += ui.value + ',';
					}
					else{
						values = values.replace(ui.value + ',', '');
					}
					$("#catalogs").val(values);
				}
			});
		});
	</script>
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="${base}/admin/post/list">列表</a></li>
			    <li><a href="javascript:void(0);" class="here">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			</ul>
		</div>
		<form id="postForm" name="postForm" action="${base}/admin/post/doCreate" method="POST">
			<@spring.bind "post" />
			<@mspring.show_errors />
			<@spring.formHiddenInput path="post.status" />
			<table class="formtable" style="width:100%;">
				<tr>
					<td class="fieldlabel" style="width:60px;">标题</td>
					<td>
						<@spring.formInput path="post.title" attributes='class="textinput" style="width:98%;"' />
					</td>
					<td class="fieldlabel" style="width:60px;">分类</td>
					<td>
						<select id="catalogs_select" multiple="multiple">
							<#if (catalogs?exists && catalogs?size > 0)>
								<#list catalogs as catalog>
									<option value="${catalog.id}">${catalog.name}</option>
								</#list>
							</#if>
						</select>
						<input type="hidden" id="catalogs" name="catalogs" />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel">标签</td>
					<td>
						<@spring.formInput path="post.tags" attributes='class="textinput" style="width:98%;"' />
					</td>
					
					<td class="fieldlabel">链接</td>
					<td>
						<@spring.formInput path="post.url" attributes='class="textinput" style="width:98%;"' />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel">访问密码</td>
					<td>
						<@spring.formInput path="post.password" attributes='class="textinput" style="width:98%;"' />
					</td>
					
					<td class="fieldlabel">允许评论</td>
					<td style="font-size:12px;">
						<@spring.formRadioButtons path="post.commentStatus" options=commentStatus defaultValue="open" separator="&nbsp;" />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel">内容</td>
					<td colspan="3">
						<@spring.formTextarea path="post.content" attributes='style="height:200px;width:100%;"' />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel">摘要</td>
					<td colspan="3">
						<@spring.formTextarea path="post.summary" attributes='style="height:150px;width:100%;"' />
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align:center;">
						<input type="button" class="btn" value=" 发布内容 " onclick="publish();" />
						<input type="button" class="btn" value=" 保存为草稿 " onclick="draft();" />
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript">
	//发布
	function publish(){
		$("#status").val("publish");
		mlog.form.submitForm("postForm");
	}
	//存为草稿
	function draft(){
		$("#status").val("draft");
		mlog.form.submitForm("postForm");
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
	});
</script>
<script type="text/javascript">
$(document).ready(function(){
	$('body').layout({
		north__closable:false,
		north__size:62,
		north__resizable:false,
		south__closable:false,
		south__size:50,
		south__resizable:false,
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