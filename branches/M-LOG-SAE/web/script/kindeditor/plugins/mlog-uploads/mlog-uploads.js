var editor;
KindEditor.plugin('mlog-uploads', function(k){
	editor = this, name = 'mlog-uploads';
	
	editor.clickToolbar(name, function() {
		var api = mlog.dialog.showDialog({
			title : '图片上传',
			content : 'url:/admin/attachment/dialog',
			width : '600px',
			height : '420px',
			button : [{
				name : '上传',
				callback : function(){
					this.content.upload()
					return false;
				}
			},{
				name : '暂停',
				callback : function(){
					this.content.stop()
					return false;
				}
			},{
				name : '清空',
				callback : function(){
					this.content.clearQueue();
					return false;
				}
			},{
				name : '全部插入',
				callback : function(){
					insertAllImage();
				}
			}]
		});
	});
});

var images = [];

function addImage(image){
	if(!imageContains(image)){
		images[images.length] = image;
	}
}

//图片是否已经存在于images列表
function imageContains(image){
	for(var i = 0; i < images.length; i++){
		if(images[i].id === image.id){
			return true;
		}
	}
	return false;
}

//向编辑器插入置顶图片
function insertImage(id){
	for(var i = 0; i < images.length; i++){
		if(images[i].id === id){
			var imageHtml = '<img src="' + images[i].path + '" id="attachment_' + images[i].id + '"/>';
			editor.insertHtml(imageHtml);
		}
	}
}

//将所有图片都插入到编辑器
function insertAllImage(){
	for(var i = 0; i < images.length; i++){
		var imageHtml = '<img src="' + images[i].path + '" id="attachment_' + images[i].id + '"/>';
		editor.insertHtml(imageHtml);
	}
}