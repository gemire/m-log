<#include "header.ftl" />
	<div id="main">
		<div id="mainContent">
			<div class="forFlow">
				<div class="main-posts main-item">
					<div class="post-copyright">您访问的文章需要密码才能查看，请输入密码！</div>
					<div style="margin:10px;padding:10px;">
						<#if not_token?has_content>
							<span style="color:red;">密码验证失败</span>
						</#if>
						<form action="${blogurl}/post/token" method="POST">
							<input type="hidden" name="postId" value="${postId}"/>
							<input type="hidden" name="postUrl" value="${postUrl}"/>
							<input type="text" name="password" value=""/>
							<input type="submit" class="btn" value="提交"/>
						</form>
					</div>
				</div>
			</div>
		</div>
		<#include "sidebar.ftl" />
	</div>
<#include "footer.ftl" />