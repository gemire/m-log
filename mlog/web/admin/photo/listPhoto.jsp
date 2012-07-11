<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
	<style type="text/css">
		.photo_name{padding:3px;font:8pt "Tahoma";color:#444444;background:#ddd;vertical-align:bottom;text-align: center;height:15px;}
		.album_list ul {list-style:none;width:100%;border:none;float:left}  
		.album_list li {width:140px;height:140px;float:left;text-align:center;position:relative}
		.album_list li table img{width:120px;height:100px;border:1px solid #ddd;padding:5px;background:#f0f0f0;}
	</style>
	<link rel="stylesheet" type="text/css" href="<%=path %>/script/extjs/ux/css/fileuploadfield.css" />
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/Reorderer.js"></script>
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/ToolbarReorderer.js"></script>
	
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.queue.js"></script>
	<script type="text/javascript" src="<%=path %>/script/swfupload/swfupload.speed.js"></script>
	<script type="text/javascript" src="<%=path %>/script/swfupload/fileprogress.js"></script>
	
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/FileUploadField.js"></script>
	<script type="text/javascript" src="<%=path %>/script/extjs/ux/DataView-more.js"></script>
		
	<script type="text/javascript">
		Ext.onReady(function() {
			Ext.QuickTips.init();
			
		   	var albumId = '<ss:property value="album.id" />';
		   	var pageSize = 15;
		   	var totalCount = '<ss:property value="photoPage.totalCount" />';
			var baseParams = {'photo.album.id':albumId};
			
			//图片查看框的大小
			var galleryWinWidth = $(window.top.document).width() * 0.8;
			var galleryWinHeight = $(window.top.document).height() * 0.8;
			
			//当前选中的图片编号
			var pictureIds = '';
			
			//图片数据源
			var pictureListStore = new Ext.data.JsonStore({
				url : '<%=path %>/photo/listPhoto.action',
				root : 'datas',
				totalProperty: 'totalCount',
		        fields: ['photoId', 'name', 'url', 'previewUrl'],
		        sortInfo: {
		            field    : 'photoId',
		            direction: 'DESC'
		        },
		       	baseParams: baseParams,
		        pruneModifiedRecords: true,
		        autoLoad: true
			});
			
			
			//工具栏
			var pictureListToolbar = new Ext.Toolbar({
				renderTo: 'pictureListToolBarDiv',
		    	items: [
					'当前：<ss:property value="album.name" />',
					'-',
				    new Ext.Button({
					    id: 'pictureList-upload-button',
						text: '本地上传',
						iconCls: 'upload',
						handler: showUploadPhotoDialog
					}),
					new Ext.Button({
					    id: 'pictureList-grab-button',
						text: '网络图片',
						iconCls: 'picture_link'
					}),
					'-',
					new Ext.Button({
					    id: 'pictureList-save-button',
					    text: '保存',
						iconCls: 'picture_save'
					}),
					new Ext.Button({
					    id: 'pictureList-delete-button',
					    text: '删除',
						iconCls: 'picture_delete',
						handler: deletePhoto
					}),
					new Ext.Button({
					    id: 'pictureList-move-button',
					    text: '移动',
						iconCls: 'picture_go'
					}),
					'-',
					new Ext.Button({
					    id: 'pictureList-view-button',
					    text: '查看',
						iconCls: 'picture'
					}),
					new Ext.Button({
					    id: 'pictureList-gallery-button',
					    text: '幻灯片',
						iconCls: 'pictures'
					})
				]
			});
			
			// 分页工具栏
			var pictureListBottomBar = new Ext.PagingToolbar({
				pageSize: pageSize,
				store: pictureListStore,
				displayInfo: true,
				displayMsg: MSpring.PAGINGTOOLBAR_DISPLAYMSG,
				emptyMsg: MSpring.PAGINGTOOLBAR_EMPTYMSG,
				doLoad: function(start){
					//baseParams.start = start;
					alert(start);
					this.store.load({params: baseParams});
				}
	        });
			
			
			// 图片显示模板
			var tpl = new Ext.XTemplate(
				'<tpl for=".">',
		            '<div class="thumb-wrap" id="picture_{photoId}">',
			            '<div class="thumb-subwrap">',
						    '<div class="thumb" id="preview_{photoId}">',
						    	'<img src="<%=path %>/{previewUrl}" title="{name}">',
						    	'<span class="x-editable">{name}</span>',
						    '</div>',
						'</div>',
				    '</div>',
		        '</tpl>',
		        '<div class="x-clear"></div>'
			);
			
			// 图片显示DataView
			var pictureDataView = new Ext.DataView({
				id: 'pictureDataView',
	            store: pictureListStore,
	            loadingText: '加载中...',
	            tpl: tpl,
	            multiSelect: true,
	            autoScroll  : true,
	            overClass:'x-view-over',
	            itemSelector:'div.thumb-wrap',
	            plugins: [
	                new Ext.DataView.DragSelector(),
	                new Ext.DataView.LabelEditor({dataIndex: 'name'})
	            ],
	            prepareData: function(data){
	                data.shortName = Ext.util.Format.ellipsis(data.pictureName, 12);
	                return data;
	            },
	            listeners: {
	            	selectionchange: function(dv,nodes){
	            		pictureIds = '';
						for(i=0;i<nodes.length;i++){
							var node = nodes[i];
							var pictureId = node.id.substring(node.id.indexOf('_')+1);
							pictureIds = pictureIds + pictureId + ',';
						}
	            	},
	            	dblclick: function(dv, index, node){
		            	//showGalleryWin(index);
		            }
	            }
	        });
			// 图片显示panel
			var pictureListPanel = new Ext.Panel({
				id: 'pictureListPanel',
				renderTo: 'pictureListPanelDiv',
				layout: 'fit',
				border: false,
				items: pictureDataView,
				bbar: pictureListBottomBar
		    });
		    
		    // 设置Grid高度和宽度
			MSpring.resizeGridTo("pictureListPanel", 0, 56);
			
			//上传照片
			function showUploadPhotoDialog(){
		    	var _baseParams = {album:'<ss:property value="album.id" />'};
		    	MSpring.uploadFile({
					baseParams: _baseParams,
					action: '/admin/uploadPhoto',
					title: '图片上传',
					fileTypes: '*.jpg;*.jpeg;*.png;',
					fileSize: '5MB',
					fileCount: 100,
					minTargetId: 'minUploadWinDiv',
					callback: function(baseParams){
						//刷新相册列表...
						pictureListStore.reload({
	    					params: baseParams
		    			});
					}
				});
		    }
		    
		    //删除照片
		    function deletePhoto(){
		    	if(pictureIds != ''){
		    		Ext.Msg.confirm("警告", "确定删除吗？", function(btn){
		    			if(btn == 'yes'){
		    				MSpring.ajaxRequest({
		    					baseParams: {photoItems:pictureIds,albumId:albumId},
		    					action: '/photo/deletePhoto.action',
		    					callback:function(jsonResult){
		    						pictureListStore.reload();
		    					},
		    					showWaiting: true,
		    					failureMsg: '删除失败。'
		    				});
		    			}
		    		});
		    	}
		    	else {
		    		MSpring.alert("请选择图片");
		    	}
		    }
		});
	</script>

	<body>
		<input type="hidden" id="albumId" name="albumId" value='<ss:property value="album.id" />' />
		<div id="pictureListDiv">
			<div id="pictureListToolBarDiv"></div>
			<div id="pictureListPanelDiv" class="pictureDataViewDiv" style="width:100%;height:100%;">
			</div>
		</div>
		<div id="mapPic" class="x-hidden">
			<div class="nav">
				<div class="up" id="up"></div>
				<div class="right" id="right"></div>
				<div class="down" id="down"></div>
				<div class="left" id="left"></div>
				<div class="zoom" id="zoom"></div>
				<div class="in" id="in"></div>
				<div class="out" id="out"></div>
			</div>
			<img id='image' src='' border='0' style="cursor: url(images/openhand_8_8.cur), default;" >
		</div>
	</body>
<%@include file="../includes/footer.jsp" %>
