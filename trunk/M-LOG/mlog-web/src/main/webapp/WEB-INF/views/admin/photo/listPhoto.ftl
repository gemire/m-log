<#import "/META-INF/spring.ftl" as spring />
<#import "/META-INF/mspring.ftl" as mspring />
<#include "../inc/header.ftl" />
	<script type="text/javascript">
		$(document).ready(function(){
		
			turnHighLight(120020);
			
			$("#albumSelect").change(function(){
				location.href = "${base}/admin/photo/list?album.id=" + $("#albumSelect").val(); 
			});
			
			$("#btnReload").click(function(){
				location.href = "${base}/admin/photo/list?album.id=${album.id}";
			});
			
			//批量管理
			$("#btnPl").click(function(){
				var arr = document.getElementsByName('id');
				for(var i = 0; i < arr.length; i++){
					arr[i].style.display = "inline";
				}
				$('#btnPl').css('display', 'none');
				$('#btnQx').css('display', 'inline');
				$('#btnDel').css('display', 'inline');
				$('#btnSelAll').css('display', 'inline');
			});
			
			//取消批量管理
			$("#btnQx").click(function(){
				var arr = document.getElementsByName('id');
				for(var i = 0; i < arr.length; i++){
					arr[i].checked = "";
					arr[i].style.display = "none";
				}
				$('#btnPl').css('display', 'inline');
				$('#btnQx').css('display', 'none');
				$('#btnDel').css('display', 'none');
				$('#btnSelAll').css('display', 'none');
			});
			
			//批量删除
			$("#btnDel").click(function(){
				var flag = true;
				var arr = document.getElementsByName('id');
				for(var i = 0; i < arr.length; i++){
					if(arr[i].checked){
						flag = false;
						break;
					}
				}
				if(flag){
					alert("请选择要删除的图片!");
					return;
				}
				mlog.form.confirmSubmit('photoForm', '${base}/admin/photo/delete', '确认要删除选中的图片吗？');
			});
			
			//全选
			$("#btnSelAll").click(function(){
				var arr = document.getElementsByName('id');
				for(var i = 0; i < arr.length; i++){
					if(arr[i].checked){
						arr[i].checked = "";
					}
					else {
						arr[i].checked = "checked";
					}
				}
			});
		});
		
		function showPic(id){
			var url = document.getElementById("url_" + id).value;
			$.dialog({
			    title: '查看照片',
			    lock: true,
			    content: '<img src="' + url + '" style="max-height:500px;" />',
			    padding: 0
			});
		}
		
		function setCover(albumId, photoId){
			$.get('${base}/admin/photo/cover?albumId=' + albumId + '&photoId=' + photoId, function(response){
				if(response == 'true'){
					alert('设置封面成功');
				}
				else{
					alert('设置封面失败');
				}
			});
		}
	</script>
	<table class="formtable">
		<tr>
			<td>当前相册：
				<select id="albumSelect">
					<#if albums?exists>
						<#list albums as a>
							<option value="${a.id}" <#if a.id == album.id>selected="selected"</#if> >${a.name}</option>
						</#list>
					</#if>
				</select>
				<button class="btn" id="btnReload">重新加载图片</button>
				<button class="btn" id="btnPl" style="color:red;">批量管理</button>
				<button class="btn" id="btnQx" style="display:none; color:red;">取消批量管理</button>
				<button class="btn" id="btnSelAll" style="display:none;">全选/反选</button>
				<button class="btn" id="btnDel" style="display:none;">批量删除</button>
				
			</td>
			<td></td>
		</tr>
	</table>
	<form id="photoForm" name="photoForm" action="${base}/admin/photo/list" method="POST">
		<@spring.bind "photo" />
		<@spring.formHiddenInput path="photo.album.id" />
		
		<@spring.bind "photoPage" />
		<!-- pagination parameter -->
		<@spring.formHiddenInput path="photoPage.pageNo" />
		<@spring.formHiddenInput path="photoPage.totalPages" />
		<@spring.formHiddenInput path="photoPage.totalCount" />
		<!-- sorter parameter -->
		<@spring.formHiddenInput path="photoPage.sortEnable" />
		<@spring.formHiddenInput path="photoPage.sort.field" />
		<@spring.formHiddenInput path="photoPage.sort.order" />
		
		<#if (photoPage?exists && photoPage.result?size > 0)>
			<div id="wall">
				<#list photoPage.result as photo>
					<div class="item">
						<div class="itemdiv">
							<img src="${base}${photo.previewUrl}" alt="${photo.description!photo.name}" class="itemimage" onclick="showPic(${photo.id});" />
						</div>
						<div class="itemmeta">
							<input type="checkbox" name="id" value="${photo.id}" style="display:none;" />
							<input type="hidden" id="url_${photo.id}" value="${base}${photo.url}"/>
							<a href="${base}/admin/photo/edit?id=${photo.id}" class="ctrl">编辑</a>
							<a href="javascript:mlog.form.confirmSubmit('photoForm','${base}/admin/photo/delete?id=${photo.id}&album.id=${album.id}','确认要删除该图片吗？');" class="ctrl">删除</a>
							<a href="javascript:setCover('${album.id}', '${photo.id}');" class="ctrl">设为封面</a>
						</div>
					</div>
				</#list>
			</div>
			<table style="width:100%;">
				<tr>
					<td>
						<#--
						<input type="checkbox" title="全选/反选" onclick="mlog.form.checkAll(this, 'id');" />
						<input type="button" class="btn" value=" 删除 " onclick="mlog.form.confirmSubmit('photoForm', '${base}/admin/photo/delete');" />
						-->
					</td>
					<td>
						<@mspring.pagingnavigator page=photoPage form_id="photoForm" />
					</td>
				</tr>
			</table>
		<#else>
			<div style="margin:10px;">
			暂无图片，<a href="${base}/admin/photo/upload" style="color:red;">点击这里上传>></a>
			</div>
		</#if>
	</form>
<#include "../inc/footer.ftl" />