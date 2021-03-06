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
						<span>${s.name}</span> | <a href="javascript:use('${s.folder}');">使用</a>
					</div>
				</div>
			</#list>
		</#if>
	</div>
</form>
<script type="text/javascript">
	function use(folder){
		$("#current_skin").val(folder);
		mlog.form.submitForm("skinForm");
	}
</script>
<#include "../inc/footer.ftl" />