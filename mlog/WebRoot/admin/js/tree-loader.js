function addTreePanel(treePanel, panelId, panelTitle, dataUrl){
	treePanel.add({
		id : panelId,
		title : panelTitle,
		autoScroll : true,
	    animCollapse : true,
	    rootVisible : false,
	    collapsible : true,
	    //dataUrl : 'admin/tree.action',
	    root : new Ext.tree.AsyncTreeNode({expanded: true}),
	    listeners : {
	    	//当点击时在右边主窗口中加载相应的资源
			click : function(node, e){			
				e.stopEvent();//停止href属性产品的链接操作(自动)
				//如果是非叶子节点，则不用加载相应资源
				if(node.isLeaf()){
					_loadPanel(node);//加载叶子节点对应的资源							
				}
			}
	    },
	    loader : new Ext.tree.TreeLoader({
			dataUrl : dataUrl,
			listeners:{  
				beforeload : function(treeLoader, node){
					if(!isNaN(node.id)){
						this.dataUrl = 'admin/tree.action?parent=' + node.id;
					}
				}  
			}
		})
	});
}

//cookie记录tree的状态
function storeTreeState(treepanel_id){
	var tree = Ext.getCmp(treepanel_id);
	// add cookie base tree state
	var treeState = new TreePanelState(tree);
	
	treeState.init();
	// initialize event handlers
	tree.on('expandnode', treeState.onExpand, treeState);
	tree.on('collapsenode', treeState.onCollapse, treeState);
	// restore last state from tree or to the root node as default
	treeState.restoreState(tree.root.getPath());
}


/**
 * 向TabPanel组件中添加窗口或激活已经存在的窗口
 * 并将指定资源加载进窗口
 * @param node : 传入的Node节点
*/
function _loadPanel(node) {
	var id=node.id;//获取节点的id
	var href = node.attributes.url;//获取节点中的href属性
	var text = node.text;
	var qtip = node.attributes.qtip; //提示信息
	
	loadPanel(id, href, text, qtip);
}

function loadPanel(id, href, text, qtip){
	var tabPanel = window.top.mainPanel;
	var tab = tabPanel.getComponent(id);//获取指定id的组件对象
	if(tab) {
		//检验当前Tab选项卡是否存在，如果存在只需要激活
		tabPanel.setActiveTab(tab);
		//tab.getUpdater().refresh(); //刷新
		return;
	}
	//如果选项卡不存在，则以下为创建选项卡代码
	var autoLoad = {url: href, nocache: true,scripts:true};//根据href生成autoLoad对象
	
	tab = tabPanel.add(new Ext.Panel ({
		//创建新选项卡的配置
		id : id,			//设置ID
		title : text,			//设置选项卡标题
		tabTip : qtip, 
		toolTip: qtip,				//提示内容
		html : '<iframe id="frame_' + id + '" name="main" src="' + href + '" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>',
		//autoLoad: autoLoad,		//设置自动加载的资源URL
		autoScroll: true,
		closable : true			//是否可以关闭
	}));
	tabPanel.setActiveTab(tab);//创建选项卡后，将其激活
}

//重建Tab
function rebuildPanel(id, href, text, qtip){
	var tabPanel = window.top.mainPanel;
	var tab = tabPanel.getComponent(id);//获取指定id的组件对象
	if(tab) {
		tabPanel.setActiveTab(tab);
		//var _frame = document.getElementById("frame_" + id);
		//_frame.src = url;
		tabPanel.remove(tab);
	}
	loadPanel(id, href, text, qtip);
}
