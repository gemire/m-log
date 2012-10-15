<table class="top" cellpadding="0" cellspacing="0">
	<tr>
		<td class="logo" rowspan="2">
			<img src="${base}/images/logo.png" />
		</td>
		<td class="metadata">
			<div class="userinfo">
				欢迎：${currentUser.alias} | <a href="${base}/admin/logout">注销登录</a> | <a href="${base}" target="_blank">浏览博客</a>
			</div>
		</td>
	</tr>
    <tr>
    	<td class="menu">
    		<a href="${base}/admin#post/create#target=main">新建文章</a>&nbsp;&nbsp;
    		<a href="${base}/admin#catalog/list#target=main">分类管理</a>&nbsp;&nbsp;
    		<a href="${base}/admin#link/list#target=main">链接管理</a>&nbsp;&nbsp;
    		<a href="${base}/admin#setting/bloginfo#target=main">博客设置</a>&nbsp;&nbsp;
    		<a href="${base}/admin#setting/skin#target=main">换肤</a>&nbsp;&nbsp;
    		<a href="${base}/admin#about#target=main">关于</a>
		</td> 
	</tr>
</table>
<script type="text/javascript">
	$(document).ready(function(){
		$(".top .menu a").click(function(event){
			event.preventDefault();
			jumpURL(this.href);
		});
	});
</script>