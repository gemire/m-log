$(function() {
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#tags" )
      // don't navigate away from the field on tab when selecting an item
      .bind( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).data( "ui-autocomplete" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function (request, response) {
            $.ajax({
                type: "POST",
                url: mlog.variable.base+"/admin/post/autocomplete?keyword=" +  extractLast( request.term ) ,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                	response( $.ui.autocomplete.filter(
                			data, extractLast( request.term ) ) );
                },
                error: function () {
                    alert("补全失败");
                }
            });
		},
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          terms.pop();
          terms.push( ui.item.value );
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
        }
      });
  });


