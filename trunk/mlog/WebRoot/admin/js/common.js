function loadend() {
	setTimeout(function() {
		$('#loading').hide();
		$('#loading-mask').fadeOut('fast');
	}, 250);
}

/* Function for showing and hiding elements that use 'display:none' to hide */
function toggleDisplay(targetId) {
	if (document.getElementById) {
		target = document.getElementById(targetId);
		if (target.style.display == "none") {
			target.style.display = "";
		} else {
			target.style.display = "none";
		}
	}
}

/* Function for check all checkBox */
function checkAll(cb, name) {
	var array = document.getElementsByName(name);
	for ( var i = 0; i < array.length; i++) {
		array[i].checked = cb.checked;
	}
}

/* function for get all checked checkbox */
function getCheckedBox(name) {
	var all = document.getElementsByName(name);
	var ret = new Array();
	var index = 0;
	for ( var i = 0; i < all.length; i++) {
		if (all[i].checked) {
			ret[index] = all[i];
			index++;
		}
	}
	return ret;
}

/* Function for get checked checkbox value */
function getCheckedValue(name, hiddenId) {
	var array = getCheckedBox(name);

	var value = "";
	if (array && array.length > 0) {
		for ( var i = 0; i < array.length; i++) {
			if (i == (array.length - 1)) {
				value += array[i].value;
			} else {
				value += array[i].value + ",";
			}
		}
	}
	if (hiddenId) {
		document.getElementById(hiddenId).value = value;
	}
}

/* function for submit form, can set form action */
function submitForm(form, actionUrl) {
	if (typeof actionUrl != "undefined" && actionUrl != "") {
		form.action = actionUrl;
	}
	form.submit();
}

/* 判断checkbox是否有选中 */
function hasChecked(name) {
	var flag = false;
	var arr = document.getElementsByName(name);
	if (arr && arr.length > 0) {
		for ( var i = 0; i < arr.length; i++) {
			// 如果选中
			if (arr[i].checked)
				flag = true;
		}
	}
	if (!flag) {
		// $.dialog({title:'提示信息'});
		$.dialog({
			title : '提示信息',
			content : '请选择至少一条对象',
			cancel : true
		});
	}
	return flag;
}

/* 确认删除 */
function confirmDelete(form, actionUrl, msg) {
	if (!msg) {
		msg = "确认删除选中项吗?";
	}
	$.dialog({
		title : '提示信息',
		content : msg,
		ok : function() {
			// this.title('3秒后自动关闭').time(3);
			submitForm(form, actionUrl);
			return false;
		},
		cancelVal : '取消',
		cancel : true
	/* 为true等价于function(){} */
	});
}

function validateForm(formId, callback) {
	var form = document.getElementById(formId);
	if (form) {
		$.metadata.setType("attr", "validate");
		$.validator.addMethod("ajaxCheck", function(value, element, param) {
			if (param.length == 0) {
				return true;
			}
			var url = "";
			var action = param[0];
			var param_name = param[1];

			url += action;
			if (param_name) {
				url += "?" + param_name + "=" + value
			}
			var result = $.ajax({
				url : url,
				async : false
			}).responseText;
			var flag = result == "true" ? true : false;
			return flag;
		});
		$(form)
				.validate(
						{
							success : callback,
							/* 重写错误显示消息方法,以alert方式弹出错误消息 */
							showErrors : function(errorMap, errorList) {

								/* 处理错误表单样式 */
								for ( var i = 0; this.errorList[i]; i++) {
									var error = this.errorList[i];
									this.settings.highlight
											&& this.settings.highlight.call(
													this, error.element,
													this.settings.errorClass,
													this.settings.validClass);
									// this.showLabel(error.element,
									// error.message);
								}
								if (this.errorList.length) {
									this.toShow = this.toShow
											.add(this.containers);
								}
								if (this.settings.success) {
									for ( var i = 0; this.successList[i]; i++) {
										this.showLabel(this.successList[i]);
									}
								}
								if (this.settings.unhighlight) {
									for ( var i = 0, elements = this
											.validElements(); elements[i]; i++) {
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
									$
											.dialog({
												// title: "验证消息",
												title : false,
												content : "<font color='red'>"
														+ msg + "</font>",
												time : 2,
												min : false,
												max : false,
												icon : 'error.gif',
												// cancel: function(){},
												close : function() {
													var duration = 400, /* 动画时长 */
													api = this, opt = api.config, wrap = api.DOM.wrap, top = $(
															window).scrollTop()
															- wrap[0].offsetHeight;

													wrap.animate({
														top : top + 'px',
														opacity : 0
													}, duration, function() {
														opt.close = $.noop;
														api.close();
													});
													return false;
												}
											});
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

/**
 * 生成摘要
 * 
 * @param {}
 *            tinymce_id
 */
function summary(tiny, targetTiny, length) {
	var content = tinyMCE.get(tiny).getContent();
	var text_content = $(content).text();
	tinyMCE.get(targetTiny).setContent(text_content.substring(0, length));
}

// javascript endwith
if (!String.prototype.endWith) {
	(function() {
		String.prototype.endWith = String_endWith;
		function String_endWith(sub) {
			return this.length >= sub.length
					&& this.substring(this.length - sub.length) == sub;
		}
	})();
}