<#import "/META-INF/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<#-- <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/3.4.1/build/cssreset/cssreset-min.css"> -->
	<link rel="stylesheet" type="text/css" href="${base}/style/login.css">
</head>
<body>
	<div class="wrapper">
		<@spring.bind "user" />
		<form action="${base}/admin/doLogin" method="post" >
		<div class="loginBox">
			<div class="loginBoxCenter">
				<p><label for="username">用户名：</label></p>
				<p>
					<@spring.formInput path="user.name" attributes='class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="请输入用户名"' />
					<#if showErrors!false>
					<@spring.showErrors separator="<br/>" classOrStyle="error" />
					</#if>
				</p>
				<p><label for="password">密码：</label></p>
				<p>
					<@spring.formPasswordInput path="user.password" attributes='class="loginInput" required="required" placeholder="请输入密码"'  />
					<#if showErrors!false>
	             	<@spring.showErrors separator="<br/>" classOrStyle="error" />
	             	</#if>
				<p>
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
</html>