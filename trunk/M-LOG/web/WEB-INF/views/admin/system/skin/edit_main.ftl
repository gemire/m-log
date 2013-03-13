<#include "../../inc/simpleheader.ftl" />
<link rel="stylesheet" type="text/css" href="${base}/script/codemirror/lib/codemirror.css">
<#--<link rel="stylesheet" type="text/css" href="${base}/script/codemirror/theme/eclipse.css">-->
<script type="text/javascript" src="${base}/script/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${base}/script/codemirror/lib/util/loadmode.js"></script>
<style type="text/css">
	.CodeMirror {border: 1px solid #cccccc; margin-bottom:10px;}
	.CodeMirror, .CodeMirror-scroll {height:450px;}
	.activeline {background: #e8f2ff !important;}
</style>
<form action="${base}/admin/system/skin/edit/save" method="POST">
	<input type="hidden" id="skin" name="skin" value="${skin}"/>
	<input type="hidden" id="path" name="path" value="${path}"/>
	<textarea id="code" name="content">${content!""}</textarea>
	<input type="submit" class="btn" value=" 保存修改 " />
	<input type="button" class="btn" value=" 重新加载 " onclick="location.reload();" />
</form>
<script type="text/javascript">
	CodeMirror.modeURL = "${base}/script/codemirror/mode/%N/%N.js";
	var textarea = document.getElementById("code");
	var editor = CodeMirror.fromTextArea(textarea, {
		matchBrackets: true,
		tabMode: 'indent',
		lineNumbers: true,
  		lineWrapping: true,
  		onCursorActivity: function() {
    		editor.setLineClass(hlLine, null, null);
    		hlLine = editor.setLineClass(editor.getCursor().line, null, "activeline");
  		},
		onChange: function() {
			
		}
	});
	var hlLine = editor.setLineClass(0, "activeline");
	var mode = getMode();
	editor.setOption('mode', mode);
	CodeMirror.autoLoadMode(editor, mode);
	
	function getMode(){
		var codeFile = $('#path').val();
		if(codeFile.endWith('ftl')){
			return 'htmlembedded';
		}
		else if(codeFile.endWith('js')){
			return 'javascript';
		}
		else if(codeFile.endWith('css')){
			return 'css';
		}
		else if(codeFile.endWith('htm')){
			return 'htmlembedded';
		}
		else if(codeFile.endWith('html')){
			return 'htmlembedded';
		}
	}
</script>
<#include "../../inc/simplefooter.ftl" />