<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blogname} - 404</title>
<style> 
	{
		PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px
	}
	BODY {
		BACKGROUND: #dad9d7; FONT-FAMILY: "微软雅黑"
	}
	IMG {
		BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: none
	}
	A  {
		CURSOR: pointer
	}
	A {
		TEXT-DECORATION: none; outline: none
	}
	A:hover {
		TEXT-DECORATION: underline
	}
	.bg {
		BACKGROUND: url(${base}/images/404_bg.jpg) #dad9d7 no-repeat center top; LEFT: 0px; OVERFLOW: hidden; WIDTH: 100%; POSITION: absolute; TOP: 0px; HEIGHT: 600px
	}
	.cont {
		MARGIN: 0px auto; WIDTH: 500px; LINE-HEIGHT: 20px
	}
	.c1 {
		HEIGHT: 360px; TEXT-ALIGN: center
	}
	.c1 .img1 {
		MARGIN-TOP: 180px
	}
	.c1 .img2 {
		MARGIN-TOP: 165px
	}
	.cont H2 {
		FONT-WEIGHT: normal; FONT-SIZE: 18px; COLOR: #555; HEIGHT: 35px; TEXT-ALIGN: center
	}
	.c2 {
		HEIGHT: 35px; TEXT-ALIGN: center
	}
	.c2 A {
		DISPLAY: inline-block; FONT-SIZE: 14px; MARGIN: 0px 4px; COLOR: #626262; PADDING-TOP: 1px; HEIGHT: 23px; TEXT-ALIGN: left; TEXT-DECORATION: none
	}
	.c2 A:hover {
		COLOR: #626262; TEXT-DECORATION: none
	}
	.c2 A.home {
		PADDING-LEFT: 30px; BACKGROUND: url(${base}/images/404_home.png); WIDTH: 66px
	}
	.c2 A.home:hover {
		BACKGROUND: url(${base}/images/404_home.png) 0px -24px
	}
	.c2 A.home:active {
		BACKGROUND: url(${base}/images/404_home.png) 0px -48px
	}
	.c2 A.re {
		PADDING-LEFT: 30px; BACKGROUND: url(${base}/images/404_ref.png); WIDTH: 66px
	}
	.c2 A.re:hover {
		BACKGROUND: url(${base}/images/404_ref.png) 0px -24px
	}
	.c2 A.re:active {
		BACKGROUND: url(${base}/images/404_ref.png) 0px -48px
	}
	.c3 {
		FONT-SIZE: 12px; COLOR: #999; HEIGHT: 180px; TEXT-ALIGN: center
	}
</style>
</head>
<body>
	<div class=bg>
		<div class=cont>
			<div class=c1><img class=img1 src="${base}/images/404.png"></div>
			<h2>Sorry…${blogname}提醒您访问的页面不存在</h2>
			<div class=c2>
				<a class=home href="${base}">返回首页</A>
				<a class=re href="javascript:history.back();">返回上页</a>
			</div>
		<div class=c3>提醒：您可能输入了错误的网址，或者该网页已删除或移动</div>
	</div>
	</div>
</body>
</html>