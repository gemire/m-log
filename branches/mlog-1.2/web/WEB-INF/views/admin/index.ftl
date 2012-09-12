<#include "inc/header.ftl" />
	<div class="ui-layout-north"><@widget.placeholder path="/admin/top"  /></div>
	<div class="ui-layout-west"><@widget.placeholder path="/admin/leftMenu" cache=false /></div>
	<div class="ui-layout-south"><@widget.placeholder path="/admin/bottom"/></div>
	<#-- <div class="ui-layout-center"><@widget.placeholder path="/admin/widget/about" /></div> -->
	<iframe class="ui-layout-center" name="main" src="${base}/admin/about" frameborder="0" scrolling="auto"></iframe>
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
<#include "inc/footer.ftl" />