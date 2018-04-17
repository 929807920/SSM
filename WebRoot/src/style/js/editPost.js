$(document).ready(function() {
		$.ajax({
		type : "post",
		url : "typeList",
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		async : false,
		success : function(data) {
			html = "<option value=''>--请选择--</option>"
			$.each(data, function(index, item) {
				html += "<option value=" + item.id + ">" + item.typeName
						+ "</option>";
			});
			$("#typeId").html(html);
		}
		});
	});