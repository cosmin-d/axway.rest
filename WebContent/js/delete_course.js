$(document).ready(function() {
	
	getCourses();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		console.log(this);
		var $this = $(this)
			, obj = {ID : DENUMIRE}
			, $tr = $this.closest('tr')
			, ID = $tr.find('.CL_ID').text()
			, DENUMIRE = $tr.find('.CL_DENUMIRE').text();
			

		
		deleteCourse(obj, ID);
	});
});

function deleteCourse(obj, ID) {
	
	ajaxObj = {  
			type: "DELETE",
			url: "http://localhost:7001/axway.rest/api/v2/delete/course/" + ID,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
	
				$('#delete_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				
				getCourses();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function getCourses() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:7001/axway.rest/api/v1/read/courses", 
			data: "ts="+n,
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					html_string = html_string + templategetCourses(val1);
				});
				
				$('#get_courses').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

	function templategetCourses(param) {
		return '<tr>' +
					'<td class="CL_ID">' + param.ID + '</td>' +
					'<td class="CL_DENUMIRE">' + param.DENUMIRE + '</td>' +
					'<td class="CL_DELETE_BTN"> <button class="DELETE_BTN" value=" ' + param.ID + ' " type="button">Delete</button> </td>' +
				'</tr>';
}