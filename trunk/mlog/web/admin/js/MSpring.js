var MSpring = {};

MSpring.path = path;

//分页工具条没有数据时显示信息
MSpring.PAGINGTOOLBAR_EMPTYMSG = "没有数据";

//分页工具条显示的分页信息
MSpring.PAGINGTOOLBAR_DISPLAYMSG = "显示 {0} - {1} 条 共 {2} 条";

/*******************************************************************************
 * 属性
 ******************************************************************************/
// 页面头部面板
MSpring.headerPanel = new Ext.Panel({
	border: false,
	layout:'anchor',
	region:'north',
	//cls: 'docs-header',
	height:60,
	items: [{
		id:'header-top',
		xtype:'box',
		el:'header',
		border:false,
		anchor: 'none -25'
	},new Ext.Toolbar({
		items:[{
			 iconCls:'icon-first'
			 },{
			 //此处加载登录用户信息
			 xtype:'label',
			 el:'welcome'
			
			 },
			 '->', {
				iconCls: 'icon-expand-all',
				tooltip: '全屏显示主操作窗口',
				handler: function(){
					var w = Ext.getCmp('left-panel');
					w.collapse();
				}
			 },'-', {
				iconCls: 'icon-close-all',
				tooltip: '恢复窗口布局',
				 handler: function(){
					var w = Ext.getCmp('left-panel');
					w.expand();
				 }
			 }
		]}
	)]
});


// 页面左侧菜单面板
MSpring.menuPanel = new Ext.Panel({
	//西　左边
	region: 'west',
	id: 'left-panel', // see Ext.getCmp() below
	title: '功能区',
	split: true,
	iconCls:'icon-tree',
	width: 150,
	minSize: 100,
	maxSize: 400,
	collapsible: true,
	margins: '0 0 0 5',
	layout: {
		type: 'accordion',
		animate: true//收起具有动画效果
	},
	defaultType :'treepanel'
});


//页面主区域Tab面板
MSpring.tabPanel = new Ext.TabPanel({
	region: 'center',
	deferredRender: false,
	activeTab: 0,
	enableTabScroll : true,
	margins: '0 0 0 0',//设置边距
	enableTabScroll: true,
    plugins: new Ext.ux.TabCloseMenu({
    	closeTabText: '关闭标签页',
    	closeOtherTabsText: '关闭其他标签页',
    	closeAllTabsText: '关闭所有标签页'
    })
});

/*******************************************************************************
 * 方法
 ******************************************************************************/


/**
 * 添加属性菜单面板
 * @param {} treePanel
 * @param {} panelId
 * @param {} panelTitle
 * @param {} dataUrl
 */
MSpring.addTreePanel = function(panelId, panelTitle, dataUrl){
	var tree = new Ext.tree.TreePanel({
		id: panelId,
		title: panelTitle,
	    useArrows: true,
	    autoScroll: true,
	    animate: true,
	    enableDD: false,
	    containerScroll: true,
	    border: false,
	    dataUrl:'',
	    rootVisible: false,
	    loader : new Ext.tree.TreeLoader({
			dataUrl : dataUrl,
			listeners:{
				beforeload : function(treeLoader, node, response){
					if(!isNaN(node.id)){
						this.dataUrl = MSpring.path + '/tree.action?parent=' + node.id;
					}
				}
			}
		}),
	    root: new Ext.tree.AsyncTreeNode({
	        text: 'root',
	        id: 'root',
	        expanded: true
    	}),
    	listeners : {
	    	//当点击时在右边主窗口中加载相应的资源
			click : function(node, e){			
				e.stopEvent();//停止href属性产品的链接操作(自动)
				//如果是非叶子节点，则不用加载相应资源
				if(node.isLeaf()){
					MSpring.openTab(node.id, node.text, node.attributes.url);
				}
			}
	    }
	});
	MSpring.menuPanel.add(tree);
}
//MSpring.addTreePanel = function(panelId, panelTitle, dataUrl){
//	MSpring.menuPanel.add({
//		id : panelId,
//		title : panelTitle,
//		autoScroll : true,
//	    animCollapse : true,
//	    rootVisible : false,
//	    collapsible : true,
//	    tools : [{
//	    	id: 'refresh',
//	    	handler: function(){
//	    		var treepanel = Ext.getCmp(panelId);
//	    		MSpring.reloadTreeNode(treepanel, null, function(node){
//	    			
//	    		});
//	    	}
//	    }],
//	    root : new Ext.tree.AsyncTreeNode({expanded: true}),
//	    listeners : {
//	    	//当点击时在右边主窗口中加载相应的资源
//			click : function(node, e){			
//				e.stopEvent();//停止href属性产品的链接操作(自动)
//				//如果是非叶子节点，则不用加载相应资源
//				if(node.isLeaf()){
//					MSpring.openTab(node.id, node.text, node.attributes.url);
//				}
//			}
//	    },
//	    loader : new Ext.tree.TreeLoader({
//			dataUrl : dataUrl,
//			listeners:{
//				beforeload : function(treeLoader, node, response){
//					if(!isNaN(node.id)){
//						this.dataUrl = MSpring.path + '/tree.action?parent=' + node.id;
//					}
//				}
//			}
//		})
//	});
//	
//	var treePanel = Ext.getCmp(panelId);
//	//处理右键菜单
//	treePanel.on('contextmenu', function(node, e){
//		e.preventDefault();
//		node.select();
//		Ext.Ajax.request({
//			url: MSpring.path + '/contextmenu.action?parent=' + node.id,
//		   	success: function(response){
//		   		var text = response.responseText;
//		   		if(text){
//		   			var contextmenus = eval(text);
//		   			if(contextmenus.length > 0){
//		   				var treeContextMenu = new Ext.menu.Menu();
//			   			for(var i = 0; i < contextmenus.length; i++){
//			   				treeContextMenu.add({
//				   				//id: contextmenus[i].id,
//				   				text: contextmenus[i].text,
//				   				iconCls: contextmenus[i].icon,
//				   				handler: function(contextMenuNode, e){
//				   					eval("MSpring.Note.showCreateNoteCategoryWindow(node.id)");
//				   				}
//			   				});
//			   			}
//			   			treeContextMenu.showAt(e.getXY());
//		   			}
//		   		}
//		   		else{
//		   			//Ext.Msg.alert('系统错误', '初始化UI界面失败.');
//		   		}
//		   	},
//		   	failure: function(){
//		   		//Ext.Msg.alert('系统错误', '初始化UI界面失败.');
//		   	}
//		});
//	});
//}

/**
 * 保存树形菜单状态
 * @param {} treepanel_id
 */
MSpring.storeTreeState = function(treepanel_id){
	var tree = Ext.getCmp(treepanel_id);
	var treeState = new TreePanelState(tree);
	treeState.init();
	tree.on('expandnode', treeState.onExpand, treeState);
	tree.on('collapsenode', treeState.onCollapse, treeState);
	treeState.restoreState(tree.root.getPath());
}


/**
 * 加载菜单面板
 * @param {} panels
 */
MSpring.loadTreePanel = function(panels){
	for(var i = 0; i < panels.length; i++){
		MSpring.addTreePanel(panels[i].id, panels[i].text, MSpring.path + '/tree.action?panel=' + panels[i].id);
		MSpring.storeTreeState(panels[i].id);
	}
}


/**
 * 初始化系统界面
 */
MSpring.initUI = function(){
	Ext.Ajax.request({
		url: MSpring.path + '/panel.action',
	   	success: function(response){
	   		var text = response.responseText;
	   		if(text){
	   			var panels = eval(text);
	   			MSpring.loadTreePanel(panels);
	   			var viewport = new Ext.Viewport({
					layout: 'border',//使用border布局
					items: [MSpring.headerPanel, MSpring.menuPanel, MSpring.tabPanel]
				});
				
				//初始化首页
				MSpring.openTab("overview", "首  页", MSpring.path + "/common/overview.action", false);
	   		}
	   		else{
	   			Ext.Msg.alert('系统错误', '初始化UI界面失败.');
	   		}
	   	},
	   	failure: function(){
	   		Ext.Msg.alert('系统错误', '初始化UI界面失败.');
	   	}
	});
}

/**
 * 重新加载树结点
 * @param tree	树对象
 * @param node	结点对象
 * @param callback	回调方法
 */
MSpring.reloadTreeNode = function(tree, node, callback){
	if(tree){
		if(!node){// 结点为空是，默认为跟结点
			node = tree.getRootNode();
		}
		tree.getLoader().load(node, function(node){
			if(callback){
				callback(node);
			}
		});
	}else{
		Ext.Msg.alert('错误', '参数错误');
	}
}

/**
 * 打开Tab页
 * @param {} tabId
 * @param {} tabTitle
 * @param {} url
 */
MSpring.openTab = function(tabId, tabTitle, url, closable){
	// 参数验证
	if(!MSpring.tabPanel || tabTitle=="" || url==""){
		Ext.Msg.alert("错误", "参数错误.", function(){return false;});
		return;
	}
	url = encodeURI(encodeURI(url));
	var tab = MSpring.tabPanel.get(tabId);
	if(tab){
		MSpring.tabPanel.setActiveTab(tab);
		return;
	}
	
	var _closable = true;
	if(arguments.length == 4){
		var _closable = closable;
	}
	
	tab = MSpring.tabPanel.add({
		id: tabId,
		title: tabTitle,
		layout: 'fit',
		autoLoad: {url:url,scripts:true,nocache:true}
		/*
		 * xtype: 'iframepanel',
		frameConfig:{name:'iframe_' + tabId},
        defaultSrc: url,
        closable: _closable,
        deferredRender: false,
	    resizeTabs: true,
        loadMask:{
        	msg:'loading...'
        }
		 * */
	});
	MSpring.tabPanel.setActiveTab(tab);
}

/**
 * 刷新tab页
 * @param {} tabId
 */
MSpring.reloadTab = function(tabId, tabTitle, url){
	if(tabId) {
		var tab = MSpring.tabPanel.get(tabId);
		if(tab){
			tab.getFrameDocument().location = tab.getFrameDocument().location;
			MSpring.tabPanel.setActiveTab(tab);
		}
		else {
			MSpring.openTab(tabId, tabTitle, url);
		}
	}
}

/**
 * 改变GridPanel的宽度高度以适应窗口大小变化
 */
MSpring.resizeGridTo = function(gridId, offWidth, offHeight){
	var w, h;
	try{
		w = MSpring.tabPanel.getWidth();
		h = MSpring.tabPanel.getHeight() - offHeight;
	}
	catch(e){
		w = window.top.MSpring.tabPanel.getWidth();
		h = window.top.MSpring.tabPanel.getHeight() - offHeight;
	}
	
	var gridPanel = gridPanel = Ext.getCmp(gridId);
	if(gridPanel){
		gridPanel.setWidth(w);
		gridPanel.setHeight(h);
	}
}

/**
 * Ajax请求方法
 * @param		settings	配置对象
 * 包括以下参数： baseParams	参数对象
 * 				action		请求的Action地址 必须
 * 				callback	回调方法
 * 				reload		重新数据载入方法
 * 				showMsg		处理成功时，是否显示提示信息 true:显示 false:不显示 默认:false
 * 				successMsg	请求成功时的提示消息
 * 				failureMsg  请求失败时的提示消息
 * 				showWaiting 加载过程中是否显示进度条 true:显示 false:不显示 默认:false
 */
MSpring.ajaxRequest = function(settings){
	//参数对象
	var params = {};
	if(settings.baseParams){
		params = settings.baseParams
	}
	
	//发送请求
	var wating = null;
	if(settings.showWaiting == true){
		waiting = Ext.Msg.wait('正在处理，请稍等...','','');
	}
	Ext.Ajax.request({
		url: MSpring.path + settings.action,
		params: params,
		success: function(response, options){
			if(waiting != null){
				waiting.hide();
			}
			var jsonResult = Ext.decode(response.responseText);
			if(jsonResult.success==true){
				if(settings.showMsg==true){// 显示提示信息
					// 请求成功时的提示文字
					var successMsg = '操作成功.';
					if(jsonResult.message && jsonResult.message != ''){
						successMsg = jsonResult.message;
					}else if(settings.successMsg && settings.successMsg != ''){
						successMsg = settings.successMsg;
					}
					Ext.Msg.alert('提示', successMsg, function(){
						if(settings.reload){// 加载方法
							settings.reload(jsonResult);
						}
						if(settings.callback){// 回调方法
							settings.callback(jsonResult);
						}
					});
				}else{
					if(settings.reload){// 加载方法
						settings.reload(jsonResult);
					}
					if(settings.callback){// 回调方法
						settings.callback(jsonResult);
					}
				}
			}else{
				var message = '发生异常.';
				if(jsonResult.message && jsonResult.message != ''){// 后台设定的业务消息
					message = jsonResult.message;
				}else if(settings.failureMsg && settings.failureMsg!=''){// 前台指定的错误信息
					message = settings.failureMsg;
				}
				if(jsonResult.exceptionMsg && jsonResult.exceptionMsg != ''){// 有异常信息
					MSpring.exceptionWindow(message, jsonResult.exceptionMsg);
				}else{
					Ext.Msg.alert('错误', message);
				}
			}
		},
		failure: function(response, options){
			Ext.Msg.alert('错误', '请求超时.');
		}
	});
}

/**
 * 显示异常信息的窗口
 * @param exceptionMsg	异常信息
 */
MSpring.exceptionWindow = function(message,exceptionMsg){
	if(message=='') message = '发生异常!';
	if(exceptionMsg=='') exceptionMsg = '发生异常!';
	var exceptionWindow = Ext.getCmp('exceptionWindow');
	if (!exceptionWindow) {
		exceptionWindow = new Ext.Window({
			id: 'exceptionWindow',
			title: '错误',
			width: 400,
			autoHeight: true,
			modal: true,
			layout:'fit',
			items: [
			    new Ext.form.Label({
			    	html:'<div style="padding:5px;">'+message+'</div>'
			    }),
				new Ext.Panel({
					layout:'fit',
					title: '详细信息',
					collapsible: true,
					collapsed: true,
					height: 200,
					items:new Ext.form.TextArea({
						name: 'errorMsg',
						value: exceptionMsg
					})
				})
			]
		}).show();
	}else{
		exceptionWindow.show();
	}
}

/**
 * alert
 * @param {} message
 */
MSpring.alert = function(message){
	Ext.Msg.show({
	   title:'系统提示',
	   msg: message,
	   buttons: Ext.Msg.OK,
	   icon: Ext.MessageBox.INFO
	});
}

/**
 * waring
 * @param {} message
 */
MSpring.warning = function(message){
	Ext.Msg.show({
	   title:'警告',
	   msg: message,
	   buttons: Ext.Msg.OK,
	   icon: Ext.MessageBox.WARNING
	});
}

/**
 * error
 * @param {} message
 */
MSpring.error = function(message){
	Ext.Msg.show({
	   title:'错误',
	   msg: message,
	   buttons: Ext.Msg.OK,
	   icon: Ext.MessageBox.ERROR
	});
}



/**
 * 打开文件上传窗口
 * @param		settings		配置对象
 * 包括以下参数：baseParams		参数对象 必须 至少有一个userId参数
 * 				action			请求的Action地址 必须
 * 				title			窗口标题
 * 				minTargetId		窗口最小化到的目标对象ID
 * 				fileTypes		文件类型
 * 				fileSize		文件大小限制
 * 				fileCount		文件数量限制
 * 				callback		回调方法
 */
MSpring.uploadFile = function(settings){
	// 参数验证
	if(!(settings.baseParams) || !(settings.action)){
		MSpring.error("参数错误.");
		return;
	}
	// 如果窗口存在，关闭
	if(Ext.getCmp('uploadFileWindow')){
		Ext.getCmp('uploadFileWindow').close();
		// 隐藏最小化按钮
		if($("#"+settings.minTargetId)){
			$("#"+settings.minTargetId).hide();
		}
	}
	
	// 参数设定
	var baseParams = baseParams = settings.baseParams;
	baseParams.actionType = "UPLOAD_ACTION";
	// 文件类型
	var fileTypes = "*.*";
	if(settings.fileTypes && settings.fileTypes!=""){
		fileTypes = settings.fileTypes;
	}
	// 文件大小限制
	var fileSize = "1MB";
	if(settings.fileSize && settings.fileSize!=""){
		fileSize = settings.fileSize;
	}
	// 文件数量限制
	var fileCount = 10;
	if(settings.fileCount && settings.fileCount!=""){
		fileCount = settings.fileCount;
	}
	// 标题
	var title = '文件上传';
	if(settings.title && settings.title!=""){
		title = settings.title;
	}

	// 文件上传数据源
    var uploadFileStore = new Ext.data.JsonStore({
        root: 'datas',
        totalProperty: 'results',
        fields: ['fileName']
    });
 	// 文件上传表格
    var uploadFileSm = new Ext.grid.CheckboxSelectionModel();
    var uploadFileGrid = new Ext.grid.GridPanel({
    	region: 'center',
		border: false,
		columnLines: true,
		stateful: true,
	    autoScroll: 'auto',
	    clicksToEdit: 1,
	    height: 395,
        store: uploadFileStore,
        cm: new Ext.grid.ColumnModel({
            defaults: {
                width: 200,
                sortable: true
            },
	        columns: [
				uploadFileSm,
				new Ext.grid.RowNumberer({header:'№'}),
				{id:'fileId',header: '文件ID', width: 50, sortable: true, dataIndex: 'fileId', css: 'vertical-align:middle;', hidden: true},
				{id:'status',header: '状态', width: 50, sortable: true, dataIndex: 'status', css: 'vertical-align:middle;'},
	            {id:'fileName',header: '文件名', width: 150, sortable: true, dataIndex: 'fileName', css: 'vertical-align:middle;'},
	            {id:'fileSize',header: '大小', width: 80, sortable: true, dataIndex: 'fileSize', align: 'right', css: 'vertical-align:middle;', renderer: formatFileSize},
	            {id:'progress',header: '进度', width: 120, sortable: true, dataIndex: 'progress', css: 'vertical-align:middle;', renderer: formatProgressBar},
	            {id:'fileTime',header: '剩余时间', width: 100, sortable: true, dataIndex: 'fileTime', css: 'vertical-align:middle;'},
	            {id:'fileSpeed',header: '速度', width: 100, sortable: true, dataIndex: 'fileSpeed', css: 'vertical-align:middle;'}
	        ]
        }),
        sm: uploadFileSm,
        columnLines: true
    });

	// 工具栏
    var uploadFileToolbar = new Ext.Toolbar({
    	items: [
    	    '<div id="uploadFile-add-button">添加</div>',
    	    new Ext.Button({
    	    	id: 'uploadFile-delete-button',
    	    	text: '删除',
    	    	iconCls: 'delete'
    	    }),
    	    '-',
	        new Ext.Button({
	        	id: 'uploadFile-upload-button',
	        	text: '开始上传',
	        	iconCls: 'upload'
	        }),
	        new Ext.Button({
	        	id: 'uploadFile-cancel-button',
	        	text: '取消队列',
	        	iconCls: 'stop2'
	        }),
	        '-',
			new Ext.Button({
			    id: 'uploadFile-hide-button',
				text: '隐藏',
				iconCls: 'hide'
			}),
			new Ext.Button({
			    id: 'uploadFile-close-button',
				text: '关闭',
				iconCls: 'cross'
			})
		]
	});

    // 状态栏
    var uploadFileStatusPanel = new Ext.Panel({
    	region: 'south',
    	layout:'fit',
    	height: 20,
    	border: false,
    	bodyStyle: 'background-color:transparent;padding:2px;',
    	html: '准备就绪'
    });
    
    // 判断是否需要最小化和模态显示
    var minimizable = true;
    var modal = false;
	if(!(settings.minTargetId && settings.minTargetId!='')){
		// 去掉隐藏按钮
		uploadFileToolbar.remove('uploadFile-hide-button');
		// 去掉最小化按钮
		minimizable = false;
		// 模态显示
		modal = true;
	}

	// 打开文件上传窗口
	var uploadFileWindow = new Ext.Window({
		id: 'uploadFileWindow',
		title: title,
		width: 680,
		height: 480,
		maximizable: false,
		minimizable: minimizable,
		resizable: false,
		layout:'border',
		plain: true,
		modal: modal,
		items: [uploadFileGrid,uploadFileStatusPanel],
		tbar: uploadFileToolbar,
		listeners: {
			close: function(){
				// 自定义的回调函数
				if(settings.callback){
					settings.callback(settings.baseParams);
				}
				// 隐藏最小化按钮
				if($("#"+settings.minTargetId)){
					$("#"+settings.minTargetId).hide();
				}
			},
			minimize: function(){
				if($("#"+settings.minTargetId)){
					// 显示最小化按钮
					$("#"+settings.minTargetId).show();
					// 更新状态
					updateStatus();
					// 隐藏窗口
					this.setAnimateTarget(settings.minTargetId);
					this.hide();
				}else{
					return;
				}
			}
		}
	}).show();
	
	// 格式化文件大小
	function formatFileSize(value, cellmeta,record){ 
		return Ext.util.Format.fileSize(value);
	}
	
	// 格式化进度条
	function formatProgressBar(value, cellmeta,record){ 
		var returnValue = '';
		if(record.data.status == '已完成'){
			if (Ext.isIE) {
				returnValue = '<div class="x-progress-wrap" style="height: 18px">'
						+ '<div class="x-progress-inner">'
						+ '<div style="width: 100%;" class="x-progress-bar x-progress-text">' + '100 %'
				'</div>' + '</div>' + '</div>';
			} else {
				returnValue = '<div class="x-progress-wrap" style="height: 18px">'
						+ '<div class="x-progress-inner">' + '<div id="progressBar_' + record.data.fileId
						+ '" style="width: 100%;" class="x-progress-bar">' + '</div>' + '<div id="progressText_'
						+ record.data.fileId
						+ '" style="width: 100%;" class="x-progress-text x-progress-text-back" />100 %</div>'
						'</div>' + '</div>';
			}
		}else{
			returnValue = '<div class="x-progress-wrap" style="height: 18px">' + '<div class="x-progress-inner">'
						+ '<div id="progressBar_' + record.data.fileId + '" style="width: 0%;" class="x-progress-bar">'
						+ '</div>' + '<div id="progressText_' + record.data.fileId
						+ '" style="width: 100%;" class="x-progress-text x-progress-text-back" />0 %</div>'
						'</div>' + '</div>';
		}
		return returnValue;
	}
	
	var swfu = new SWFUpload({
		flash_url : MSpring.path + "/script/swfupload/swfupload.swf",
		upload_url: MSpring.path + settings.action,	
		post_params: {albumId: 1},
		file_post_name : "fileData",
		file_size_limit : fileSize,
		file_types : fileTypes,
		file_types_description : "文件",
		file_upload_limit : fileCount,  //配置上传个数
		file_queue_limit : 0,
		use_query_string: true,
		debug: false,

		// Button settings
		button_image_url: MSpring.path + "/script/extjs/resources/icons/add-upload.png",
		button_width: "45",
		button_height: "20",
		button_placeholder_id: "uploadFile-add-button",
		button_text: '添加',
		button_text_style: '',
		button_text_left_padding: 17,
		button_cursor: SWFUpload.CURSOR.HAND,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		
		file_queued_handler : fileQueued,
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,
		queue_complete_handler : queueComplete	
	});
	
    
    // 正在上传的记录
    var currentUploadRecord = null;
    // 本次上传的文件数
    var currentUploadedCount = 0;
    // 总计上传的文件数
    var totalUploadedCount = 0;
	
	// 添加按钮
    $("#uploadFile-add-button").click(function(){
    	$("#SWFUpload_0").click();
	});
	
	// 删除按钮
    $("#uploadFile-delete-button").click(function(){
    	var records = uploadFileGrid.getSelectionModel().getSelections();
		if(records.length < 1){
			Ext.Msg.alert("提示", "请至少选择一条数据.");
		}else{
			for(var i=0;i<records.length;i++){
				var record = records[i];
				uploadFileStore.remove(record);
				swfu.cancelUpload(record.get("fileId"), false);
				if(record.get("status")=="已完成"){
					// 上传总数
					if(currentUploadedCount - 1>0){
						currentUploadedCount = currentUploadedCount - 1;
					}else{
						currentUploadedCount = 0;
					}
				}
			}
			// 更新状态栏
        	updateStatus();
		}
	});
	
	// 上传按钮
    $("#uploadFile-upload-button").click(function(){
    	if(uploadFileStore.getCount()<1){
	    	Ext.Msg.alert("提示", "请添加文件.");
		}else{
			swfu.setPostParams(baseParams);
			swfu.startUpload();
		}
	});

	// 取消按钮
    $("#uploadFile-cancel-button").click(function(){
    	if(currentUploadRecord!=null){
	    	swfu.cancelUpload(currentUploadRecord.get("fileId"));
		}else{
			Ext.Msg.alert("提示", "没有上传中的数据.");
		}
	});
    
    // 隐藏按钮
    $("#uploadFile-hide-button").click(function(){
    	uploadFileWindow.minimize();
	});
	
	// 关闭按钮
    $("#uploadFile-close-button").click(function(){
    	uploadFileWindow.close();
	});

    // 选择完文件后，将文件添加到Grid
    function fileQueued(file) {
    	var record = new Ext.data.Record({
    		fileId: file.id,
    		status: '等待中',
    		fileName: file.name,
    		fileSize: file.size,
    		process: '',
    		fileTime: '--',
    		fileSpeed: '--'
		});
    	uploadFileStore.insert(uploadFileStore.getCount(), record);
    }

    // 文件验证
    function fileQueueError(file, errorCode, message) {
    	if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
    		Ext.Msg.alert("错误", "最多只能同时上传  " + message + " 个文件.");
    		return;
    	}

    	switch (errorCode) {
    	case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
    		Ext.Msg.alert("错误", "文件尺寸过大.文件尺寸: " + file.size / 1024 +"KB.");
    		break;
    	case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
    		Ext.Msg.alert("错误", "无法上传零字节文件.");
    		break;
    	case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
    		Ext.Msg.alert("错误", "不支持的文件类型.文件名: " + file.name +".");
    		break;
    	default:
    		if (file !== null) {
    			Ext.Msg.alert("错误", "未处理的错误.");
    		}
    		break;
    	}
    }
    //文件选择完成后
    function fileDialogComplete(numFilesSelected, numFilesQueued) {
    	// 更新状态栏
    	updateStatus();
    }

    //单个文件开始上传
    function uploadStart(file) {
    	var fileId = file.id;
    	var records = uploadFileStore.getRange(0,uploadFileStore.getCount()-1);
    	for(var i=0;i<records.length;i++){
    		var record = records[i];
    		if(fileId==record.get("fileId")){
    			currentUploadRecord = record;
    			break;
    		}
    	}
    }

    //单个文件上传中
    function uploadProgress(file, bytesLoaded, bytesTotal) {
    	if(currentUploadRecord!=null){
    		// 更新状态
    		currentUploadRecord.set("status", "进行中");
    		// 剩余时间
    		currentUploadRecord.set("fileTime", SWFUpload.speed.formatTime(file.timeRemaining));
    		// 当前速度
    		currentUploadRecord.set("fileSpeed", SWFUpload.speed.formatBPS(file.currentSpeed));
    		// 更新进度条
    		var percent = Math.ceil((bytesLoaded / bytesTotal) * 99);
    		Ext.getDom('progressBar_' + file.id).style.width = percent + "%";
    		Ext.getDom('progressText_' + file.id).innerHTML = percent + " %";
    	}else{
    		Ext.Msg.alert("错误", "没有上传中的文件.");
    	}
    }

    //单个文件上传成功
    function uploadSuccess(file, serverData) {
    	var jsonResult = Ext.decode(serverData);
    	if(jsonResult && jsonResult.success){
    		if(currentUploadRecord!=null){
    			// 更新状态
    			currentUploadRecord.set("status", "已完成");
    			// 本次上传
    			currentUploadedCount = currentUploadedCount + 1;
    			// 总计上传
    			totalUploadedCount = totalUploadedCount + 1;
    			// 更新状态栏
    			updateStatus();
    		}else{
    			Ext.Msg.alert("错误", "没有上传中的文件.");
    		}
    	}else{
    		var message = '发生异常.';
			if(jsonResult.message && jsonResult.message != ''){// 后台设定的业务消息
				message = jsonResult.message;
			}else if(settings.failureMsg && settings.failureMsg!=''){// 前台指定的错误信息
				message = settings.failureMsg;
			}
			if(jsonResult.exceptionMsg && jsonResult.exceptionMsg != ''){// 有异常信息
				MSpring.exceptionWindow(message, jsonResult.exceptionMsg);
			}else{
				Ext.Msg.alert('错误', message);
			}
    	}
    }

    //文件上传错误验证
    function uploadError(file, errorCode, message) {
    	switch (errorCode) {
    	case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
    		currentUploadRecord.set("status", "HTTP错误");
    		break;
    	case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
    		currentUploadRecord.set("status", "上传失败");
    		break;
    	case SWFUpload.UPLOAD_ERROR.IO_ERROR:
    		currentUploadRecord.set("status", "IO 错误");
    		break;
    	case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
    		currentUploadRecord.set("status", "安全错误");
    		break;
    	case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
    		currentUploadRecord.set("status", "超出上传限制");
    		break;
    	case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
    		currentUploadRecord.set("status", "文件验证失败");
    		break;
    	case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
    		currentUploadRecord.set("status", "取消");
    		break;
    	case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
    		currentUploadRecord.set("status", "停止");
    		break;
    	default:
    		currentUploadRecord.set("status", "未处理的错误");
    		break;
    	}
    }

    //单个文件上传完成
    function uploadComplete(file) {
    }

    // 全部上传完成
    function queueComplete(numFilesUploaded) {
    	currentUploadRecord = null;
    	Ext.Msg.alert('提示', '上传完成.');
    }
    
    // 更新状态栏
    function updateStatus(){
    	// 等待上传
    	var unUploadCount = uploadFileStore.getCount() - currentUploadedCount;
    	uploadFileStatusPanel.body.update("等待上传: "+unUploadCount+" 个，本次上传: "+currentUploadedCount+" 个 ，总计上传: "+totalUploadedCount+" 个。");
    	if(Ext.getCmp("minUploadWinBtn")){
    		var btnText = "";
    		if(unUploadCount==0 && currentUploadedCount==0){
    			btnText = "准备就绪";
    		}else if(unUploadCount!=0 && currentUploadedCount==0){
    			btnText = "等待上传";
    		}else if(unUploadCount!=0 && currentUploadedCount!=0){
    			btnText = "上传中..."+currentUploadedCount+"/"+(currentUploadedCount+unUploadCount);
    		}else if(unUploadCount==0 && currentUploadedCount!=0){
    			btnText = "上传完成";
    		}
    		Ext.getCmp("minUploadWinBtn").setText(btnText);
    	}
    }
}