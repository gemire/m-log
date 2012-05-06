var Docs = {};

Docs.ContentManage = {
	id: '002',
    text: '内容管理',
    leaf: false,
    expanded: true,
    children: [
    	{id: '002001',text:'新建文章',leaf:true,url:'admin/toCreateArticle.action',qtip:'新建文章'},
    	{id: '002002',text:'文章管理',leaf:true,url:'admin/queryArticle.action',qtip:'文章管理'},
    	{id: '002003',text:'分类管理',leaf:true,url:'admin/queryCategory.action',qtip:'分类管理'},
    	{id: '002004',text:'文章重建',leaf:true,url:'admin/toBuildArticle.action',qtip:'文章重建'},
    	{id: '002005',text:'标签管理',leaf:true,url:'admin/queryTag.action',qtip:'标签管理'},
    	{id: '002006',text:'评论管理',leaf:true,url:'admin/queryComment.action',qtip:'评论管理'}
    ]
};

Docs.AlbumManage = {
	id: '003',
   	text: '我的相册',
   	leaf: false,
   	expanded: true,
   	children:[
   		{id: '003002',text:'创建相册',leaf:true,url:'admin/toCreateAlbum.action',qtip:'创建相册'},
   		//{id: '003001',text:'上传照片',leaf:true,url:'admin/toUploadPhoto.action',qtip:'上传照片'},
   		{id: '003003',text:'相册管理',leaf:true,url:'admin/queryAlbum.action',qtip:'相册管理'},
   		{id: '003004',text:'相册设置',leaf:true,url:'admin/toEditAlbumConfig.action',qtip:'相册设置'}
   	]
};

Docs.LinkManage = {
	id: '004',
   	text: '链接',
   	leaf: false,
   	expanded: true,
   	children:[
   		{id: '004001',text:'添加链接',leaf:true,url:'admin/toCreateLink.action',qtip:'添加链接'},
   		{id: '004002',text:'链接管理',leaf:true,url:'admin/queryLink.action',qtip:'链接管理'},
   		{id: '004003',text:'链接分类',leaf:true,url:'admin/queryLinkType.action',qtip:'链接分类'}
   	]
};

Docs.SettingManage = {
	id: '005',
   	text: '设置',
   	leaf: false,
   	expanded: true,
   	children:[
   		{id: '005001',text:'网站设置',leaf:true,url:'admin/viewConfig.action',qtip:'网站设置'}
   	]
};

/*
Docs.classData = {
	text: '树根',//节点名称
    expanded: true,//默认展开根节点
	children: [
	    {
	    	id: '001',
	        text: '后台首页',//节点名称
	        leaf: true,//true说明为叶子节点
	        url: 'admin/admin_index.action',
			qtip:'后台首页'
	    }, 
	    {
	    	id: '002',
	        text: '内容管理',
	        leaf: false,
	        expanded: true,
	        children: [
	        	{id: '002001',text:'新建文章',leaf:true,url:'admin/toCreateArticle.action',qtip:'新建文章'},
	        	{id: '002002',text:'文章管理',leaf:true,url:'admin/queryArticle.action',qtip:'文章管理'},
	        	{id: '002003',text:'分类管理',leaf:true,url:'admin/queryCategory.action',qtip:'分类管理'},
	        	{id: '002004',text:'文章重建',leaf:true,url:'admin/toBuildArticle.action',qtip:'文章重建'},
	        	{id: '002005',text:'标签管理',leaf:true,url:'admin/queryTag.action',qtip:'标签管理'},
	        	{id: '002006',text:'评论管理',leaf:true,url:'admin/queryComment.action',qtip:'评论管理'}
	        ]
	    }, 
	    {
	    	id: '003',
	    	text: '我的相册',
	    	leaf: false,
	    	expanded: true,
	    	children:[
	    		{id: '003001',text:'发布照片',leaf:true,url:'admin/toCreateAlbum.action',qtip:'发布照片'},
	    		{id: '003002',text:'创建相簿',leaf:true,url:'admin/toCreateAlbum.action',qtip:'创建相簿'},
	    		{id: '003003',text:'我的相册',leaf:true,url:'admin/queryAlbum.action',qtip:'我的相册'}
	    	]
	    },
	    {
	    	id: '004',
	    	text: '链接',
	    	leaf: false,
	    	expanded: true,
	    	children:[
	    		{id: '004001',text:'添加链接',leaf:true,url:'admin/toCreateLink.action',qtip:'添加链接'},
	    		{id: '004002',text:'链接管理',leaf:true,url:'admin/queryLink.action',qtip:'链接管理'},
	    		{id: '004003',text:'链接分类',leaf:true,url:'admin/queryLinkType.action',qtip:'链接分类'}
	    	]
	    },
	    {
	    	id: '005',
	    	text: '设置',
	    	leaf: false,
	    	expanded: true,
	    	children:[
	    		{id: '005001',text:'网站设置',leaf:true,url:'admin/viewConfig.action',qtip:'网站设置'}
	    	]
	    }
    ]
};
*/

/*
var Docs = {};

Docs.classData={
	"id":"root1",
	"iconCls":"icon-docs",
	"text":"根节点",
	"singleClickExpand":true,
	"expanded":true,
	"href":"",
	"leaf":false,
	"children":[{
		"id":"id11",
		"iconCls":"icon-pkg",
		"text":"子节点一",
		"qtip":"asdk",//需要使用 Ext.QuickTips.init();//开启提示功能 
		"singleClickExpand":true,
		"expanded":true,
		"href":"version.html",
		"leaf":true
	},{
		"id":"child12",
		"iconCls":"icon-pkg",
		"text":"子节点点二节点二",
		"singleClickExpand":true,//单击节点是否展开
		"expanded":true,
		"href":"",
		"leaf":false,
		"children":[{
			"id":"child121",
			"iconCls":"icon-pkg",
			"text":"子节点三",
			"singleClickExpand":true,//单击节点是否展开
			"expanded":true,
			"href":"baidu.htm",
			"leaf":true
		}]
	}]
};

*/

