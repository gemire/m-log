<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "header.ftl" />
	<div style="text-align:center;">
		<form method="get" id="searchform">
			<input type="text" name="keyword" id="edtSearch" style="width:300px;" value="${searchKeyword!""}" />
	    	<input type="submit" id="btnPost" name="btnPost" value="" />
		</form>
	</div>
	<#include "post-multi.ftl" />
<#include "footer.ftl" />