<table class="top" cellpadding="0" cellspacing="0">
	<tr>
		<td class="logo" rowspan="2">
			<img src="${base}/images/logo.png" />
		</td>
		<td class="metadata">
			<div class="userinfo">
				欢迎：${currentUser.alias} | <a href="${base}/admin/logout">注销登录</a> | <a href="${siteurl}" target="_blank">主页</a> | <a href="http://www.mspring.org" target="_blank">M-LOG</a>
			</div>
		</td>
	</tr>
    <tr>
    	<td class="menu">
    		<a href="javascript:setMainFrameUrl('${base}/admin/post/create');">新建文章</a>&nbsp;&nbsp;
    		<a href="javascript:setMainFrameUrl('${base}/admin/catalog/list');">分类管理</a>&nbsp;&nbsp;
    		<a href="javascript:setMainFrameUrl('${base}/admin/link/list');">链接管理</a>&nbsp;&nbsp;
    		<a href="javascript:setMainFrameUrl('${base}/admin/setting/bloginfo');">博客设置</a>&nbsp;&nbsp;
    		<a href="javascript:setMainFrameUrl('${base}/admin/setting/skin');">换肤</a>&nbsp;&nbsp;
    		<a href="javascript:setMainFrameUrl('${base}/admin/about');">关于</a>
		</td> 
	</tr>
</table>