<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
	<script type="text/javascript" src="${base}/script/autocomplete.js" charset="utf-8"></script>
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
		});
	</script>
	<script type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
		};
		
		
		var zNodes =[
			<#if (catalogs?exists && catalogs?size > 0)>
				<#list catalogs as catalog>
					{id:${catalog.id}, pId:<#if catalog.parent?exists>${catalog.parent.id}<#else>0</#if>, name:"${catalog.name}"}
					<#if catalog_has_next>,</#if>
				</#list>
			</#if>
		];
		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("catalogComboTree");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("catalogComboTree"),
			nodes = zTree.getCheckedNodes(true),
			text = "",
			value = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				text += nodes[i].name + ",";
				value += nodes[i].id + ",";
			}
			if (text.length > 0 ) text = text.substring(0, text.length-1);
			$("#catalogSel").attr("value", text);
			
			if (value.length > 0 ) value = value.substring(0, value.length-1);
			$("#catalogs").attr("value", value);
		}
		function showMenu() {
			var catalogSelObj = $("#catalogSel");
			var catalogSelOffset = $("#catalogSel").offset();
			$("#menuContent").css({left:catalogSelOffset.left + "px", top:catalogSelOffset.top + catalogSelObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "catalogSel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		//设置选中项
		function setCheckedItems(){
			var zTree = $.fn.zTree.getZTreeObj("catalogComboTree");
			<#assign selectHiddenValue="" />
			<#if (post.catalogs?exists && post.catalogs?size > 0)>
				<#list post.catalogs as catalog>
					zTree.checkNode(zTree.getNodeByParam("id", "${catalog.id}", null), true, true);
					<#assign selectHiddenValue = selectHiddenValue + catalog.id + "," />
				</#list>
			</#if>
			$("#catalogs").val('${selectHiddenValue}');
		}
		$(document).ready(function(){
			$.fn.zTree.init($("#catalogComboTree"), setting, zNodes);
			setCheckedItems();
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
							<@spring.formInput path="post.title" attributes='class="textinput" style="width:98%;" validate=\'{required:true, messages:{required:"请输入文章标题"}}\'' />
						</td>
						<td class="fieldlabel" style="width:60px;">分类</td>
						<td>
							<input type="text" id="catalogSel" onclick="showMenu();" class="textinput" readonly="readonly" style="width:98%;"/>
							<input type="hidden" name="catalogs" id="catalogs"/>
							<div id="menuContent" class="menuContent" style="display:none; position: absolute; overflow:auto; background:#fff; border:1px solid #cccccc;z-index: 100;">
								<div style="display:block;float:right; padding:2px;"><input type="button" class="btn" onclick="hideMenu();" value="关闭" /></div>
								<ul id="catalogComboTree" class="ztree" style="margin-top:0; width:240px; height: 300px;"></ul>
							</div>
						</td>
					</tr>
					<tr>
						<td class="fieldlabel">标签</td>
						<td>
							<@spring.formInput path="post.tags" attributes='class="textinput" style="width:98%;"' />
						</td>
						<td class="fieldlabel">访问密码</td>
						<td>
							<@spring.formInput path="post.password" attributes='class="textinput" style="width:98%;"' />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel">来源站点</td>
						<td>
							<@spring.formInput path="post.site" attributes='class="textinput" style="width:98%;"' />
						</td>
						<td class="fieldlabel">来源链接</td>
						<td>
							<@spring.formInput path="post.url" attributes='class="textinput" style="width:98%;"' />
						</td>
					</tr>
					<tr>
						<td class="fieldlabel">允许评论</td>
						<td>
						<@spring.formRadioButtons path="post.commentStatus" options=commentStatus defaultValue="open" separator="&nbsp;" />
						</td>
						<td class="fieldlabel">是否置顶</td>
						<td>
						<@spring.formRadioButtons path="post.isTop" options=isTop defaultValue="false" separator="&nbsp;" />
						</td>
					</tr>
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
		turnHighLight(105015);
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