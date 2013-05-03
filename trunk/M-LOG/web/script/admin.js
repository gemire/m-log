$(document).ready(function(){
	$("table.dtable tr").each(function(){
		$(this).mouseover(function(){
			$(this).children("td").each(function(){
				$(this).css("background-color", "#F2F4E7");
			});
		});
		
		$(this).mouseout(function(){
			$(this).children("td").each(function(){
				$(this).css("background-color", "");
			});
		});
		
		// $(this).click(function(){
			// var td1 = $(this).children("td:eq(0)");
			// var chk = $(td1).children("input:checkbox");
			// if(chk){
				// if($(chk).attr('checked')){
				// $(chk).removeAttr('checked');
				// }
				// else {
					// $(chk).attr('checked', 'checked');
				// }
			// }
		// });
	});
});

function setMainFrameUrl(url){
	window.top.main.location = url; 
}

/**
 * 转换高亮tab 
 * @param {Object} id
 */
function turnHighLight(id){
	if(id){
		$(".tab a").each(function(){
			if(this.id == id){
				$(this).attr("class", "here");
			}
			else {
				$(this).removeAttr("class");
			}
		});	
	}
}