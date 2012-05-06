var code = {
	c : 'btnCode', t : '插入代码', s : 'ctrl+6',
	e : function(){
		var _this = this;
		var htmlCode = '';
		htmlCode += '<div>';
		htmlCode += '	<select id="xheCodeType"><option value="html">HTML/XML</option><option value="js">Javascript</option><option value="css">CSS</option><option value="php">PHP</option><option value="java">Java</option><option value="py">Python</option><option value="pl">Perl</option><option value="rb">Ruby</option><option value="cs">C#</option><option value="c">C++/C</option><option value="vb">VB/ASP</option><option value="">其它</option></select>';
		htmlCode += '</div>';
		htmlCode += '<div style="text-align:center;">';
		htmlCode += '	<textarea id="xheCodeValue" wrap="soft" spellcheck="false" style="width:98%;height:220px;" />';
		htmlCode += '</div>';
		htmlCode += '<div style="text-align:right;">';
		htmlCode += '	<input type="button" id="xheSave" value="确定" class="button" />';
		htmlCode += '</div>';
		var jCode=$(htmlCode),jType=$('#xheCodeType',jCode),jValue=$('#xheCodeValue',jCode),jSave=$('#xheSave',jCode);
		jSave.click(function(){
			_this.loadBookmark();
			var code = _this.domEncode(jValue.val());
			if(!code) {
				alert('请输入要插入的代码');
				return false;
			}
			_this.pasteHTML('<pre class="prettyprint lang-'+jType.val()+'">'+code+'</pre>');
			_this.removeModal();
			return false;
		});
		_this.showModal('插入代码', jCode, 500, 300);
	}
}