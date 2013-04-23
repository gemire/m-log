<#include "/WEB-INF/views/admin/inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<script type='text/javascript' src='${base}/dwr/interface/dwrPush.js'></script>
<script type='text/javascript' src='${base}/dwr/engine.js'></script>
<script type='text/javascript' src='${base}/dwr/util.js'></script>
<div id="error" class="message error" style="display:none;"></div>
<textarea id="content" style="width:100%; height:300px;"></textarea>
<input type="button" class="btn" value="运行" onclick="run();" />
<script type="text/javascript">
	window.onload = function(){
		dwr.engine.setActiveReverseAjax(true);
	}
	function run(){
		$.get("${base}/admin/spider/rule/run?id=${rule.id}", function(){});
	}
	
	function receiveMessages(message) {
   		var c = document.getElementById('content');
   		c.value = '\n' + message + c.value
	}
	
	turnHighLight(815005020);
	$(document).ready(function(){
		//斑马线
		var tables=document.getElementsByTagName("table");
		var b=false;
		for (var j = 0; j < tables.length; j++){
			var cells = tables[j].getElementsByTagName("tr");
			//cells[0].className="color3";
			b=false;
			for (var i = 0; i < cells.length; i++){
				if(b){
					cells[i].className="color2";
					b=false;
				}
				else{
					cells[i].className="color3";
					b=true;
				};
			};
		}
	});
</script>
<#include "/WEB-INF/views/admin/inc/footer.ftl" />