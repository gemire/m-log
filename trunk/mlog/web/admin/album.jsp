<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>G-Blog Manager</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/script/extjs/resources/css/ext-all.css" />
  		<link rel="stylesheet" type="text/css" href="<%=path %>/admin/resources/main.css" />
  		<link rel="stylesheet" type="text/css" href="<%=path %>/admin/resources/album.css" />
  		
	    <script type="text/javascript" src="<%=path %>/script/extjs/adapter/ext/ext-base.js"></script>
	    <script type="text/javascript" src="<%=path %>/script/extjs/ext-all.js"></script>

		<script type="text/javascript" src="<%=path %>/script/extjs/ux/DataView-more.js"></script>
		
	    <script type="text/javascript">
		    Ext.onReady(function(){
				Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
				Ext.QuickTips.init();//开启提示功能   
				
				var tb = new Ext.Toolbar({
					items:[
						{
							text : '新建相册',
							iconCls: 'album-btn',
							handler: function(){
				                var node = root.appendChild(new Ext.tree.TreeNode({
				                    text:'Album ' + (++newIndex),
				                    cls:'album-node',
				                    allowDrag:false
				                }));
				                tree.getSelectionModel().select(node);
				                setTimeout(function(){
				                    ge.editNode = node;
				                    ge.startEdit(node.ui.textNode);
				                }, 10);
				            }
						}
					]
				});
				
				var tree = new Ext.tree.TreePanel({
					animate:true,
			         enableDD:true,
			         containerScroll: true,
			         ddGroup: 'organizerDD',
			         rootVisible:false,
			         // layout
			         region:'west',
			         width:200,
			         split:true,
			         // panel
			         title:'相册列表',
			         autoScroll:true,
			         tbar: tb,
			         margins: '5 0 5 5'
				});
				
				var root = new Ext.tree.TreeNode({
			        text: 'Albums',
			        allowDrag:false,
			        allowDrop:false
			    });
			    tree.setRootNode(root);
			    
			    root.appendChild(
			        new Ext.tree.TreeNode({text:'Album 1', cls:'album-node', allowDrag:false}),
			        new Ext.tree.TreeNode({text:'Album 2', cls:'album-node', allowDrag:false}),
			        new Ext.tree.TreeNode({text:'Album 3', cls:'album-node', allowDrag:false})
			    );
			    
			    // add an inline editor for the nodes
			    var ge = new Ext.tree.TreeEditor(tree, {/* fieldconfig here */ }, {
			        allowBlank:false,
			        blankText:'A name is required',
			        selectOnFocus:true
			    });
			    
			    var view = new Ext.DataView({
			        itemSelector: 'div.thumb-wrap',
			        style:'overflow:auto',
			        multiSelect: true,
			        plugins: new Ext.DataView.DragSelector({dragSafe:true}),
			        store: new Ext.data.JsonStore({
			            url: '../view/get-images.php',
			            autoLoad: true,
			            root: 'images',
			            id:'name',
			            fields:[
			                'name', 'url',
			                {name: 'shortName', mapping: 'name', convert: shortName}
			            ]
			        }),
			        tpl: new Ext.XTemplate(
			            '<tpl for=".">',
			            '<div class="thumb-wrap" id="{name}">',
			            '<div class="thumb"><img src="../view/{url}" class="thumb-img"></div>',
			            '<span>{shortName}</span></div>',
			            '</tpl>'
			        )
			    });
			
			    var images = new Ext.Panel({
			        id:'images',
			        title:'图片列表',
			        region:'center',
			        margins: '5 5 5 0',
			        layout:'fit',
			        
			        items: view
			    });
			    
			    var viewport = new Ext.Viewport({
					layout: 'border',
					items: [
						tree,
						images
					]
				});
			
			    var dragZone = new ImageDragZone(view, {containerScroll:true, ddGroup: 'organizerDD'});
		    });
		    
		    
		    /**
			 * Create a DragZone instance for our JsonView
			 */
			ImageDragZone = function(view, config){
			    this.view = view;
			    ImageDragZone.superclass.constructor.call(this, view.getEl(), config);
			};
			Ext.extend(ImageDragZone, Ext.dd.DragZone, {
			    // We don't want to register our image elements, so let's 
			    // override the default registry lookup to fetch the image 
			    // from the event instead
			    getDragData : function(e){
			        var target = e.getTarget('.thumb-wrap');
			        if(target){
			            var view = this.view;
			            if(!view.isSelected(target)){
			                view.onClick(e);
			            }
			            var selNodes = view.getSelectedNodes();
			            var dragData = {
			                nodes: selNodes
			            };
			            if(selNodes.length == 1){
			                dragData.ddel = target;
			                dragData.single = true;
			            }else{
			                var div = document.createElement('div'); // create the multi element drag "ghost"
			                div.className = 'multi-proxy';
			                for(var i = 0, len = selNodes.length; i < len; i++){
			                    div.appendChild(selNodes[i].firstChild.firstChild.cloneNode(true)); // image nodes only
			                    if((i+1) % 3 == 0){
			                        div.appendChild(document.createElement('br'));
			                    }
			                }
			                var count = document.createElement('div'); // selected image count
			                count.innerHTML = i + ' images selected';
			                div.appendChild(count);
			                
			                dragData.ddel = div;
			                dragData.multi = true;
			            }
			            return dragData;
			        }
			        return false;
			    },
			
			    // this method is called by the TreeDropZone after a node drop
			    // to get the new tree node (there are also other way, but this is easiest)
			    getTreeNode : function(){
			        var treeNodes = [];
			        var nodeData = this.view.getRecords(this.dragData.nodes);
			        for(var i = 0, len = nodeData.length; i < len; i++){
			            var data = nodeData[i].data;
			            treeNodes.push(new Ext.tree.TreeNode({
			                text: data.name,
			                icon: '../view/'+data.url,
			                data: data,
			                leaf:true,
			                cls: 'image-node'
			            }));
			        }
			        return treeNodes;
			    },
			    
			    // the default action is to "highlight" after a bad drop
			    // but since an image can't be highlighted, let's frame it 
			    afterRepair:function(){
			        for(var i = 0, len = this.dragData.nodes.length; i < len; i++){
			            Ext.fly(this.dragData.nodes[i]).frame('#8db2e3', 1);
			        }
			        this.dragging = false;    
			    },
			    
			    // override the default repairXY with one offset for the margins and padding
			    getRepairXY : function(e){
			        if(!this.dragData.multi){
			            var xy = Ext.Element.fly(this.dragData.ddel).getXY();
			            xy[0]+=3;xy[1]+=3;
			            return xy;
			        }
			        return false;
			    }
			});
			
			// Utility functions
			
			function shortName(name){
			    if(name.length > 15){
			        return name.substr(0, 12) + '...';
			    }
			    return name;
			};
	    </script>
	</head>

	<body>
		<div id="layout"></div>
	</body>
</html>
