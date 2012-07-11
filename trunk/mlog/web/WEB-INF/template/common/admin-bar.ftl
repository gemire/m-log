<style type="text/css">
#adminbar * {
	height: auto;
	width: auto;
	margin: 0;
	padding: 0;
	position: static;
	text-transform: none;
	letter-spacing: normal;
	line-height: 1;
	font: normal 13px/ 28px sans-serif;
	color: #ccc;
	text-shadow: #444 0 -1px 0;
}
#adminbar {
   	background-image: linear-gradient(top,#FFFFFF,#E5E5E5);
	background-image: -moz-linear-gradient(top,#FFFFFF,#E5E5E5);
	background-image: -ms-linear-gradient(top,#FFFFFF,#E5E5E5);
	background-image: -o-linear-gradient(top,#FFFFFF,#E5E5E5);
	background-image: -webkit-gradient(linear,left top,left bottom,from(#FFFFFF),to(#E5E5E5));
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#FFFFFF', endColorstr='#E5E5E5');
	border-bottom: 1px solid #E5E5E5;
	height: 26px;
	line-height: 26px;
}

#adminbar a, #adminbar span span {
    border-right: 1px solid #D9D9D9;
    color: #4C4C4C;
    float: left;
    line-height: 14px;
    margin: 6px 0;
    padding: 0 6px;
    text-decoration: none;
    text-shadow: 0 -1px 0 #FFFFFF;
    font-weight: normal;
}

#adminbar a:hover, #adminbar a.hover {
    background-color: #EEF2FA;
    border-left-color: #707070;
    border-radius: 0 13px 13px 0;
    margin: 0px;
    line-height: 26px;
}
</style>

<div id="adminbar">
	<a href="http://www.mspring.org" target="_blank" class="hover">
        M-LOG
    </a>
    
    <span style="float: right;">
    	<a href="${path}/articleAtom.action" target="_blank">订阅本站</a>
	    <#-- 如果用户已经登录 -->
	    <#if currentUser?exists>
	    	<a href="javascript:">欢迎您：${currentUser.name}</a>
	    	<a href="${path}/admin/admin_default.jsp" target="_blank">后台管理</a>
	    	<a href="${path}/admin/logout.action">退出登录</a>
	    <#-- 如果如果访客名字存在 -->
	    <#elseif guest?exists>
	    	<a href="javascript:window.external.AddFavorite('${blogurl}', '${blogname}')">收藏本站</a>
	    	<a  href='#' onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('${blogurl}');">设为首页</a>
	    	<a href="${path}/admin/admin_login.jsp" target="_blank">登录</a>
	    </#if>
    </span>
</div> 
