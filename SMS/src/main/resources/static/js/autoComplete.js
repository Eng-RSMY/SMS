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
						//alert(data);
						console.log(data);
						response(data);
					}
				});
			},
			minLength : 2
		});
	});