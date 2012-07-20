<#include "../inc/header.ftl" />
<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
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
	
	$(document).ready(function(){
		var tabs = $(".tab").find("ul li a");
		$(tabs).each(function(i){
			var id = $(this).attr("id");
			var _class = $(this).attr("class");
			
			//如果是一个叶签项
			if(id && id.endWith("-tab")){
				var viewId = id.substring(0, id.lastIndexOf("-tab")) + "-view"
				var view = document.getElementById(viewId);
				if(_class != "here" && view){ //不过不是当前叶签,且当前叶签对应的面板存在, 那么隐藏该面板
					view.style.display = "none";
				}
			}
		});
	});
	</script>
	<div class="ui-layout-east">
		<div class="tab">
			<ul>
			    <li><a href="javascript:void(0);" id="add-tab" class="here">增加</a></li>
			    <li><a href="javascript:void(0);" id="modify-tab">修改</a></li>
			</ul>
		</div>
		<div id="add-view">
			<@widget.placeholder path="/admin/catalog/create" />
		</div>
		<div id="modify-view">
			asdfasdfsadf
		</div>
	</div>
	<div class="ui-layout-center">
		<form id="catalogForm" name="catalogForm" action="${base}/admin/catalog/list" method="POST">
			<@spring.bind "catalogPage" />
			<!-- pagination parameter -->
			<@spring.formHiddenInput path="catalogPage.pageNo" />
			<@spring.formHiddenInput path="catalogPage.totalPages" />
			<@spring.formHiddenInput path="catalogPage.totalCount" />
			<!-- sorter parameter -->
			<@spring.formHiddenInput path="catalogPage.sortEnable" />
			<@spring.formHiddenInput path="catalogPage.sort.field" />
			<@spring.formHiddenInput path="catalogPage.sort.order" />
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
			<table style="width:100%;">
				<tr>
					<td>
						<input type="button" class="btn" value=" 删除 " onclick="mspring.confirmDelete('catalogForm', '${base}/admin/catalog/delete');" />
					</td>
					<td>
						<@mspring.pagingnavigator page=catalogPage form_id="catalogForm" />
					</td>
				</tr>
			</table>
		</form>
	</div>
<#include "../inc/footer.ftl" />