      <div class="span4 " id="sidebar">
        <!-- weget theme-->
        <div class="weget box" id="ad1">
          <h5>{$_侧边栏AD1-TITLE}</h5>
          {$_侧边栏AD1-CONTENT}
        </div>
        <!-- / weget -->
        
        <!-- weget tab -->
        <div class="weget box">
          <ul class="nav nav-list">
            <li class="nav-header">分类目录</li>
            <@m.render_allCatalogList template="/widget/catalogList.ftl" idle=86400 />
            <li class="nav-header">最近文章</li>
            <@m.render_recentPost template="/widget/recentPost.ftl" maxResult=20 idle=3600 />
            <li class="nav-header">热门文章</li>
            <@m.render_mostViewPost template="/widget/mostViewPost.ftl" idle=3600 />
          </ul>
        </div>
        <!-- / weget tab -->


        <!-- frend links -->
        <div class="weget box" id="links">
          <h5>友情链接</h5>
          <div id="links">
            <ul class="nav nav-pills">
              <@m.render_link template="/widget/link.ftl" idle=3600/>
            </ul>                
          </div>
        </div>
        <!-- / frend links -->

      </div>
