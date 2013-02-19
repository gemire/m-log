/**
 * @author GaoYoubo
 * @since 2012-09-25
 * form元素的操作和处理
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};
mlog.dialog = {};
$.extend(mlog.dialog, {
	/**
	 * tip
	 * @param {} setting.msg 
	 * @param {} setting.type error, tip, success, warn
	 */
	tip : function(setting){
		if(setting === undefined){
			return;
		}
		if (setting.msg === undefined) {
			return;
		}
		if (setting.type === undefined) {
			setting.type = 'tips.gif';
		}
		
		var icon = 'tips.gif';
		if(setting.type == 'error'){
			icon = 'error.gif';
		}
		else if(setting.type == 'success'){
			icon = 'success.gif';
		}
		else if(setting.type == 'warn'){
			icon = 'alert.gif';
		}
		else {
			icon = 'tips.gif';
		}
		
		$.dialog({
			title : false,
			content : setting.msg,
			time : 1,
			min : false,
			max : false,
			icon : icon,
			// cancel: function(){},
			close : function() {
				var duration = 400, /* 动画时长 */
				api = this, opt = api.config, wrap = api.DOM.wrap, top = $(window).scrollTop() - wrap[0].offsetHeight;
				wrap.animate(
					{
						top : top + 'px',
						opacity : 0
					}, 
					duration, function() {
						opt.close = $.noop;
						api.close();
					}
				);
				return false;
			}
		});
	},
	
	
	showModelDialog : function(config){
		return $.dialog({
			title           : config.title,
			content         : config.content,
			lock            : true,
			button          : config.button,
			width           : config.width,
			height          : config.height,
			max             : config.max,
			min             : config.min
		});
	}, 
	
	showDialog : function(config){
		return $.dialog({
			title           : config.title,
			content         : config.content,
			lock            : config.lock === undefined ? false : config.lock,
			button          : config.button,
			width           : config.width,
			height          : config.height,
			max             : config.max,
			min             : config.min
		});
	}
});