<#include "../../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<iframe class="ui-layout-center" id="skin_edit_main" name="skin_edit_main" src="${base}/admin/system/skin/edit_main?skin=${skin}&path=${path}" frameborder="0" scrolling="auto"></iframe>
<iframe class="ui-layout-east" id="skin_edit_files" name="skin_edit_files" src="${base}/admin/system/skin/edit_files?skin=${skin}" frameborder="0" scrolling="auto"></iframe>
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
<#include "../../inc/footer.ftl" />