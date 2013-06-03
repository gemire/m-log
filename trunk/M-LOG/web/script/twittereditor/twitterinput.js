if(typeof(mlog) === "undefined"){var mlog = function(){}};
//if(typeof(mlog.twitter) === "undefined"){var mlog.twitter = function(){}};

var emotions = [{"id":"14", "phrase":"微笑", "url":"emotions/14.gif"},{"id":"1", "phrase":"撇嘴", "url":"emotions/1.gif"},{"id":"2", "phrase":"色", "url":"emotions/2.gif"},{"id":"3", "phrase":"发呆", "url":"emotions/3.gif"},{"id":"4", "phrase":"得意", "url":"emotions/4.gif"},{"id":"5", "phrase":"流泪", "url":"emotions/5.gif"},{"id":"6", "phrase":"害羞", "url":"emotions/6.gif"},{"id":"7", "phrase":"闭嘴", "url":"emotions/7.gif"},{"id":"8", "phrase":"睡", "url":"emotions/8.gif"},{"id":"9", "phrase":"大哭", "url":"emotions/9.gif"},{"id":"10", "phrase":"尴尬", "url":"emotions/10.gif"},{"id":"11", "phrase":"发怒", "url":"emotions/11.gif"},{"id":"12", "phrase":"调皮", "url":"emotions/12.gif"},{"id":"13", "phrase":"呲牙", "url":"emotions/13.gif"},{"id":"0", "phrase":"惊讶", "url":"emotions/0.gif"},{"id":"15", "phrase":"难过", "url":"emotions/15.gif"},{"id":"16", "phrase":"酷", "url":"emotions/16.gif"},{"id":"96", "phrase":"冷汗", "url":"emotions/96.gif"},{"id":"18", "phrase":"抓狂", "url":"emotions/18.gif"},{"id":"19", "phrase":"吐", "url":"emotions/19.gif"},{"id":"20", "phrase":"偷笑", "url":"emotions/20.gif"},{"id":"21", "phrase":"可爱", "url":"emotions/21.gif"},{"id":"22", "phrase":"白眼", "url":"emotions/22.gif"},{"id":"23", "phrase":"傲慢", "url":"emotions/23.gif"},{"id":"24", "phrase":"饥饿", "url":"emotions/24.gif"},{"id":"25", "phrase":"困", "url":"emotions/25.gif"},{"id":"26", "phrase":"惊恐", "url":"emotions/26.gif"},{"id":"27", "phrase":"流汗", "url":"emotions/27.gif"},{"id":"28", "phrase":"憨笑", "url":"emotions/28.gif"},{"id":"29", "phrase":"大兵", "url":"emotions/29.gif"},{"id":"30", "phrase":"奋斗", "url":"emotions/30.gif"},{"id":"31", "phrase":"咒骂", "url":"emotions/31.gif"},{"id":"32", "phrase":"疑问", "url":"emotions/32.gif"},{"id":"33", "phrase":"嘘", "url":"emotions/33.gif"},{"id":"34", "phrase":"晕", "url":"emotions/34.gif"},{"id":"35", "phrase":"折磨", "url":"emotions/35.gif"},{"id":"36", "phrase":"衰", "url":"emotions/36.gif"},{"id":"37", "phrase":"骷髅", "url":"emotions/37.gif"},{"id":"38", "phrase":"敲打", "url":"emotions/38.gif"},{"id":"39", "phrase":"再见", "url":"emotions/39.gif"},{"id":"97", "phrase":"擦汗", "url":"emotions/97.gif"},{"id":"98", "phrase":"抠鼻", "url":"emotions/98.gif"},{"id":"99", "phrase":"鼓掌", "url":"emotions/99.gif"},{"id":"100", "phrase":"糗大了", "url":"emotions/100.gif"},{"id":"101", "phrase":"坏笑", "url":"emotions/101.gif"},{"id":"102", "phrase":"左哼哼", "url":"emotions/102.gif"},{"id":"103", "phrase":"右哼哼", "url":"emotions/103.gif"},{"id":"104", "phrase":"哈欠", "url":"emotions/104.gif"},{"id":"105", "phrase":"鄙视", "url":"emotions/105.gif"},{"id":"106", "phrase":"委屈", "url":"emotions/106.gif"},{"id":"107", "phrase":"快哭了", "url":"emotions/107.gif"},{"id":"108", "phrase":"阴险", "url":"emotions/108.gif"},{"id":"109", "phrase":"亲亲", "url":"emotions/109.gif"},{"id":"110", "phrase":"吓", "url":"emotions/110.gif"},{"id":"111", "phrase":"可怜", "url":"emotions/111.gif"},{"id":"112", "phrase":"菜刀", "url":"emotions/112.gif"},{"id":"89", "phrase":"西瓜", "url":"emotions/89.gif"},{"id":"113", "phrase":"啤酒", "url":"emotions/113.gif"},{"id":"114", "phrase":"篮球", "url":"emotions/114.gif"},{"id":"115", "phrase":"乒乓", "url":"emotions/115.gif"},{"id":"60", "phrase":"咖啡", "url":"emotions/60.gif"},{"id":"61", "phrase":"饭", "url":"emotions/61.gif"},{"id":"46", "phrase":"猪头", "url":"emotions/46.gif"},{"id":"63", "phrase":"玫瑰", "url":"emotions/63.gif"},{"id":"64", "phrase":"凋谢", "url":"emotions/64.gif"},{"id":"116", "phrase":"示爱", "url":"emotions/116.gif"},{"id":"66", "phrase":"爱心", "url":"emotions/66.gif"},{"id":"67", "phrase":"心碎", "url":"emotions/67.gif"},{"id":"53", "phrase":"蛋糕", "url":"emotions/53.gif"},{"id":"54", "phrase":"闪电", "url":"emotions/54.gif"},{"id":"55", "phrase":"炸弹", "url":"emotions/55.gif"},{"id":"56", "phrase":"刀", "url":"emotions/56.gif"},{"id":"57", "phrase":"足球", "url":"emotions/57.gif"},{"id":"117", "phrase":"瓢虫", "url":"emotions/117.gif"},{"id":"59", "phrase":"便便", "url":"emotions/59.gif"},{"id":"75", "phrase":"月亮", "url":"emotions/75.gif"},{"id":"74", "phrase":"太阳", "url":"emotions/74.gif"},{"id":"69", "phrase":"礼物", "url":"emotions/69.gif"},{"id":"49", "phrase":"拥抱", "url":"emotions/49.gif"},{"id":"76", "phrase":"强", "url":"emotions/76.gif"},{"id":"77", "phrase":"弱", "url":"emotions/77.gif"},{"id":"78", "phrase":"握手", "url":"emotions/78.gif"},{"id":"79", "phrase":"胜利", "url":"emotions/79.gif"},{"id":"118", "phrase":"抱拳", "url":"emotions/118.gif"},{"id":"119", "phrase":"勾引", "url":"emotions/119.gif"},{"id":"120", "phrase":"拳头", "url":"emotions/120.gif"},{"id":"121", "phrase":"差劲", "url":"emotions/121.gif"},{"id":"122", "phrase":"爱你", "url":"emotions/122.gif"},{"id":"123", "phrase":"NO", "url":"emotions/123.gif"},{"id":"124", "phrase":"OK", "url":"emotions/124.gif"},{"id":"42", "phrase":"爱情", "url":"emotions/42.gif"},{"id":"85", "phrase":"飞吻", "url":"emotions/85.gif"},{"id":"43", "phrase":"跳跳", "url":"emotions/43.gif"},{"id":"41", "phrase":"发抖", "url":"emotions/41.gif"},{"id":"86", "phrase":"怄火", "url":"emotions/86.gif"},{"id":"125", "phrase":"转圈", "url":"emotions/125.gif"},{"id":"126", "phrase":"磕头", "url":"emotions/126.gif"},{"id":"127", "phrase":"回头", "url":"emotions/127.gif"},{"id":"128", "phrase":"跳绳", "url":"emotions/128.gif"},{"id":"129", "phrase":"挥手", "url":"emotions/129.gif"},{"id":"130", "phrase":"激动", "url":"emotions/130.gif"},{"id":"131", "phrase":"街舞", "url":"emotions/131.gif"},{"id":"132", "phrase":"献吻", "url":"emotions/132.gif"},{"id":"133", "phrase":"左太极", "url":"emotions/133.gif"},{"id":"134", "phrase":"右太极", "url":"emotions/134.gif"}];

mlog.twitter.editor = {};
//上传的图片
mlog.twitter.editor.uploadImage = null;
$.extend(mlog.twitter.editor,{
	/**
	 * @param conf.target
	 * 
	 */
	init : function(conf){
		var defaults = {
				
		};
		
		var conf = $.extend(defaults, conf);
		
		if(!conf || !conf.target){
			return;
		}
		
		var id = $(conf.target).attr('id');
		var name = $(conf.target).attr('name');
		var layer_div = mlog.twitter.editor._get_layer_div_id(conf);
		
		var em_images = '';
		for(var i = 0; i < emotions.length; i++){
			em_images += '<a href="javascript:;" class="emotion_img" title="' + emotions[i].phrase + '"><img src="' + mlog.twitter.editor.base + emotions[i].url + '" alt="' + emotions[i].phrase + '"/></a>' + '\n';
		}
		
		var template = [
			'<div class="twitter-input-container">',
			'	<textarea id="' + id + '" name="' + name + '" class="twitter-input"></textarea>',
			'</div>',
			
			
			'<div class="bottom-tools">',
			'	<div class="left">',
			'		<div class="insert-buttons">',
			'			<span>插入</span>：',
			'			<a id="btn_show_em_' + id + '" class="insert_em">表情</a>',
			'			<a id="btn_show_img_' + id + '" class="insert_img">图片</a>',
			'		</div>',
			'	</div>',
			'	<div class="right" style="padding-right:15px;">',
			'		<input type="button" class="twitter-btn" onclick="mlog.twitter.add();" value="发表" id="btn-posttwitter" />',
			'	</div>',
			'</div>',
			
			'<div class="layer" id="' + layer_div + '">',
		    '	<div id="layer-popup-arrow" class="layer-popup-arrow em">',
		    
		    '		<ul>',
		    
		    '			<li id="em">',
		    '				<div class="layer-popup">',
			'					<div id="twitter-emotions">',
	        '       				<div class="layer-popup-title">',
	        '      						<a id="btn_close_em_' + id + '">关闭</a>插入表情',
	        '	        			</div>',
			'		        	</div>',
			em_images,
			'					<div class="twitter-clearfix"></div>',
			'				</div>',
			'			</li>',
			
			'			<li id="img">',
			'				<div class="layer-popup">',
			'					<div id="twitter-images">',
	        '       				<div class="layer-popup-title">',
	        '      						<a id="btn_close_img_' + id + '">关闭</a>插入图片',
	        '        				</div>',
			'		        	</div>',
			'					<form id="twitter-image-upload" action="' + mlog.variable.base + '/t/insert_image" method="POST" enctype="multipart/form-data" style="display: block;">',		
			'						<div class="l" id="t_image_upload">',
			'							本地图片：<input id="twitter-image-input" type="file" name="image" size="30">',
			'						</div>',
			'						<div class="l tip">仅支持JPG,GIF,PNG,BMP图片文件，且文件小于512K</div>',
			'						<div class="l submit">',
			'							<input type="button" onclick="mlog.twitter.editor.insertImage(this);" value="插入图片" class="twitter-btn" style="height:24px;line-height:24px;padding:0 3px;">',
			'							<span id="ajax_processing">正在插入图片，请稍候...</span>',
			'						</div>',
			'					</form>',
			'					<div id="twitter-image-view" style="display:none;">',
			'						<p>',
			'							<span id="twitter-image-name"></span>',
			'							<a href="javascript:void(0);" id="twitter-image-delete">删除</a>',
			'						</p>',
			'						<img id="twitter-image" src="" style="max-width:100px;max-height:200px;display:block;margin:10px;border:1px solid #40AA53;">',
			'					</div>',
			'					<div class="twitter-clearfix"></div>',
			'				</div>',
			'			</li>',
			
			'		</ul>',
			
			'	</div>',
			'</div>',
			
        ].join('');
		
		$(conf.target).replaceWith(template);
		
		//绑定事件
		//显示/关闭 表情输入框
		$('#btn_show_em_' + id).click(function(){
			mlog.twitter.editor.showLayer(id, 'em');
		});
		$('#btn_close_em_' + id).click(function(){
			mlog.twitter.editor.closeLayer(id);
		});
		//显示/关闭 图片输入框
		$('#btn_show_img_' + id).click(function(){
			mlog.twitter.editor.showLayer(id, 'img');
		});
		$('#btn_close_img_' + id).click(function(){
			mlog.twitter.editor.closeLayer(id);
		});
		
		//插入表情
		$('.emotion_img').click(function(){
			mlog.twitter.editor.insertEmotion(id, $(this).attr('title'));
			mlog.twitter.editor.closeEmInsert(id);
		});
	},
	
	/**
	 * @param id
	 * 获取内容
	 */
	getValue : function(id){
		if(!id){
			return;
		}
		return $("#" + id).val();
	},
	
	/**
	 * @param id
	 * @param value
	 * 设置内容
	 */
	setValue : function(id, value){
		if(!id){
			return;
		}
		return $("#" + id).val(value);
	},
	
	/**
	 * 锁定按钮，防止重复发布
	 * @param flag true:锁定  false：解锁
	 * @param btnValue 按钮显示的文字
	 */
	lockButton : function(flag, btnValue){
		if(flag === 'undefined'){
			flag = true;
		}
		if(flag === true){
			$('#btn-posttwitter').attr('disabled', 'disabled');
		}
		else {
			$('#btn-posttwitter').removeAttr('disabled');
		}
		
		if(btnValue){
			$('#btn-posttwitter').val(btnValue);
		}
	},
	
	showLayer : function(id, li){
		if(!id || !li){
			return;
		}
		var layer = mlog.twitter.editor._get_layer_div_id({
			target : $('#' + id)
		});
		var selector = '#' + layer + ' ul li';
		$(selector).each(function(){
			if($(this).attr('id') === li){
				$(this).css('display', 'block');
			}
			else {
				$(this).css('display', 'none');
			}
		});
		$('#layer-popup-arrow').attr('class', 'layer-popup-arrow ' + li);
		$('#' + layer).css('display', 'block');
	},
	
	/**
	 * 关闭layer
	 */
	closeLayer : function(id) {
		if(!id){
			return;
		}
		var layer = mlog.twitter.editor._get_layer_div_id({
			target : $('#' + id)
		});
		var selector = '#' + layer + ' ul li';
		$(selector).each(function(){
			$(this).css('display', 'none');
		});
		$('#' + layer).css('display', 'none');
	},
	
	/**
	 * @param inputId 输入框ID
	 * @param phrase 表情别名
	 * 
	 * 插入表情
	 */
	insertEmotion : function(inputId, phrase){
		mlog.twitter.editor.insertContent(inputId, '[' + phrase + ']');
	},
	
	/**
	 * 插入图片
	 */
	insertImage : function(btn){
		if(!$('#twitter-image-input').val()){
			alert('请先选择要上传的图片');
			return;
		}
		if (mlog.variable.user === undefined) {
			alert('请先登录');
			return;
		}
		$(btn).attr('disabled', 'disabled');
		$('#ajax_processing').css('display', 'block');
		$('#twitter-image-upload').ajaxSubmit({
			success: function(response){
				if(response.success === true){
					mlog.twitter.editor.uploadImage = response.data.attachment.id;
					
					$("#twitter-image").attr("src", mlog.variable.base + response.data.attachment.path);
					$("#twitter-image-name").text(response.data.attachment.path.substr(response.data.attachment.path.lastIndexOf('/') + 1));
					$("#twitter-image-view").css('display', 'block');
					$("#twitter-image-upload").css('display', 'none');
					
					//绑定删除事件
					$("#twitter-image-delete").bind("click", function(){
						mlog.twitter.editor.deleteImage();
					});
				}
				else {
					alert(response.message);
				}
				$(btn).removeAttr('disabled');
				$('#ajax_processing').css('display', 'none');
			}
		});
	},
	
	/**
	 * 删除Image
	 */
	deleteImage : function(){
		if(mlog.twitter.editor.uploadImage){
			if (mlog.variable.user === undefined) {
				alert('请先登录');
				return;
			}
			$.get(mlog.variable.base + '/t/delete_image?id=' + mlog.twitter.editor.uploadImage, function(response){
				if(response.success == true){
					mlog.twitter.editor.reviewImageUpload();
				}
				else {
					alert(response.message);
				}
			});
		}
	},
	
	/**
	 * 重置图片上传
	 */
	reviewImageUpload : function(){
		$("#twitter-image-view").css('display', 'none');
		$("#twitter-image-upload").css('display', 'block');
		
		//清空系统记录的已上传照片的ID
		mlog.twitter.editor.uploadImage = null;
	},
	
	/**
	 * @param s 内容
	 * 解析表情
	 * 
	 */
	showEmotion : function(s){
		if(typeof (s) != "undefined") {
			var sArr = s.match(/\[.*?\]/g);
			if(sArr){
				for(var i = 0; i < sArr.length; i++){
					for(var j = 0; j < emotions.length; j++){
						if('[' + emotions[j].phrase + ']' === sArr[i]){
							var reStr = "<img src=\"" + mlog.twitter.editor.base + emotions[j].url + "\" height=\"22\" width=\"22\" />";
							s = s.replace(sArr[i], reStr);
						}
					}
				}
			}
		}
		return s;
	},
	
	/**
	 * @param inputId 输入框ID
	 * @param content 内容
	 * 插入内容
	 */
	insertContent : function(inputId, content){
		if(!inputId || !content){return;}
		var inputObj = document.getElementById(inputId);
		if(!inputObj){return;}
		
		//IE support
	    if (document.selection) {
	        inputObj.focus();
	        sel = document.selection.createRange();
	        sel.text = content;
	        sel.select();
	    }
	    //MOZILLA/NETSCAPE support
	    else if (inputObj.selectionStart || inputObj.selectionStart == '0') {
	    	var startPos = inputObj.selectionStart;
	        var endPos = inputObj.selectionEnd;
	        // save scrollTop before insert
	        var restoreTop = inputObj.scrollTop;
	        inputObj.value = inputObj.value.substring(0, startPos) + content + inputObj.value.substring(endPos, inputObj.value.length);
	        if (restoreTop > 0) {
	        	inputObj.scrollTop = restoreTop;
	        }
	        inputObj.focus();
	        inputObj.selectionStart = startPos + content.length;
	        inputObj.selectionEnd = startPos + content.length;
		} else {
			inputObj.value += content;
			inputObj.focus();
		}
	},
	
	
	
	/******************************************************************************/
	/******************************************************************************/
	/******************************************************************************/
	/**
	 * @param conf.target
	 * 获取弹出框ID
	 */
	_get_layer_div_id : function(conf){
		if(!conf || !conf.target){
			return;
		}
		var id = $(conf.target).attr('id');
		return id + '_layer';
	},
	
	/**
	 * 获取twitterinput对应的目录
	 */
	_getBasePath : function() {
		var els = document.getElementsByTagName('script'), src;
		for (var i = 0, len = els.length; i < len; i++) {
			src = els[i].src || '';
			if (/twitterinput[\w\-\.]*\.js/.test(src)) {
				return src.substring(0, src.lastIndexOf('/') + 1);
			}
		}
		return '';
	},
});

mlog.twitter.editor.base = mlog.twitter.editor._getBasePath();