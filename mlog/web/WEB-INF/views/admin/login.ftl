<#import "/META-INF/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/3.4.1/build/cssreset/cssreset-min.css">
	<link rel="stylesheet" type="text/css" href="${base}/style/login.css">
</head>
<body>
	<div class="wrapper">
		<@spring.bind "user" />
		<form action="${base}/admin/doLogin" method="post" >
		<div class="loginBox">
			<div class="loginBoxCenter">
				<p><label for="username">用户名：</label></p>
				<#-- <p><input type="input" name="name" class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="请输入用户名" value="<#if user?exists && user.name?exists>${user.name}</#if>" /></p> -->
				<p>
					<@spring.formInput path="user.name" attributes='class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="请输入用户名"' />
					<#if showErrors!false>
					<@spring.showErrors separator="<br/>" classOrStyle="error" />
					</#if>
				</p>
				<p><label for="password">密码：</label></p>
				<#-- <p><input type="password" id="user_password" name="password" class="loginInput" required="required" placeholder="请输入密码" value="<#if user?exists && user.password?exists>${user.password}</#if>" /></p> -->
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
				<button class="loginBtn">登录</button>
			</div>
		</div>
		</form>
	</div>
</body>
</html>