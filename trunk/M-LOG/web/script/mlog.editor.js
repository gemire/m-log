/**
 * @author GaoYoubo
 * @since 2012-09-25
 * form元素的操作和处理
 */
if(typeof(mlog) === "undefined"){var mlog = function(){}};

mlog.editor = {};
mlog.editor.map = {}; //用于保存所有editor对象

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
		if(conf.model != 'all' && conf.model != 'simple') conf.model = 'all';
		if(conf.type === undefined) conf.type = 'kindeditor';
		if(conf.model === undefined) conf.model = 'all';
		

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
		mlog.editor.map[conf.id] = mlog.editor.map[conf.id] || editor_ins;
		return editor_ins;
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
                		"link", "unlink", "pagebreak", "|", "emoticons", /*"mlog-uploads", "image", "multiimage",*/ "flash", "media", "code", "fullscreen", "/",
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