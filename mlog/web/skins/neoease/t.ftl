<#include "header.ftl" />
<div class="body">
    <div class="wrapper">
        <div class="main">
        	<form action="${base}/t/add" method="POST" style="text-align:center;">
				<input type="text" name="content" class="inputtext" style="width:300px;" />
				<input type="submit" value=' 发表 ' class="button"/>
			</form>
        </div>
        <#list weibos as weibo>
	        <div class="article">
	        	${weibo.text !""}<br/>
	        </div>
        </#list>
        <#include "sidebar.ftl">
        <div class="clear"></div>
    </div>
</div>
<#include "footer.ftl" />