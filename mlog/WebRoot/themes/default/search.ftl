<#include "header.ftl" />
<script type="text/javascript">
	function doSearch(){
		var queryString = $("#queryString").val();
		if(!queryString) {
			alert("请出入搜索关键字");
			return;
		}
		var encodeQueryString = encodeURI(queryString);
		window.location = "${path}/search.action?queryString=" + encodeQueryString;
	}
</script>
<!-- article node -->
<div id="search-div" style="text-align: center;">
	<input type="text" value="${queryString?default(searchInfo)}" name="queryString" id="queryString" style="width:300px;" 
		onfocus="this.value = this.value == this.defaultValue ? '' : this.value" onblur="this.value = this.value == '' ? this.defaultValue : this.value">
	<input type="button" value=" Search " onclick="doSearch();" />
	<#--
	<form name="searchform" action="${path}/search.action" method="get">
		<input type="text" value="${queryString?default(searchInfo)}" name="queryString" style="width:300px;" onfocus="this.value = this.value == this.defaultValue ? '' : this.value" onblur="this.value = this.value == '' ? this.defaultValue : this.value">
	</form>
	-->
</div>
<div id="node-130" class="node">
	<#if articlePage?exists && articlePage.result?exists>
		<#list articlePage.result as article>
			<h2>
				<b style='color: #FF6347'><a href="${path}/post/${article.id?trim}.html" target="_blank">${article.title}</a></b>
			</h2>
			<span class="submitted">${postBy}：${mspring_config_base_nickname?default("")}   ${postAt}${article.createTime?default("")}</span>
			<div class="content">
				<@mspring_tag.splitArticleContent content="${article.content}" intro="" />
			</div>
			<br/>
		</#list>
	</#if>
</div>
<#include "footer.ftl" />
