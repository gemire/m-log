/*
 * 前台页面通用JavaScript文件
 * @author Gao Youbo
 * @since 2012-07-28
 * 
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};
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
	 * 为表情图像绑定点击事件
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
	 * @param selector 被替换表情的容器
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
    
    /*
     * @description 初始化 SyantaxHighlighter
     * @param {Array} languages 需要加载的语言 
     */
    initSyntaxHighlighter: function(languages){
    	for(var i = 0; i < languages.length; i++){
    		switch(languages[i]){
	    		case "groovy":
	                languages[i] =  'groovy				' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushGroovy.js';
	                break;
	            case "java":
	                languages[i] =  'java				' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushJava.js';
	                break;
	            case "php":
	                languages[i] =  'php				' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushPhp.js';
	                break;
	            case "scala":
	                languages[i] =  'scala				' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushScala.js';
	                break;
	            case "sql":
	                languages[i] =  'sql				' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushSql.js';
	                break;
	            case "applescript":
	                languages[i] =  'applescript			' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushAppleScript.js';
	                break;
	            case "as3": 
	            case "actionscript3":
	                languages[i] =  'actionscript3 as3                  ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushAS3.js';
	                break;
	            case "bash":
	            case "shell":
	                languages[i] =  'bash shell                         ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushBash.js';
	                break;
	            case "coldfusion":
	            case "cf":
	                languages[i] =  'coldfusion cf			' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushColdFusion.js';
	                break;
	            case "c#":
	            case "c-sharp":
	            case "csharp":
	                languages[i] =  'c# c-sharp csharp                  ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushCSharp.js';
	                break;
	            case "cpp":
	            case "c":
	                languages[i] =  'cpp c				' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushCpp.js';
	                break;	
	            case "css":
	                languages[i] =  'css				' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushCss.js';
	                break;
	            case "delphi":
	            case "pascal":
	                languages[i] =  'delphi pascal			' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushDelphi.js';
	                break;			
	            case "diff":
	            case "patch":
	            case "pas":
	                languages[i] =  'diff patch pas			' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushDiff.js';
	                break;			
	            case "erl":
	            case "erlang":
	                languages[i] =  'erl erlang                         ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushErlang.js';
	                break;			
	            case "js":
	            case "jscript":
	            case "javascript":
	                languages[i] =  'js jscript javascript              ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushJScript.js';
	                break;			
	            case "jfx":
	            case "javafx":
	                languages[i] =  'jfx javafx                 	' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushJavaFX.js';
	                break;			
	            case "perl":
	            case "pl":
	                languages[i] =  'perl pl                    	' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushPerl.js';
	                break;			
	            case "plain":
	            case "text":
	                languages[i] =  'text plain                 	' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushPlain.js';
	                break;			
	            case "ps":
	            case "powershell":
	                languages[i] =  'ps powershell                      ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushPowerShell.js';
	                break;			
	            case "py":
	            case "python":
	                languages[i] =  'py python                          ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushPython.js';
	                break;			
	            case "rails":
	            case "ror":
	            case "ruby":
	            case "rb":
	                languages[i] =  'ruby rails ror rb          	' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushRuby.js';
	                break;	
	            case "sass":
	            case "scss":
	                languages[i] =  'sass scss                  	' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushSass.js';
	                break;
	            case "vb":
	            case "vbnet":
	                languages[i] =  'vb vbnet                   	' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushVb.js';
	                break;			
	            case "xml":
	            case "xhtml":
	            case "xslt": 
	            case "html":
	                languages[i] =  'xml xhtml xslt html                ' + mlog.variable.base + '/script/SyntaxHighlighter/scripts/shBrushXml.js';
	                break;	
	            default:
	                break;
    		}
    	}
    	// code high lighter
        SyntaxHighlighter.autoloader.apply(null, languages);
        SyntaxHighlighter.config.stripBrs = true;
        SyntaxHighlighter.all();
    },
    
    /*
     * @description 加载 SyntaxHighlighter 
     * @param {String} SHTheme SyntaxHighLighter 样式
     * @param {String} selector SyntaxHighLighter 容器
     */
    loadSyntaxHighlighter : function(SHTheme, selector){
    	var cssName = SHTheme ? SHTheme : "shCoreEclipse";
    	var _this = this;
    	// load css
        mlog.utils.loader.loadStyleSheet(mlog.variable.base + "/script/SyntaxHighlighter/styles/" + cssName + ".css");
        
        // load js
        /**
    	 * 加载JavaScript文件
    	 * @param setting 设置项
    	 * @param setting.url JavaScript地址
    	 * @param setting.async (默认: true) 默认设置下，所有请求均为异步请求
    	 * @param setting.success 加载成功后的回调函数
    	 */
        mlog.utils.loader.loadJavaScriptByAjax({
        	url : mlog.variable.base + "/script/SyntaxHighlighter/scripts/shCore.js",
        	success : function(){
        		// get brush settings
                var languages = [], isScrip = false;
                $(selector).each(function () {
                    var name = this.className.split(";")[0];
                    var language = name.substr(7, name.length - 1);
                    if (this.className.indexOf("html-script: true") > -1 && (language !== "xml" && language !== "xhtml" && language !== "xslt" && language != "html")) {
                        isScrip = true;
                    }
                    languages.push(language);
                });
                // when html-script is true, need shBrushXml.js
                if (isScrip) {
                	mlog.utils.loader.loadJavaScriptByAjax({
                		url : mlog.variable.base + "/script/SyntaxHighlighter/scripts/shBrushXml.js",
                		success: function() {
                            _this.initSyntaxHighlighter(languages);
                        }
                	});
                } else {
                    _this.initSyntaxHighlighter(languages);
                }
        	}
        });
    },
    
    /*
     * @description 解析语法高亮
     * @param {Obj} setting 语法高亮配置参数
     * @param {Obj} setting.SHTheme 语法高亮 SyntaxHighLighter 样式
     * @param {Obj} setting.contentSelector 文章内容容器
     */
    parseLanguage: function (setting) {
        var isPrettify = false;
        var isSH = false;
        
        var selector = setting ? (setting.contentSelector ? setting.contentSelector : ".content") : ".content";
        selector = selector + " pre";
        
        $(selector).each(function () {
            if (this.className.indexOf("brush") > -1) {
                isSH = true;
            } 
            
            if (this.className.indexOf("prettyprint") > -1) {
                isPrettify = true;
            }
        });
        
        if (isSH) {
        	var SHTheme = setting ? (setting.SHTheme ? setting.SHTheme : undefined) : undefined;
            this.loadSyntaxHighlighter(SHTheme, selector);
        }
        
        if (isPrettify) {
            // load css
            mlog.utils.loader.loadStyleSheet(mlog.variable.base + "/script/prettify/prettify.css");
            // load js
            mlog.utils.loader.loadJavaScriptByAjax({
            	url : mlog.variable.base + "/script/prettify/prettify.js"
            })
            // load function
            $(document).ready(function () {
                prettyPrint();
            });
        }
    },
    
    /**
     * 初始化编辑器 
     * @param conf.selector
     * @param conf.width
     * @param conf.height
     */
    initEditor : function(conf){
    	var _this = this;
    	if(typeof(mlog.editor) === "undefined"){
    		mlog.utils.loader.loadJavaScript(mlog.variable.base + "/script/mlog.editor.js", function(){
				mlog.editor.init({
					id: conf.selector,
					width: conf.width,
					height: conf.height,
					model : "simple"
				});
			});
    	}
		else{
			mlog.editor.init({
				id: conf.selector,
				width: conf.width,
				height: conf.height,
				model : "simple"
			});
		}
    },
    
    /**
     * 回复
     */
    reply : function(){
    	
    },
	
	/**
	 * @description 文章/自定义页面加载
     * @param {Object} setting 配置设定
     * @param {Object} setting.language 代码高亮配置
     * @param {Object} setting.contentSelector 文章内容容器,默认".content"
	 */
	load : function(setting){
		var _this = this;
		// language
		_this.parseLanguage(setting);
	},
	
    /**
     * 自动加载
     */
    autoLoad : function(){
    	var _this = this;
    	
    	//为表情对象绑定点击 事件
		_this.insertEmotions();
    }
});

/**
 * 自动加载
 */
$(document).ready(function(){
	mlog.autoLoad();
});
