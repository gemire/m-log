<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form id="bloginfoForm" name="bloginfoForm" method="post" action="${base}/admin/setting/saveBloginfo">
	<div id="error" class="message error" style="display:none;"></div>
	<table class="infotable">
		<tr>
			<td colspan="3" class="partition">博客信息</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">博客标题:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="blogname" value="${blogname!""}" validate="{required:true, maxlength:100, messages:{required:'博客标题必填', maxlength:'博客标题长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度100</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">博客子标题:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="blogsubname" value="${blogsubname!""}" validate="{required:true, maxlength:300, messages:{required:'博客子标题必填', maxlength:'博客子标题长度不能超过{0}'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度300</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">博客地址:</td>
			<td>
				<input type="input" class="textinput" style="width:95%;" name="blogurl" value="${blogurl!""}" validate="{required:true, maxlength:200, blogurl:true, messages:{required:'博客地址必填', maxlength:'博客地址最大长度不能超过{0}', blogurl:'博客地址格式不正确'}}"/>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：必填、最大长度200、必须以"http://"或"https://"开头、不能以"/"或"\"结尾</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">公告:<br />(支持HTML代码)</td>
			<td>
				<textarea class="textinput" style="width:95%;height:50px;" name="notice" validate="{maxlength:2000, messages:{maxlength:'公告最大长度不能超过{0}'}}">${notice!""}</textarea>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度不能超过2000</td>
		</tr>
		<tr>
			<td class="fieldlabel" style="width:120px;">版权信息:<br /></td>
			<td>
				<textarea class="textinput" style="width:95%;" name="copyright" validate="{maxlength:2000, messages:{maxlength:'版权信息最大长度不能超过{0}'}}">${copyright!""}</textarea>
			</td>
			<td class="fieldnotice" style="width:300px;">规则：最大长度不能超过2000</td>
		</tr>
		
		<tr>
			<td class="fieldlabel" style="width:120px;">是否开启评论审核:</td>
			<td>
				<input type="checkbox" <#if comment_audit?exists && comment_audit == "true">checked="checked"</#if> onclick='$("#comment_audit").val(this.checked);' />
				<input type="hidden" id="comment_audit" name="comment_audit" value="${comment_audit!"false"}" />
			</td>
			<td class="fieldnotice" style="width:300px;"></td>
		</tr>
		
		<tr>
			<td class="fieldlabel" style="width:120px;">菜单:</td>
			<td>
				<textarea class="textinput" style="width:95%;height:210px;" name="menu" validate="{maxlength:2000, messages:{maxlength:'菜单最大长度不能超过{0}'}}">${menu!""}</textarea>
			</td>
			<td class="fieldnotice" style="width:300px;">
				规则：必填、最大长度2000<br/>
				全局变量：%base% - 程序当前路径<br/>
				说明：每一行链接代表一个菜单项。<br />
				示例：&lt;a href="%base%/index.html" target="_self"&gt;...&lt;/a&gt;<br />
				默认：<br/>&lt;a href="%base%" target="_self"&gt;首页&lt;/a&gt;<br/>
					&lt;a href="%base%/album/list.html" target="_self"&gt;相册&lt;/a&gt;<br/>
					&lt;a href="%base%/admin" target="_blank"&gt;管理&lt;/a&gt;
			</td>
		</tr>
		
		<tr>
			<td class="fieldlabel" style="width:120px;">关闭站点:</td>
			<td>
				<input type="checkbox" <#if site_close?exists && site_close == "true">checked="checked"</#if> onclick='$("#site_close").val(this.checked);' />
				<input type="hidden" id="site_close" name="site_close" value="${site_close!"false"}" />
			</td>
			<td class="fieldnotice" style="width:300px;"></td>
		</tr>
		
		<tr>
			<td colspan="3" style="text-align:center;"><input type="submit" class="btn" value=" 提 交 " /></td>
		</tr>
	</table>
</form>
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
		
		mlog.form.validate({
			selector : "#bloginfoForm",
			errorLabelContainer : "#error",
			wrapper: 'li',
			onfocusout : false,
			onkeyup : false,
			onclick : false,
			success : function(){
				mlog.utils.scrollTop();
			}
		});
	});
</script>
<#include "../inc/footer.ftl" />