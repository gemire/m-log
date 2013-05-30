<div class="span3">
	<div class="well sidebar-nav">
		<div>
			<ul class="nav nav-list">
				<li class="nav-header">分类目录</li>
				<@m.render_allCatalogList template="/widget/catalogList.ftl" idle=86400 />
				<li class="nav-header">最近文章</li>
				<@m.render_recentPost template="/widget/recentPost.ftl" maxResult=20 idle=3600 />
				<li class="nav-header">热门文章</li>
				<@m.render_mostViewPost template="/widget/mostViewPost.ftl" idle=3600 />
			</ul>
		</div>
	</div>
	<@m.render_recentComment template="/widget/recentComment.ftl" maxResult=20 idle=1800 />
	<div class="well sidebar-nav">
		<div>
			<ul class="nav nav-list">
				<li class="nav-header">链接</li>
				<@m.render_link template="/widget/link.ftl" idle=3600/>
			</ul>
		</div>
	</div>
</div>