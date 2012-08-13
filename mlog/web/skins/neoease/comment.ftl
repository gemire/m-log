<div class="share">
    <a class="share-comment" href="#commentForm">
        ${post.commentCount}&nbsp;&nbsp;评论
    </a>
    <span class="clear"></span>
</div>
<div id="comments">
    <@list_comment>
    <div id="<@comment_id />" class="<#if list_index % 2 == 0>comment-even<#else>comment-odd</#if>">
        <img class="comment-header" title="<@comment_author />" alt="<@comment_author />" src=""/>
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
	                    <span class="em00" title=""></span>
	                    <span class="em01" title=""></span>
	                    <span class="em02" title=""></span>
	                    <span class="em03" title=""></span>
	                    <span class="em04" title=""></span>
	                    <span class="em05" title=""></span>
	                    <span class="em06" title=""></span>
	                    <span class="em07" title=""></span>
	                    <span class="em08" title=""></span>
	                    <span class="em09" title=""></span>
	                    <span class="em10" title=""></span>
	                    <span class="em11" title=""></span>
	                    <span class="em12" title=""></span>
	                    <span class="em13" title=""></span>
	                    <span class="em14" title=""></span>
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
</#if>