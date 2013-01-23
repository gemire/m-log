<div class="span3">
	<div class="well sidebar-nav">
		<div>
			<ul class="nav nav-list">
				<li class="nav-header">分类目录</li>
				<@tldwidget.placeholder path="/widget/listCatalog" />
				<li class="nav-header">最近文章</li>
				<@tldwidget.placeholder path="/widget/recentPost" />
				<li class="nav-header">热门文章</li>
				<@tldwidget.placeholder path="/widget/mostViewPost" />
			</ul>
		</div>
	</div>
	<div class="well sidebar-nav">
		<div>
			<ul class="nav nav-list">
				<li class="nav-header">最新评论</li>
				<@tldwidget.placeholder path="/widget/recentComment" />
			</ul>
		</div>
	</div>
	<div class="well sidebar-nav">
		<div>
			<ul class="nav nav-list">
				<li class="nav-header">链接</li>
				<@tldwidget.placeholder path="/widget/links" />
			</ul>
		</div>
	</div>
	<div class="well sidebar-nav">
		<div>
			<ul class="nav nav-list">
				<li class="nav-header">统计信息</li>
				<li><a href="javascript:void(0);">文章总数：<@stat_post_count /></a></li>
				<li><a href="javascript:void(0);">评论总数：<@stat_comment_count /></a></li>
				<li><a href="javascript:void(0);">总点击量：<@stat_click_count /></a></li>
				<li><a href="${base}/admin" target="_blank">登录管理</a></li>
			</ul>
		</div>
	</div>
</div>