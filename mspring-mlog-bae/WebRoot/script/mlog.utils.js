/**
 * @author GaoYoubo
 * @since 2012-09-25
 * mlog.utils
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};
mlog.utils = {};
$.extend(mlog.utils, {
	/**
	 * 获取站点的根路径
	 * 不推荐使用此方法获取网站路径,当程序安装在域名的自路径的时候会出现问题.
	 * 推荐使用mlog.variable.base活blogurl
	 * @return {}
	 */
	getWebRootPath : function(){
		var path = location.href ; 
		var pathArr = path.split("/"); 
		return pathArr[0]+"//"+pathArr[2]+"/"+pathArr[3] ;
	},
	
	/**
	 * 获取XMLHttpRequest
	 */
	getXMLHttpRequest : function(){
		if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        } else if (window.ActiveXObject) {// code for IE6, IE5
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
	},
	
	 /**
     * 获取Cookie值
     */
    getCookie : function(sName) {
    	var arr = document.cookie.match(new RegExp("(^| )" + sName + "=([^;]*)(;|$)"));
    	if (arr != null) {
    		return unescape(arr[2]);
    	}
    	return null;
    },

    /**
     * 设置cookie值
     * @param sName 名字
     * @param sValue 值
     * @param iExpireDays cookie保存时间(单位：天)
     */
    setCookie : function(sName, sValue, iExpireDays) {
    	if (iExpireDays) {
    		var dExpire = new Date();
    		dExpire.setTime(dExpire.getTime() + parseInt(iExpireDays * 24 * 60 * 60 * 1000));
    		document.cookie = sName + "=" + escape(sValue) + "; expires=" + dExpire.toGMTString() + "; path=/";
    	} else {
    		document.cookie = sName + "=" + escape(sValue) + "; path=/";
    	}
    },
    
    /**
     * 跳转到页面顶部
     */
    scrollTop : function(){
		var acceleration = acceleration || 0.1;

        var y = $(window).scrollTop();
        var speed = 1 + acceleration;
        window.scrollTo(0, Math.floor(y / speed));

        if (y > 0) {
            var invokeFunction = "mlog.utils.scrollTop(" + acceleration + ")";
            window.setTimeout(invokeFunction, 16);
        }
    }
});

mlog.utils.loader = {};
$.extend(mlog.utils.loader, {
	/**
	 * 加载JS文件,在此严重的鄙视IE
	 * @param {} path　JS文件的路径
	 * @param {} callback　JS文件加载成功后的回调函数
	 */
    loadJavaScript : function(path, callback) {  
        try {  
            var script = document.createElement('script');  
            script.src = path;  
            script.type = "text/javascript";  
            document.getElementsByTagName("head")[0].appendChild(script);  
            if( script.addEventListener ) {
                script.addEventListener("load", callback, false);  
            } else if(script.attachEvent) {
                script.attachEvent("onreadystatechange", function(){  
                        if(script.readyState == 4  
                            || script.readyState == 'complete'  
                            || script.readyState == 'loaded') {  
                            callback();  
                        }
                });  
            }
        } catch(e) {
            callback();  
        }
    },
    
   	/**
	 * 加载StyleSheet
	 * @param url stylesheet的地址
	 */
	loadStyleSheet : function(url){
		if (document.createStyleSheet) {
            return document.createStyleSheet(url);
        } else {
            $("head").append($("<link rel='stylesheet' href='" + url + "' type='text/css' charset='utf-8' />"));
        }
	},
	
	
	/**
	 * 加载JavaScript文件
	 * @param setting 设置项
	 * @param setting.url JavaScript地址
	 * @param setting.async (默认: false) 默认设置下，所有请求均为同步请求
	 * @param setting.success 加载成功后的回调函数
	 */
	loadJavaScriptByAjax : function(setting){
		if(setting === undefined || setting.url === undefined) {
			return;
		}
		//默认同步加载JS文件
		if(setting.async === undefined) setting.async = false;
		
		
		$.ajax({
            url: setting.url,
            dataType: "script",
            async : setting.async,
            cache: true,
            success: setting.success
        });
	}
});


/**
 * startWith
 * @param {} s
 * @return {Boolean}
 */
String.prototype.startWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substr(0, s.length) == s)
		return true;
	else
		return false;
	return true;
}

/**
 * endWidth
 * @param {} s
 * @return {Boolean}
 */
String.prototype.endWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length)
		return false;
	if (this.substring(this.length - s.length) == s)
		return true;
	else
		return false;
	return true;
}