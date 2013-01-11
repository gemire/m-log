<#include "inc/simpleheader.ftl" />
	<div class="ui-layout-north" style="overflow:hidden;"><@tldwidget.placeholder path="/admin/top" cache=true idle=86400 /></div>
	<div class="ui-layout-west"><@tldwidget.placeholder path="/admin/leftMenu" cache=true idle=86400 /></div>
	<div class="ui-layout-south"><@tldwidget.placeholder path="/admin/bottom" cache=true idle=86400 /></div>
	<iframe class="ui-layout-center" id="main-frame" name="main" src="${base}/admin/redirect?id=105001" frameborder="0" scrolling="auto"></iframe>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('body').layout({
			north__closable:false,
			north__size:65,
			north__resizable:false,
			south__closable:false,
			south__size:20,
			south__resizable:false,
			west__size:160,
			togglerTip_open : "关闭",
			togglerTip_closed : "打开",
			resizerTip:"调整宽度"
		});
	});
	</script>
<#include "inc/simplefooter.ftl" />