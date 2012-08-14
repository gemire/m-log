/*
 * 前台页面通用JavaScript文件
 * @author GaoYoubo
 * @since 2012-07-28
 * 
 */

/**
 * 获取Cookie值
 */
var getCookie = function(sName) {
	var arr = document.cookie.match(new RegExp("(^| )" + sName
			+ "=([^;]*)(;|$)"));
	if (arr != null) {
		return unescape(arr[2])
	}
	;
	return null;
}

/**
 * 设置cookie值
 * 
 * @param sName
 *            名字
 * @param sValue
 *            值
 * @param iExpireDays
 *            cookie保存时间(单位：天)
 */
var setCookie = function(sName, sValue, iExpireDays) {
	if (iExpireDays) {
		var dExpire = new Date();
		dExpire.setTime(dExpire.getTime()
				+ parseInt(iExpireDays * 24 * 60 * 60 * 1000));
		document.cookie = sName + "=" + escape(sValue) + "; expires="
				+ dExpire.toGMTString() + "; path=/";
	} else {
		document.cookie = sName + "=" + escape(sValue) + "; path=/";
	}
}

/**
 * 获取cookie中保存的评论作者
 * 
 * @returns
 */
var getCookieCommentAuthor = function() {
	return getCookie("comment_author_cookie");
}

/**
 * 获取cookie中保存的评论邮箱
 * 
 * @returns
 */
var getCookieCommentEmail = function() {
	return getCookie("comment_email_cookie");
}

/**
 * 获取评论中保存的评论作者主页地址
 * 
 * @returns
 */
var getCookieCommentUrl = function() {
	return getCookie("comment_url_cookie");
}

var mlog = function(){	
}

$.extend(mlog, {
	
	/**
	 * 获取当前光标所在位置
	 */
	getCursorEndPosition: function (textarea) {
        textarea.focus();
        if (textarea.setSelectionRange) { // W3C
            return textarea.selectionEnd;
        } else if (document.selection) { // IE
            var i = 0,
            oS = document.selection.createRange(),
            oR = document.body.createTextRange(); 
            oR.moveToElementText(textarea);
            oS.getBookmark();
            for (i = 0; oR.compareEndPoints('StartToStart', oS) < 0 && oS.moveStart("character", -1) !== 0; i ++) {
                if (textarea.value.charAt(i) == '\n') {
                    i ++;
                }
            }
            return i;
        }
    },
	
	/**
	 * 插入图片表情
	 */
	insertEmotions : function(name){
		var _this = this;
		
		if(name === undefined){
			name = "";
		}
		
		$("#emotions" + name + " span").click(function () {
	        var $comment = $("#comment" + name);
	        var endPosition = _this.getCursorEndPosition($comment[0]);
	        var key = "[" + this.className + "]",
	        textValue  = $comment[0].value;
	        textValue = textValue.substring(0, endPosition) + key + textValue.substring(endPosition, textValue.length);
	        $("#comment" + name).val(textValue);

	        if ($.browser.msie) {
	            endPosition -= textValue.split('\n').length - 1;
	            var oR = $comment[0].createTextRange();
	            oR.collapse(true);
	            oR.moveStart('character', endPosition + 6);
	            oR.select();
	        } else {
	            $comment[0].setSelectionRange(endPosition + 6, endPosition + 6);
	        }
	    });
	},
	
	/**
	 * 将评论中的表情标识，替换成表情图片
	 */
	replaceCommentsEm : function(selector){
		var _this = this;
		var $commentContents = $(selector);
        for (var i = 0; i < $commentContents.length; i++) {
            var str = $commentContents[i].innerHTML;
            $commentContents[i].innerHTML =  _this.replaceEmString(str);
        }
	},
	
	/**
	 * 替换表情html文本
	 */
	replaceEmString : function(str){
		var _this = this;
		var commentSplited = str.split("[em");
        if (commentSplited.length === 1) {
            return str;
        }
        str = _this._processEm(commentSplited[0]);
        if ($.trim(commentSplited[0]) === "") {
            str = "";
        }
        for (var j = 1; j < commentSplited.length; j++) {
            var key = commentSplited[j].substr(0, 2);
            str += "<span class='em" + key + "'></span>" + this._processEm(commentSplited[j].slice(3));
        }
        return str + "<div class='clear'></div>";
	},
	
	_processEm: function (str) {
        if (str.replace(/\s/g, "") === "") {
            return "";
        }
        
        var strList = [], 
        resultStr = "",
        brList = ["<br>", "<br/>", "<BR>", "<BR/>"];
        for (var j = 0; j < brList.length; j++) {
            if (str.indexOf(brList[j]) > -1) {
                strList = str.split(brList[j]);
            }
        }
        
        if (strList.length === 0) {
            return "<span class='em-span'>" + str + "</span>";
        }
        
        for (var i = 0; i < strList.length; i++) {
            resultStr += "<span class='em-span'>" + strList[i] + "</span>";
            if (i !== strList.length - 1) {
                resultStr +="<br class='em-br'>";
            }
        }
        return resultStr;
    },
	
	/**
	 * 页面加载
	 */
	load : function(){
		var _this = this;
		_this.insertEmotions();
	}
});