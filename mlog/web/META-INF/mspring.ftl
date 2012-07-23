<#--
//********************************************************************
// @author: gaoyb(www.mspring.org)
// @Revision: 1.1 $, $Date: 2012/07/20 11:24:03 $
//******************************************************************** 
-->

<#--
/*
 *分页条
 */
-->
<#macro pagingnavigator page="" form_id="" >
	<div class="pagger">
		<span>共${page.getTotalCount()}条</span>
		<span>第${page.getPageNo()}/${page.getTotalPages()}页</span>
		
		<#if ((page.getPageNo()) > 1)>
			<span><a href="javascript:changePage('${form_id}', 1);">首页</a></span>
			<span><a href="javascript:changePage('${form_id}', ${page.getPageNo() - 1});">上一页</a></span>
		<#else>
			<span class="disabled">首页</span>
			<span class="disabled">上一页</span>
		</#if>
		
		<#if (page.getPageNo() < page.getTotalPages())>
			<span><a href="javascript:changePage('${form_id}', ${page.getPageNo() + 1});">下一页</a></span>
			<span><a href="javascript:changePage('${form_id}', ${page.getTotalPages()});">末页</a></span>
		<#else>
			<span class="disabled">下一页</span>
			<span class="disabled">末页</span>
		</#if>
		<span>跳转到</span>
		<input type="text" class="textinput" style="width:20px;" name="goPageNumber" />
		<span><a href="javascript:changePageCustom('${form_id}');">GO</a></span>
	</div>
	<script type="text/javascript">
		function changePage(formId, pageNumber) {
			if (pageNumber < 1) {
		        pageNumber = 1;
			}
			var pageNoObj = document.getElementById("pageNo");
			if (pageNoObj) {
			    pageNoObj.value = pageNumber;
			}
			document.getElementById(formId).submit();
		}
		function changePageCustom(formId) {
		    var pageNumbers = document.getElementsByName('goPageNumber');
		    if (!pageNumbers) {
		        changePage(formId, 1);
		    }
		    var pageNumber = 1;
		    for (var i = 0; i < pageNumbers.length; i++) {
		        if (pageNumber < pageNumbers[i].value) {
		            pageNumber = pageNumbers[i].value;
		        }
		    }
		    changePage(formId, pageNumber);
		}
	</script>
</#macro>

<#--
列循环时获取属性值
-->
<#macro fieldValue value attribute="" >
	<#assign currentElement = value />
	
	<#list attribute?split(".") as x>
		<#if x?exists>
			<#if x_has_next>
				<#assign currentElement=value['${x}'] />
			<#else>
				${currentElement['${x}']!""}
			</#if>
		</#if>
	</#list>
	
</#macro>