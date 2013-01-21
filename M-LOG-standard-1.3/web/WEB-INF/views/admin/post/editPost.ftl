<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
	<script type="text/javascript" src="${base}/script/kindeditor/kindeditor.js" charset="utf-8"></script>
	<script type="text/javascript">
		turnHighLight(11505015);
		$(document).ready(function(){
			mlog.editor.init({
				type : "kindeditor",
				model : "all",
				id : "content"
			});
			mlog.editor.init({
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
	
			<div id="error" class="message error" style="display:none;"></div>
			<form id="postForm" name="postForm" action="${base}/admin/post/edit/save" method="POST">
				<@spring.bind "post" />
				<@mspring.show_errors />
				<@spring.formHiddenInput path="post.id" />
				<@spring.formHiddenInput path="post.author.id" />
				<@spring.formHiddenInput path="post.createTime" />
				<@spring.formHiddenInput path="post.status" />
				<@spring.formHiddenInput path="post.commentCount" />
				<@spring.formHiddenInput path="post.viewCount" />
				<table class="formtable">
					<tr>
						<td class="fieldlabel" style="width:60px;">标题</td>
						<td>
							<@spring.formInput path="post.title" attributes='class="textinput" style="width:98%;" validate=\'{required:true,postTitleExists:{id:"${post.id}"}, messages:{required:"请输入文章标题", postTitleExists:"文章标题已经存在"}}\'' />
						</td>
						<td class="fieldlabel" style="width:60px;">分类</td>
						<td>
							<select id="catalogs_select" multiple="multiple">
								<#assign selected_catalogs = "" />
								<#if (catalogs?exists && catalogs?size > 0)>
									<#list catalogs as catalog>
										<#assign has_selected = "" />
										<#list post.catalogs as selected_catalog>
											<#if catalog.id == selected_catalog.id>
												<#assign selected_catalogs = selected_catalogs + catalog.id + "," />
												<#assign has_selected = 'selected="selected"' />
												<#break />
											</#if>
										</#list>
										<option value="${catalog.id}" ${has_selected}>${catalog.name}</option>
									</#list>
								</#if>
							</select>
							<input type="hidden" id="catalogs" name="catalogs" value="${selected_catalogs}" />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel">标签</td>
						<td>
							<@spring.formInput path="post.tags" attributes='class="textinput" style="width:98%;"' />
						</td>
						
						<td class="fieldlabel">链接</td>
						<td>
							<@spring.formInput path="post.url" attributes='class="textinput" style="width:98%;" validate=\'{postUrlIllegal:true, postUrlExists:{id:"${post.id}"}, messages:{postUrlIllegal:"链接不合法",postUrlExists:"链接已经存在"}}\'' />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel">访问密码</td>
						<td>
							<@spring.formInput path="post.password" attributes='class="textinput" style="width:98%;"' />
						</td>
						
						<td colspan="2">
							<table class="formtable">
								<tr>
									<td class="fieldlabel">允许评论</td>
									<td><@spring.formRadioButtons path="post.commentStatus" options=commentStatus defaultValue="open" separator="&nbsp;" /></td>
									<td class="fieldlabel">是否置顶</td>
									<td><@spring.formRadioButtons path="post.isTop" options=isTop defaultValue="1" separator="&nbsp;" /></td>
								</tr>
							</table>
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
							<input type="button" class="btn" value=" 修改 " onclick="publish();" />
							<input type="button" class="btn" value=" 保存为草稿 " onclick="draft();" />
						</td>
					</tr>
				</table>
			</form>
			
	<script type="text/javascript">
		//发布
		function publish(){
			$("#status").val("publish");
			if($("#postForm").valid()){
				mlog.form.submitForm("postForm");
			}
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
			
			mlog.form.validate({
				selector : "#postForm",
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