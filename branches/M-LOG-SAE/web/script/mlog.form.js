/**
 * @author GaoYoubo
 * @since 2012-09-25
 * form元素的操作和处理
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};
mlog.form = {};
$.extend(mlog.form, {
	
   /**
	 * 选择选择所有checkbox
	 */
	checkAll : function(_this, cbName) {
		var array = document.getElementsByName(cbName);
		for ( var i = 0; i < array.length; i++) {
			array[i].checked = _this.checked;
		}
	},

	/**
	 * 当只选择当前checkbox时， 返回当前checkbox的值
	 */
	checkThis : function(_this, cbName) {
		var array = document.getElementsByName(cbName);
		var count = 0;
		for ( var i = 0; i < array.length; i++) {
			if (array[i].checked) {
				count++;
			}
			if (count >= 2) {
				return;
			}
		}
		return _this.value;
	}, 
	
	/**
	 * 提交form
	 * @param {} formId
	 * @param {} action
	 */
	submitForm : function(formId, action){
		var form = document.getElementById(formId);
		if (action) {
			form.action = action;
		}
		form.submit();
	},
	
	confirmSubmit : function(formId, action, msg) {
		var default_msg = '确认删除选择项吗？';
		if (msg) {
			default_msg = msg;
		}
		if (confirm(default_msg)) {
			mlog.form.submitForm(formId, action);
		}
	},
	
	/**
	 * form表单验证
	 * @param {} conf.selector form选择器
	 * @param {} conf.showErrors showErrors方法
	 * @param {} conf.success 验证成功后的回调函数
	 * @param {} conf.onfocusout 失去焦点时是否验证 默认：false
	 * @param {} conf.onkeyup 输入时是否验证 默认：false
	 * @param {} conf.onclick 点击时是否验证 默认：false
	 * @param {} conf.errorLabelContainer
	 * @param {} conf.wrapper
	 */
	validate : function(conf){
		if(conf === undefined || conf.selector === undefined) return;
		
		if(conf.onfocusout === undefined){conf.onfocusout = false}
		if(conf.onkeyup === undefined){conf.onkeyup = false}
		if(conf.onclick === undefined){conf.onclick = false}
		
		$.metadata.setType("attr", "validate");
		$(conf.selector).validate({
			success : conf.success,
			errorLabelContainer : $(conf.errorLabelContainer),
			wrapper: conf.wrapper,
			onfocusout : conf.onfocusout,
			onkeyup : conf.onkeyup,
			onclick : conf.onclick,
			showErrors : conf.showErrors
		});
	}
});