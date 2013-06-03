/**
 * @author GaoYoubo
 * @since 2012-09-25
 * form元素的操作和处理
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};
mlog.twitter = {};

mlog.twitter.page = 1; //当前页
mlog.twitter.loadfinished = false; //判断是否全部加载完成
$.extend(mlog.twitter,{
	//发布JAW
	add : function(){
		if (mlog.variable.user === undefined) {
			alert('请先登录');
			return;
		}
		mlog.twitter.editor.lockButton(true, '发布中...');
		$("#twitter-form").ajaxSubmit({
			beforeSubmit : function(formData){
				if(mlog.twitter.editor.uploadImage){
					formData[formData.length] = {'name' : 'attachment', 'value' : mlog.twitter.editor.uploadImage };
				}
				return true;
			},
			success : function(response) {
				if (response.success === true) {
					var html = mlog.twitter.getListHTML(response.data.twitter);
					$("#twitters").html(html + $("#twitters").html());
					
					//重置输入框
					mlog.twitter.editor.setValue('content', '');
					//重置图片上传
					mlog.twitter.editor.reviewImageUpload();
				} else {
					alert(response.message);
				}
			}
		});
		mlog.twitter.editor.lockButton(false, '发布');
	},
	
	
	//加载JAW
	load : function(page){
		if(mlog.twitter.loadfinished){ //如果已经全部加载完成，那么不加载
			return;
		}
		if(page){
			mlog.twitter.page = page;
		}
		mlog.twitter.showLoading(true);
		$.get(mlog.variable.base + '/t/get?page=' + mlog.twitter.page, function(response) {
			if(response.success == true && response.data && response.data.twitter.length > 0){
				var html = mlog.twitter.getListHTML(response.data.twitter);
				$("#twitters").html($("#twitters").html() + html);
			}
			else {
				mlog.twitter.loadfinished = true;
			}
			mlog.twitter.showLoading(false);
		});
	},
	
	//显示loading提示
	showLoading : function(flag){
		if(flag === true){
			$("#twitters").append('<div id="loadingjaws" style="text-align:center;"><img src="' + mlog.variable.template_url + '/images/loading.gif"/>正在加载...</div>');
		}
		else {
			$("#loadingjaws").remove();
		}
	},
	
	getListHTML : function(data){
		var html = '';
		for(var i = 0; i < data.length; i++){
			var author = data[i].author.alias ? data[i].author.alias : data[i].author.name;
			var createTime = data[i].createTime;
			var content = data[i].content;
			content = mlog.twitter.editor.showEmotion(content);
			
            html += '<li>';
            html += '	<a href="javascript:void(0);" class="portrait">';
            html += '		<img src="http://www.gravatar.com/avatar/ab7a087849cdbae54bab1676cc07e374.jpg" align="absmiddle" alt="' + author + '" title="' + author + '">';
            html += '	</a>';
            html += '	<div class="twitter">';
            html += '		<p class="content"><a href="javascript:void(0);">' + author + '</a> : ' + content + '</p>';
            if(data[i].image){
        	html += '		<p class="content"><a href="' + mlog.variable.blogurl + '/' + data[i].image.path + '" target="_blank"></a><img src="' + mlog.variable.blogurl + '/' +data[i].image.path + '" id="attachment_' + data[i].image.id + '" style="max-width:100px; max-height:200px;"/></p>';
			}
            html += '		<p class="meta">发布于' + createTime + '</p>';
            html += '	</div>';
            html += '</li>';
		}
		return html;
	},
	
});