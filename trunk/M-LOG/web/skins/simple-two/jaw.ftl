<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
		<link rel="stylesheet" type="text/css" href="${template_url}/style/jaw.css">
		<script type="text/javascript" src="${template_url}/script/jaw.js"></script>
		<script type="text/javascript" src="${base}/script/jquery.form.js"></script>
		
		<link rel="stylesheet" type="text/css" href="${base}/script/jaweditor/jawinput.css">
		<script type="text/javascript" src="${base}/script/jaweditor/jawinput.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				mlog.jaw.editor.init({
					target: $('#content')
				});
				$("#content").ctrlSubmit(function(event) {
					mlog.jaw.add();
				});
				mlog.jaw.load();
			});
		</script>
		
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<form id="jaw-form" action="${base}/jaw/add" method="POST">
						<textarea id="content" name="content" style="display:none;"></textarea>
					</form>
				</div>
				<div class="row-fluid">
					<ul class="jaws" id="jaws">
					</ul>
				</div>
			</div>
		</div>
<#include "footer.ftl" />