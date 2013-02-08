		<script type="text/javascript">
			var navs =[
				<#list navs as nav>
					<#if nav_has_next>
						{id:'${nav.id}', name:'${nav.name}', parent:'<#if nav.parent?exists>${nav.parent.id!""}</#if>', deep:'${nav.deep!"1"}', hasChild:${nav.hasChild?string("true", "false")}},
					<#else>
						{id:'${nav.id}', name:'${nav.name}', parent:'<#if nav.parent?exists>${nav.parent.id!""}</#if>', deep:'${nav.deep!"1"}', hasChild:${nav.hasChild?string("true", "false")}}
					</#if>
				</#list>
			];
			
			$(document).ready(function(){
				var navHtml = '';
				for(var i = 0; i < navs.length; i++){
					//navHtml += '<ul class="nav">';
					if(navs[i].deep == 1){
						var url = '${blogurl}/catalog/catalog-' + navs[i].id + '.html';
						if(navs[i].hasChild){
							navHtml += '<li class="dropdown">';
							navHtml += '<a href="' + url + '" class="dropdown-toggle" data-toggle="dropdown">' + navs[i].name + ' <b class="caret"></b></a>';
							nav(navs[i]);
							navHtml += '</li>';
						}
						else {
							navHtml += '<li><a href="' + url + '">' + navs[i].name + '</a></li>';
						}
					}
					//navHtml += '</ul>';
				}
				$('#nav-top.nav').html(navHtml);
				
				function nav(parent){
					if(parent.hasChild){
						navHtml += '<ul class="dropdown-menu">';
					}
					else {
						navHtml += '<ul>';
					}
					for(var i = 0; i < navs.length; i++){
						if(navs[i].parent == parent.id){
							var url = '${blogurl}/catalog/catalog-' + navs[i].id + '.html';
							if(navs[i].hasChild){
								navHtml += '<li class="dropdown-submenu">';
								navHtml += '<a href="' + url + '" class="dropdown-toggle" data-toggle="dropdown">' + navs[i].name + ' <b class="caret"></b></a>';
								nav(navs[i]);
								navHtml += '</li>';
							}
							else {
								navHtml += '<li><a href="' + url + '">' + navs[i].name + '</a></li>';
							}
						}
					}
					navHtml += '</ul>';
				}
			});
			
		</script>
		
		<div class="navbar">
		    <div class="navbar-inner">
		      	<div class="container">
		        	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		          		<span class="icon-bar"></span>
		          		<span class="icon-bar"></span>
		          		<span class="icon-bar"></span>
		        	</a>
		        	<#--<a class="brand" href="#">项目名称</a>-->
		        	<div class="nav-collapse">
		        		<ul id="nav-top" class="nav">
		        			
		        		</ul>
		        		<form class="navbar-search pull-right" action="">
		            		<input type="text" class="search-query span2" placeholder="搜索">
		          		</form>
		        	</div>
		      	</div>
	    	</div>
		</div>