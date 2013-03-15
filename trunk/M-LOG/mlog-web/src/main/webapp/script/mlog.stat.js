/**
 * @author GaoYoubo
 * @since 2012-11-16
 * mlog.stat mlog统计函数
 */
if ( typeof (mlog) === "undefined") {
	var mlog = function() {
	}
};

mlog.stat = {};

$.extend(mlog.stat, {
	postClick : function(post_id) {
		if (!post_id) {
			return;
		}
		$.get(mlog.variable.base + "/script/stat?cmd=post_click&post_id=" + post_id);
	},
	blogClick : function() {
		$.get(mlog.variable.base + "/script/stat?cmd=blog_click");
	}
});
