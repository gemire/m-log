if(typeof(mlog) === "undefined"){var mlog = function(){}};
//if(typeof(mlog.jaw) === "undefined"){var mlog.jaw = function(){}};

//var emotions = [{"phrase":"[草泥马]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7a/shenshou_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7a/shenshou_thumb.gif","value":"[草泥马]","picid":""},{"phrase":"[神马]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/60/horse2_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/60/horse2_thumb.gif","value":"[神马]","picid":""},{"phrase":"[浮云]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/fuyun_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/fuyun_thumb.gif","value":"[浮云]","picid":""},{"phrase":"[给力]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c9/geili_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c9/geili_thumb.gif","value":"[给力]","picid":""},{"phrase":"[围观]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f2/wg_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f2/wg_thumb.gif","value":"[围观]","picid":""},{"phrase":"[威武]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/vw_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/70/vw_thumb.gif","value":"[威武]","picid":""},{"phrase":"[熊猫]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/panda_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/panda_thumb.gif","value":"[熊猫]","picid":""},{"phrase":"[兔子]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/81/rabbit_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/81/rabbit_thumb.gif","value":"[兔子]","picid":""},{"phrase":"[奥特曼]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/otm_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/bc/otm_thumb.gif","value":"[奥特曼]","picid":""},{"phrase":"[囧]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/15/j_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/15/j_thumb.gif","value":"[囧]","picid":""},{"phrase":"[互粉]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/89/hufen_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/89/hufen_thumb.gif","value":"[互粉]","picid":""},{"phrase":"[礼物]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c4/liwu_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c4/liwu_thumb.gif","value":"[礼物]","picid":""},{"phrase":"[呵呵]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ac/smilea_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ac/smilea_thumb.gif","value":"[呵呵]","picid":""},{"phrase":"[嘻嘻]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0b/tootha_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0b/tootha_thumb.gif","value":"[嘻嘻]","picid":""},{"phrase":"[哈哈]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/laugh.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/laugh.gif","value":"[哈哈]","picid":""},{"phrase":"[可爱]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/14/tza_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/14/tza_thumb.gif","value":"[可爱]","picid":""},{"phrase":"[可怜]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/kl_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/kl_thumb.gif","value":"[可怜]","picid":""},{"phrase":"[挖鼻屎]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a0/kbsa_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a0/kbsa_thumb.gif","value":"[挖鼻屎]","picid":""},{"phrase":"[吃惊]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f4/cj_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f4/cj_thumb.gif","value":"[吃惊]","picid":""},{"phrase":"[害羞]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/shamea_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/shamea_thumb.gif","value":"[害羞]","picid":""},{"phrase":"[挤眼]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c3/zy_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c3/zy_thumb.gif","value":"[挤眼]","picid":""},{"phrase":"[闭嘴]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/29/bz_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/29/bz_thumb.gif","value":"[闭嘴]","picid":""},{"phrase":"[鄙视]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/71/bs2_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/71/bs2_thumb.gif","value":"[鄙视]","picid":""},{"phrase":"[爱你]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/lovea_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/lovea_thumb.gif","value":"[爱你]","picid":""},{"phrase":"[泪]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9d/sada_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9d/sada_thumb.gif","value":"[泪]","picid":""},{"phrase":"[偷笑]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/19/heia_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/19/heia_thumb.gif","value":"[偷笑]","picid":""},{"phrase":"[亲亲]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/8f/qq_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/8f/qq_thumb.gif","value":"[亲亲]","picid":""},{"phrase":"[生病]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b6/sb_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b6/sb_thumb.gif","value":"[生病]","picid":""},{"phrase":"[太开心]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/58/mb_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/58/mb_thumb.gif","value":"[太开心]","picid":""},{"phrase":"[懒得理你]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/17/ldln_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/17/ldln_thumb.gif","value":"[懒得理你]","picid":""},{"phrase":"[右哼哼]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/98/yhh_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/98/yhh_thumb.gif","value":"[右哼哼]","picid":""},{"phrase":"[左哼哼]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/zhh_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6d/zhh_thumb.gif","value":"[左哼哼]","picid":""},{"phrase":"[嘘]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a6/x_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a6/x_thumb.gif","value":"[嘘]","picid":""},{"phrase":"[衰]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/cry.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/cry.gif","value":"[衰]","picid":""},{"phrase":"[委屈]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/73/wq_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/73/wq_thumb.gif","value":"[委屈]","picid":""}];
var emotions = [{"id":"14", "phrase":"微笑", "url":"emotions/14.gif"},{"id":"1", "phrase":"撇嘴", "url":"emotions/1.gif"},{"id":"2", "phrase":"色", "url":"emotions/2.gif"},{"id":"3", "phrase":"发呆", "url":"emotions/3.gif"},{"id":"4", "phrase":"得意", "url":"emotions/4.gif"},{"id":"5", "phrase":"流泪", "url":"emotions/5.gif"},{"id":"6", "phrase":"害羞", "url":"emotions/6.gif"},{"id":"7", "phrase":"闭嘴", "url":"emotions/7.gif"},{"id":"8", "phrase":"睡", "url":"emotions/8.gif"},{"id":"9", "phrase":"大哭", "url":"emotions/9.gif"},{"id":"10", "phrase":"尴尬", "url":"emotions/10.gif"},{"id":"11", "phrase":"发怒", "url":"emotions/11.gif"},{"id":"12", "phrase":"调皮", "url":"emotions/12.gif"},{"id":"13", "phrase":"呲牙", "url":"emotions/13.gif"},{"id":"0", "phrase":"惊讶", "url":"emotions/0.gif"},{"id":"15", "phrase":"难过", "url":"emotions/15.gif"},{"id":"16", "phrase":"酷", "url":"emotions/16.gif"},{"id":"96", "phrase":"冷汗", "url":"emotions/96.gif"},{"id":"18", "phrase":"抓狂", "url":"emotions/18.gif"},{"id":"19", "phrase":"吐", "url":"emotions/19.gif"},{"id":"20", "phrase":"偷笑", "url":"emotions/20.gif"},{"id":"21", "phrase":"可爱", "url":"emotions/21.gif"},{"id":"22", "phrase":"白眼", "url":"emotions/22.gif"},{"id":"23", "phrase":"傲慢", "url":"emotions/23.gif"},{"id":"24", "phrase":"饥饿", "url":"emotions/24.gif"},{"id":"25", "phrase":"困", "url":"emotions/25.gif"},{"id":"26", "phrase":"惊恐", "url":"emotions/26.gif"},{"id":"27", "phrase":"流汗", "url":"emotions/27.gif"},{"id":"28", "phrase":"憨笑", "url":"emotions/28.gif"},{"id":"29", "phrase":"大兵", "url":"emotions/29.gif"},{"id":"30", "phrase":"奋斗", "url":"emotions/30.gif"},{"id":"31", "phrase":"咒骂", "url":"emotions/31.gif"},{"id":"32", "phrase":"疑问", "url":"emotions/32.gif"},{"id":"33", "phrase":"嘘", "url":"emotions/33.gif"},{"id":"34", "phrase":"晕", "url":"emotions/34.gif"},{"id":"35", "phrase":"折磨", "url":"emotions/35.gif"},{"id":"36", "phrase":"衰", "url":"emotions/36.gif"},{"id":"37", "phrase":"骷髅", "url":"emotions/37.gif"},{"id":"38", "phrase":"敲打", "url":"emotions/38.gif"},{"id":"39", "phrase":"再见", "url":"emotions/39.gif"},{"id":"97", "phrase":"擦汗", "url":"emotions/97.gif"},{"id":"98", "phrase":"抠鼻", "url":"emotions/98.gif"},{"id":"99", "phrase":"鼓掌", "url":"emotions/99.gif"},{"id":"100", "phrase":"糗大了", "url":"emotions/100.gif"},{"id":"101", "phrase":"坏笑", "url":"emotions/101.gif"},{"id":"102", "phrase":"左哼哼", "url":"emotions/102.gif"},{"id":"103", "phrase":"右哼哼", "url":"emotions/103.gif"},{"id":"104", "phrase":"哈欠", "url":"emotions/104.gif"},{"id":"105", "phrase":"鄙视", "url":"emotions/105.gif"},{"id":"106", "phrase":"委屈", "url":"emotions/106.gif"},{"id":"107", "phrase":"快哭了", "url":"emotions/107.gif"},{"id":"108", "phrase":"阴险", "url":"emotions/108.gif"},{"id":"109", "phrase":"亲亲", "url":"emotions/109.gif"},{"id":"110", "phrase":"吓", "url":"emotions/110.gif"},{"id":"111", "phrase":"可怜", "url":"emotions/111.gif"},{"id":"112", "phrase":"菜刀", "url":"emotions/112.gif"},{"id":"89", "phrase":"西瓜", "url":"emotions/89.gif"},{"id":"113", "phrase":"啤酒", "url":"emotions/113.gif"},{"id":"114", "phrase":"篮球", "url":"emotions/114.gif"},{"id":"115", "phrase":"乒乓", "url":"emotions/115.gif"},{"id":"60", "phrase":"咖啡", "url":"emotions/60.gif"},{"id":"61", "phrase":"饭", "url":"emotions/61.gif"},{"id":"46", "phrase":"猪头", "url":"emotions/46.gif"},{"id":"63", "phrase":"玫瑰", "url":"emotions/63.gif"},{"id":"64", "phrase":"凋谢", "url":"emotions/64.gif"},{"id":"116", "phrase":"示爱", "url":"emotions/116.gif"},{"id":"66", "phrase":"爱心", "url":"emotions/66.gif"},{"id":"67", "phrase":"心碎", "url":"emotions/67.gif"},{"id":"53", "phrase":"蛋糕", "url":"emotions/53.gif"},{"id":"54", "phrase":"闪电", "url":"emotions/54.gif"},{"id":"55", "phrase":"炸弹", "url":"emotions/55.gif"},{"id":"56", "phrase":"刀", "url":"emotions/56.gif"},{"id":"57", "phrase":"足球", "url":"emotions/57.gif"},{"id":"117", "phrase":"瓢虫", "url":"emotions/117.gif"},{"id":"59", "phrase":"便便", "url":"emotions/59.gif"},{"id":"75", "phrase":"月亮", "url":"emotions/75.gif"},{"id":"74", "phrase":"太阳", "url":"emotions/74.gif"},{"id":"69", "phrase":"礼物", "url":"emotions/69.gif"},{"id":"49", "phrase":"拥抱", "url":"emotions/49.gif"},{"id":"76", "phrase":"强", "url":"emotions/76.gif"},{"id":"77", "phrase":"弱", "url":"emotions/77.gif"},{"id":"78", "phrase":"握手", "url":"emotions/78.gif"},{"id":"79", "phrase":"胜利", "url":"emotions/79.gif"},{"id":"118", "phrase":"抱拳", "url":"emotions/118.gif"},{"id":"119", "phrase":"勾引", "url":"emotions/119.gif"},{"id":"120", "phrase":"拳头", "url":"emotions/120.gif"},{"id":"121", "phrase":"差劲", "url":"emotions/121.gif"},{"id":"122", "phrase":"爱你", "url":"emotions/122.gif"},{"id":"123", "phrase":"NO", "url":"emotions/123.gif"},{"id":"124", "phrase":"OK", "url":"emotions/124.gif"},{"id":"42", "phrase":"爱情", "url":"emotions/42.gif"},{"id":"85", "phrase":"飞吻", "url":"emotions/85.gif"},{"id":"43", "phrase":"跳跳", "url":"emotions/43.gif"},{"id":"41", "phrase":"发抖", "url":"emotions/41.gif"},{"id":"86", "phrase":"怄火", "url":"emotions/86.gif"},{"id":"125", "phrase":"转圈", "url":"emotions/125.gif"},{"id":"126", "phrase":"磕头", "url":"emotions/126.gif"},{"id":"127", "phrase":"回头", "url":"emotions/127.gif"},{"id":"128", "phrase":"跳绳", "url":"emotions/128.gif"},{"id":"129", "phrase":"挥手", "url":"emotions/129.gif"},{"id":"130", "phrase":"激动", "url":"emotions/130.gif"},{"id":"131", "phrase":"街舞", "url":"emotions/131.gif"},{"id":"132", "phrase":"献吻", "url":"emotions/132.gif"},{"id":"133", "phrase":"左太极", "url":"emotions/133.gif"},{"id":"134", "phrase":"右太极", "url":"emotions/134.gif"}];

mlog.jaw.editor = {};
$.extend(mlog.jaw.editor,{
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
		var div_em_insert = mlog.jaw.editor._get_em_div_id(conf);
		
		var em_images = '';
		for(var i = 0; i < emotions.length; i++){
			em_images += '<a href="javascript:;" class="emotion_img" title="' + emotions[i].phrase + '"><img src="' + mlog.jaw.editor.base + emotions[i].url + '" alt="' + emotions[i].phrase + '"/></a>' + '\n';
		}
		
		var template = [
			'<div class="jaw-input-container">',
			'	<textarea id="' + id + '" name="' + name + '" class="jaw-input"></textarea>',
			'</div>',
			
			
			'<div class="bottom-tools">',
			'	<div class="left">',
			'		<div class="insert-buttons">',
			'			<span>插入</span>：<a id="btn_show_em_' + id + '" class="insert_em">表情</a>',
			'		</div>',
			'	</div>',
			'	<div class="right" style="padding-right:15px; padding-top:10px;">',
			'		<input type="button" class="btn" onclick="mlog.jaw.add();" value="发表" id="btn-postjaw" />',
			'	</div>',
			'</div>',
			
			'<div class="layer" id="' + div_em_insert + '">',
		    '	<div class="layer-popup-arrow em">',
		    '		<div class="layer-popup">',
			'			<div id="emotions">',
	        '       		<div class="layer-popup-title">',
	        '      				<a id="btn_close_em_' + id + '">关闭</a>插入表情',
	        '        		</div>',
			'	        </div>',
			
			em_images,
			
			'			<div class="clearfix"></div>',
			'		</div>',
			'	</div>',
			'</div>',
			
        ].join('');
		
		$(conf.target).replaceWith(template);
		
		//绑定事件
		$('#btn_show_em_' + id).click(function(){
			mlog.jaw.editor.showEmInsert(id);
		});
		$('#btn_close_em_' + id).click(function(){
			mlog.jaw.editor.closeEmInsert(id);
		});
		$('#' + div_em_insert).blur(function(){
			mlog.jaw.editor.closeEmInsert(id);
		});
		$('.emotion_img').click(function(){
			mlog.jaw.editor.insertEmotion(id, $(this).attr('title'));
			mlog.jaw.editor.closeEmInsert(id);
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
	 * @param id textarea框ID
	 * 显示表情输入框
	 * 
	 */
	showEmInsert : function(id){
		if(!id){
			return;
		}
		var id = mlog.jaw.editor._get_em_div_id({
			target : $('#' + id)
		});
		$('#'+id).css('display', 'block');
	},
	
	/**
	 * @param id textarea框ID
	 * 关闭表情输入框
	 */
	closeEmInsert : function(id){
		if(!id){
			return;
		}
		var id = mlog.jaw.editor._get_em_div_id({
			target : $('#' + id)
		});
		$('#'+id).css('display', 'none');
	},
	
	/**
	 * @param inputId 输入框ID
	 * @param phrase 表情别名
	 * 
	 * 插入表情
	 */
	insertEmotion : function(inputId, phrase){
		mlog.jaw.editor.insertContent(inputId, '[' + phrase + ']');
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
							var reStr = "<img src=\"" + mlog.jaw.editor.base + emotions[j].url + "\" height=\"22\" width=\"22\" />";
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
	 * 获取表情弹出框ID
	 */
	_get_em_div_id : function(conf){
		if(!conf || !conf.target){
			return;
		}
		var id = $(conf.target).attr('id');
		return id + '_em_insert';
	},
	
	/**
	 * 获取jawinput对应的目录
	 */
	_getBasePath : function() {
		var els = document.getElementsByTagName('script'), src;
		for (var i = 0, len = els.length; i < len; i++) {
			src = els[i].src || '';
			if (/jawinput[\w\-\.]*\.js/.test(src)) {
				return src.substring(0, src.lastIndexOf('/') + 1);
			}
		}
		return '';
	},
});

mlog.jaw.editor.base = mlog.jaw.editor._getBasePath();