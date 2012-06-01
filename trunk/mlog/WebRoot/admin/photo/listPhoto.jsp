<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<style type="text/css">
		.photo_name{padding:3px;font:8pt "Tahoma";color:#444444;background:#ddd;vertical-align:bottom;text-align: center;height:15px;}
		.album_list ul {list-style:none;width:100%;border:none;float:left}  
		.album_list li {width:140px;height:140px;float:left;text-align:center;position:relative}
		.album_list li table img{width:120px;height:100px;border:1px solid #ddd;padding:5px;background:#f0f0f0;}
	</style>
	
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/Reorderer.js"></script>
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/ToolbarReorderer.js"></script>
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.js"></script>
	
	<script type="text/javascript" src="<%=path %>/admin/js/uploaderPanel.js"></script>
	
	<script type="text/javascript">
		Ext.onReady(function() {
			Ext.QuickTips.init();
			
			var enable_edit_button = "enableEditButton";
			var disable_edit_button = "disableEditButton";
			var delete_button = "deleteButton";
			var selectall_button = "selectallButton";
			var selectother_button = "selectotherButton";

			var enableEditAction = "enableEdit";
			var disableEditAction = "disableEdit";
			var deleteAction = "delete";
			var selectAllAction = "selectAll";
			var selectOtherAction = "selectOther";
			var uploadPhotoAction = "uploadPhoto";
			
			var handleAction = function(action){
				switch (action) {
					case enableEditAction: //启用编辑模式
						enableEdit();
					break;
					case disableEditAction: //退出编辑模式
						disableEdit();
					break;
					case deleteAction:
						deletePhoto();
					break;
					case selectAllAction: 
						selectAll();
					break;
					case selectOtherAction:
						selectOther();
					break;
					case uploadPhotoAction: 
						showUploadPhotoDialog();
					break;
				}
			}
			
		    var toolbar = new Ext.Toolbar({
		    	renderTo: document.getElementById("SubMenu"),
		        plugins : [
		            new Ext.ux.ToolbarReorderer({
		                defaultReorderable: true
		            })
		        ],
		        items : [
		        	{
		        		text:'返回相册列表',
		        		handler:function(){
		        			window.location = "<%=path %>/admin/queryAlbum.action";
		        		}
		        	},'-',
		            {
						id: enable_edit_button,
		                text: '启用编辑模式',
		                iconCls: 'edit',
		                handler: handleAction.createCallback(enableEditAction)
		            },
					{
						id: disable_edit_button,
						text: '退出编辑模式',
						iconCls: 'turn_left',
						hidden: true,
		                handler: handleAction.createCallback(disableEditAction)
					},'-',
		            {
						id: delete_button,
		                text: '删除',
		                iconCls: 'delete',
		                reorderable: true,
		                disabled:true,
						handler: handleAction.createCallback(deleteAction)
		            },
					{
						id: selectall_button,
						text: '全选',
						disabled:true,
						handler: handleAction.createCallback(selectAllAction)
					},
					{
						id:selectother_button,
						text: '反选',
						disabled:true,
						handler: handleAction.createCallback(selectOtherAction)
					},'->',
					{
						text: '上传照片',
						handler: handleAction.createCallback(uploadPhotoAction)
					}
		        ]
		    });
		    
		    //启用编辑模式
		    function enableEdit(){
		    	Ext.getCmp(delete_button).setDisabled(false);
				Ext.getCmp(selectall_button).setDisabled(false);
				Ext.getCmp(selectother_button).setDisabled(false);

				Ext.getCmp(enable_edit_button).hide();
				Ext.getCmp(disable_edit_button).show();

				var checkboxes = document.getElementsByName("photoItems");
				for(var i = 0; i < checkboxes.length; i++){
					checkboxes[i].disabled = "";
					checkboxes[i].style.display = "";
				}
		    }
		    
		    //退出编辑模式
		    function disableEdit(){
		    	Ext.getCmp(delete_button).setDisabled(true);
				Ext.getCmp(selectall_button).setDisabled(true);
				Ext.getCmp(selectother_button).setDisabled(true);

				Ext.getCmp(enable_edit_button).show();
				Ext.getCmp(disable_edit_button).hide();

				var checkboxes = document.getElementsByName("photoItems");
				for(var i = 0; i < checkboxes.length; i++){
					checkboxes[i].disabled = "disabled";
					checkboxes[i].style.display = "none";
				}
		    }
		    
		    //删除照片
		    function deletePhoto(){
		    	document.photoForm.submit();
		    }
		    
		    //全选
		    function selectAll(){
		    	var checkboxes = document.getElementsByName("photoItems");
				for(var i = 0; i < checkboxes.length; i++){
					checkboxes[i].checked = "checked";
				}
		    }
		    
		    //反选
		    function selectOther(){
		    	var checkboxes = document.getElementsByName("photoItems");
				for(var i = 0; i < checkboxes.length; i++){
					if(checkboxes[i].checked)
						checkboxes[i].checked = "";
					else
						checkboxes[i].checked = "checked";
				}
		    }
		    
		    //显示上传照片对话框
		    function showUploadPhotoDialog(){
		    	new Ext.Window({
					width : 650,
					title : 'swfUpload demo',
					height : 300,
					layout : 'fit',
					items : [
						{
							xtype:'uploadPanel',
							border : false,
							fileSize : 1024*550,//限制文件大小
							uploadUrl : '<%=path %>/admin/uploadPhoto',
							flashUrl : '<%=path%>/script/swfupload/swfupload.swf',
							filePostName : 'file', //后台接收参数
							fileTypes : '*.*',//可上传文件类型
							postParams : {album:getAlbum()}
						}
					]
				}).show();
		    }
		    
		    //得到当前相册编号
		    function getAlbum(){
				return document.getElementById("albumId").value;
			}
		});
	</script>

	<body>
		<div id="divMain">
			<div class="Header">
				<span class="nav">
					<a href="<%=path %>/admin/album/queryAlbum.action">相册管理</a>&gt;&gt;
					<a href="javascript:void(0);" class="current"><ss:property value="album.name" /></a>
				<span>
			</div>
			<div class="SubMenu" id="SubMenu">
				
			</div>
			<div id="divMain2">
				<form name="photoForm" action="<%=path %>/admin/deletePhoto.action" method="post">
					<input type="hidden" id="albumId" name="albumId" value="<ss:property value="photo.album.id" />" />
					<div class="album_list">
						<ul>
							<ss:iterator id="photo" value="photoPage.result">
								<li>
									<table cellpadding="0" cellspacing="0" class="photoTable">
										<tr>
											<td>
												<img src="<%=path %>/<ss:property value="previewUrl" />" border="0"/>
											</td>
										</tr>
										<tr>
											<td class="photo_name">
												<input type="checkbox" name="photoItems" style="display:none;" value="<ss:property value="photoId" />" title="<ss:property value="name" />" />
												<ss:property value="name" />
											</td>
										</tr>
									</table>
								</li>
							</ss:iterator>
						</ul>
					</div>
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
