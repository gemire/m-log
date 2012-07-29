<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />

<link rel="stylesheet" type="text/css" href="${base}/script/jquery-kandytabs/kandytabs.css">
<script type="text/javascript" src="${base}/script/jquery-kandytabs/kandytabs.pack.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("#slide").KandyTabs({
		    action:"slide",
		    trigger:"click"
		});
	});
</script>
<style type="text/css">
#slide{
	font-size:12px;
}

#slide dd{
	height:100%;
}
</style>

<div class="ui-layout-center">
	<form id="configForm" method="post" action="${base}/admin/setting/saveSetting">
		<div id="slide">
			<h1>信息配置</h1>
		  	<div><@widget.placeholder path="/admin/setting/info" cache=false /></div>
		  	<h2>全局设置</h2>
		  	<div><@widget.placeholder path="/admin/setting/global" cache=false /></div>
		  	<h2>皮肤设置</h2>
		  	<div><@widget.placeholder path="/admin/setting/skin" cache=false /></div>
		</div>
		<div style="text-align:center; margin-top:10px;">
			<input type="submit" class="btn" value=" 提 交 " />
		</div>
	</form>
</div>


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
		resizerClass: 'ui-state-default',
		//west__spacing_closed:10,
		west__onresize: function (pane, $Pane) {  
            
        }
	});
});
</script>
<#include "../inc/footer.ftl" />