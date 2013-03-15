<#import "include/fun.ftl" as simple>
<#include "header.ftl" />

		<div class="row-fluid">
			<#include "sidebar.ftl" />
			<div class="span9">
				<div class="row-fluid">
					<div class="post-copyright">您访问的相册需要密码才能查看，请输入密码！</div>
					<div style="margin:10px;padding:10px;">
						<#if not_token?has_content>
							<span style="color:red;">密码验证失败</span>
						</#if>
						<form action="${blogurl}/album/token" method="POST">
							<input type="hidden" name="albumId" value="${albumId}"/>
							<input type="text" name="password" value=""/>
							<input type="submit" value="提交"/>
						</form>
					</div>
				</div>
			</div>
		</div>
		

<#include "footer.ftl" />