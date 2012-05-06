<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/Reorderer.js"></script>
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/ToolbarReorderer.js"></script>
	<script type="text/javascript">
	  	Ext.onReady(function() {
	  		var enablePageCache = $("#enablePageCache").val();
	  		var enableDBQueryCache = $("#enableDBQueryCache").val();
	  		var toolbar = new Ext.Toolbar({
		    	renderTo: document.getElementById("SubMenu"),
		        plugins : [
		            new Ext.ux.ToolbarReorderer({
		                defaultReorderable: true
		            })
		        ],
		        items : [
		        	{
						text: ('1' == enablePageCache) ? '禁用页面缓存' : '启用页面缓存',
						iconCls: 'plugin',
						handler: function(){
							enablePageCache = ('1' == enablePageCache) ? '0' : '1'
							window.location = "<%=path %>/admin/cache/enablePageCache.action?enablePageCache=" + enablePageCache;
						}
					},"-",
					{
						text: ('1' == enableDBQueryCache) ? '禁用数据库查询缓存' : '启用数据库查询缓存',
						iconCls: 'plugin',
						handler: function(){
							enableDBQueryCache = ('1' == enableDBQueryCache) ? '0' : '1'
							window.location = "<%=path %>/admin/cache/enableDBQueryCache.action?enableDBQueryCache=" + enableDBQueryCache;
						}
					}
		        ]
		    });
	  	});
  	</script>
  	<body>
  		<div id="divMain">
			<div class="Header">
				<ss:text name="linklist.title" />
			</div>
			<div class="SubMenu" id="SubMenu"></div>
			<div id="divMain2">
				<form name="cacheForm" method="post" action="<%=path %>/admin/cache/listPageCache.action">
					<!-- pagination parameter -->
					<input type="hidden" name="elementPage.pageNo" value='<ss:property value="elementPage.pageNo"/>'>
					<input type="hidden" name="elementPage.totalPages" value='<ss:property value="elementPage.totalPages" />' />
					<input type="hidden" name="elementPage.totalCount" value='<ss:property value="elementPage.totalCount" />' />
					
					<!-- sorter parameter -->
					<input type="hidden" name="elementPage.sortEnable" value='<ss:property value="elementPage.sortEnable"/>'>
					<input type="hidden" name="elementPage.sort.field" value='<ss:property value="elementPage.sort.field"/>'>
			   		<input type="hidden" name="elementPage.sort.order" value='<ss:property value="elementPage.sort.order"/>'>
			   		
			   		<input type="hidden" name="enablePageCache" id="enablePageCache" value='<ss:property value="enablePageCache" />' />
			   		<input type="hidden" name="enableDBQueryCache" id="enableDBQueryCache" value='<ss:property value="enableDBQueryCache" />' />
				
					<table class="gridtable" border="1" width="100%" cellspacing="1" cellpadding="1">
						<tr>
							<th style="text-align:center;width:50px;"><input type="checkbox" onclick="checkAll(this,'keys')" /></th>
					        <th>编号</th>
					        <th>标题</th>
					        <th>命中次数</th>
					        <th>大小(byte)</th>
					        <th>创建时间</th>
			      		</tr>
						<ss:iterator id="element" value="elementPage.result" status="rowstatus">
							<tr>
								<td style="text-align:center;width:50px;"><input type="checkbox" value='<ss:property value="key" />' name="keys" /></td>
								<td><ss:property value="article.id" /></td>
								<td><ss:property value="article.title" /></td>
								<td><ss:property value="hitCount" /></td>
								<td><ss:property value="serializedSize" /></td>
								<td><ss:property value="creationTime" /></td>
							</tr>
						</ss:iterator>
					</table>
					
					<!-- control bar -->
				    <table class="gridtable" cellpadding="1" cellspacing="0" border="0" width="100%">
				        <tr style="border:none;">
				        	<td align="right" nowrap class="tableControlBarText" height="26">
				        		<div style="float: left;">
				        			<input type="button" class="button" value="<ss:text name="button.deleteselect" />" onclick="submitForm(cacheForm,'<%=path %>/admin/cache/removePageCache.action');" />
				        		</div>
					            <jsp:include page="../includes/pagingnavigator.jsp">
					                <jsp:param name="formName" value="cacheForm"/>
					                <jsp:param name="entityName" value="elementPage" />
					            </jsp:include>
				            </td>
				        </tr>
				   </table> 
					
				</form>
			</div>
		</div>
	</body>
  
	<script>
		$(document).ready(function(){ 
			//斑马线
			var tables=document.getElementsByTagName("table");
			var b=false;
			for (var j = 0; j < tables.length; j++){
		
				var cells = tables[j].getElementsByTagName("tr");
		
				cells[0].className="color1";
				for (var i = 1; i < cells.length; i++){
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
	
<%@include file="../includes/footer.jsp" %>