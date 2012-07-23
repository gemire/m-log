<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
	<script type="text/javascript" src="${base}/script/tiny_mce/tiny_mce.js" charset="utf-8"></script>
	<script type="text/javascript">
		tinyMCE.init({
            // General options
            language: 'zh-cn',
            mode : "exact",
            elements : "content",
            theme : "advanced",
            plugins : "autosave,style,advhr,advimage,advlink,preview,inlinepopups,media,paste,fullscreen,syntaxhl",

            // Theme options
            theme_advanced_buttons1 : "forecolor,backcolor,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
            theme_advanced_buttons2 : "bullist,numlist,outdent,indent,|,undo,redo,|,sub,sup,blockquote,charmap,image,iespell,media,|,advhr,link,unlink,anchor,cleanup,|,pastetext,pasteword,code,preview,fullscreen,syntaxhl",
            theme_advanced_buttons3 : "",
            theme_advanced_toolbar_location : "top",
            theme_advanced_toolbar_align : "left",
            theme_advanced_resizing : true,

            extended_valid_elements: "pre[name|class],iframe[src|width|height|name|align]",

            valid_children : "+body[style]",
            relative_urls: false,
            remove_script_host: false,
            oninit : function () {
                if (typeof(fun) === "function") {
                    fun();
                }
            }
        });
	</script>
	<div class="ui-layout-center">
		<div class="tab">
			<ul>
			    <li><a href="${base}/admin/post/list">列表</a></li>
			    <li><a href="javascript:void(0);" class="here">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			</ul>
		</div>
		<form id="postForm" name="postForm" action="${base}/admin/post/doCreate" method="POST">
			<@spring.bind "post" />
			<table class="formtable" style="width:100%;">
				<tr>
					<td class="fieldlabel" style="width:50px;">编号</td>
					<td>
						<@spring.formInput path="post.id" attributes='class="textinput" style="width:98%;" disabled="disabled"' defaultValue="自定生成"  />
					</td>
					<td class="fieldlabel">标题</td>
					<td>
						<@spring.formInput path="post.title" attributes='class="textinput" style="width:98%;"' />
					</td>
				</tr>
				<tr>
					<td class="fieldlabel" style="width:50px;">分类</td>
					<td>
						<@spring.formSingleSelect path="post.catalog.id" options=catalogs valueAttr="id" textAttr="name" attributes='style="width:99%;"' />
					</td>
					<td class="fieldlabel">标签</td>
					<td>
						<#-- <@spring.formInput path="post.title" attributes='class="textinput" style="width:98%;"' /> -->
					</td>
				</tr>
				<tr>
					<td class="fieldlabel">内容</td>
					<td colspan="3">
						<@spring.formTextarea path="post.content" attributes='style="height:200px;width:100%;"' />
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align:center;">
						<input type="submit" class="btn" value=" 提交 " />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			//斑马线
			var tables=document.getElementsByTagName("table");
			var b=false;
			for (var j = 0; j < tables.length; j++){
				var cells = tables[j].getElementsByTagName("tr");
				//cells[0].className="color3";
				b=false;
				for (var i = 0; i < cells.length; i++){
					if(b){
						cells[i].className="color2";
						b=false;
					}
					else{
						cells[i].className="color3";
						b=true;
					};
				};
			}
		});
	</script>
<#include "../inc/footer.ftl" />