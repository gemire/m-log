<#include "header.ftl" />
  <!-- #container -->
  <div class="container" id="container">
    <div class="row">
      <!-- #mainContent -->
      <div class="span8" id="mainContent">
        <div class="content">
          <!-- .post -->
    <#if post?exists>
          <div class="post box">
            <!-- .post-title -->
            <div class="post-title clearfix">
              <div class="pull-left">
                <h4>
                  <a href="<@postUrl post="post" />" title="${post.title}">
                    <#if post.isTop>
                    <img src="http://x.libdd.com/farm1/2893f5/070f0fcd/sticky.gif" alt="置顶文章" /> 
                    <span style="color:red;">[置顶]</span>
                    </#if>
                    ${post.title}
                  </a>
                </h4>
                <p>
                  <i class="icon-user"></i> 
                  ${post.author.alias}
                  <i class="icon-calendar"></i> ${post.createTime}
                  <i class="icon-thumbs-up"></i> 被围观 ${post.viewCount}+
                </p>
              </div>

<!--               <div class="post-comment-counts">
	        	<div class="post-comment-nums">
		  			<span class="pull-right btn-success btn-mini badge dd-thread-count">25</span>
				</div>
                <a href="<?=post.url?>#post-comment" >
                  <span  class="pull-right badge badge-inverse post-comment-num" title="点点和多说评论都支持哈！">22</span>                  
                </a>
              </div>
 -->
            </div>
            <!-- /.post-title -->

            

            <!-- view.list -->
              <!-- .post-content -->
              <div class="post-content clearfix">

                <!-- post.text -->
                  <div class="post-summary">
                    ${post.content}
                  </div>
                <!-- /post.text -->
              </div>
            <!-- /.post-content -->
            <!-- /view.list -->
              <!-- .post-meta -->
              <div class="post-meta clearfix">
                <div class="pull-left">
                  <h6> <i class="icon-tags"></i>
                    <a href="<?= tag.url ?>" rel="tag" title="<?= tag.name ?>" target="_blank"> <?= tag.name ?> </a> 
                </div>
              </div>
              <!-- /.post-meta -->
          </div>

	</#if>

          <!-- .pagination -->
          <div class="pagination pagination-centered clearfix">

            <ul>
              <li>
                  <a href="<?= dian.data.pagination.prev.url  ?>">上一页</a>
              </li>
              <li>
                    <span class="current"><?= page.number ?></span>
                    <a class='inactive' href="<?= page.url ?>"><?= page.number ?></a>
              </li>
              <li>
                  <a class='page_next' href='<?= dian.data.pagination.next.url ?>'>下一页</a>
              </li>
            </ul>
          </div>
          <!-- /.pagination -->

        <!-- /.post -->
        </div>
      </div>
      <!-- /#mainContent -->

      <!-- #sidebar -->
      <#include "sidebar.ftl" />
      <!-- /#sidebar -->
    </div>
<#include "footer.ftl" />