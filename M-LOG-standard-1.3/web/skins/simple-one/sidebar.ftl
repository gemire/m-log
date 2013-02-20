		<div class="side-bar clearfix">
			<div class="side-bar-info">
				<h2 class="side-bar-title">导航菜单</h2>
				<ul class="side-list">
					<@m.widget path="/widget/menus" />
				</ul>
				<h2 class="side-bar-title">分类目录</h2>
				<ul class="side-list">
					<@m.widget path="/widget/listCatalog" />
				</ul>
				<h2 class="side-bar-title">最新文章</h2>
				<ul class="side-list">
					<@m.widget path="/widget/recentPost" />
				</ul>
				<h2 class="side-bar-title">置顶文章</h2>
				<ul class="side-list">
					<@m.widget path="/widget/topPost" />
				</ul>
				<h2 class="side-bar-title">最新评论</h2>
				<ul class="side-list">
					<@m.widget path="/widget/recentComment" />
				</ul>
				<h2 class="side-bar-title">链接</h2>
				<ul class="side-list">
					<@m.widget path="/widget/links" />
				</ul>
				<h2 class="side-bar-title">博客信息</h2>
				<ul class="side-list">
					<li>文章总数：<@stat_post_count /></li>
					<li>评论总数：<@stat_comment_count /></li>
					<li>总点击量：<@stat_click_count /></li>
					<li><a href="${base}/admin" target="_blank">登录管理</a></li>
				</ul>
			</div>
			<div class="side-bar-category">
				
			</div>
		</div>