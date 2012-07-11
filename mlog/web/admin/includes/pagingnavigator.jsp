<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mspring.tld" prefix="mspring" %>
<%@taglib prefix="ss" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String formName = request.getParameter("formName");
	String entityName = request.getParameter("entityName");
%>

<script language="javascript" type="text/javascript">
	//********************************************************************
	// @author: gaoyb(www.mspring.org)
	// @Revision: 1.1 $, $Date: 2012/01/30 11:24:03 $
	//******************************************************************** 
	
	var theForm = null;
	
	/**
	 * @param form 表单对象
	 * @param pageNo 翻页页码
	 */
	function changePage(form, pageNumber) {
		//	if (!isInteger(pageNumber)) {
		//		alert("请输入一个有效的数字作为页数!");
		//		return false;
		//	}
	
		if (pageNumber < 1) {
	        pageNumber = 1;
		}
		//判断form是否有此属性
		if (form.elements['<%=entityName %>.pageNo']) {
		    form.elements['<%=entityName %>.pageNo'].value = pageNumber;
		}
		form.submit();
	}
	
	function changePageCustom(form) {
	    var pageNumbers = document.getElementsByName('goPageNumber');
	    if (!pageNumbers) {
	        changePage(form, 1);
	    }
	    var pageNumber = 1;
	    for (var i = 0; i < pageNumbers.length; i++) {
	        if (pageNumber < pageNumbers[i].value) {
	            pageNumber = pageNumbers[i].value;
	        }
	    }
	    
	    changePage(form, pageNumber);
	}
	
	/**
	 * 向页面打印出分页导航栏
	 *
	 * @param form 分页导航栏所在表单对象
	 * @param page 当前所在页码
	 * @param pageCount 总页数
	 * @param totalCount (optional) 总记录数
	 */
	function showPaginationNavigator(form, page, pageCount, totalCount) {
		page = isNaN(page) ? 0 : page;
		pageCount = isNaN(pageCount) ? 0 : pageCount;
		totalCount = isNaN(totalCount) ? 0 : totalCount;
	    var doc = "";
	    if (typeof totalCount != "undefined") {
	        doc = "共" + totalCount + "条&nbsp;";
	    }
	    doc = doc + getPaginationNavigator(form, page, pageCount);
	    // alert(doc);
	    document.write(doc);
	}
	
	function getPaginationNavigator(form, page, pageCount) {
	    var doc = "第" + page + "/" + pageCount + "页&nbsp;";
		if (page > 1) {
			doc = doc + "<a href='javascript:changePage(" + form + ", 1)'>首页</a>&nbsp;";
	        doc = doc + "<a href='javascript:changePage(" + form + ", " + (page - 1) + ")'>上一页</a>&nbsp;";
		} else {
			doc = doc + "首页&nbsp;";
			doc = doc + "上一页&nbsp;";
		}
		if (page < pageCount) {
			doc = doc + "<a href='javascript:changePage(" + form + ", " + (page + 1) + ")'>下一页</a>&nbsp;";
	        doc = doc + "<a href='javascript:changePage(" + form + ", " + pageCount + ")'>末页</a>&nbsp;";
		} else {
			doc = doc + "下一页&nbsp;";
	        doc = doc + "末页&nbsp;";
		}
	    doc = doc + "跳转到&nbsp<input type='text' size='3' font-size='12px' name='goPageNumber' value='' onKeypress='if(event.keyCode == 13){ changePageCustom(" + form + ", " + form + ".goPageNumber);return false; }'>&nbsp;";
	    doc = doc + "<a href='javascript:changePageCustom(" + form + ")'>GO</a>&nbsp;";
	    
	    return doc;
	}
		
</script>

<script language="javascript" type="text/javascript">

	var _page = parseInt(<%=formName %>.elements["<%=entityName%>.pageNo"].value);
	var _pageCount = parseInt(<%=formName %>.elements["<%=entityName%>.totalPages"].value);
	var _totalCount = parseInt(<%=formName %>.elements["<%=entityName%>.totalCount"].value);
	
	showPaginationNavigator('<%=formName %>', _page, _pageCount,_totalCount);
</script>