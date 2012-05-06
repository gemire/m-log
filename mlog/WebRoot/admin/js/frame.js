//设置窗口首页显示的页面
var mainIndexUrl = "admin/overview.action";
Ext.BLANK_IMAGE_URL = 'script/extjs/resources/images/default/s.gif';

/**
 * 主窗口TabPanel组件
 * 作用：作为其它窗口的窗口
*/	
var mainPanel = new Ext.TabPanel({
	region: 'center',
	deferredRender: false,
	activeTab: 0,
	enableTabScroll : true,
	margins: '0 0 0 0',//设置边距
	items: [{
		id: '001',
		title: '后台首页',                
		autoScroll: true,
		html : '<iframe id="frame_001" name="main" src="admin/overview.action" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>'
	}]
	//,plugins: [new Ext.ux.TabCloseMenu()]
});

/**
 * 左边功能区组件
 */
var treePanel = new Ext.Panel({
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

/**
 * 声明一个顶部组件
*/
var headerPanel = new Ext.Panel({
	border: false,
	layout:'anchor',
	region:'north',
	cls: 'docs-header',
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
			
			 },'-', {
			 	text: '返回首页',
			 	iconCls: 'icon-home',
			 	handler: function(){
			 		window.location = '/mlog';
			 	}
			 },'->', {
				iconCls: 'icon-expand-all',
				tooltip: '全屏显示主操作窗口',
				handler: function(){
					var w = Ext.getCmp('left-panel');
					w.collapse();					
					//var b = Ext.getCmp('bottom_panel');
					//b.collapse();
				}
			 },'-', {
				iconCls: 'icon-close-all',
				tooltip: '恢复窗口布局',
				 handler: function(){
					var w = Ext.getCmp('left-panel');
					w.expand();					
					//var b = Ext.getCmp('bottom_panel');
					//b.expand();
				 }
			 },'-'
		]}
	)]
})

/**
 * 声明一个底部组件(版本信息)
*/
//var buttomPanel = new Ext.Panel({
//	//下南创建panl对象，因为viewport子项默认就是panel（xtype=panel）
//	id: 'bottom_panel',
//	region: 'south',
//	contentEl: 'bottom',//可以将html标签ID为south，内容加载进来
//	height: 70,//设置高度
//	collapsible: true,//是否可以收缩起来
//	margins: '0 0 0 0'//设置边距
//	//split: true,//设置是否可以使用鼠标调整大小(true:可以，否则不可以)
//	//minSize: 60,//设置最小高度(主要用于手动调整大小)
//	//maxSize: 70,//设置最大高度(主要用于手动调整大小)
//})

var buttomPanel = new Ext.Panel({
	border: false,
	layout: 'anchor',
	region: 'south',
	height: 30,
	items: [{
		id: 'bottom_panel',
		xtype: 'box',
		el: 'bottom',
		border: false,
		anchor: 'none -25'
	}]
});

function loadTreePanel(panels){
	for(var i = 0; i < panels.length; i++){
		addTreePanel(treePanel, panels[i].id, panels[i].text, 'admin/tree.action?panel=' + panels[i].id);
		storeTreeState(panels[i].id);
	}
}

//初始化主界面
function initUI(){
	Ext.Ajax.request({
		url: 'admin/panel.action',
	   	success: function(response){
	   		var text = response.responseText;
	   		if(text){
	   			var panels = eval(text);
	   			loadTreePanel(panels);
	   			var viewport = new Ext.Viewport({
	   				id : 'main_viewport',
					layout: 'border',//使用border布局
					items: [//布局中的各项目
						headerPanel,
						treePanel,
						mainPanel,
						buttomPanel
					]
				});
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