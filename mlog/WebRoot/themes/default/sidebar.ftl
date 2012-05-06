<div id="sidebar-right" class="sidebar">
	
	<div class="clear-block block block-catalog">
		<h2>${articleCategory}</h2>
		<div class="content">
			<div class="item-list" id="item-list-category">
				<ul>
					${widget("CategoryListWidget")}
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>${recentArticle}</h2>
		<div class="content">
			<div class="item-list" id="item-list-top">
				<ul>
					${widget("RecentArticleWidget", "20")}
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>${recentComment}</h2>
		<div class="content">
			<div class="item-list" id="item-list-topcomment">
				<ul>
					${widget("RecentCommentWidget", "20")}
				</ul>
			</div>
		</div>
	</div>
	
	<div class="clear-block block block-catalog">
		<h2>${friendLinks}</h2>
		<div class="content">
			<div class="item-list" id="item-list-topcomment">
				<ul>
					${widget("LinkListWidget")}
				</ul>
			</div>
		</div>
	</div>
</div>