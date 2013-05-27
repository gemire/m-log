$(document).ready(function() {
	$("#jaw-input").ctrlSubmit(function(event) {
		mlog.jaw.add();
	});
	mlog.jaw.load();
});

/**
 * @author GaoYoubo
 * @since 2012-09-25
 * form元素的操作和处理
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};
mlog.jaw = {};

var template = [
        '<#list data.jaw as jaw>',
        '<li link="http://my.oschina.net/wangxuanyihaha/tweet/2056406">',
		'	<a href="http://my.oschina.net/wangxuanyihaha" target="_blank" class="portrait">',
		'		<img src="http://www.gravatar.com/avatar/ab7a087849cdbae54bab1676cc07e374.jpg" align="absmiddle" alt="${jaw.author.alias?jaw.author.alias:jaw.author.name}" title="${jaw.author.alias?jaw.author.alias:jaw.author.name}">',
		'	</a>',
		'	<div class="jaw">',
		'		<p class="content"><a href="http://my.oschina.net/wangxuanyihaha" target="_blank">${jaw.author.alias?jaw.author.alias:jaw.author.name}</a> : ${jaw.content}</p>',
		'		<p class="meta">发布于${jaw.createTime}</p>',
		'	</div>',
		'</li>',
		'</#list>'
].join("");

$.extend(mlog.jaw,{
	//发布JAW
	add : function(){
		var content = $('#jaw-input').text();
		$('#content').val(content);
		$("#jaw-form").ajaxSubmit({
			success : function(response) {
				if (response.success === true) {
					var html = easyTemplate(template, response.data);
					$("#jaws").html(html + $("#jaws").html());
					
					//清空输入框中的值
					$('#jaw-input').text('');
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
			var html = easyTemplate(template, response.data);
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
	
});


/******************************************************************/
/******************************************************************/
/******************************************************************/
jQuery.fn.extend({
	ctrlSubmit : function(fn, thisObj) {
		var obj = thisObj || this;
		var stat = false;
		return this.each(function() {
			$(this).keyup(function(event) {
				if (event.keyCode == 17) {
					stat = true;
					setTimeout(function() {
						stat = false;
					}, 300);
				}
				if (event.keyCode == 13 && (stat || event.ctrlKey)) {
					fn.call(obj, event);
				}
			});
		});
	}
});