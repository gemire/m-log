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
	/*border:solid 1px red;*/
	height:100%;
}
</style>

<div class="ui-layout-center">
	<form id="configForm" method="post" action="${base}/admin/setting/saveSetting">
		<div id="slide">
			<h1>信息配置</h1>
		  	<div><@widget.placeholder path="/admin/setting/info" /></div>
		  	<h2>皮肤设置</h2>
		  	<div><@widget.placeholder path="/admin/setting/skin" /></div>
		</div>
		<div style="text-align:center; margin-top:10px;">
			<input type="submit" class="btn" value=" 提 交 " />
		</div>
	</form>
</div>

<#include "../inc/footer.ftl" />