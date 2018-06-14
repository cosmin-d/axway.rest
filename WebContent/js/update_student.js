$(document).ready(function() {
	
	var $update_form = $('#update_form')
	, $SET_NUME = $('#SET_NUME')
	, $SET_PRENUME = $('#SET_PRENUME');
	
	getStudents();
	
	$(document.body).on('click', ':button, .UPDATE_BTN', function(e) {
		console.log(this);
		var $this = $(this)
			, $tr = $this.closest('tr')
			, ID = $tr.find('.CL_ID').text()
			, NUME = $tr.find('.CL_NUME').text()
			, PRENUME = $tr.find('.CL_PRENUME').text();

		$('#SET_ID').text(ID);
		$('#SET_NUME').val(NUME);
		$('#SET_PRENUME').val(PRENUME);
		$('#update_response').text("");
	});
	
	$update_form.submit(function(e) {
		e.preventDefault(); //cancel form submit
		console.log($SET_NUME.text());
		var obj = $update_form.serializeObject()
			, NUME = $SET_NUME.text()
			, PRENUME = $SET_PRENUME.text()
			, ID = $('#SET_ID').text();
		
		updateStudent(obj, ID);
	});
});

function updateStudent(obj, id) {
	
	ajaxObj = {  
			type: "PUT",
			url: "http://localhost:7001/axway.rest/api/v2/update/student/" + id,
			data: JSON.stringify(obj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) {
				//console.log(data);
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
			url: "http://localhost:7001/axway.rest/api/v1/read/students", 
			data: "ts="+n, 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			},
			success: function(data) { 
				//console.log(data);
				var html_string = "";
				
				$.each(data, function(index1, val1) {
					//console.log(val1);
					html_string = html_string + templategetStudents(val1);
				});
				
				$('#get_students').html("<table border='1'>" + html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
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
				'<td class="CL_UPDATE_BTN"> <button class="UPDATE_BTN" value=" ' + param.ID + ' " type="button">Update</button> </td>' +
			'</tr>';
}
