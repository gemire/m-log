<#include "header.ftl" />
<#import "include/fun.ftl" as fun>
		<link rel="stylesheet" type="text/css" href="${template_url}/style/jaw.css">
		<script type="text/javascript" src="${template_url}/script/jaw.js"></script>
		<script type="text/javascript" src="${base}/script/jquery.form.js"></script>
		<div class="row-fluid">
			<#-- <#include "sidebar.ftl" /> -->
			<div class="span12">
				<div class="row-fluid">
					<div>
						<form id="jaw-form" action="${base}/jaw/add" method="POST">
							<div>
								<textarea id="jaw-input" class="jaw-input"></textarea>
							</div>
							<div>
								<div class="pull-left">
									
								</div>
								<div class="pull-right" style="padding-right:15px; padding-top:10px;">
									<input type="button" class="btn" onclick="add();" value="发表" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
<#include "footer.ftl" />