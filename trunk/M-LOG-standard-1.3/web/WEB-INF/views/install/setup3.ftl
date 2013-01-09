<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh_CN" lang="zh_CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="Content-Language" content="zh_CN" />
	<title>M-LOG安装引导</title>
	<link rel="stylesheet" href="${base}/style/install.css" type="text/css" />
	<script type="text/javascript" src="${base}/script/jquery.js"></script>
	<script type="text/javascript" src="${base}/script/jquery-validation/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/script/jquery-validation/jquery.metadata.js"></script>
	
	<script type="text/javascript" src="${base}/script/mlog.form.js"></script>
	
	<script type="text/javascript">
		String.prototype.startWith = function(s) {
			if (s == null || s == "" || this.length == 0 || s.length > this.length)
				return false;
			if (this.substr(0, s.length) == s)
				return true;
			else
				return false;
			return true;
		}
		String.prototype.endWith = function(s) {
			if (s == null || s == "" || this.length == 0 || s.length > this.length)
				return false;
			if (this.substring(this.length - s.length) == s)
				return true;
			else
				return false;
			return true;
		}
		
		$(document).ready(function(){
			/**
			 * 验证博客地址的合法性
			 */
			$.validator.addMethod("blogurl", function(value, element, params){
				if(!value.startWith("http://") && !value.startWith("https://")) {
					return false;
				}
				if(value.endWith('/') || value.endWith('\\')) {
					return false;
				}
				return true;
			});
			
			mlog.form.validate({
				selector : "#installForm",
				errorLabelContainer : "#error",
				wrapper: 'li',
				onfocusout : false,
				onkeyup : false,
				onclick : false,
				success : function(){
				}
			});
		});
	</script>
</head>
<body>
<div class="bodybg">
	<div id="wrap">
		<div id="logobanner">
		    <div id="logo">
		    	<a href="http://www.mspring.org"><img src="${base}/images/logo.png"/></a>
			</div>
		</div>
		<div class="clear"></div>
		<div class="bodycenter">
			<div class="flink">
				<div class="hd"><span class="hd1">博客信息</span></div>
				<div class="bd" align="center">
					<form  id="installForm" action="${base}/install/setup4" method="post" >
						<table style="width:80%;">
							<tr>
								<td colspan="2"><div id="error" class="message error" style="display:none;"></div></td>
							</tr>
							<tr>
								<td class="fieldlabel">博客名称：<span style="color:red;">*</span></td>
								<td class="field"><input type="text" class="textinput" name="blogname" validate="{required:true, messages:{required:'请输入博客名称！'}}" /></td>
							</tr>
							<tr>
								<td class="fieldlabel">博客地址：<span style="color:red;">*</span></td>
								<td class="field"><input type="text" class="textinput" name="blogurl" value='${blogurl}' validate="{required:true, blogurl:true, messages:{required:'请输入博客地址！', blogurl:'博客地址格式填写错误！'}}" /></td>
							</tr>
							<tr>
								<td class="fieldlabel">登录名：<span style="color:red;">*</span></td>
								<td class="field"><input type="text" class="textinput" name="username" validate="{required:true, messages:{required:'请输入博客登录名！'}}" /></td>
							</tr>
							<tr>
								<td class="fieldlabel">昵称：<span style="color:red;">*</span></td>
								<td class="field"><input type="text" class="textinput" name="alias" validate="{required:true, messages:{required:'请输入昵称！'}}" /></td>
							</tr>
							<tr>
								<td class="fieldlabel">密码：<span style="color:red;">*</span></td>
								<td class="field"><input type="password" class="textinput" name="password" id="password" validate="{required:true, messages:{required:'请输入密码！'}}"/></td>
							</tr>
							<tr>
								<td class="fieldlabel">重复密码：<span style="color:red;">*</span></td>
								<td class="field"><input type="password" class="textinput" name="repassword" validate="{required:true, equalTo:'#password', messages:{required:'请重复输入密码！',equalTo:'两次输入的密码不匹配'}}"/></td>
							</tr>
							<tr>
								<td class="fieldlabel">管理员邮箱：<span style="color:red;">*</span></td>
								<td class="field"><input type="text" class="textinput" name="email" validate="{required:true,email:true, messages:{required:'请输入管理员邮箱！',email:'请输入正确的邮箱格式'}}" /></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" class="btn" value=" 提 交 " />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="clear"></div>
			
		</div>
		<div class="clear"></div>
		<div id="foot">
		    <span id="bottomnav">
		        <a href="http://www.mspring.org" target="_blank">M-Spring</a>&nbsp;&nbsp;-&nbsp;&nbsp;<a href="http://wiki.mspring.org" target="_blank">Wiki</a>
		    </span>
		</div>
	</div>
</div>
</body>
</html>