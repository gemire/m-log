<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "/WEB-INF/views/admin/inc/header.ftl" />

<link rel="stylesheet" type="text/css" href="${base}/script/lhgcalendar/skins/lhgcalendar.css">
<script type="text/javascript" src="${base}/script/lhgcalendar/lhgcalendar.min.js"></script>
<form id="spiderPostForm" name="spiderPostForm" action="${base}/admin/spider/post/list" method="POST">
	<@spring.bind "spiderPost" />
	<table>
		<tr>
			<td class="fieldlabel">状态</td>
			<td>
				<select name="posted">
					<option value="">--请选择--</option>
					<option value="1" <#if spiderPost.posted?exists && spiderPost.posted>selected="selected"</#if>>已发布</option>
					<option value="0" <#if spiderPost.posted?exists && !spiderPost.posted>selected="selected"</#if>>未发布</option>
				</select>
			</td>
			
			<td class="fieldlabel">标题</td>
			<td>
				<@spring.formInput path="spiderPost.title" attributes='class="textinput"' />
			</td>
			
			<td class="fieldlabel">规则</td>
			<td>
				<@spring.formSingleSelect path="spiderPost.rule.id" options=rules has_default=true attributes='class="textinput"' />
			</td>
			
			<td class="fieldlabel">抓取时间</td>
			<td>
				<input type="text" class="textinput" name="createTimeBeg" id="createTimeBeg" readonly="readonly" value="${createTimeBeg?default("")}" style="width:85px;" /> - 
				<input type="text" class="textinput" name="createTimeEnd" id="createTimeEnd" readonly="readonly" value="${createTimeEnd?default("")}" style="width:85px;" />
			</td>
			
			<td><input type="submit" class="btn" value=" 查 询 " /></td>
		</tr>
	</table>
		
	<@spring.bind "spiderPostPage" />
	<@spring.formHiddenInput path="spiderPostPage.pageNo" />
	<@spring.formHiddenInput path="spiderPostPage.totalPages" />
	<@spring.formHiddenInput path="spiderPostPage.totalCount" />
	
	<@spring.formHiddenInput path="spiderPostPage.sortEnable" />
	<@spring.formHiddenInput path="spiderPostPage.sort.field" />
	<@spring.formHiddenInput path="spiderPostPage.sort.order" />
	<table class="dtable" cellspacing="0" cellpadding="0">
		<tr>
			<th><input type="checkbox" onclick="mlog.form.checkAll(this, 'id');" /></th>
			<th>编号</th>
			<th>标题</th>
			<th>状态</th>
		</tr>
		<#if spiderPostPage?exists>
			<#list spiderPostPage.result as item>
				<#assign tdClass = "odd">
				<#if item_index%2 == 0>
					<#assign tdClass = "even">
				</#if>
				<tr>
					<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" /></td>
					<td class="${tdClass}">${item.id} </td>
					<td class="${tdClass}">${item.title} </td>
					<td class="${tdClass}">${item.posted?string("<font style='color:blue;'>已发布</font>","<font style='color:red;'>未发布</font>")} </td>
				</tr>
			</#list>
		</#if>
	</table>
	<table style="width:100%;">
		<tr>
			<td>
				<input type="button" class="btn" value=" 标记为已发布 " onclick="mlog.form.confirmSubmit('spiderPostForm', '${base}/admin/spider/post/changePosted?posted=true', '确认要将选中文章标记为已发布吗？');" />
				<input type="button" class="btn" value=" 标记为未发布 " onclick="mlog.form.confirmSubmit('spiderPostForm', '${base}/admin/spider/post/changePosted?posted=false', '确认要将选中文章标记为未发布吗？');" />
				<input type="button" class="btn" value=" 删除 " onclick="mlog.form.confirmSubmit('spiderPostForm', '${base}/admin/spider/post/delete', '确认要删除选中文章吗？');" />
				<input type="button" class="btn" value=" 发布到 " onclick="mlog.form.confirmSubmit('spiderPostForm', '${base}/admin/spider/post/publish', '确认要发布选中文章吗？');" />
				<input type="text" id="catalogSel" onclick="showMenu();" class="textinput" readonly="readonly"/>
				<input type="hidden" name="catalogs" id="catalogs"/>
				<div id="menuContent" class="menuContent" style="display:none; position: absolute; overflow:auto; background:#fff; border:1px solid #cccccc;z-index: 100;">
					<div style="display:block;float:right; padding:2px;"><input type="button" class="btn" onclick="hideMenu();" value="关闭" /></div>
					<ul id="catalogComboTree" class="ztree" style="margin-top:0; width:240px; height: 300px;"></ul>
				</div>
			</td>
			<td>
				<@mspring.pagingnavigator page=spiderPostPage form_id="spiderPostForm" />
			</td>
		</tr>
	</table>
</form>
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
		$("#menuContent").css({left:catalogSelOffset.left + "px", bottom:catalogSelObj.outerHeight() + "px"}).slideDown("fast");

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
	$(document).ready(function(){
		$.fn.zTree.init($("#catalogComboTree"), setting, zNodes);
	});
</script>
<script type="text/javascript">
	turnHighLight(815010005);
	$(document).ready(function(){
		$("#createTimeBeg").calendar({ maxDate:'#createTimeEnd', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
		$("#createTimeEnd").calendar({ minDate:'#createTimeBeg', format:'yyyy-MM-dd HH:mm:ss', targetFormat:'yyyy-MM-dd HH:mm:ss' });
	});
</script>
<#include "/WEB-INF/views/admin/inc/footer.ftl" />