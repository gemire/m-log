/**
 * @author GaoYoubo
 * @since 2012-09-25
 * form元素的操作和处理
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};
mlog.jaw = {};

$.extend(mlog.jaw,{
	//发布JAW
	add : function(){
		$("#jaw-form").ajaxSubmit({
			success : function(response) {
				if (response.success === true) {
					var html = mlog.jaw.getListHTML(response.data.jaw);
					$("#jaws").html(html + $("#jaws").html());
					mlog.jaw.editor.setValue('content', '');
				} else {
					alert(response.message);
				}
			}
		});
	},
	
	
	//加载JAW
	load : function(page){
		mlog.jaw.showLoading(true);
		if(!page){
			page = 1;
		}
		$.get(mlog.variable.base + '/jaw/get?page=' + page, function(response) {
			var html = mlog.jaw.getListHTML(response.data.jaw);
			$("#jaws").html($("#jaws").html() + html);
			mlog.jaw.showLoading(false);
		});
	},
	
	//显示loading提示
	showLoading : function(flag){
		if(flag === true){
			$("#jaws").append('<div id="loadingjaws" style="text-align:center;"><img src="' + mlog.variable.template_url + '/images/loading.gif"/>正在加载...</div>');
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
			content = mlog.jaw.editor.showEmotion(content);
			
            html += '<li link="http://my.oschina.net/wangxuanyihaha/tweet/2056406">';
            html += '	<a href="http://my.oschina.net/wangxuanyihaha" target="_blank" class="portrait">';
            html += '		<img src="http://www.gravatar.com/avatar/ab7a087849cdbae54bab1676cc07e374.jpg" align="absmiddle" alt="' + author + '" title="' + author + '">';
            html += '	</a>';
            html += '	<div class="jaw">';
            html += '		<p class="content"><a href="http://my.oschina.net/wangxuanyihaha" target="_blank">' + author + '</a> : ' + content + '</p>';
            html += '		<p class="meta">发布于' + createTime + '</p>';
            html += '	</div>';
            html += '</li>';
		}
		return html;
	},
	
});