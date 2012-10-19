<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
	<style type="text/css">
          #wall{ width:100%; margin:0 auto; font-size:0;}
          .item{ display:inline-block; margin:4px; width:158px; *display:inline; zoom:1; vertical-align:top;}
          .itemimage{ border:solid 2px #efefef; padding:2px; max-height: 150px; max-width: 150px;}
          .itemmeta{ display:block; width:100%; text-align:center; font-size:12px; color:#666;  line-height:20px; }
          .itemmeta .itemtitle{ text-decoration:none; color: #666600;}
          .itemmeta .itemtitle:hover{ text-decoration:none; color:red;}
          .itemmeta .ctrl, .itemmeta .ctrl:hover { text-decoration:none; color:blue;}
	</style>
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="javascript:void(0);" class="here">列表</a></li>
			    <li><a href="${base}/admin/album/create">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			    <li><a href="${base}/admin/photo/upload">图片上传</a></li>
			    <li><a href="${base}/admin/album/config">相册设置</a></li>
			</ul>
		</div>
		<div class="tab-container">
			<form id="albumForm" name="catalogForm" action="${base}/admin/album/list" method="POST">
				<div id="wall">
					<#if albumPage??>
						<#list albumPage.result as album>
							<div class="item">
								<img src="${base}/images/nophoto.gif" alt="${album.description!album.name}" class="itemimage" />
								<div class="itemmeta">
									<a href="#" class="itemtitle">${album.name!""}</a>
									<a href="${base}/admin/album/edit?id=${album.id}" class="ctrl">编辑</a>
									<a href="${base}/admin/album/delete?id=${album.id}" class="ctrl">删除</a>
								</div>
							</div>
						</#list>
					</#if>
				</div>
			</form>
		</div>
	</div>
<#include "../inc/footer.ftl" />