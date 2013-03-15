<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
<script type="text/javascript">
	var setting = {
		view: {
			showLine: true,
			selectedMulti: false,
			dblClickExpand: false
		},
		check: {
			enable: true
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
		<#if treeItems?exists>
			<#list treeItems as item>
				<#if item_has_next>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'javascript:void(${item.id});', open:false, target:'${item.target?default("")}' },
				<#else>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'javascript:void(${item.id});', open:false, target:'${item.target?default("")}' }
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
		setCheckedItems();
	});
	
	//设置选中项
	function setCheckedItems(){
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		
		/**
		 * 被勾选时：关联父
		 * 取消勾选时：关联父  关联子
		 */
		zTree.setting.check.chkboxType = { "Y" : "p", "N" : "ps" };
		
		<#if authorized?exists>
			<#list authorized as item>
				zTree.checkNode(zTree.getNodeByParam("id", "${item.id}", null), true, true);
			</#list>
		</#if>
		
		/**
		 * 被勾选时：关联父  关联子
		 * 取消勾选时：关联父  关联子
		 */
		zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
	}
	
	//获取选中项
	function getCheckedItems(){
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		var nodes = zTree.getCheckedNodes(true);
		
		var checkedItems = "";
		for (var i = 0; i < nodes.length; i++) {
            checkedItems += nodes[i].id;
            if(i < nodes.length - 1){
            	checkedItems += ",";
            }
        }
        $("#checkedItems").val(checkedItems);
	}
	
	//获取选未中项
	function getNotCheckedItems(){
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		var nodes = zTree.getCheckedNodes(false);
		
		var notCheckedItems = "";
		for (var i = 0; i < nodes.length; i++) {
            notCheckedItems += nodes[i].id;
            if(i < nodes.length - 1){
            	notCheckedItems += ",";
            }
        }
        $("#notCheckedItems").val(notCheckedItems);
	}
	
	function submitForm(){
		getCheckedItems();
		getNotCheckedItems();
		$("#roleForm").submit();
	}
</script>
<form class="form" id="roleForm" action="${base}/admin/role/authorize/save" method="POST">
	<input type="hidden" id="checkedItems" name="checkedItems" />
	<input type="hidden" id="notCheckedItems" name="notCheckedItems" />
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="infotable">
		<tr>
			<td class="partition">授权</td>
		</tr>
		<tr>
			<td>
				<div class="treeMenuDiv">
					<ul id="treeMenu" class="ztree" ></ul>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center;">
				<input type="button" class="btn" onclick="submitForm();" value=" 提交 " />
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	turnHighLight(310020);
</script>
<#include "../inc/footer.ftl" />