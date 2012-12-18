<div id="sidebar-right" class="sidebar">
	<div class="clear-block block block-catalog">
		<h2>分类目录</h2>
		<div class="content">
			<div class="item-list" id="item-list-category">
				<ul>
					<@tldwidget.placeholder path="/widget/listCatalog" />
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>最新文章</h2>
		<div class="content">
			<div class="item-list" id="item-list-top">
				<ul>
					<@tldwidget.placeholder path="/widget/recentPost" />
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>最新评论</h2>
		<div class="content">
			<div class="item-list" id="item-list-top">
				<ul>
					<@tldwidget.placeholder path="/widget/recentComment" />
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>链接</h2>
		<div class="content">
			<div class="item-list" id="item-list-topcomment">
				<ul>
					<@tldwidget.placeholder path="/widget/links" />
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>Meta</h2>
		<div class="content">
			<div class="item-list" id="item-list-topcomment">
				<ul>
					<li>文章总数：<@stat_post_count /></li>
					<li>评论总数：<@stat_comment_count /></li>
					<li>总点击量：<@stat_click_count /></li>
					<li><a href="${base}/admin" target="_blank">登录管理</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>