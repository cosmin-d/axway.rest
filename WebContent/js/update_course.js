$(document).ready(function() {
	
	var $update_form = $('#update_form')
	, $SET_DENUMIRE = $('#SET_DENUMIRE');
	
	getStudents();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		console.log(this);
		var $this = $(this)
			, $tr = $this.closest('tr')
			, ID = $tr.find('.CL_ID').text()
			, DENUMIRE = $tr.find('.CL_DENUMIRE').text();

		$('#SET_ID').text(ID);
		$('#SET_DENUMIRE').val(DENUMIRE);
		$('#update_response').text("");
	});
	
	$update_form.submit(function(e) {
		e.preventDefault(); //cancel form submit
		var obj = $update_form.serializeObject()
			, DENUMIRE = $SET_DENUMIRE.text()
			, ID = $('#SET_ID').text();
		
		updateCourse(obj, ID);
	});
});

function updateCourse(obj, id) {
	
	ajaxObj = {  
			type: "PUT",
			url: "http://localhost:7001/axway.rest/api/v2/update/course/" + id,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				$('#update_response').text( data[0].MSG );
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
				'<td class="CL_UPDATE_BTN"> <button class="UPDATE_BTN" value=" ' + param.ID + ' " type="button">Update</button> </td>' +
			'</tr>';
}
