<#include "/WEB-INF/views/admin/inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<div id="message" class="message notice" style="height:300px;word-break:break-all;OVERFLOW:auto"></div>
<input type="button" class="btn" value="运行" onclick="run();" />
<iframe id="comet-frame" style="display: none;" src="${base}/common/push"></iframe>
<script type="text/javascript">
	function pushMessage(data) {
		//$('#message').val($('#content').val() + data + '\n');
		$('#message').append(data + '<br/>');
		var d = document.getElementById('message');
		message.scrollTop = message.scrollHeight;
	}
	
	function run(){
		$.get('${base}/admin/spider/rule/run?id=${rule.id}', function(){});
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