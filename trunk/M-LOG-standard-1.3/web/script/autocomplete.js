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
				                        return { label: (item.name.toString()).substring((item.name.toString()).lastIndexOf(",")+1,(item.name.toString()).length), value: item.name, id: item.id };
				                    }));
				                },
				                error: function () {
				                    alert("补全失败");
				                }
				            });
		        		},
				select: function(e, ui) { 
		        			
						} 
			});

});

