

CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn';   // Default Value: true;
	
	config.defaultLanguage = 'zh-cn';  // Default Value: 'en'
	config.skin = 'v2';
	config.theme = 'default'; // Default Value: 'default'
	config.baseHref = '';   // Default Value: ''; Other Value: http://www.example.com/path/
	config.toolbar = 'Nor'; // Default Value: 'Full'; Other Value: Basic
	config.toolbar_Basic =
	[
	    [ 'Source', '-', 'Bold', 'Italic' ]
	];
	config.toolbar_Full =
	[
	    ['Source','-','Save','NewPage','Preview','-','Templates'],
	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	    '/',
	    ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField','-','syntaxhighlight'],
	    '/',
	    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	    ['Link','Unlink','Anchor'],
	    ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	    '/',
	    ['Styles','Format','Font','FontSize'],
	    ['TextColor','BGColor'],
	    ['Maximize', 'ShowBlocks','-','About']
	];
	
	config.toolbar_Nor = [
		['Source','-','Save','NewPage','Preview','-','Templates'],
	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	    '/',
	    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	    ['Link','Unlink','Anchor'],
	    ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	    '/',
	    ['Styles','Format','Font','FontSize'],
	    ['TextColor','BGColor'],
	    ['Maximize', 'ShowBlocks','syntaxhighlight']
	];
	
	
	
	//扩展插件: SyntaxHighlight语法高亮代码,插入HTML代码,插入文件图标,歌词同步播放,Hideslide特效
	config.extraPlugins='syntaxhighlight'; 
	
	
	/***
	//CKFinder目录路径地址:
	var CKFinderPath = CKEDITOR.basePath + 'ckfinder/';
	config.filebrowserBrowseUrl = CKFinderPath + 'ckfinder.html';
	config.filebrowserImageBrowseUrl = CKFinderPath + 'ckfinder.html?Type=Image';
	config.filebrowserFlashBrowseUrl = CKFinderPath + 'ckfinder.html?Type=Flash';
	config.filebrowserMediaBrowseUrl = CKFinderPath + 'ckfinder.html?Type=Media';
	config.filebrowserUploadUrl = CKFinderPath + 'core/connector/php/connector.php?command=QuickUpload&type=File';
	config.filebrowserImageUploadUrl = CKFinderPath + 'core/connector/php/connector.php?command=QuickUpload&type=Image';
	config.filebrowserFlashUploadUrl = CKFinderPath + 'core/connector/php/connector.php?command=QuickUpload&type=Flash';
	config.filebrowserMediaUploadUrl = CKFinderPath + 'core/connector/php/connector.php?command=QuickUpload&type=Media';
	
	config.filebrowserWindowWidth = '640';
	config.filebrowserWindowHeight = '480';
	config.filebrowserImageWindowWidth = '640';
	config.filebrowserImageWindowHeight = '480';
	***/
	
	config.docType = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">';
	
	
	config.fullPage = false; // Default Value: false;
	
	
	config.autoUpdateElement = true;  // Default Value: true
	
	
	config.baseFloatZIndex = 10000;   // Default Value: 10000; Example Value: 2000
	
	config.undoStackSize = 20;  // Default Value: 20;
	
	config.contentsLangDirection = 'ltr';  // Default Value: 'ltr'
	
	
	config.startupFocus = true;   // Default Value: false;
	
	
	config.startupMode = 'wysiwyg';    // Default Value: 'wysiwyg'; Other Value: 'source';
	
	config.startupOutlineBlocks = false;    // Default Value: false;
	
	config.toolbarCanCollapse = true;  // Default Value: true;
	
	/*
	The "theme space" to which rendering the toolbar. For the default theme, the recommended options are "top" and "bottom". 
	Defined in: plugins/toolbar/plugin.js.
	See: CKEDITOR.config.theme
	*/
	config.toolbarLocation = 'top';  // Default Value: 'top'; Other Value: 'bottom'
	
	/*
	Whether the toolbar must start expanded when the editor is loaded. 
	Defined in: plugins/toolbar/plugin.js.
	*/
	config.toolbarStartupExpanded = true;  // Default Value: true;
	
	
	
	
	/*
	Whether to render or not the editing block area in the editor interface. 
	Defined in: plugins/editingblock/plugin.js.
	*/
	config.editingBlock = true;    // Default Value: true;
	
	
	/*
	Whether to use HTML entities in the output. 
	Defined in: plugins/entities/plugin.js.
	*/
	config.entities = true;    // Default Value: true;
	
	/*
	An additional list of entities to be used. It's a string containing each entry separated by a comma. Entities names or number must be used, exclusing the "&" preffix and the ";" termination. 
	Defined in: plugins/entities/plugin.js.
	config.entities_additional = '#39'; // Default Value: '#39' // The single quote (') character.
	*/
	
	/*
	Whether to convert some symbols, mathematical symbols, and Greek letters to HTML entities. This may be more relevant for users typing text written in Greek. The list of entities can be found at the W3C HTML 4.01 Specification, section 24.3.1. 
	Defined in: plugins/entities/plugin.js.
	*/
	config.entities_greek = true;   // Default Value: true;
	
	
	/*
	<static> {Boolean} CKEDITOR.config.entities_latin Since: 3.0
	Whether to convert some Latin characters (Latin alphabet No. 1, ISO 8859-1) to HTML entities. The list of entities can be found at the W3C HTML 4.01 Specification, section 24.2.1. 
	Defined in: plugins/entities/plugin.js.
	*/
	config.entities_latin = true;   // Default Value: true;
	
	
	/*
	Whether to convert all remaining characters, not comprised in the ASCII character table, to their relative numeric representation of HTML entity. For example, the phrase "This is Chinese: 汉语." is outputted as "This is Chinese: &#27721;&#35821;." 
	Defined in: plugins/entities/plugin.js.
	*/
	config.entities_processNumerical = false;  // Default Value: false;
	
	
	
	// The editor tabindex value.
	config.tabIndex = 0;    // Default Value: 0;
	
	/*
	Intructs the editor to add a number of spaces (&nbsp;) to the text when hitting the TAB key. If set to zero, the TAB key will be used to move the cursor focus to the next element in the page, out of the editor focus. 
	Defined in: plugins/tab/plugin.js.
	*/
	config.tabSpaces = 4;    // Default Value: 0;
	
	
	
	/*
	Sets the behavior for the ENTER key. It also dictates other behaviour rules in the editor, like whether the <br> element is to be used as a paragraph separator when indenting text. The allowed values are the following constants, and their relative behavior:
	CKEDITOR.ENTER_P (1): new <p> paragraphs are created;
	CKEDITOR.ENTER_BR (2): lines are broken with <br> elements;
	CKEDITOR.ENTER_DIV (3): new <div> blocks are created.
	Note: It's recommended to use the CKEDITOR.ENTER_P value because of its semantic value and correctness. The editor is optimized for this value.
	*/
	// Not recommended.
	config.enterMode = CKEDITOR.ENTER_P;
	
	/*
	Just like the CKEDITOR.config.enterMode setting, it defines the behavior for the SHIFT+ENTER key. The allowed values are the following constants, and their relative behavior:
	CKEDITOR.ENTER_P (1): new <p> paragraphs are created;
	CKEDITOR.ENTER_BR (2): lines are broken with <br> elements;
	CKEDITOR.ENTER_DIV (3): new <div> blocks are created.
	*/
	config.shiftEnterMode = CKEDITOR.ENTER_BR;
	
	
	
	/*
	Whether the editor must output an empty value ("") if it's contents is made by an empty paragraph only. 
	Defined in: plugins/wysiwygarea/plugin.js.
	*/
	config.ignoreEmptyParagraph = true;  // Default Value: true;
	
	
	/*
	Whether to remove links when emptying the link URL field in the image dialog. 
	Defined in: plugins/image/plugin.js.
	*/
	config.image_removeLinkByEmptyURL = true;  // Default Value: true;
	
	
	
	
	/*
	Whether to enable the resizing feature. If disabed the resize handler will not be visible. 
	Defined in: plugins/resize/plugin.js.
	*/
	config.resize_enabled = false;   // Default Value: true;
	
	/* Defined in: plugins/resize/plugin.js. */
	//The maximum editor height, in pixels, when resizing it with the resize handle. 
	config.resize_maxHeight = 600; //Default Value: 3000;
	//The maximum editor width, in pixels, when resizing it with the resize handle. 
	config.resize_maxWidth = 750; //Default Value: 3000;
	// The minimum editor height, in pixels, when resizing it with the resize handle. 
	config.resize_minHeight = 600;  //Default Value: 250;
	// The minimum editor width, in pixels, when resizing it with the resize handle. 
	config.resize_minWidth = 500;   //Default Value: 750;
	
	
	/*
	A comma separated list of items group names to be displayed in the context menu. The items order will reflect the order in this list if no priority has been definted in the groups. 
	Defined in: plugins/menu/plugin.js.
	*/
	//config.menu_groups = 'clipboard,table,anchor,link,image';
	config.menu_groups = 'clipboard,form,tablecell,tablecellproperties,tablerow,tablecolumn,table,anchor,link,image,flash,checkbox,radio,textfield,hiddenfield,imagebutton,button,select,textarea';
	
	/*
	The amount of time, in milliseconds, the editor waits before showing submenu options when moving the mouse over options that contains submenus, like the "Cell Properties" entry for tables. 
	Defined in: plugins/menu/plugin.js.
	// Remove the submenu delay.
	config.menu_subMenuDelay = 0;
	*/
	config.menu_subMenuDelay = 400; // Default Value: 400;
	
	
	
	/*
	The "styles definition set" to load into the styles combo. The styles may be defined in the page containing the editor, or can be loaded on demand from an external file when opening the styles combo for the fist time. In the second case, if this setting contains only a name, the styles definition file will be loaded from the "styles" folder inside the stylescombo plugin folder. Otherwise, this setting has the "name:url" syntax, making it possible to set the URL from which loading the styles file. 
	Defined in: plugins/stylescombo/plugin.js.
	*/
	//config.stylesCombo_stylesSet = 'mystyles'; // Load from the stylescombo styles folder (mystyles.js file).
	//config.stylesCombo_stylesSet = 'mystyles:/editorstyles/styles.js'; // Load from a relative URL.
	//config.stylesCombo_stylesSet = 'mystyles:http://www.example.com/editorstyles/styles.js'; // Load from a full URL.
	config.stylesCombo_stylesSet = 'default';
	
	
	/*
	The templates definition set to use. It accepts a list of names separated by comma. It must match definitions loaded with the templates_files setting. 
	Defined in: plugins/templates/plugin.js.
	*/
	config.templates = 'default';
	
	/*
	The list of templates definition files to load. 
	Defined in: plugins/templates/plugin.js.
	config.templates_files =
	    [
	        '/editor_templates/site_default.js',
	        'http://www.example.com/user_templates.js'
	    ];
	*/
	config.templates_files = [ 'plugins/templates/templates/default.js' ];
	
	/*
	Whether the "Replace actual contents" checkbox is checked by default in the Templates dialog. 
	Defined in: plugins/templates/plugin.js.
	*/
	config.templates_replaceContent = true;    // Default Value: true;
	
	
	
	
	// Holds the style definition to be used to apply the text background color. 
	config.colorButton_backStyle =
	    {
	        element : 'span',
	        styles : { 'background-color' : '#(color)' }
	    };
	
	
	/*
	Defines the colors to be displayed in the color selectors. It's a string containing the hexadecimal notation for HTML colors, without the "#" prefix. 
	*/
	// Brazil colors only.
	config.colorButton_colors = '000,800000,8B4513,2F4F4F,008080,000080,4B0082,696969,B22222,A52A2A,DAA520,006400,40E0D0,0000CD,800080,808080,F00,FF8C00,FFD700,008000,0FF,00F,EE82EE,A9A9A9,FFA07A,FFA500,FFFF00,00FF00,AFEEEE,ADD8E6,DDA0DD,D3D3D3,FFF0F5,FAEBD7,FFFFE0,F0FFF0,F0FFFF,F0F8FF,E6E6FA,FFF';
	
	
	// Whether to enable the "More Colors..." button in the color selectors. 
	config.colorButton_enableMore = false;
	
	
	// Holds the style definition to be used to apply the text foreground color. 
	config.colorButton_foreStyle =
	    {
	        element : 'span',
	        styles : { 'color' : '#(color)' }
	    };
	
	config.font_defaultLabel = 'Arial';
	
	
	config.font_names = '宋体;楷体_GB2312;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana;' ;
	
	
	
	config.font_style =
	    {
	        element		: 'span',
	        styles		: { 'font-family' : '#(family)' },
	        overrides	: [ { element : 'font', attributes : { 'face' : null } } ]
	    };
	
	

	config.fontSize_defaultLabel = '12px';
	
	
	config.fontSize_sizes = '8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;72/72px';
	
	
	config.fontSize_style =
	    {
	        element		: 'span',
	        styles		: { 'font-size' : '#(size)' },
	        overrides	: [ { element : 'font', attributes : { 'size' : null } } ]
	    };
	
	
	// The color of the dialog background cover. It should be a valid CSS color string. 
	config.dialog_backgroundCoverColor = 'white';  // Default Value: 'white';Other Value: rgb(255, 254, 253);
	
	// The opacity of the dialog background cover. It should be a number within the range [0.0, 1.0]. 
	config.dialog_backgroundCoverOpacity = 0.5; // Default Value: 0.5; Other Value: 0.7;
	
	// The distance of magnetic borders used in moving and resizing dialogs, measured in pixels. 
	config.dialog_magnetDistance = 20;  // Default Value: 20; Other Value: 30;
	
	
	/*
	Disables the built-in spell checker while typing natively available in the browser (currently Firefox and Safari only).
	
	Even if word suggestions will not appear in the CKEditor context menu, this feature is useful to help quickly identifying misspelled words.
	
	This setting is currently compatible with Firefox only due to limitations in other browsers. 
	Defined in: plugins/wysiwygarea/plugin.js.
	*/
	config.disableNativeSpellChecker = true;  // Default Value: true;
	
	/*
	Disables the "table tools" offered natively by the browser (currently Firefox only) to make quick table editing operations, like adding or deleting rows and columns. 
	Defined in: plugins/wysiwygarea/plugin.js.
	*/
	config.disableNativeTableHandles = true;  // Default Value: true;
	
	
	/*
	Disables the ability of resize objects (image and tables) in the editing area. 
	Defined in: plugins/wysiwygarea/plugin.js.
	*/
	config.disableObjectResizing = false;  // Default Value: false;
	
	
	
	/*
	Whether to force all pasting operations to insert on plain text into the editor, loosing any formatting information possibly available in the source text. 
	Defined in: plugins/pastetext/plugin.js.
	*/
	config.forcePasteAsPlainText = false;  // Default Value: false;
	
	
	/*
	Whether to force using "&" instead of "&amp;" in elements attributes values. It's not recommended to change this setting for compliance with the W3C XHTML 1.0 standards (C.12, XHTML 1.0). 
	Defined in: plugins/htmldataprocessor/plugin.js.
	*/
	config.forceSimpleAmpersand = false;  // Default Value: false;
	
	
	
	/* Defined in: plugins/format/plugin.js. */
	/***
	// The style definition to be used to apply the "Address" format. 
	config.format_address = { element : 'address', attributes : { class : 'styledAddress' } };
	// The style definition to be used to apply the "Normal (DIV)" format. 
	config.format_div = { element : 'div', attributes : { class : 'normalDiv' } };
	// The style definition to be used to apply the "Heading 1" format. 
	config.format_h1 = { element : 'h1', attributes : { class : 'contentTitle1' } };
	config.format_h2 = { element : 'h2', attributes : { class : 'contentTitle2' } };
	config.format_h3 = { element : 'h3', attributes : { class : 'contentTitle3' } };
	config.format_h4 = { element : 'h4', attributes : { class : 'contentTitle4' } };
	config.format_h5 = { element : 'h5', attributes : { class : 'contentTitle5' } };
	config.format_h6 = { element : 'h6', attributes : { class : 'contentTitle6' } };
	// The style definition to be used to apply the "Normal" format. 
	config.format_p = { element : 'p', attributes : { class : 'normalPara' } };
	// The style definition to be used to apply the "Formatted" format. 
	config.format_pre = { element : 'pre', attributes : { class : 'code' } };
	***/
	
	/*
	A list of semi colon separated style names (by default tags) representing the style definition for each entry to be displayed in the Format combo in the toolbar. Each entry must have its relative definition configuration in a setting named "format_(tagName)". For example, the "p" entry has its definition taken from config.format_p. 
	Defined in: plugins/format/plugin.js.
	*/
	config.format_tags = 'p;h1;h2;h3;h4;h5;h6;pre;address;div'; // Default Value: 'p;h1;h2;h3;h4;h5;h6;pre;address;div';
	
	
	
	/*
	The HTML to load in the editor when the "new page" command is executed. 
	Defined in: plugins/newpage/plugin.js.
	*/
	// config.newpage_html = '<p>Type your text here.</p>';
	config.newpage_html = ''; // Default Value: '';
	
	
	/*
	Whether the "Ignore font face definitions" checkbox is enabled by default in the Paste from Word dialog. 
	Defined in: plugins/pastefromword/plugin.js.
	*/
	config.pasteFromWordIgnoreFontFace = true;  // Default Value: true;
	
	/*
	Whether to keep structure markup (<h1>, <h2>, etc.) or replace it with elements that create more similar pasting results when pasting content from Microsoft Word into the Paste from Word dialog. 
	Defined in: plugins/pastefromword/plugin.js.
	*/
	config.pasteFromWordKeepsStructure = false;  // Default Value: false;
	
	/*
	Whether the "Remove styles definitions" checkbox is enabled by default in the Paste from Word dialog. 
	Defined in: plugins/pastefromword/plugin.js.
	*/
	config.pasteFromWordRemoveStyle = false;  // Default Value: false;
	
	
	
	/*
	Comma separated list of plugins to load and initialize for an editor instance. This should be rarely changed, using instead the CKEDITOR.config.extraPlugins and CKEDITOR.config.removePlugins for customizations.
	<static> {Array} CKEDITOR.config.protectedSource Since: 3.0
	List of regular expressions to be executed over the input HTML, indicating code that must stay untouched.
	Default Value: [] (empty array)
	*/
	//config.protectedSource.push( /<\?[\s\S]*?\?>/g );   // PHP Code
	//config.protectedSource.push( / /g );   // ASP Code
	//config.protectedSource.push( /(]+>[\s|\S]*?<\/asp:[^\>]+>)|(]+\/>)/gi );   // ASP.Net Code
	
	
	
	/*
	A comma separated list of elements attributes to be removed when executing the "remove format" command. 
	Defined in: plugins/removeformat/plugin.js.
	*/
	config.removeFormatAttributes = 'class,style,lang,width,height,align,hspace,valign';
	
	/*
	A comma separated list of elements to be removed when executing the "remove " format" command. Note that only inline elements are allowed. 
	Defined in: plugins/removeformat/plugin.js.
	*/
	config.removeFormatTags = 'b,big,code,del,dfn,em,font,i,ins,kbd,q,samp,small,span,strike,strong,sub,sup,tt,u,var';
	
	
	/*
	Defines the style to be used to highlight results with the find dialog. 
	Defined in: plugins/find/plugin.js.
	// Highlight search results with blue on yellow.
	*/
	config.find_highlight =
	    {
	        element : 'span',
	        styles : { 'background-color' : '#ff0', 'color' : '#00f' }
	    };
	
	
	/*
	A list of keystrokes to be blocked if not defined in the CKEDITOR.config.keystrokes setting. In this way it is possible to block the default browser behavior for those keystrokes. 
	*/
	config.blockedKeystrokes =
	[
	    CKEDITOR.CTRL + 66 /*B*/,
	    CKEDITOR.CTRL + 73 /*I*/,
	    CKEDITOR.CTRL + 85 /*U*/
	];
	
	/*
	A list associating keystrokes to editor commands. Each element in the list is an array where the first item is the keystroke, and the second is the name of the command to be executed. 
	Defined in: plugins/keystrokes/plugin.js.
	*/
	config.keystrokes =
	[
	    [ CKEDITOR.ALT + 121 /*F10*/, 'toolbarFocus' ],
	    [ CKEDITOR.ALT + 122 /*F11*/, 'elementsPathFocus' ],
	
	    [ CKEDITOR.SHIFT + 121 /*F10*/, 'contextMenu' ],
	
	    [ CKEDITOR.CTRL + 90 /*Z*/, 'undo' ],
	    [ CKEDITOR.CTRL + 89 /*Y*/, 'redo' ],
	    [ CKEDITOR.CTRL + CKEDITOR.SHIFT + 90 /*Z*/, 'redo' ],
	
	    [ CKEDITOR.CTRL + 76 /*L*/, 'link' ],
	
	    [ CKEDITOR.CTRL + 66 /*B*/, 'bold' ],
	    [ CKEDITOR.CTRL + 73 /*I*/, 'italic' ],
	    [ CKEDITOR.CTRL + 85 /*U*/, 'underline' ],
	
	    [ CKEDITOR.ALT + 109 /*-*/, 'toolbarCollapse' ]
	];
	
	/***
	config.smiley_path =  CKEDITOR.basePath + '/plugins/smiley/images/';
	
	config.smiley_images = [
		'regular_smile.gif','sad_smile.gif','wink_smile.gif','teeth_smile.gif','confused_smile.gif','tounge_smile.gif',
		'embaressed_smile.gif','omg_smile.gif','whatchutalkingabout_smile.gif','angry_smile.gif','angel_smile.gif',
		'shades_smile.gif','devil_smile.gif','cry_smile.gif','lightbulb.gif','thumbs_down.gif','thumbs_up.gif',
		'heart.gif','broken_heart.gif','kiss.gif','envelope.gif'];
	
	config.smiley_descriptions = [
	    ':)', ':(', ';)', ':D', ':/', ':P',
	    '', '', '', '', '', '',
	    '', ';(', '', '', '', '',
	    '', ':kiss', '' ];
	***/
	
	/*
	The base path used to build the URL for the smiley images. It must end with a slash. 
	Defined in: plugins/smiley/plugin.js.
	*/
	//config.smiley_path = 'http://www.example.com/images/smileys/';
	//config.smiley_path = '/images/smileys/';
	 // Default Value: CKEDITOR.basePath + 'plugins/smiley/images/';
	config.smiley_path =  CKEDITOR.basePath + 'plugins/smiley/images/qq2007/';
	
	/*
	The file names for the smileys to be displayed. These files must be contained inside the URL path defined with the CKEDITOR.config.smiley_path setting. 
	Defined in: plugins/smiley/plugin.js.
	*/
	config.smiley_images = ['0.gif','1.gif','2.gif','3.gif','4.gif','5.gif','6.gif','7.gif','8.gif','9.gif','10.gif',
	'11.gif','12.gif','13.gif','14.gif','15.gif','16.gif','17.gif','18.gif','19.gif','20.gif','21.gif','22.gif',
	'23.gif','24.gif','25.gif','26.gif','27.gif','28.gif','29.gif','30.gif','31.gif','32.gif','33.gif','34.gif',
	'35.gif','36.gif','37.gif','38.gif','39.gif','40.gif','41.gif','42.gif','43.gif','44.gif','45.gif','46.gif',
	'47.gif','48.gif','49.gif','50.gif','51.gif','52.gif','53.gif','54.gif','55.gif','56.gif','57.gif','58.gif',
	'59.gif','60.gif','61.gif','62.gif','63.gif','64.gif','65.gif','66.gif','67.gif','68.gif','69.gif','70.gif',
	'71.gif','72.gif','73.gif','74.gif','75.gif','76.gif','77.gif','78.gif','79.gif','80.gif','81.gif','82.gif',
	'83.gif','84.gif','85.gif','86.gif','87.gif','88.gif','89.gif'];
	
	/*
	The description to be used for each of the smileys defined in the CKEDITOR.config.smiley_images setting. Each entry in this array list must match its relative pair in the CKEDITOR.config.smiley_images setting. 
	Defined in: plugins/smiley/plugin.js.
	*/
	// This is actually the default value.
	config.smiley_descriptions = [
	    '', '', '', '', '', '','', '', '','','', '', '', '', '', '','', '', '','',
	    '', '', '', '', '', '','', '', '','','', '', '', '', '', '','', '', '','',
	    '', '', '', '', '', '','', '', '','','', '', '', '', '', '','', '', '','',
	    '', '', '', '', '', '','', '', '','','', '', '', '', '', '','', '', '','',
		'', '', '', '', '', '','', '', '',''];
	
	};