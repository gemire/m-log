var arrCSS = [
  	["<img src='" + mlog.variable.template_url + "/images/layout1.png' width='10' height='10' class='themes' alt='layout1'>",mlog.variable.template_url + "/style/layout1.css" ],
  	["<img src='" + mlog.variable.template_url + "/images/layout2.png' width='10' height='10' class='themes' alt='layout2'>",mlog.variable.template_url + "/style/layout2.css" ],
  	["<img src='" + mlog.variable.template_url + "/images/layout3.png' width='10' height='10' class='themes' alt='layout3'>",mlog.variable.template_url + "/style/layout3.css" ],
  	["<img src='" + mlog.variable.template_url + "/images/layout4.png' width='10' height='10' class='themes' alt='layout4'>",mlog.variable.template_url + "/style/layout4.css" ],
  	["<img src='" + mlog.variable.template_url + "/images/layout5.png' width='10' height='10' class='themes' alt='layout5'>",mlog.variable.template_url + "/style/layout5.css" ],
  	"" 
];

var style_sheet_cookie = "5style_cookie";
var current_style = mlog.utils.getCookie(style_sheet_cookie);
var default_style = "layout1";

/**
 * 设置样式
 */
function writeCSS() {
	if(current_style === undefined || current_style === 'undefined' || current_style === null){
		current_style = default_style;
	}
	//加载所有样式表
	for(var i = 0; i < arrCSS.length - 1; i++){
		var disabled = true;
		//alert(current_style);
		if(arrCSS[i][1].indexOf(current_style) > 0 ){
			disabled = false;
		}
		writeStyleSheet(arrCSS[i][1], "layout" + (i + 1), disabled);
	}
}

/**
 * 将样式写入dom元素
 * @param url
 * @param disabled
 */
function writeStyleSheet(url, title, disabled){
	if(disabled === undefined || disabled === 'undefined'){disabled = false}
	if(disabled){
		$("head").append($("<link rel='stylesheet' href='" + url + "' disabled='" + disabled + "' title='" + title + "' type='text/css' charset='utf-8' />"));
	}
	else{
		$("head").append($("<link rel='stylesheet' href='" + url + "' title='" + title + "' type='text/css' charset='utf-8' />"));
	}
}

writeCSS();
$(document).ready(function(){
	StyleController();
});

/**
 * 设置样式控制器
 */
function StyleController() {
	var style_ctrl_html = "";
	for ( var i = 0; i < arrCSS.length - 1; i++) {
		if (i > 0)
			style_ctrl_html += "  ";
		style_ctrl_html += '<a href="javascript:setStyleSheet(\'layout' + (i + 1) + '\')">' + arrCSS[i][0] + '</a>';
	}
	$("#StyleController").html(style_ctrl_html);
}

function setStyleSheet(css) {
	var styles = $("link[href][rel='stylesheet'][type='text/css'][title^='layout']");
	for(var i = 0; i < styles.length; i++){
		if(styles[i].title === css){
			styles[i].disabled = "";
			current_style = styles[i].title;
			mlog.utils.setCookie(style_sheet_cookie, current_style, 365);
		}
		else{
			styles[i].disabled = "disabled";
		}
	}
}