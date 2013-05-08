jQuery(document).ready(function($) {
	var url = "http://pmeapp.duapp.com/mingyan";
    $.ajax({
      url:url, 
      dataType:"jsonp",
      success:function(data) {
      $('#mingyan').html(data.content);
    }});
});