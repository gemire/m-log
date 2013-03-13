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
				<#--
				<#if item_has_next>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call?has_content>${base}/${item.call}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' },
				<#else>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call?has_content>${base}/${item.call}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' }
				</#if>
				-->
				<#if item_has_next>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.type = 'tree_item'>${base}/admin/redirect?id=${item.id}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' },
				<#else>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.type = 'tree_item'>${base}/admin/redirect?id=${item.id}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' }
				</#if>
			</#list>
		</#if>
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

<div class="treeMenuDiv">
	<ul id="treeMenu" class="ztree" ></ul>
</div>