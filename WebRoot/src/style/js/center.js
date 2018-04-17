$(document).ready(function() {
	$.ajax({
		url : 'commentListJson',
		dataType : 'json',
		data : {
			pageNum : 1
		},
		success : function(data) {
			var html = template('comments-list-script', data);
			document.getElementById('home').innerHTML = html;	
		},
		error : function() {
		}
	})
});
$('home').on('click', '.turningPage li', function() {
	var $this = $(this);
		var pageNum = $this.val();
		$.ajax({
			url : 'commentListJson',
			dataType : 'json',
			data : {
				pageNum : pageNum
			},
			success : function(data) {
				var html = template('comments-list-script', data);
				document.getElementById('home').innerHTML = html;
			},
			error : function() {
				
			}
		});
	});