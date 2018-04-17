$(function() {
	$.ajax({
		type : "post",
		url : "tagCloud",
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		async : true,
		success : function(data) {
			var fontStyle = new Array();
			fontStyle[2]="success";
			fontStyle[3]="primary";
			fontStyle[4]="warning";
			fontStyle[5]="info";
			fontStyle[6]="danger";
			
			html="";
			for (var x in data) {
				for(var y in data[x]){
					html+="<a href='#'><h"+data[x][y]+" style='display:inline-block;'>" +
							"<span class='label label-"+fontStyle[data[x][y]]+"'>"+y+"</span></h"+data[x][y]+"></a>";
				}
			}
			document.getElementById("tagCloud").innerHTML = html;
		}
	});
});
