<#-- 
/*
 * prompt message
 * @author Gao Youbo
 * @since 2012-07-31
 */  
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${blogname} - 系统消息</title>
	<link rel="stylesheet" type="text/css" href="${base}/style/global.css">
	<script type="text/javascript" src="${base}/script/jquery.js"></script>
	<style type="text/css">
		a						{ text-decoration: none; color: #002280 }
		a:hover					{ text-decoration: underline }
		body					{ font-size: 9pt; }
		table					{ font: 9pt Tahoma, Verdana; color: #000000 }
		input,select,textarea	{ font: 9pt Tahoma, Verdana; font-weight: normal; }
		select					{ font: 9pt Tahoma, Verdana; font-weight: normal; }
		.nav					{ font: 9pt Tahoma, Verdana; color: #000000; font-weight: bold }
		.nav a					{ color: #000000 }
		.header					{ font: 9pt Tahoma, Verdana; color: #FFFFFF; font-weight: bold; background-color: #4FB4DE }
		.header a				{ color: #FFFFFF }
		.category				{ font: 9pt Tahoma, Verdana; color: #000000; background-color: #fcfcfc }
		.tableborder			{ background: #C9F1FF; border: 1px solid #4FB4DE } 
		.singleborder			{ font-size: 0px; line-height: 1px; padding: 0px; background-color: #F8F8F8 }
		.smalltxt				{ font: 9pt Tahoma, Verdana }
		.outertxt				{ font: 9pt Tahoma, Verdana; color: #000000 }
		.outertxt a				{ color: #000000 }
		.bold					{ font-weight: bold }
		
		/*分页样式*/
		.epages{margin:3px 0;font:11px/12px Tahoma}
		.epages *{vertical-align:middle;}
		.epages a{padding:1px 4px 1px;border:1px solid #A6CBE7;margin:0 1px 0 0;text-align:center;text-decoration:none;font:normal 12px/14px verdana;}
		.epages a:hover{border:#659B28 1px solid;background:#f3f8ef;text-decoration:none;color:#004c7d}
		.epages input{margin-bottom:0px;border:1px solid #659B28;height:15px;font:bold 12px/15px Verdana;padding-bottom:1px;padding-left:1px;margin-right:1px;color:#659B28;}
	</style>
	
	<script type="text/javascript">
		var secs=3;//3秒
		for(i=1;i<=secs;i++) { 
			window.setTimeout("update(" + i + ")", i * 1000);
		} 
		function update(num) {
			$("#times").html("&nbsp;" + (secs - num + 1) + "秒...");
			if(num == secs) {
				dispatcher();
			} 
			else{
			}
		}
		function dispatcher(){
			<#if dispatcher?exists>
				document.location = '${dispatcher}';
			<#else>
				history.go(-1);
			</#if>
		}
	</script>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table width="500" border="0" align="center" cellpadding="3" cellspacing="1" class="tableborder">
		<tr class="header"> 
			<td height="25"><div align="center">${title!"信息提示"}<label id="times"></label></div></td>
	  	</tr>
	  	<tr bgcolor="#FFFFFF">
	  		<td height="80">
	  			<div align="center">
		  			<br>
		  			<b>${message!""}</b>
		  			<br>
		  			<br>
		  			<a href="javascript:dispatcher();">如果您的浏览器没有自动跳转，请点击这里</a>
		  			<br>
		  			<br>
		  		</div>
			</td>
		</tr>
	</table>
</body>
</html>