<#-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>M-LOG Manager</title>
	<link rel="stylesheet" type="text/css" href="${base}/style/global.css">
	
	<script type="text/javascript" src="${base}/script/jquery.js"></script>
	<script type="text/javascript" src="${base}/script_variable.js"></script>
	
	<#-- jquery ui -->
	<link rel="stylesheet" type="text/css" href="${base}/script/jquery-ui/themes/base/jquery.ui.all.css">
	<script type="text/javascript" src="${base}/script/jquery-ui/jquery.ui.all.js"></script>
	
	<#-- jquery layout -->
	<link rel="stylesheet" type="text/css" href="${base}/script/jquery-layout/layout-default.css">
	<script type="text/javascript" src="${base}/script/jquery-layout/jquery.layout.min.js"></script>
	
	<#-- jquery validate -->
	<script type="text/javascript" src="${base}/script/jquery-validation/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/script/jquery-validation/jquery.metadata.js"></script>
	
	<#-- lhgdialog -->
	<script type="text/javascript" src="${base}/script/lhgdialog/lhgdialog.min.js"></script>
	
	<#-- jquery ztree -->
	<link rel="stylesheet" type="text/css" href="${base}/script/jquery-zTree/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript" src="${base}/script/jquery-zTree/jquery.ztree.all-3.2.min.js"></script>
	
	<#-- jquery multiselect -->
	<link rel="stylesheet" type="text/css" href="${base}/script/jquery-multiselect/jquery.multiselect.css">
	<script type="text/javascript" src="${base}/script/jquery-multiselect/src/jquery.multiselect.min.js"></script>
	
	
	<script type="text/javascript" src="${base}/script/common.js"></script>
</head>
<body>
	<div class="ui-layout-north"><@widget.placeholder path="/admin/top"/></div>
	<div class="ui-layout-west"><@widget.placeholder path="/admin/leftMenu"/></div>
	<div class="ui-layout-south"><@widget.placeholder path="/admin/bottom"/></div>