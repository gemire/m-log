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
	 * @param {} msg
	 */
	tip : function(msg){
		$.dialog({
			title : false,
			content : msg,
			time : 1,
			min : false,
			max : false,
			icon : 'error.gif',
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
			lock            : config.lock === undefined ? true : config.lock,
			button          : config.button,
			width           : config.width,
			height          : config.height,
			max             : config.max,
			min             : config.min
		});
	}
});