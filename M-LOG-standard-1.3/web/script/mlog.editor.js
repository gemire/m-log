/**
 * @author GaoYoubo
 * @since 2012-09-25
 * form元素的操作和处理
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};

mlog.editor = {};
mlog.editor.ins = {};
mlog.editor.ins.createPostEditor = {};
mlog.editor.ins.createPostSummaryEditor = {};
mlog.editor.ins.editPostEditor = {};
mlog.editor.ins.editPostSummaryEditor = {};

$.extend(mlog.editor,{
	/*
     * @description 初始化编辑器
     * @param conf 编辑器初始化参数
     * @param conf.type 编辑器种类kindeditor/tinymce
     * @param conf.model 编辑器显示模式simple/all
     * @param conf.id 编辑器渲染元素 id
     * @param conf.fun 编辑器首次加载完成后回调函数
     * @param conf.width
     * @param conf.height
     * @param conf.language 语言
     */
	init : function(conf){
		if(conf.type != 'kindeditor' && conf.type != 'tinymce') conf.type = 'kindeditor';
		if(conf.model != 'all' && conf.model != 'simple') conf.model = 'all';
		if(conf.type === undefined) conf.type = 'kindeditor';
		if(conf.model === undefined) conf.model = 'all';
		
		//KindEditor
		if(conf.type === 'kindeditor'){
			var editor_ins = null; //editor实例
			if(typeof(KindEditor) === "undefined"){
				editor_ins = mlog.utils.loader.loadJavaScript(mlog.variable.base + "/script/kindeditor/kindeditor.js", function(){
					mlog.editor.KindEditor.init({
						model : conf.model,
						id : conf.id,
						fun : conf.fun,
						width : conf.width,
						height : conf.height,
						language : conf.language
					});
				});
			}
			else{
				editor_ins = mlog.editor.KindEditor.init({
					model : conf.model,
					id : conf.id,
					fun : conf.fun,
					width : conf.width,
					height : conf.height,
					language : conf.language
				});
			}
			return editor_ins;
		}
		//TinyMCE
		else if(conf.type === 'tinymce'){
			if(typeof(tinyMCE) === "undefined"){
				mlog.utils.loader.loadJavaScript(mlog.variable.base + "/script/tinymce/tiny_mce.js", function(){
					mlog.editor.TinyMCE.init({
						model : conf.model,
						id : conf.id,
						fun : conf.fun,
						width : conf.width,
						height : conf.height,
						language : conf.language
					});
				});
			}
			else{
				mlog.editor.TinyMCE.init({
					model : conf.model,
					id : conf.id,
					fun : conf.fun,
					width : conf.width,
					height : conf.height,
					language : conf.language
				});
			}
		}
		else{}
	}
});


/**
 * KindEditor扩展
 * @type 
 */
mlog.editor.KindEditor = {};
$.extend(mlog.editor.KindEditor, {
	/*
     * @description 初始化KindEditor编辑器
     * @param conf 编辑器初始化参数
     * @param conf.model 编辑器显示模式simple/all
     * @param conf.id 编辑器渲染元素 id
     * @param conf.fun 编辑器首次加载完成后回调函数
     * @param conf.width 宽度
     * @param conf.height 高度
     * @param conf.language 语言
     */
	init : function(conf){
		if(typeof(KindEditor) === "undefined") return;
		if(conf.language === undefined) conf.language = "zh_CN";
		if(conf.model === undefined) conf.model = 'all';
		
		if(conf.model === "all"){
			this[conf.id] = KindEditor.create('#' + conf.id, {
				langType : conf.language,
				width : conf.width,
				height : conf.height,
				uploadJson : mlog.variable.base + '/admin/attachment/upload',
				//urlType : 'absolute',
				//fileManagerJson : mlog.variable.base + '/file_manager_json.jsp',
				//allowFileManager : true,
                items: ["formatblock", "fontname", "fontsize", "|", "bold", "italic", "underline", "strikethrough", "forecolor", "|",
                		"link", "unlink", "pagebreak", "|", "emoticons", "mlog-uploads", /*"image", "multiimage",*/ "flash", "media", "code", "fullscreen", "/",
                		"undo", "redo", "|", "insertunorderedlist", "insertorderedlist", "indent", "outdent", "|", 
                		"justifyleft", "justifycenter", "justifyright", "justifyfull", "|", "plainpaste", "wordpaste", "|", 
                		"clearhtml", "source", "preview"
                	],
                afterCreate: function () {
                    // TODO: chrome bug
                    //window.onhashchange = admin.setCurByHash;
                	this.sync();
                    if (typeof(conf.fun) === "function") {
                        conf.fun();
                    }
                },
                afterChange : function(){
                	this.sync();
                }
            });
		}
		else if(conf.model === "simple"){
			this[conf.id] = KindEditor.create('#' + conf.id, {
                langType : conf.language,
                width : conf.width,
				height : conf.height,
                resizeType: 0,
                items: ["bold", "italic", "underline", "strikethrough", "|", "undo", "redo", "|", 
                "insertunorderedlist", "insertorderedlist", "|", "emoticons"
                ],
                afterCreate: function () {
                    // TODO: chrome bug
                    //window.onhashchange = admin.setCurByHash;
                	this.sync();
                    if (typeof(conf.fun) === "function") {
                        conf.fun();
                    }
                },
                afterChange : function(){
                	this.sync();
                }
            });
		}
		return this[conf.id];
	},
	getHtmlContent : function(editorId){
		var content = "";
        try {
            content = this[editorId].html();
        } catch (e) {
            content = $("#" + editorId).val();
        }
        return content;
	},
	setHtmlContent : function(editorId, content){
		try {
            this[editorId].html(content);
        } catch (e) {
            $("#" + editorId).val(content);
        }
	}
});


mlog.editor.TinyMCE = {};
$.extend(mlog.editor.TinyMCE, {
	/*
     * @description 初始化KindEditor编辑器
     * @param conf 编辑器初始化参数
     * @param conf.model 编辑器显示模式simple/all
     * @param conf.id 编辑器渲染元素 id
     * @param conf.fun 编辑器首次加载完成后回调函数
     * @param conf.language 语言
     */
	init : function(conf){
		if(typeof(tinyMCE) === "undefined") return;
		if(conf.language === undefined) conf.language = "zh-cn";
		if(conf.model === undefined) conf.model = 'all';
		
		if(conf.model === "all"){
            tinyMCE.init({
                // General options
                language: conf.language,
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
		}
		else if(conf.model === "simple"){
			tinyMCE.init({
                // General options
                language: conf.language,
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
		}
	}
});