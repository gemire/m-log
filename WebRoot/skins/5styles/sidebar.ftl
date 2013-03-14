			<div id="sidebar">
				<div id="rss">
					<a href="${base}/rss.xml"><div id="rss-pic"></div></a>
				</div>
				<form method="get" id="searchform" action="${base}/search">
					<input type="text" value="" name="keyword" id="edtSearch" value="${keyword!""}" />
			    	<input type="submit" id="btnPost" name="btnPost" value="" />
				</form>
				<ul id="sidebarul">
					<li id="categories-3" class="widget widget_categories">
			        <h3 class="widgettitle">分类目录</h3>
			        	<ul>
						<@tldwidget.placeholder path="/widget/listCatalog" />
						</ul>
			        </li>
			        
			        <li id="recent-posts-1" class="widget widget_recent_entries">		
			        <h3 class="widgettitle">最近文章</h3>		
			        	<ul>
			        	<@tldwidget.placeholder path="/widget/recentPost" />
						</ul>
					</li>
					
					<li id="recent-posts-1" class="widget widget_recent_entries">		
			        <h3 class="widgettitle">热门文章</h3>		
			        	<ul>
			        	<@tldwidget.placeholder path="/widget/mostViewPost" />
						</ul>
					</li>
			        
			        <li id="recent-comments-5" class="widget widget_recent_comments">			
			        <h3 class="widgettitle">最近评论</h3>			
			        	<ul id="recentcomments">
			                <@tldwidget.placeholder path="/widget/recentComment" />
			            </ul>
			        </li>
			        
			        <li id="linkcat-2" class="widget widget_links">
			        <h3 class="widgettitle">友情链接</h3>
						<ul class='xoxo blogroll'>
						<@tldwidget.placeholder path="/widget/links" />
			            </ul>
			        </li>
				</ul>
			</div>