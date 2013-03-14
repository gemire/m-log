<style type="text/css">
.bnav {
	text-align: left;
	height: 25px;
	width: 100%;
	line-height: 25px;
	background: #fff;
	margin: 0 0;
	border: #B4B4B4 1px solid;
	border-top: none;
	z-index: 9999;
	position: fixed;
	top: 0;
	left: 0;
	_top: expression(documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight);
	_position: abstract;
	overflow: hidden;
	overflow: visible;
}
.bnav dl, .bnav dt, .bnav dd {
	padding: 0;
	margin: 0;
}
.close {
	position: absolute;
	right: 5px;
	height: 25px;
	width: 16px;
	text-indent: -9999px;
	padding-left: 10px;
}
.close a {
	background: url(${base}/images/up.gif) no-repeat center;
	width: 16px;
	display: block;
}
.bnav2 {
	height: 24px;
	line-height: 24px;
	margin: 1px;
	margin-bottom: 0;
	background: #E5E5E5;
}
.bnav .s1 {
	position: absolute;
	left: 10px;
}
.bnav .s1 img {
	padding-top: 3px;
	margin-right: 7px;
}
.bnav .s2 {
	position: absolute;
	right: 30px;
	color: #888;
}
.bnav .s2 span {
	padding-right: 10px;
}
.bnav .s2 a {
	margin: 0 6px;
}
.rolltext {
	position: absolute;
	left: 80px;
	height: 25px;
	line-height: 25px;
	overflow: hidden;
}
.rolltext dt,.rolltext dd {
	float: left;
	width: auto;
}
.rolltext a {
	display: block;
	height: 25px;
	overflow: hidden;
}
.bnav3 {
	height: 25px;
	width: 16px;
	line-height: 25px;
	margin: 0 0;
	padding-right: 6px;
	border-top: none;
	z-index: 999;
	position: fixed;
	top: 0;
	right: 0;
	_position: absolute;
	_top: expression(documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight);
	overflow: visible;
}
.bnav3 a {
	background: url(${base}/images/down.gif) no-repeat center;
	display: block;
	height: 25px;
	width: 16px;
	text-indent: -5000px;
}
</style>
</head>
<body>
<script type="text/javascript">
var closeBN = mlog.utils.getCookie("bnav");
if (closeBN == "0"){closeNav();}
function showNav(){
	$(".openClose").toggle(300);
	bodymargin(25); 
}
function closeNav(){
	$(".openClose").toggle(300);
	bodymargin(0);
	mlog.utils.setCookie("bnav", "0");
}
// 单行滚动
function SingleScroll(){
	$(".rolltext dd").animate({marginTop:"-25px"},500,function(){
		$(this).css({marginTop:"0px"}).find("a:first").appendTo(this);
	});
}
$(document).ready(function(){
	setInterval("SingleScroll()",3000);
	bodymargin(25);
});

function bodymargin(px){
	$(document.body).css("margin-top", px + "px");
}
</script>
<div class="bnav openClose">
    <div class="bnav2">
        <span class="s1">
            <a href="#"><img src="${base}/images/qqonline.gif"></a>
        </span>
        <dl class="rolltext">
            <dt>公告：</dt>
            <dd>
                <a href="javascript:void(0);">${notice!"暂无"}</a>
            </dd>
        </dl>
        <span class="s2">
            <#--<span><a href="#">[登录]</a><a href="#">[免费注册]</a></span><a href="#">购物车</a>|<a href="#">帮助中心</a>|<a href="#">在线留言</a>-->
            <span>欢迎<a href="javascript:void(0);">[${user.alias!user.name}]</a><a href="${base}/admin" target="_blank">后台管理</a>|<a href="javascript:void(0);">在线留言</a><a href="javascript:void(0);">帮助中心</a></span>
        </span>
        <span class="close"><a href="javascript:void(0)" onclick="closeNav()" title="关闭">关闭</a></span>
    </div>
</div>
<div class="bnav3 openClose" style="display:none;"><a href="javascript:void(0)" onclick="showNav()" title="打开">打开</a></div>