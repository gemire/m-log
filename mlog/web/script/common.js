/**
 * @author Gao Youbo 
 */
var mspring = {};

/**
 * 选择选择所有checkbox
 */
mspring.checkAll = function(_this, cbName){
	var array = document.getElementsByName(cbName);
	for(var i = 0; i < array.length; i++){
		array[i].checked = _this.checked;
	}
}

/**
 * 当只选择当前checkbox时， 返回当前checkbox的值
 */
mspring.checkThis = function(_this, cbName){
	var array = document.getElementsByName(cbName);
	var count = 0;
	for(var i = 0; i < array.length; i++){
		if(array[i].checked){
			count++;
		}
		if(count >= 2){
			return;
		}
	}
	return _this.value;
}