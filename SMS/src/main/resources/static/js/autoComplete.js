 $(function() {
		$("#student").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "/students",
					dataType : "json",
					data : {
						q : request.term
					},
					success : function(data) {
						 response($.map(data, function (value, key) {
				                return {
				                    label: value.name+" "+value.lastName,
				                    value: value.name+" "+value.lastName,
				                    abbrev:value.id
				                }
				            }));
					}
				});
			},
			minLength : 2,
			select: function (event, ui) {
				alert(JSON.stringify(ui.item.abbrev));
				$('#studentId').val(ui.item.abbrev); 
	            return true;
	        }
		});
		
	});