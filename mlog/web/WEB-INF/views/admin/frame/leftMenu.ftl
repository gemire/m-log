<link rel="stylesheet" type="text/css" href="${base}/script/jquery-zTree/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="${base}/script/jquery-zTree/jquery.ztree.all-3.2.min.js"></script>
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
		<#if items?exists>
			<#list items as item>
				<#if item_has_next>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call??>${base}/${item.call}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' },
				<#else>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call??>${base}/${item.call}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' }
				</#if>
			</#list>
		</#if>
	];

	function onClick(e,treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		zTree.expandNode(treeNode);
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeMenu"), setting, zNodes);
	});
</script>

<div class="treeMenuDiv">
	<ul id="treeMenu" class="ztree" ></ul>
</div>