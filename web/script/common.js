/**
 * @author Gao Youbo
 */
var mspring = {};

/**
 * 选择选择所有checkbox
 */
mspring.checkAll = function(_this, cbName) {
	var array = document.getElementsByName(cbName);
	for ( var i = 0; i < array.length; i++) {
		array[i].checked = _this.checked;
	}
}

/**
 * 当只选择当前checkbox时， 返回当前checkbox的值
 */
mspring.checkThis = function(_this, cbName) {
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
}

/**
 * 提交表单
 */
mspring.submitForm = function(formId, action) {
	var form = document.getElementById(formId);
	if (action) {
		form.action = action;
	}
	form.submit();
}

/**
 * 确认提交表单
 */
mspring.confirmSubmit = function(formId, action, msg) {
	var default_msg = '确认删除选择项吗？';
	if (msg) {
		default_msg = msg;
	}
	if (confirm(default_msg)) {
		mspring.submitForm(formId, action);
	}
}

mspring.validateForm = function(formId, callback) {
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

/**
 * tip消息
 */
mspring.tip = function(msg){
	$.dialog({
		// title: "验证消息",
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

mspring.editor = {
	/*
     * @description 初始化编辑器
     * @param conf 编辑器初始化参数
     * @param conf.type 编辑器类型
     * @param conf.id 编辑器渲染元素 id
     * @param conf.fun 编辑器首次加载完成后回调函数
     */
    init: function (conf) {
    	var language = "zh";
        if (language === "zh") {
            language = "zh-cn";
        }
        
        if (conf.type && conf.type === "simple") {
            try {
                tinyMCE.init({
                    // General options
                    language: language,
                    mode : "exact",
                    elements : conf.id,
                    theme : "advanced",

                    // Theme options
                    theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,undo,redo,|,bullist,numlist",
                    theme_advanced_buttons2 : "",
                    theme_advanced_buttons3 : "",
                    theme_advanced_toolbar_location : "top",
                    theme_advanced_toolbar_align : "left",
                
                    valid_children : "+body[style]"
                });
            } catch (e) {
            	alert("TinyMCE load fail");
            }
        } else {
            try {
            	tinyMCE.init({
                    // General options
                    language: 'zh-cn',
                    mode : "exact",
                    elements : conf.id,
                    theme : "advanced",
                    plugins : "autosave,style,advhr,advimage,advlink,preview,inlinepopups,media,paste,fullscreen,syntaxhl",

                    // Theme options
                    theme_advanced_buttons1 : "forecolor,backcolor,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
                    theme_advanced_buttons2 : "bullist,numlist,outdent,indent,|,undo,redo,|,sub,sup,blockquote,charmap,image,iespell,media,|,advhr,link,unlink,anchor,cleanup,|,pastetext,pasteword,code,preview,fullscreen,syntaxhl",
                    theme_advanced_buttons3 : "",
                    theme_advanced_toolbar_location : "top",
                    theme_advanced_toolbar_align : "left",
                    theme_advanced_resizing : true,
                    theme_advanced_statusbar_location : "bottom",

                    extended_valid_elements: "pre[name|class],iframe[src|width|height|name|align]",

                    valid_children : "+body[style]",
                    relative_urls: false,
                    remove_script_host: false,
                    oninit : function () {
                    	//window.onhashchange = admin.setCurByHash;
                        if (typeof(conf.fun) === "function") {
                            conf.fun();
                        }
                    },
                    //处理jquery-validation的异常，在每次tinyMCE内容改变是，都执行triggerSave()操作
                    onchange_callback : function(){
                    	tinyMCE.triggerSave();
                    }
                });
            } catch (e) {
            	alert("TinyMCE load fail");
            }
        }
    },
    
    /*
     * @description 获取编辑器值
     * @param {string} id 编辑器id
     * @returns {string} 编辑器值
     */
    getContent: function (id) {
        var content = "";
        try {
            content = tinyMCE.get(id).getContent();
        } catch (e) {
            content = $("#" + id).val();
        }
        return content;
    },
    
    /*
     * @description 设置编辑器值
     * @param {string} id 编辑器 id
     * @param {string} content 设置编辑器值
     */
    setContent: function (id, content) {
        try {
            if (tinyMCE.get(id)) {
                tinyMCE.get(id).setContent(content);
            } else {
                $("#" + id).val(content);
            }
        } catch (e) {
            $("#" + id).val(content);
        }
    }
};



/*****************************/
String.prototype.endWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substring(this.length - s.length) == s)
		return true;
	else
		return false;
	return true;
}

String.prototype.startWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substr(0, s.length) == s)
		return true;
	else
		return false;
	return true;
}