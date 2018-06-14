$(document).ready(function() {
	
	getStudents();
	
	$(document.body).on('click', ':button, .DELETE_BTN', function(e) {
		console.log(this);
		var $this = $(this)
			, obj = {ID : NUME}
			, $tr = $this.closest('tr')
			, ID = $tr.find('.CL_ID').text()
			, NUME = $tr.find('.CL_NUME').text();
			

		
		deleteStudent(obj, ID);
	});
});

function deleteStudent(obj, ID) {
	
	ajaxObj = {  
			type: "DELETE",
			url: "http://localhost:7001/axway.rest/api/v2/delete/student/" + ID,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
	
				$('#delete_response').text( data[0].MSG );
			},
			complete: function(XMLHttpRequest) {
				
				getStudents();
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

function getStudents() {
	
	var d = new Date()
		, n = d.getTime();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:7001/axway.rest/api/v1/read/students", 
			data: "ts="+n,
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					html_string = html_string + templategetStudents(val1);
				});
				
				$('#get_students').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
			}, 
			dataType: "json" //request JSON
		};
		
	return $.ajax(ajaxObj);
}

	function templategetStudents(param) {
		return '<tr>' +
					'<td class="CL_ID">' + param.ID + '</td>' +
					'<td class="CL_NUME">' + param.NUME + '</td>' +
					'<td class="CL_PRENUME">' + param.PRENUME + '</td>' +
					'<td class="CL_DELETE_BTN"> <button class="DELETE_BTN" value=" ' + param.ID + ' " type="button">Delete</button> </td>' +
				'</tr>';
}