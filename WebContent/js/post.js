$(document).ready(function() {
	
	var $post_form = $('#post_form');

	$('#submit_it2').click(function(e) {
		e.preventDefault(); //cancel form submit
		
		var jsObj = $post_example.serializeObject()
			, ajaxObj = {};
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:7001/api/v2/insert/student", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG );
				}
			},
			complete: function(XMLHttpRequest) {
				
			}, 
			dataType: "json" //request JSON
		};
		
		$.ajax(ajaxObj);
	});
});