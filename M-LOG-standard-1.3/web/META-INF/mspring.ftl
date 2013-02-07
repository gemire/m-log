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
	<style type="text/css">
	/* 分页栏样式 */
	div.pagger {
		PADDING-RIGHT: 3px;
		PADDING-LEFT: 3px;
		PADDING-BOTTOM: 3px;
		MARGIN: 3px;
		PADDING-TOP: 3px;
		TEXT-ALIGN: center;
		FONT-SIZE: 12px;
		FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
		PADDING-LEFT: 25px;
		float: right;
	}
	
	div.pagger A {
		BORDER-RIGHT: #eee 1px solid;
		PADDING-RIGHT: 5px;
		BORDER-TOP: #eee 1px solid;
		PADDING-LEFT: 5px;
		PADDING-BOTTOM: 2px;
		MARGIN: 2px;
		BORDER-LEFT: #eee 1px solid;
		COLOR: #036cb4;
		PADDING-TOP: 2px;
		BORDER-BOTTOM: #eee 1px solid;
		TEXT-DECORATION: none
	}
	
	div.pagger A:hover {
		BORDER-RIGHT: #999 1px solid;
		BORDER-TOP: #999 1px solid;
		BORDER-LEFT: #999 1px solid;
		COLOR: #666;
		BORDER-BOTTOM: #999 1px solid
	}
	
	div.pagger A:active {
		BORDER-RIGHT: #999 1px solid;
		BORDER-TOP: #999 1px solid;
		BORDER-LEFT: #999 1px solid;
		COLOR: #666;
		BORDER-BOTTOM: #999 1px solid
	}
	
	div.pagger .current {
		BORDER-RIGHT: #036cb4 1px solid;
		PADDING-RIGHT: 5px;
		BORDER-TOP: #036cb4 1px solid;
		PADDING-LEFT: 5px;
		FONT-WEIGHT: bold;
		PADDING-BOTTOM: 2px;
		MARGIN: 2px;
		BORDER-LEFT: #036cb4 1px solid;
		COLOR: #fff;
		PADDING-TOP: 2px;
		BORDER-BOTTOM: #036cb4 1px solid;
		BACKGROUND-COLOR: #036cb4
	}
	
	div.pagger .disabled {
		BORDER-RIGHT: #eee 1px solid;
		PADDING-RIGHT: 5px;
		BORDER-TOP: #eee 1px solid;
		PADDING-LEFT: 5px;
		PADDING-BOTTOM: 2px;
		MARGIN: 2px;
		BORDER-LEFT: #eee 1px solid;
		COLOR: #ddd;
		PADDING-TOP: 2px;
		BORDER-BOTTOM: #eee 1px solid
	}
	</style>
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
				<#assign currentElement=currentElement['${x}']!"" />
				<#if currentElement?is_boolean>
					${currentElement?string('是','否')}
				<#else>
					${currentElement}
				</#if>
			</#if>
		</#if>
	</#list>
</#macro>


<#macro show_errors>
	<#if (errors?exists && errors.errors?exists && errors.errors?size > 0)>
		<#assign msg = "" />
		<#list errors.errors as error>
			<#assign msg = msg + error.message + "<br />" />
		</#list>
		<#if (msg?exists && msg?length > 0)>
			<script type="text/javascript">mlog.dialog.tip(msg:'${msg}', type:'error');</script>
		</#if>
	</#if>
</#macro>

<#macro sub_string content from=0 to=0 suffix="">
	<#if (content!?length > 0 && from < content?length)>
		<#if (to > 0 && content?length > (to + 1) )>
			${content?substring(from, to)}${suffix}
		<#else>
			${content?substring(from)}
		</#if>
	</#if>
</#macro>