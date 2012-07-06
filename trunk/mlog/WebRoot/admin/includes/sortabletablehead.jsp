<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.mspring.platform.persistence.support.Page"%>
<%@taglib prefix="ss" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	String formName = request.getParameter("formName");
	String entityName = request.getParameter("entityName");
%>

<script type="text/javascript" language="javascript">
	function sortTable(theForm,field,order){
		if(field){
			if(theForm.elements["<%=entityName%>.categoryPage.pageNo"]){
				theForm.elements["<%=entityName%>.categoryPage.pageNo"].value = 1;
			}
			if(theForm.elements["<%=entityName%>.sort.field"]){
				theForm.elements["<%=entityName%>.sort.field"].value = field;
			}
			if(theForm.elements["<%=entityName%>.sort.order"]){
				theForm.elements["<%=entityName%>.sort.order"].value = order;
			}
		}
		theForm.submit();
	}
</script>

<%
	Page ps = (Page)ActionContext.getContext().getValueStack().findValue("categoryPage");
	ActionContext.getContext().getValueStack().set("$page",ps);
%>

<ss:iterator id="fieldColumn" value="fieldColumns">
	<ss:if test="sortable">
		<ss:if test="$page.sort != null && $page.sort.field == fieldColumn.id">
			<ss:if test="$page.sort.order.indexOf('desc') > -1">
				<th style="cursor: pointer;" onclick="sortTable(<%=formName%>, '<ss:property value="id"/>','asc');">
	                <span class="af-outputText" title='按照　<ss:property value="name" />　降序'><b><ss:property value="name"/></b></span>
	                <img src="<%=path %>/images/up.gif" height="14" width="11" align="absmiddle" title="降序" border="0" />
	            </th>
			</ss:if>
			<ss:else>
				<th style="cursor: pointer;" onclick="sortTable(<%=formName%>, '<ss:property value="id"/>','desc');">
	                <span class="af-outputText" title='按照　<ss:property value="name" />　升序'><b><ss:property value="name"/></b></span>
	                <img src="<%=path %>/images/down.gif" height="14" width="11" align="absmiddle" title="升序" border="0" />
	            </th>
			</ss:else>
		</ss:if>
		<ss:else>
			<th style="cursor: pointer;" onclick="sortTable(<%=formName%>, '<ss:property value="id"/>','desc');">
                <span class="af-outputText" title='按照　<ss:property value="name" />　升序'><b><ss:property value="name"/></b></span>
            </th>
		</ss:else>
	</ss:if>
	<ss:elseif test="name.length() > 0">
        <th style="cursor: pointer;" class="tableColumnHeader">
             <span class="af-outputText" title=''><b><ss:property value="name"/></b></span>
        </th>  
    </ss:elseif>
    <ss:else>
        <th>
             <span class="af-outputText" title=''><b><ss:property value="name"/></b></span>
        </th>  
    </ss:else>
</ss:iterator>