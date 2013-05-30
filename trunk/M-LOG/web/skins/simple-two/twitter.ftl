<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
		<link rel="stylesheet" type="text/css" href="${template_url}/style/twitter.css">
		<script type="text/javascript" src="${template_url}/script/twitter.js"></script>
		<script type="text/javascript" src="${base}/script/jquery.form.js"></script>
		
		<link rel="stylesheet" type="text/css" href="${base}/script/twittereditor/twitterinput.css">
		<script type="text/javascript" src="${base}/script/twittereditor/twitterinput.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				mlog.twitter.editor.init({
					target: $('#content')
				});
				$("#content").ctrlSubmit(function(event) {
					mlog.twitter.add();
				});
				
				mlog.twitter.load();
				$(window).scroll(function(){
					if(isUserAtBottom()){
						mlog.twitter.page = mlog.twitter.page + 1;
						mlog.twitter.load();
					}
				});
				
				function isUserAtBottom() {
  					return ((($(document).height() - $(window).height()) - $(window).scrollTop()) <= 50) ? true : false;
				}
			});
		</script>
		
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<form id="twitter-form" action="${base}/t/add" method="POST">
						<textarea id="content" name="content" style="display:none;"></textarea>
					</form>
				</div>
				<div class="row-fluid" style="min-height:600px;">
					<ul class="twitters" id="twitters">
					</ul>
				</div>
			</div>
		</div>
<#include "footer.ftl" />