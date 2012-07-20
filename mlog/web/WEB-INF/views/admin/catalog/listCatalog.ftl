<#include "../inc/header.ftl" />
	<script type="text/javascript">
	$(document).ready(function(){
		$('body').layout({
			north__closable:false,
			north__size:62,
			north__resizable:false,
			south__closable:false,
			south__size:50,
			south__resizable:false,
			east__size:250,
			togglerTip_open : "关闭",
			togglerTip_closed : "打开",
			resizerTip:"调整宽度",
			resizerClass: 'ui-state-default',
			//west__spacing_closed:10,
			west__onresize: function (pane, $Pane) {  
                
            }
		});
	});
	
	function checkThisCatalog(_this, cbName){
		var value = mspring.checkThis(_this, cbName);
		if(value){
			
		}
	}
	</script>
	<div class="ui-layout-east">
		<div class="tab">
			<ul>
			    <li><a href="javascript:void(0);" class="here">增加</a></li>
			    <li><a href="javascript:void(0);">修改</a></li>
			</ul>
		</div>
		<@widget.placeholder path="/admin/catalog/create" />
	</div>
	<div class="ui-layout-center">
		<table class="dtable" cellspacing="0" cellpadding="0">
			<tr>
				<th>
					<input type="checkbox" onclick="mspring.checkAll(this, 'id');" />
				</th>
				<#if columnfields??>
					<#list columnfields as field>
						<th>${field.name!""}</th>
					</#list>
				</#if>
			</tr>
			<#if catalogPage??>
				<#list catalogPage.result as item>
					<#assign tdClass = "odd">
					<#if item_index%2 == 0>
						<#assign tdClass = "even">
					</#if>
					<tr>
						<td class="${tdClass}"><input type="checkbox" name="id" value="${item.id}" onclick="checkThisCatalog(this, 'id');" /></td>
						<#if columnfields??>
							<#list columnfields as field>
								<#if field??>
									<td class="${tdClass}">${item['${field.id}']!""} </td>
								<#else>
									<td class="${tdClass}"></td>
								</#if>
							</#list>
						</#if>
					</tr>
				</#list>
			</#if>
		</table>
		<div class="pagger"><span class="disabled"> <  Prev</span><span class="current">1</span><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">7</a>...<a href="#">199</a><a href="#">200</a><a href="#">Next  > </a></div>
	</div>
<#include "../inc/footer.ftl" />