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