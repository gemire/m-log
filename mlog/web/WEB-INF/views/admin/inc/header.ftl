<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>M-LOG Manager</title>
	<link rel="stylesheet" type="text/css" href="${base}/style/global.css">
	<script type="text/javascript" src="${base}/script/jquery.js"></script>
	<script type="text/javascript" src="${base}/script/jquery.layout.js"></script>
	<script type="text/javascript" src="${base}/script/jquery.ui.all.js"></script>
	<script type="text/javascript" src="${base}/script/lhgdialog/lhgdialog.min.js"></script>
	<script type="text/javascript" src="${base}/script/common.js"></script>
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
</head>
<body>
	<div class="ui-layout-north"><@widget.placeholder path="/admin/top"/></div>
	<div class="ui-layout-west"><@widget.placeholder path="/admin/leftMenu"/></div>
	<div class="ui-layout-south"><@widget.placeholder path="/admin/bottom"/></div>