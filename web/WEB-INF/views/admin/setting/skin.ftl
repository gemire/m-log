<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<form id="skinForm" method="post" action="${base}/admin/setting/saveSkin">
	<#-- 当前主题保单对象 -->
	<input type="hidden" id="current_skin" name="skin" value="${skin!""}"/>
	<div class="skins">
		<#if skins?exists>
			<#list skins as s>
				<#assign divClass = "left skinItem" />
				<#-- 如果循环变量中的主题是当前系统设置的主题,那么让该主题选中 -->
				<#if s.folder == skin>
					<#assign divClass = "left skinItem selected" />
				</#if>
				<div id="skin_${s.folder}" name="skindiv" title='${s.name}' class="${divClass}">
					<img class="skinPreview" src='${base}/skins/${s.folder}/preview.png' width="280px" height="160px">
					<div class="metadata">
						${s.name} | <a href="javascript:selectTheme('skin_${s.folder}', '${s.folder}');submitSkinForm();">使用</a>
					</div>
				</div>
			</#list>
			<script type="text/javascript">
				function selectTheme(id, folder){
					var _this = document.getElementById(id);
					var skins = document.getElementsByName("skindiv");
					for(var i = 0; i < skins.length; i++){
						if(skins[i].id == _this.id){
							document.getElementById("current_skin").value = folder;
							skins[i].className = "left skinItem selected";
						}
						else {
							skins[i].className = "left skinItem";
						}
					}
				}
				function submitSkinForm(){
					mlog.form.submitForm("skinForm");
				}
			</script>
		</#if>
	</div>
</form>
<#include "../inc/footer.ftl" />