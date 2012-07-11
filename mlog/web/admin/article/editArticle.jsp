<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="../includes/header.jsp" %>
	<script type="text/javascript" src="<%=path %>/script/datepicker/WdatePicker.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=path %>/script//tiny_mce/tiny_mce.js" charset="utf-8"></script>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			tinyMCE.init({
                // General options
                language: 'zh-cn',
                mode : "exact",
                elements : "article_content, article_intro",
                theme : "advanced",
                plugins : "autosave,style,advhr,advimage,advlink,preview,inlinepopups,media,paste,fullscreen,syntaxhl",

                // Theme options
                theme_advanced_buttons1 : "forecolor,backcolor,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
                theme_advanced_buttons2 : "bullist,numlist,outdent,indent,|,undo,redo,|,sub,sup,blockquote,charmap,image,iespell,media,|,advhr,link,unlink,anchor,cleanup,|,pastetext,pasteword,code,preview,fullscreen,syntaxhl",
                theme_advanced_buttons3 : "",
                theme_advanced_toolbar_location : "top",
                theme_advanced_toolbar_align : "left",
                theme_advanced_resizing : true,

                extended_valid_elements: "pre[name|class],iframe[src|width|height|name|align]",

                valid_children : "+body[style]",
                relative_urls: false,
                remove_script_host: false,
                oninit : function () {
                    if (typeof(fun) === "function") {
                        fun();
                    }
                }
            });
            
            //文章分类选择
            $("#category_select_btn").click(function(){
            	//var content = $("#category_select_div").html();
            	$.getJSON("<%=path %>/admin/category/findAllCategoriesByAjax.action", function(data){
            		if(data && data.length > 0){
            			var content = new Array();
            			content.push('<div style="width: 400px; height: 300px; margin:0px; padding:0px; overflow: auto;">');
            			content.push('	<ul>');
            			for(var i = 0; i < data.length; i++){
	            			content.push('		<li>');
	            			content.push('			<input type="checkbox" name="category_checkbox" id="category_' + data[i].id + '" value="' + data[i].id + '" title="' + data[i].name + '" />');
	            			content.push('			' + data[i].name);
	            			content.push('		</li>');
            			}
            			content.push('	</ul>');
            			content.push('</div>');
            			
            			var dialog = $.dialog({
							drag: true,
							esc: true,
							lock:true,
							min: false,
							max: false,
							width: '400px',
		    				height: '300px',
							title: '分类选择',
						    content: content.join(""),
						    init: function(){
					    		var _categoryids = $("#category_id_input").val();
					    		if(_categoryids){
					    			var api = this;
					    			var _content = api.DOM.content;
					    			var _category_array = _categoryids.split(',');
					    			var categoryCheckboxes = $(_content).find("input");
					    			for(var i = 0; i < categoryCheckboxes.length; i++){
					    				var cb = categoryCheckboxes[i];
					    				for(var j = 0; j < _category_array.length; j++){
				    						if(_category_array[j] == cb.value){
				    							cb.checked = "checked";
				    						}
				    					}
					    			}
					    		}
						    },
						    button: [
						    	{
						    		name: '确定',
						    		callback: function(){
						    			var selectedCategoryNames = "";
						    			var selectedCategoryIds = "";
						    			var _content = dialog.getContent();
						    			var categoryCheckboxes = $(_content).find("input");
						    			for(var i = 0; i < categoryCheckboxes.length; i++){
						    				var cb = categoryCheckboxes[i];
						    				if(cb.checked) {
						    					selectedCategoryNames += cb.title + ",";
						    					selectedCategoryIds += cb.value + ",";
						    				}
						    			}
						    			if(selectedCategoryNames.endWith(",")){
						    				selectedCategoryNames = selectedCategoryNames.substring(0, selectedCategoryNames.length - 1);
						    			}
						    			if(selectedCategoryIds.endWith(",")){
						    				selectedCategoryIds = selectedCategoryIds.substring(0, selectedCategoryIds.length - 1);
						    			}
						    			$("#category_name_input").val(selectedCategoryNames);
						    			$("#category_id_input").val(selectedCategoryIds);
						    		}
						    	},
						    	{
						    		name: '取消'
						    	}
						    ]
						});
            		}
            		else{
            			alert("无法查找分类到信息,请先创建分类");
            		}
            	});
            });
            
            //标签选择
            $("#tag_select_btn").click(function(){
            	$.getJSON("<%=path %>/admin/tag/findAllTagsByAjax.action", function(data){
            		if(data && data.length > 0){
            			var content = new Array();
            			
            			content.push('<div style="width: 400px; height: 300px; margin:0px; padding:0px; overflow: auto;">');
            			content.push('	<ul>');
            			content.push('		<li>');
            			for(var i = 0; i < data.length; i++){
           					content.push('			<input type="checkbox" name="tag_checkbox"  value="' + data[i].id + '"  title="' + data[i].name + '" />');
            				content.push('			' + data[i].name);
            			}
            			content.push('		</li>');
            			content.push('	</ul>');
            			content.push('</div>');
            			
            			
            			var dialog = $.dialog({
							drag: true,
							esc: true,
							lock:true,
							min: false,
							max: false,
							width: '400px',
		    				height: '300px',
							title: '分类选择',
						    content: content.join(""),
						    init: function(){
					    		var _tagids = $("#tag_id_input").val();
					    		if(_tagids){
					    			var api = this;
					    			var _content = api.DOM.content;
					    			var _tag_array = _tagids.split(',');
					    			var tagCheckboxes = $(_content).find("input");
					    			for(var i = 0; i < tagCheckboxes.length; i++){
					    				var cb = tagCheckboxes[i];
					    				for(var j = 0; j < _tag_array.length; j++){
				    						if(_tag_array[j] == cb.value){
				    							cb.checked = "checked";
				    						}
				    					}
					    			}
					    		}
						    },
						    button: [
						    	{
						    		name: '确定',
						    		callback: function(){
						    			var selectedTagNames = "";
						    			var selectedTagIds = "";
						    			var _content = dialog.getContent();
						    			var tagCheckboxes = $(_content).find("input");
						    			for(var i = 0; i < tagCheckboxes.length; i++){
						    				var cb = tagCheckboxes[i];
						    				if(cb.checked) {
						    					selectedTagNames += cb.title + ",";
						    					selectedTagIds += cb.value + ",";
						    				}
						    			}
						    			if(selectedTagNames.endWith(",")){
						    				selectedTagNames = selectedTagNames.substring(0, selectedTagNames.length - 1);
						    			}
						    			if(selectedTagIds.endWith(",")){
						    				selectedTagIds = selectedTagIds.substring(0, selectedTagIds.length - 1);
						    			}
						    			$("#tag_name_input").val(selectedTagNames);
						    			$("#tag_id_input").val(selectedTagIds);
						    		}
						    	},
						    	{
						    		name: '取消'
						    	}
						    ]
						});
            		}
            		else {
            			alert("无法查找到TAG信息,请先创建TAG");
            		}
            	});
            });
            
            
            //新建分类
            $("#category_create_btn").click(function(){
            	var content = new Array();
            	content.push('<table style="border-collapse: collapse;border: 1px solid #91ADBF;background: #ffffff;margin-top: 10px;line-height: 120%;" border="1" width="400px" cellspacing="1" cellpadding="1">');
            	content.push('	<tr>');
            	content.push('		<td style="width: 25%; border-color: #f7f7e7;font-size: x-small;font-weight: bold;text-align: right;background-color: #DAE2E8;color: #333333;vertical-align: middle;border: #ccc solid 1px;padding: 5px;" >');
            	content.push('			<p align="right">分类名称</p>');
            	content.push('		</td>');
            	content.push('		<td style="border-color: #f7f7e7;font-weight: bold;text-align: left;background-color: #f7f7f7;color: #336699;vertical-align: middle;border: #ccc solid 1px;padding: 5px;" style="width: 75%">');
            	content.push('			<input name="category_name" id="category_name" text="text" style="width: 95%;padding: 0.15em 0.25em 0.20em 0.25em;border: 1px double #668294;background-position: bottom;background: #f0f0f0;" />');
            	content.push('		</td>');
            	content.push('	</tr>');
            	content.push('</table>');
			
            	var dialog = $.dialog({
            		drag: true,
					esc: true,
					lock:true,
					min: false,
					max: false,
					width: '400px',
    				height: '50px',
					title: '新建分类',
				    content: content.join(""),
				    button: [
				    	{
				    		name: '确定',
				    		callback: function(){
				    			var _content = dialog.getContent();
				    			var _category_name = _content.find("input#category_name").val();
				    			
				    			//检测分类名称不能为空
				    			if(!($.trim(_category_name))){
				    				alert('请输入分类名称');
				    				return false;
				    			}
				    			
				    			//检测分类名称是否存在
				    			$.post("<%=path %>/admin/category/categoryWhetherRepeat.action", "name=" + _category_name, function(data){
				    				//如果存在
				    				if("false" == data){
				    					alert("分类名称[" + _category_name + "]已经存在");
				    					return;
				    				}
				    				
				    				$.ajax({
									   	type: "POST",
									   	url: "<%=path%>/admin/category/createCategoryByAjax.action",
									   	data: "name=" + _category_name,
									   	dateType: "json",
									   	success: function(data){
									   		var _category_id_input_value = $("#category_id_input").val();
									   		var _category_name_input_value = $("#category_name_input").val();
											
											var _category_id = data.id;
											var _category_name = data.name;
											
											if(_category_id_input_value)
												_category_id_input_value += "," + _category_id;
											else
												_category_id_input_value += _category_id;
											
											if(_category_name_input_value)
												_category_name_input_value += "," + _category_name;
											else
												_category_name_input_value += _category_name;
												
											$("#category_id_input").val(_category_id_input_value);
											$("#category_name_input").val(_category_name_input_value);
									   	},
									   	error: function(){
									   		alert("创建分类失败");
									   	}
									});
				    			});
				    		}
				    	},
				    	{
				    		name: '取消'
				    	}
				    ]
            	});
            });
            
            
            //新建tag
			$("#tag_create_btn").click(function(){
				var content = new Array();
				content.push('<table style="border-collapse: collapse;border: 1px solid #91ADBF;background: #ffffff;margin-top: 10px;line-height: 120%;" border="1" width="400px" cellspacing="1" cellpadding="1">');
				content.push('	<tr>');
				content.push('		<td style="width: 25%; border-color: #f7f7e7;font-size: x-small;font-weight: bold;text-align: right;background-color: #DAE2E8;color: #333333;vertical-align: middle;border: #ccc solid 1px;padding: 5px;" >');
				content.push('			<p align="right">TAG</p>');
				content.push('		</td>');
				content.push('		<td style="border-color: #f7f7e7;font-weight: bold;text-align: left;background-color: #f7f7f7;color: #336699;vertical-align: middle;border: #ccc solid 1px;padding: 5px;" style="width: 75%">');
				content.push('			<input name="tag_name" id="tag_name" text="text" style="width: 95%;padding: 0.15em 0.25em 0.20em 0.25em;border: 1px double #668294;background-position: bottom;background: #f0f0f0;" />');
				content.push('		</td>');
				content.push('	</tr>');
				content.push('</table>');
			
				var dialog = $.dialog({
					drag: true,
					esc: true,
					lock:true,
					min: false,
					max: false,
					width: '400px',
					height: '50px',
					title: '新建TAG',
					content: content.join(""),
					button: [
						{
							name: '确定',
							callback: function(){
								var _content = dialog.getContent();
								var _tag_name = _content.find("input#tag_name").val();
								
								//检测分类名称不能为空
								if(!($.trim(_tag_name))){
									alert('请输入Tag名称');
									return false;
								}
								
								//检测分类名称是否存在
								$.post("<%=path %>/admin/tag/tagWhetherRepeat.action", "name=" + _tag_name, function(data){
									//如果存在
									if("false" == data){
										alert("TAG名称[" + _tag_name + "]已经存在");
										return;
									}
									
									$.ajax({
										type: "POST",
										url: "<%=path%>/admin/tag/createTagByAjax.action",
										data: "name=" + _tag_name,
										dateType: "json",
										success: function(data){
											var _tag_id_input_value = $("#tag_id_input").val();
											var _tag_name_input_value = $("#tag_name_input").val();
											
											var _tag_id = data.id;
											var _tag_name = data.name;
											
											if(_tag_id_input_value)
												_tag_id_input_value += "," + _tag_id;
											else
												_tag_id_input_value += _tag_id;
											
											if(_tag_name_input_value)
												_tag_name_input_value += "," + _tag_name;
											else
												_tag_name_input_value += _tag_name;
												
											$("#tag_id_input").val(_tag_id_input_value);
											$("#tag_name_input").val(_tag_name_input_value);
										},
										error: function(){
											alert("创建分类失败");
										}
									});
								});
							}
						},
						{
							name: '取消'
						}
					]
				});
			});
		});
	</script>
	<body>
		<div id="divMain">
			<div class="Header">
				<span class="navigate"><a href="<%=path %>/admin/article/queryArticle.action"><ss:text name="articlelist.title" /></a>&gt;&gt;<a href="javascript:void(0);"><ss:text name="articleedit.edittitle" /></a><span>
			</div>
			<div class="SubMenu"></div>
			<div class="form">
				<form action="<%=path %>/admin/article/doEditArticle.action" id="articleForm" name="articleForm" method="post">
					<!-- hidden -->
					<input type="hidden" name="article.id" value="<ss:property value="article.id" />" />
					
					<table class="gridtable" width='100%' style='padding: 0px; margin: 0px;' cellspacing='0' cellpadding='0'>
						<tr>
							<td style='width: 5%'>
								<p align='left'><ss:text name="article.field.title" /></p>
							</td>
							<td style="width: 85%">
								<input type="text" name="article.title" value="<ss:property value="article.title" />" class="textfield" style="width: 460px;" /><span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 5%'>
								<p align='left'><ss:text name="article.field.category" /></p>
							</td>
							<td style="width: 85%">
								<input type="text" id="category_name_input" name="categoryNames" value='<ss:property value="categoryNames" />' style="width: 460px;" readonly="readonly" class="textfield" />
								<input type="hidden" id="category_id_input" name="categoryIds" value='<ss:property value="categoryIds" />'  />
								<a id="category_select_btn" href="javascript:void(0);">选择</a>
								<a id="category_create_btn" href="javascript:void(0);">新建</a>
							</td>
						</tr>
						<tr>
							<td style='width: 5%'>
								<p align='left'><ss:text name="article.field.tag" /></p>
							</td>
							<td style="width: 85%">
								<input type="text" id="tag_name_input" name="tagNames" value='<ss:property value="tagNames" />' style="width: 460px;" readonly="readonly" class="textfield" />
								<input type="hidden" id="tag_id_input" name="tagIds" value='<ss:property value="tagIds" />'  />
								<a id="tag_select_btn" href="javascript:void(0);">选择</a>
								<a id="tag_create_btn" href="javascript:void(0);">新建</a>
							</td>
						</tr>
						<tr>
							<td style='width: 5%'>
								<p align='left'><ss:text name="article.field.createtime" /></p>
							</td>
							<td style="width: 85%">
								<input type="text" name="article.createTime" value='<ss:property value="article.createTime" />' class="textfield" style="width: 460px;"  readonly="readonly" />
								<span class="spanerror">(*)</span>
							</td>
						</tr>
						<tr>
							<td style='width: 5%'>
								<p align='left'><ss:text name="article.field.istop" /></p>
							</td>
							<td style="width: 85%">
								<input type="checkbox" value="0" onclick="if(this.checked){document.getElementById('hidIsTop').value = '1'}else{this.value=document.getElementById('hidIsTop').value = '0'}" />
								<input type="hidden" name="article.isTop" value="0" id="hidIsTop" <ss:if test="article.isTop == 1">checked="checked"</ss:if> />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea id="article_content" name="article.content" validate="{required:true}" style="height:200pt;width:100%;"><ss:property value="article.content" /></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea id="article_intro" name="article.intro" style="height:80pt;width:100%;"><ss:property value="article.intro" /></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<a href="javascript:" onclick="javascript:void(0);">[<ss:text name="articleedit.label.ping" />]</a>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input class="button" type="submit" value=" <ss:text name="button.submit" /> " />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
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
<%@include file="../includes/footer.jsp" %>