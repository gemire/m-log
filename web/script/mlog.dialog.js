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
			time : 2,
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
	}
});
