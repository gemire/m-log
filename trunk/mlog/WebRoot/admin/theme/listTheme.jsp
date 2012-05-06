<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<style>
	.skinItem {
	    background-color: #FAFAFA;
	    border-radius: 3px;
	    box-shadow: 1px 1px 3px #333333;
	    line-height: 16px;
	    margin: 24px 12px;
	    padding: 10px 5px;
	    text-align: center;
	    width: 306px;
	}
	#skinMain .skinItem.selected {
	    background-color: #D54121;
	}
	.skinItem:hover {
	    background-color: #EEE;
	}
	
	.nowrap {
	    white-space: nowrap;
	}
	.left {
	    float: left;
	}
	.right {
	    float: right;
	}
	.clear {
	    background-color: transparent;
	    border: 0px;
	    clear: both;
	    display: block;
	    font-size: 0px;
	    height: 0px;
	    line-height: 0px;
	    overflow: hidden;
	}
</style>
<body>
	<div id="divMain">
		<div class="Header">
			主题管理
		</div>
		<div class="SubMenu"></div>
		<div id="divMain2">
			<div id="skinMain">
				<ss:iterator value="themes" var="theme">
					<div title='<ss:property value="name" />' class='left skinItem <ss:if test="folderName == config.mspring_config_base_theme">selected</ss:if>'>
		        		<img class="skinPreview" src='<%=path %>/themes/<ss:property value="folderName" />/preview.png'>
		        		<div>
		        			<ss:property value="name" />
		        			<ss:if test="folderName != config.mspring_config_base_theme"><a href='<%=path %>/admin/useThisTheme.action?folderName=<ss:property value="folderName" />'>使用该主题</a></ss:if>
		        		</div>
		        	</div>
				</ss:iterator>
	        </div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		function selectTheme(_this){
			$("#skinMain").children().each(function(){
				if(this == _this)
					$(this).attr("class", "left skinItem selected");
				else
					$(this).attr("class", "left skinItem");
			});
		}
		
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
<%@include file="../includes/footer.jsp"%>
