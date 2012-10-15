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
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call??>${base}/${item.call}<#if item.target?? && item.target?has_content>#target=${item.target}</#if><#else>javascript:void(0);</#if>', open:${item.open?string("true","false")} },
				<#else>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call??>${base}/${item.call}<#if item.target?? && item.target?has_content>#target=${item.target}</#if><#else>javascript:void(0);</#if>', open:${item.open?string("true","false")} }
				</#if>
				<#--
				<#if item_has_next>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call??>${base}/${item.call}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' },
				<#else>
					{ id:${item.id}, pId:${item.parent?default("0")}, name:'${item.name?default("")}', url:'<#if item.call??>${base}/${item.call}<#else>javascript:void(0);</#if>', open:${item.open?string("true","false")}, target:'${item.target?default("")}' }
				</#if>
				-->
			</#list>
		</#if>
	];

	function onClick(e,treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeMenu");
		zTree.expandNode(treeNode);
		
		e.preventDefault();
		jumpURL(treeNode.url);
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeMenu"), setting, zNodes);
	});
	
	
	window.onload = function(){
		var url = window.location.href.substring(window.location.href.indexOf("#"));
		var urlInfo = getUrlInfo(url);
		
		
		var goto = "";
		if(!urlInfo || !urlInfo.url){
			goto = mlog.variable.base + "/admin/about";
		}
		else{
			goto = urlInfo.url;
		}
		$("iframe[name=main]").attr("src", goto);
		
	}
	
	function jumpURL(url){
		var urlInfo = getUrlInfo(url);
		
		if(urlInfo){
			var iframe = getIframe(urlInfo.target);
			if(!iframe){
				window.location = urlInfo.url;
			}
			else{
				$(iframe).attr("src", urlInfo.url);
			}
			window.location = url;
		}
	}
	
	function getIframe(name){
		if(!name) return null;
		var f = $("iframe[name=" + name + "]");
		return f;
	}
	
	function getUrlInfo(info){
		var abstractUrl = getUrl(info);
		if(abstractUrl) {
			abstractUrl = mlog.variable.base + "/admin/" + abstractUrl;
		}
		return {
			target : getTarget(info),
			url : abstractUrl
		}
	}
	
	function getTarget(info){
		var target = info.match(new RegExp("[\#]target=([^\&]*)(\&?)","i"));
		return target ? target[1] : target;
	}
	
	function getUrl(info){
		var start = info.indexOf("#");
		if(start === -1){
			return "";
		}
		var end = info.lastIndexOf("#");
		if(start === end){
			return info.substring(start + 1);
		}
		else{
			return info.substring(start + 1, end);
		}
	}
</script>

<div class="treeMenuDiv">
	<ul id="treeMenu" class="ztree" ></ul>
</div>