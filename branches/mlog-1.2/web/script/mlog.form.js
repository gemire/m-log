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
	
	validateForm : function(formId, callback) {
		var form = document.getElementById(formId);
		if (form) {
			$.metadata.setType("attr", "validate");
			/**
			 * 自定义check方法,该方法用于做ajax检测
			 * @param value 当前验证表单元素的值
			 * @param element 当前验证表单元素
			 * @param param 当前验证传递的参数
			 */
			$.validator.addMethod("ajaxCheck", function(value, element, param) {
				try{
					if (param.length == 0) {
						return true;
					}
					var url = param[0];
					var param_name = param[1];
					if (param_name) {
						url += "?" + param_name + "=" + value;
					}
					var text = $.ajax({
						url : url,
						async : false
					}).responseText;
					
					var validationResult = eval('('+text+')');
					$.validator.messages["ajaxCheck"] = validationResult.message != undefined ? validationResult.message : $.validator.messages["ajaxCheck"];
					var result = false;
					if(validationResult.result === true){
						result = true;
					}
					return result;
				}
				catch(e){
					mspring.tip("<font color='red'>Validator error:" + e.message + "</font>");
				}
			});
			$(form).validate({
				success : callback,
				/* 重写错误显示消息方法,以alert方式弹出错误消息 */
				showErrors : function(errorMap, errorList) {
	
					/* 处理错误表单样式 */
					for ( var i = 0; this.errorList[i]; i++) {
						var error = this.errorList[i];
						this.settings.highlight && this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass);
						// this.showLabel(error.element,error.message);
					}
					if (this.errorList.length) {
						this.toShow = this.toShow.add(this.containers);
					}
					if (this.settings.success) {
						for ( var i = 0; this.successList[i]; i++) {
							this.showLabel(this.successList[i]);
						}
					}
					if (this.settings.unhighlight) {
						for ( var i = 0, elements = this.validElements(); elements[i]; i++) {
							this.settings.unhighlight.call(this,
									elements[i],
									this.settings.errorClass,
									this.settings.validClass);
						}
					}
					this.toHide = this.toHide.not(this.toShow);
					this.hideErrors();
					this.addWrapper(this.toShow).show();
					/* end 处理错误表单样式 */
	
					var msg = "";
					$.each(errorList, function(i, v) {
						// v.element.style =
						msg += (v.message + "<br />");
					});
					if (msg) {
						mspring.tip("<font color='red'>" + msg + "</font>");
					}
				},
				// 失去焦点时不验证
				onfocusout : false,
				// 输入时不验证
				onkeyup : false,
				// 点击时不验证
				onclick : false
			});
		}
	}
});
