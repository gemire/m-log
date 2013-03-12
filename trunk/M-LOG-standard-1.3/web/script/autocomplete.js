$(function() {
		$("#tags").autocomplete({ 
				minLength: 1,
		        source: function (request, response) {
				            $.ajax({
				                type: "POST",
				                url: mlog.variable.base+"/admin/post/autocomplete?keyword=" + encodeURIComponent(request.term),
				                contentType: "application/json; charset=utf-8",
				                dataType: "json",
				                success: function (data) {
			            			response($.map(data, function (item) {
				                        return { label: item.name, value: item.name, id: item.id };
				                    }));
				                },
				                error: function () {
				                    alert(url);
				                }
				            });
		        		},
				select: function(e, ui) { 
		        			
						} 
			});

});

