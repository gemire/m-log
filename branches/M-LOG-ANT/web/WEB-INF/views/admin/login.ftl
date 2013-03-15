<#import "/META-INF/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>M-LOG登录</title>
	<link rel="icon" type="image/png" href="${base}/images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="${base}/style/login.css">
</head>
<body>
	<div class="wrapper">
		<@spring.bind "user" />
		<form action="${base}/admin/doSecurity" method="post" >
		<div class="loginBox">
			<div class="loginBoxCenter">
				<p><label for="username">用户名：</label></p>
				<p>
					<input type="text" id="name" name="name" class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="请输入用户名"/>
				</p>
				<p><label for="password">密码：</label></p>
				<p>
					<input type="password" id="password" name="password" class="loginInput" required="required" placeholder="请输入密码" />
				<p>
				<p>
					<label for="password">验证码：</label><img id="validateCode" src="${base}/common/validateCode" />&nbsp;&nbsp;<a href="javascript:changeImg();">换一张</a>
				</p>
				<p>
					<input name="validateCode" class="loginInput" required="required" placeholder="请输入验证码" />
				<p>
				<#if SPRING_SECURITY_LAST_EXCEPTION?has_content>
				<p style="color:red;">${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
				</#if>
			</div>
			<div class="loginBoxButtons">
				<@spring.formCheckbox path="user.rememberMe" />
				<label for="rememberMe">记住登录状态</label>
				<input type="submit" class="loginBtn" value="登录" />
			</div>
		</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	function changeImg(){
		var img = document.getElementById('validateCode');
		img.src = "${base}/common/validateCode";
	}
</script>
</html>