<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
	<script type="text/javascript">
		Ext.onReady(function(){
			// basic tabs 1, built from existing content
		    var tabs = new Ext.TabPanel({
		        renderTo: 'divMain2',
		        activeTab: 0,
		        frame:true,
		        defaults:{autoHeight: true},
		        items:[
		            {contentEl:'base_config', title: '<ss:text name="webconfig.category.base" />'},
		            {contentEl:'global_config', title: '<ss:text name="webconfig.category.global" />'},
		            {contentEl:'seo_config', title: '<ss:text name="webconfig.category.seo" />'},
		            {contentEl:'sys_config', title: '<ss:text name="webconfig.category.system" />'}
		        ]
		    });
		});
		
		$(document).ready(function(){
			validateForm("configForm");
		});
		
		function changeCheckbox(chk, id) {
			var hid = document.getElementById(id);
			if(chk.checked){
				hid.value = "1";
			}
			else{
				hid.value = "0";
			}
		}
	</script>
	<body>
		<div id="divMain">
			<div class="Header">
				<ss:text name="webconfig.title" />
			</div>
			<form id="configForm" method="post" action="<%=path %>/admin/saveConfig.action">
				<div id="divMain2">
					<div title="<ss:text name="webconfig.category.base" />" id="base_config" class="x-hide-display">
						<table class='gridtable' width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
							<!-- BLOG地址 -->
							<tr>
								<td style='width: 32%'><p align='left'><ss:text name="webconfig.base.blogurl" /></p></td>
								<td style="width: 68%">
									<input name="config.mspring_config_base_blogurl" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_blogurl" />" validate="{required:true,maxlength:200,messages:{required:'请输入BLOG地址'}}" />
								</td>
							</tr>
							<!-- BLOG名称 -->
							<tr>
								<td style='width: 32%'><p align='left'><ss:text name="webconfig.base.blogname" /></p></td>
								<td style="width: 68%">
									<input name="config.mspring_config_base_blogname" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_blogname" />" validate="{required:true, maxlength:20, messages:{required:'请输入BLOG名称', maxlength:'BLOG名称长度不等超过{0}'}}" />
								</td>
							</tr>
							<!-- BLOG简介 -->
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.blogintro" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_base_blogintro" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_blogintro" />" validate="{maxlength:1000, message:{maxlength:'BLOG简介长度不能超过{0}'}}" />
									</p>
								</td>
							</tr>
							<!-- 网站标题 -->
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.blogtitle" /></p>
								</td>
								<td style="width: 68%">
									<input name="config.mspring_config_base_title" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_title" />"  validate="{required:true, maxlength:20, messages:{required:'请输入网站标题', maxlength:'网站标题长度不等超过{0}'}}" />
								</td>
							</tr>
							<!-- 网站子标题 -->
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.secondtitle" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_base_secondtitle" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_secondtitle" />" validate="{maxlength:200, message:{maxlength:'网站子标题长度不得超过{0}'}}" />
									</p>
								</td>
							</tr>
							<!-- 昵称 -->
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.nickname" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_base_nickname" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_nickname" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.email" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_base_email" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_email" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.copyright" /></p>
									<p><ss:text name="webconfig.base.copyright.prompt" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<textarea rows="4" name="config.mspring_config_base_copyright" style="width: 95%" type="text" class="textfield"><ss:property value="config.mspring_config_base_copyright" /></textarea>
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.owner" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_base_owner" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_base_owner" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.base.auditcomment" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input type="hidden" id="config_mspring_config_base_auditcomment" name="config.mspring_config_base_auditcomment" value="<ss:property value="config.mspring_config_base_auditcomment" />" />
										<input type="checkbox" onclick="changeCheckbox(this, 'config_mspring_config_base_auditcomment');" <ss:if test="config.mspring_config_base_auditcomment == 1">checked="checked"</ss:if> />
									</p>
								</td>
							</tr>
							
						</table>
					</div>
					<div title="<ss:text name="webconfig.category.global" />" id="global_config" class="x-hide-display">
						<table class='gridtable' width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.global.autorebuild" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_global_autorebuild" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_global_autorebuild" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.global.rebuildfolder" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_global_rebuildfolder" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_global_rebuildfolder" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'><ss:text name="webconfig.global.language" /></p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_global_language" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_global_language" />" />
									</p>
								</td>
							</tr>
						</table>
					</div>
					<div title="SEO设置" id="seo_config" class="x-hide-display">
						<table class='gridtable' width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
							<tr>
								<td style='width: 32%'>
									<p align='left'>
										<ss:text name="webconfig.seo.enableseo" />
									</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_seo_enableseo" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_seo_enableseo" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'>
										<ss:text name="webconfig.seo.keyword" />
									</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_seo_keyword" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_seo_keyword" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'>
										<ss:text name="webconfig.seo.description" />
									</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_seo_description" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_seo_description" />" />
									</p>
								</td>
							</tr>
						</table>
					</div>
					<div title="系统设置" id="sys_config" class="x-hide-display">
						<table class='gridtable' width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
							<tr>
								<td style='width: 32%'>
									<p align='left'>
										<ss:text name="webconfig.system.mail.host" />
									</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_system_mail_host" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_system_mail_host" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'>
										<ss:text name="webconfig.system.mail.port" />
									</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_system_mail_port" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_system_mail_port" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'>
										<ss:text name="webconfig.system.mail.username" />
									</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_system_mail_username" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_system_mail_username" />" />
									</p>
								</td>
							</tr>
							<tr>
								<td style='width: 32%'>
									<p align='left'>
										<ss:text name="webconfig.system.mail.password" />
									</p>
								</td>
								<td style="width: 68%">
									<p>
										<input name="config.mspring_config_system_mail_password" style="width: 95%" type="text" class="textfield" value="<ss:property value="config.mspring_config_system_mail_password" />" />
									</p>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div style="text-align: center;"><input type="submit" class="button" value="<ss:text name="button.submit" />" id="btnPost" style="width:120px;" /></div>
			</form>
		</div>
		<script language="javascript">
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
	</body>
<%@ include file="../includes/footer.jsp" %>