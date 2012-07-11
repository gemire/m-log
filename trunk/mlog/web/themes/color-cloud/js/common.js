/*
//start 工具条
$(document).ready(function(){
    try{
		$('#mspring_notice').css('width',$(window).width() - $('#wgSNS').outerWidth() -40);
		$("#wgNotice li:gt(0)").css("display","none");var B=$("#wgNotice li:last");var C=$("#wgNotice li:first");setInterval(function(){if(B.is(":visible")){C.fadeIn(500).addClass("in");B.hide()}else{$("#wgNotice li:visible").addClass("in");$("#wgNotice li.in").next().fadeIn(500);$("#wgNotice li.in").hide().removeClass("in")}},5000);
	}catch(exception){
	}
});
function addFavorite(){if(document.all){try{window.external.addFavorite(window.location.href,document.title)}catch(e){alert("加入收藏失败，请使用 Ctrl+D 进行添加")}}else if(window.sidebar){window.sidebar.addPanel(document.title,window.location.href,"")}else{alert("加入收藏失败，请使用 Ctrl+D 进行添加")}}
//end 工具条
*/

/* start 滚动的云 */
var scrollSpeed = 70;
var step = 1;
var current = 0;
var imageWidth = 2247;
var headerWidth = 800;   
var restartPosition = -(imageWidth - headerWidth);
function scrollColud(){
	current -= step;
	if (current == restartPosition){
		current = 0;
	}
	$('#top').css("background-position",current+"px 0");
}
var init = setInterval("scrollColud()", scrollSpeed);
/* end 滚动的云 */


/* ctrl + enter 发表评论 */
jQuery(document).keypress(function(e){
	if(e.ctrlKey && e.which == 13 || e.which == 10) {
		jQuery("#commentform").submit();
	} else if (e.shiftKey && e.which==13 || e.which == 10) {
		jQuery("#commentform").submit();
	}
});