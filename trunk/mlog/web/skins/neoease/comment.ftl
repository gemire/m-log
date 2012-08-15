<div class="share">
    <a class="share-comment" href="#commentForm">
        ${post.commentCount}&nbsp;&nbsp;评论
    </a>
    <span class="clear"></span>
</div>
<div id="comments">
    <@list_comment>
    <div id="<@comment_id />" class="<#if list_index % 2 == 0>comment-even<#else>comment-odd</#if>">
        <img class="comment-header" title="<@comment_author />" alt="<@comment_author />" src="<@comment_gravatar />"/>
        <div class="comment-panel">
            <div class="left">
                <a href="<@comment_author_url />" target="_blank"><@comment_author /></a>
            </div>
            <div class="right">
                <a rel="nofollow" href="javascript:replyTo('<@comment_id />');">Reply</a>
                &nbsp;|&nbsp;
                <@comment_time />
            </div>
            <span class="clear"></span>
            <div class="article-body"><@comment_content /></div>
        </div>
        <span class="clear"></span>
    </div>
    </@list_comment>
</div>
<#if post.commentStatus == "open">
<div class="form">
    <h4>发表评论</h4>
    <form id="comment-form" target="_self" method="post" action="${base}/comment/post">
    	<input type="hidden" name="postId" value="${postId}" />
	    <table id="commentForm">
	        <tbody>
	            <tr>
	                <td colspan="2">
	                    <input type="text" class="normalInput" name="author" id="commentName" value="${author!""}"/>
	                    <label for="commentName">姓名</label>
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2">
	                    <input type="text" class="normalInput" name="email" id="commentEmail" value="${email!""}"/>
	                    <label for="commentEmail">邮箱</label>
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2">
	                    <input type="text" id="commentURL" name="url" value="${url!""}"/>
	                    <label for="commentURL">URL</label>
	                </td>
	            </tr>
	            <tr>
	                <td id="emotions" colspan="2">
	                    <span class="em00" title="微笑"></span>
	                    <span class="em01" title="大笑"></span>
	                    <span class="em02" title="高兴"></span>
	                    <span class="em03" title="悲伤"></span>
	                    <span class="em04" title="哭泣"></span>
	                    <span class="em05" title="无语"></span>
	                    <span class="em06" title="烦躁"></span>
	                    <span class="em07" title="生气"></span>
	                    <span class="em08" title="我瞅"></span>
	                    <span class="em09" title="惊讶"></span>
	                    <span class="em10" title="酷"></span>
	                    <span class="em11" title="顽皮"></span>
	                    <span class="em12" title="爱心"></span>
	                    <span class="em13" title="心碎"></span>
	                    <span class="em14" title="魔鬼"></span>
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2">
	                    <textarea rows="10" cols="96" name="content" id="comment"></textarea>
	                </td>
	            </tr>
	            <tr>
	                <td>
	                    <#--
	                    <input type="text" class="normalInput" id="commentValidate"/>
	                    <@widget.placeholder path="/common/validateImage" />
	                    -->
	                </td>
	                <th>
	                    <span class="tip" id="commentErrorTip"></span>
	                </th>
	            </tr>
	            <tr>
	                <td colspan="2" align="right">
	                    <button id="submitCommentButton" onclick="page.submitComment();"> 提交评论 </button>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		//将评论文本中的表情标识，替换成图片
		mlog.replaceCommentsEm("#comments .article-body");
	});
</script>
</#if>