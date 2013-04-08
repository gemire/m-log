<#include "../../inc/simpleheader.ftl" />
<ul id="treeMenu" class="ztree" ></ul>
<script type="text/javascript">
	var setting = {
		view: {
			showLine: true,
			selectedMulti: false,
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick
		}
	};
	var zNodes =[
		${treeJson!""}
		<#--
		<#if files?exists>
			<#list files as item>
				<#if item_has_next>
					{ id:"${item.name}", pId:'${item.parentFile.name}', name:'${item.name?default("")}', url:'${base}/admin/system/skin/edit_main?skin=${skin}&path=${item.name}', target:'skin_edit_main', open:true },
				<#else>
					{ id:"${item.name}", pId:'${item.parentFile.name}', name:'${item.name?default("")}', url:'${base}/admin/system/skin/edit_main?skin=${skin}', target:'skin_edit_main', open:true }
				</#if>
			</#list>
		</#if>
		-->
	];

	function onClick(e,treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		zTree.expandNode(treeNode);
		//e.preventDefault();
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeMenu"), setting, zNodes);
	});
</script>
<#include "../../inc/simplefooter.ftl" />