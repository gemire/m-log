$(document).ready(function(){
	$("table.dtable tr").each(function(){
		$(this).mouseover(function(){
			$(this).children("td").each(function(){
				$(this).css("background-color", "#F2F4E7");
			});
		});
		
		$(this).mouseout(function(){
			$(this).children("td").each(function(){
				$(this).css("background-color", "");
			});
		});
		
		// $(this).click(function(){
			// var td1 = $(this).children("td:eq(0)");
			// var chk = $(td1).children("input:checkbox");
			// if(chk){
				// if($(chk).attr('checked')){
				// $(chk).removeAttr('checked');
				// }
				// else {
					// $(chk).attr('checked', 'checked');
				// }
			// }
		// });
	});
});

function setMainFrameUrl(url){
	window.top.main.location = url; 
}

/**
 * 转换高亮tab 
 * @param {Object} id
 */
function turnHighLight(id){
	if(id){
		$(".tab a").each(function(){
			if(this.id == id){
				$(this).attr("class", "here");
			}
			else {
				$(this).removeAttr("class");
			}
		});	
	}
}


/*
 * @description 加载 SyntaxHighlighter 
 * @param {String} SHTheme SyntaxHighLighter 样式
 * @param {String} selector SyntaxHighLighter 容器
 */
function loadSyntaxHighlighter(SHTheme, selector){
	var cssName = SHTheme ? SHTheme : "shCoreEclipse";
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
                        initSyntaxHighlighter(languages);
                    }
            	});
            } else {
                initSyntaxHighlighter(languages);
            }
    	}
    });
}

/*
 * @description 初始化 SyantaxHighlighter
 * @param {Array} languages 需要加载的语言 
 */
function initSyntaxHighlighter(languages){
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
}